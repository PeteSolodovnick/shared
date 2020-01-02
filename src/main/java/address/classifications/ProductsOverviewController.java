package address.classifications;

import address.mains.ControllerReference;
import address.mains.FarmFX;
import address.mains.SuperEntityTreeController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import models.RefClassificationEntity;
import models.RefNomenklEntity;
import models.RefSizeEntity;
import models.SuperEntity;

public class ProductsOverviewController extends SuperEntityTreeController implements ControllerReference {
    @FXML
    private TableColumn<RefNomenklEntity, RefSizeEntity> size;
    @FXML
    private TableColumn<RefNomenklEntity, RefClassificationEntity> classification;

    public ProductsOverviewController() {}

    @FXML @Override
    protected void initialize() {
        RefClassificationEntity rootClassificationEntity = new RefClassificationEntity();
        rootClassificationEntity.setName("Classification");
        getRootItem().setValue(rootClassificationEntity);
        super.initialize();
        size.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefSizeBySizeId().getName()));
        classification.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefClassificationByClassificationId().getName()));
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/productEditDialog.fxml");
        setFileTree("/classificationEditDialog.fxml");
        getEntitiesTree().addAll(farm.getReferences().getClassificationData());
        getEntities().addAll(farm.getReferences().getProductsData());
        super.setFarmFX(farm);
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setProductsOverviewController(this);
        setTitle("New Product...");
        super.handleNewEntity();
    }

    @Override
    protected void initRoot() {
        for (SuperEntity entity : getEntitiesTree()) {
            RefClassificationEntity classification = (RefClassificationEntity) entity;
            if (classification.getRefClassificationByParentId() == null) {
                TreeItem<SuperEntity> newRoot = new TreeItem(classification);
                initializeClassification(newRoot, classification);
                getRootItem().getChildren().add(newRoot);
            }
        }
    }
    private void initializeClassification(TreeItem<SuperEntity> root, RefClassificationEntity classification) {
        if (classification != null) {
            for (SuperEntity entity : getEntitiesTree()) {
                RefClassificationEntity classif = (RefClassificationEntity) entity;
                if (classif.getRefClassificationByParentId() != null && classif.getRefClassificationByParentId().getId() == classification.getId()) {
                    TreeItem<SuperEntity> newRoot = new TreeItem(classif);
                    initializeClassification(newRoot, classif);
                    root.getChildren().add(newRoot);
                }
            }
        }
    }
    @Override
    public void handleEditEntity() {
        getFarm().getConfigDialogController().setProductsOverviewController(this);
        setTitle("Edit Product...");
        super.handleEditEntity();
    }
    @Override
    public void handleDeleteTreeEntity() {
        super.handleDeleteTreeEntity();
    }
    @Override
    public void handleNewTreeEntity() {
        getFarm().getConfigDialogController().setProductsOverviewController(this);
        setTitle("New Classification...");
        super.handleNewTreeEntity();
    }
    @Override
    public void handleEditTreeEntity(){
        getFarm().getConfigDialogController().setProductsOverviewController(this);
        setTitle("Edit Classification...");
        super.handleEditTreeEntity();
     //   int selectedId = getTreeView().getSelectionModel().getSelectedIndex();
     //   if (selectedId > 0) {
     //       RefClassificationEntity findItem = (RefClassificationEntity) getTreeView().getSelectionModel().getSelectedItem().getValue();
     //       getFarm().showEntityDialog(findItem,getReferenceStage(),getFileTree(),getTitle());
            /* RefClassificationEntity parent = (RefClassificationEntity) getTreeView().getSelectionModel().getSelectedItem().getParent().getValue();
            for (int i = 0; i < getEntitiesTree().size(); i++) {
                RefClassificationEntity classificationEntity = (RefClassificationEntity) getEntitiesTree().get(i);
                if (getEntitiesTree().get(i).getName().equals(findItem)) {
                    if (classificationEntity.getRefClassificationByParentId() == null) {
                        if (parent.equals("Classification"))
                            getFarm().showEntityDialog(getEntitiesTree().get(i), getReferenceStage(), getFileTree(), getTitle());
                    } else {
                        if (parent.equals(classificationEntity.getRefClassificationByParentId().getName()))
                            getFarm().showEntityDialog(getEntitiesTree().get(i), getReferenceStage(), getFileTree(), getTitle());
                    }
                }
            } */
      //  } else {
      //          Alert alert = new Alert(Alert.AlertType.WARNING);
      //          alert.initOwner(getFarm().getPrimaryStage());
      //          alert.setTitle("No Selection");
      //          alert.setHeaderText("No one element Selected");
      //          alert.setContentText("Please select an element in the tree for editing");
      //          alert.showAndWait();
      //  }
    }

    @Override
    public void deletedFromTreeArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getClassificationData().remove(selectedEntity);
    }

    @Override
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getProductsData().remove(selectedEntity);
    }
}
