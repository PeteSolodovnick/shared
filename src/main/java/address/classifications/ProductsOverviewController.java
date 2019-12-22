package address.classifications;

import address.mains.ControllerReference;
import address.mains.FarmFX;
import address.mains.SuperEntityTreeController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import models.RefClassificationEntity;
import models.RefNomenklEntity;
import models.SuperEntity;

public class ProductsOverviewController extends SuperEntityTreeController implements ControllerReference {
    @FXML
    private TableColumn<RefNomenklEntity, String> size;
    @FXML
    private TableColumn<RefNomenklEntity, String> classification;

    public ProductsOverviewController() {}

    @FXML @Override
    protected void initialize() {
        getRootItem().setValue("Classification");
        super.initialize();
        size.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRefSizeBySizeId().getName()));
        classification.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRefClassificationByClassificationId().getName()));
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
        logger.info("in init root products");
        for (SuperEntity entity : getEntitiesTree()) {
            RefClassificationEntity classification = (RefClassificationEntity) entity;
            if (classification.getRefClassificationByParentId() == null) {
                TreeItem<String> newRoot = new TreeItem(classification.getName());
                initializeClassification(newRoot, classification);
                getRootItem().getChildren().add(newRoot);
            }
        }
    }
    private void initializeClassification(TreeItem<String> root, RefClassificationEntity classification) {
        if (classification != null) {
            for (SuperEntity entity : getEntitiesTree()) {
                RefClassificationEntity classif = (RefClassificationEntity) entity;
                if (classif.getRefClassificationByParentId() != null && classif.getRefClassificationByParentId().getId() == classification.getId()) {
                    TreeItem<String> newRoot = new TreeItem(classif.getName());
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
        int selectedId = getTreeView().getSelectionModel().getSelectedIndex();
        if (selectedId > 0) {
            String findItem = getTreeView().getSelectionModel().getSelectedItem().getValue();
            String parent = getTreeView().getSelectionModel().getSelectedItem().getParent().getValue();
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
            }
        } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(getFarm().getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No one element Selected");
                alert.setContentText("Please select an element in the tree for editing");
                alert.showAndWait();
        }
    }

    @Override
    public void deletedFromTreeArray(int id) {
        getFarm().getReferences().getClassificationData().remove(id);
    }

    @Override
    public void deletedFromArray(int id) {
        getFarm().getReferences().getProductsData().remove(id);
    }
}
