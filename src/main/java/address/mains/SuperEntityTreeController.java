package address.mains;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import models.RefCityEntity;
import models.RefContragentEntity;
import models.RefNomenklEntity;
import models.SuperEntity;
import services.EntityService;

import java.util.Optional;

public abstract class SuperEntityTreeController extends SuperEntityController {
    @FXML
    private TreeView<SuperEntity> treeView;
    private TreeItem <SuperEntity> rootItem = new TreeItem("Entity");
    private ObservableList<SuperEntity> entitiesTree = FXCollections.observableArrayList();
    private String fileTree;

    public SuperEntityTreeController() {}

    @FXML @Override
    protected void initialize() {
        super.initialize();
        treeView.setRoot(rootItem);
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showEntities(newValue));
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        initRoot();
        super.setFarmFX(farm);
    }

    protected void initRoot() {
        for (SuperEntity entity:entitiesTree) {
            if (entity != null) {
                rootItem.getChildren().add(new TreeItem<SuperEntity>(entity));
            }
        }
    }

    private void showEntities(TreeItem<SuperEntity> entity) {
        ObservableList<SuperEntity> entities = FXCollections.observableArrayList();
        if (entity != null) {
            if (entity.getValue().equals(rootItem.getValue())) {
                getEntityTable().setItems(this.getEntities());
            } else {
                for (SuperEntity someEntity : this.getEntities()) {
                    switch (someEntity.getClass().getName()) {
                        case "models.RefCityEntity":
                            RefCityEntity cityEntity = (RefCityEntity) someEntity;
                            if (cityEntity.getRefTerritoryByTerId().getId() == entity.getValue().getId()) {
                                entities.add(cityEntity);
                            }
                            break;
                        case "models.RefContragentEntity":
                            RefContragentEntity contragentEntity = (RefContragentEntity) someEntity;
                            if (contragentEntity.getRefTypeContragentByTypeContraId().getId() == entity.getValue().getId()) {
                                entities.add(contragentEntity);
                            }
                            break;
                        case "models.RefNomenklEntity":
                            RefNomenklEntity productEntity = (RefNomenklEntity) someEntity;
                            if (productEntity.getRefClassificationByClassificationId().getId() == entity.getValue().getId()) {
                                entities.add(productEntity);
                            }
                            break;
                    }
                }
                getEntityTable().setItems(entities);
            }
        }
    }

    @FXML
    public void handleDeleteTreeEntity() {
        int selectedId = treeView.getSelectionModel().getSelectedIndex();
        if (selectedId>0) {
            SuperEntity name = treeView.getTreeItem(selectedId).getValue();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete " + rootItem);
            alert.setHeaderText(rootItem.getValue() +" "+ treeView.getTreeItem(selectedId).getValue() + " will be deleted");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EntityService service = new EntityService();
                try {
                    service.delete(name);
                    entitiesTree.remove(name);
                    rootItem.getChildren().remove(selectedId-1);
                    deletedFromTreeArray(name);
                } catch (Exception e) {
                    Alert exAlert = new Alert(Alert.AlertType.ERROR);
                    exAlert.setTitle("Error!");
                    exAlert.setHeaderText("Object couldn't be deleted");
                    exAlert.setContentText("Object can't be deleted because others objects\n\n have references to it.");
                    exAlert.showAndWait();
                }
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getFarm().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No "+rootItem.getValue()+" Selected");
            alert.setContentText("Please select a "+rootItem.getValue()+" in the table for deleting");
            alert.showAndWait();
        }
    }
    public abstract void deletedFromTreeArray(SuperEntity selectedEntity);
    @FXML
    public void handleNewTreeEntity() {
        getFarm().showEntityDialog(null,getReferenceStage(),fileTree,getTitle());
    }
    @FXML
    public void handleEditTreeEntity() {
        int selectedId = treeView.getSelectionModel().getSelectedIndex();
        if (selectedId > 0) {
            getFarm().showEntityDialog(entitiesTree.get(selectedId-1),getReferenceStage(),fileTree,getTitle());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getFarm().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No one element Selected");
            alert.setContentText("Please select an element in the tree for editing");
            alert.showAndWait();
        }
    }

    public TreeView<SuperEntity> getTreeView() {
        return treeView;
    }

    public TreeItem<SuperEntity> getRootItem() {
        return rootItem;
    }

    public ObservableList<SuperEntity> getEntitiesTree() {
        return entitiesTree;
    }

    public void setFileTree(String fileTree) {
        this.fileTree = fileTree;
    }

    public String getFileTree() {
        return fileTree;
    }
}
