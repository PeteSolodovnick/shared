package address.classifications;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import models.RefClassificationEntity;
import models.SuperEntity;

public class ClassificationDialogController extends SuperDialogEntityController {
    @FXML
    private TextField parent;
    private RefClassificationEntity classificationEntity;
    private RefClassificationEntity parentEntity;
    private String fileClassif;
    public ClassificationDialogController() {}
    public void setFarmFX(FarmFX farm, SuperEntity classification) {
        fileClassif = "/classifClassifTable.fxml";
        classificationEntity = (RefClassificationEntity) classification;
        if (classification != null) {
            if (classificationEntity.getRefClassificationByParentId() != null) {
                parent.setText(classificationEntity.getRefClassificationByParentId().getName());
                parentEntity = classificationEntity.getRefClassificationByParentId();
            }
            setNew(false);
        } else {
            classificationEntity = new RefClassificationEntity();
            setNew(true);
        }
        super.setFarmFX(farm, classificationEntity);
    }
    @Override
    protected void createEntity() {
        super.createEntity();
        for (int i = 0; i < getFarm().getReferences().getClassificationData().size(); i++) {
            if (getFarm().getReferences().getClassificationData().get(i).getId() == getParentEntity().getId());
                classificationEntity.setRefClassificationByParentId(getFarm().getReferences().getClassificationData().get(i));
        }
        setEntity(classificationEntity);
    }
    @FXML
    private void handleClassificationChoose() {
        getFarm().getConfigDialogController().setClassificationDialogController(this);
        getFarm().showEntityOverview(fileClassif);
    }
    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getClassificationData().add(classificationEntity);
        if (getFarm().getConfigDialogController().getClassificationProductTableController() != null) {
            getFarm().getConfigDialogController().getClassificationProductTableController().getEntityTable().getItems().add(classificationEntity);
        }
        getFarm().getConfigDialogController().getProductsOverviewController().getRootItem().getChildren().add(new TreeItem<>(classificationEntity));
        getFarm().getConfigDialogController().getProductsOverviewController().getEntitiesTree().add(classificationEntity);
    }

    @Override
    public void editEntity(SuperEntity entity) {
        logger.info("in edit entity");
        if (getFarm().getConfigDialogController().getClassificationProductTableController() != null) {
            getFarm().getConfigDialogController().getClassificationProductTableController().getEntityTable().refresh();
            getFarm().getConfigDialogController().getProductsOverviewController().getRootItem().getChildren().clear();
            getFarm().getConfigDialogController().getProductsOverviewController().initRoot();
        } else {
            getFarm().getConfigDialogController().getProductsOverviewController().getTreeView().getSelectionModel().getSelectedItem().setValue(entity);
        }
        for (int i = 0; i < getFarm().getReferences().getProductsData().size(); i++) {
            if (getFarm().getReferences().getProductsData().get(i).getRefClassificationByClassificationId().getId() == entity.getId()) {
                getFarm().getReferences().getProductsData().get(i).getRefClassificationByClassificationId().setName(entity.getName());
            }
        }
        getFarm().getConfigDialogController().getProductsOverviewController().getEntityTable().refresh();
    }

    public TextField getParent() {
        return parent;
    }

    public RefClassificationEntity getParentEntity() {
        return parentEntity;
    }

    public void setParentEntity(RefClassificationEntity parentEntity) {
        this.parentEntity = parentEntity;
    }
}
