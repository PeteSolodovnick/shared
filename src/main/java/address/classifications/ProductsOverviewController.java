package address.classifications;

import address.mains.ControllerReference;
import address.mains.FarmFX;
import address.mains.SuperEntityTreeController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import models.*;

public class ProductsOverviewController extends SuperEntityTreeController implements ControllerReference {
    @FXML
    private TableColumn<NomenklEntity, SizeEntity> size;
    @FXML
    private TableColumn<NomenklEntity, ClassificationEntity> classification;

    public ProductsOverviewController() {}

    @FXML @Override
    protected void initialize() {
        ClassificationEntity rootClassificationEntity = new ClassificationEntity();
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
            ClassificationEntity classification = (ClassificationEntity) entity;
            if (classification.getRefClassificationByParentId() == null) {
                TreeItem<SuperEntity> newRoot = new TreeItem(classification);
                initializeClassification(newRoot, classification);
                getRootItem().getChildren().add(newRoot);
                getRootItem().setExpanded(true);
            }
        }
    }
    private void initializeClassification(TreeItem<SuperEntity> root, ClassificationEntity classification) {
        if (classification != null) {
            for (SuperEntity entity : getEntitiesTree()) {
                ClassificationEntity classif = (ClassificationEntity) entity;
                if (classif.getRefClassificationByParentId() != null && classif.getRefClassificationByParentId().getId() == classification.getId()) {
                    TreeItem<SuperEntity> newRoot = new TreeItem(classif);
                    initializeClassification(newRoot, classif);
                    root.getChildren().add(newRoot);
                    root.setExpanded(true);
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
