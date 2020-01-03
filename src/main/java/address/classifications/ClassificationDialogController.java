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
    private RefClassificationEntity newClassificationEntity;
    private RefClassificationEntity newParentEntity;
    private String fileClassif;
    public ClassificationDialogController() {}
    public void setFarmFX(FarmFX farm, SuperEntity selectedClassification) {
        fileClassif = "/classifClassifTable.fxml";
        newClassificationEntity = (RefClassificationEntity) selectedClassification;
        if (selectedClassification != null) {
            if (newClassificationEntity.getRefClassificationByParentId() != null) {
                parent.setText(newClassificationEntity.getRefClassificationByParentId().getName());
                newParentEntity = newClassificationEntity.getRefClassificationByParentId();
            }
            setNew(false);
        } else {
            newClassificationEntity = new RefClassificationEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newClassificationEntity);
    }
    @Override
    protected void createEntity() {
        super.createEntity();
        newClassificationEntity.setRefClassificationByParentId(getNewParentEntity());
        setNewEntity(newClassificationEntity);
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
        getFarm().getReferences().getClassificationData().add(newClassificationEntity);
        if (getFarm().getConfigDialogController().getClassificationProductTableController() != null) {
            getFarm().getConfigDialogController().getClassificationProductTableController().getEntityTable().getItems().add(newClassificationEntity);

        }
        getFarm().getConfigDialogController().getProductsOverviewController().getEntitiesTree().add(newClassificationEntity);
        getFarm().getConfigDialogController().getProductsOverviewController().getRootItem().getChildren().clear();
        getFarm().getConfigDialogController().getProductsOverviewController().initRoot();
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        if (getFarm().getConfigDialogController().getClassificationProductTableController() != null) {
            getFarm().getConfigDialogController().getClassificationProductTableController().getEntityTable().refresh();
            getFarm().getConfigDialogController().getProductsOverviewController().getRootItem().getChildren().clear();
            getFarm().getConfigDialogController().getProductsOverviewController().initRoot();
        } else {
            getFarm().getConfigDialogController().getProductsOverviewController().getTreeView().getSelectionModel().getSelectedItem().setValue(newEntity);
            getFarm().getConfigDialogController().getProductsOverviewController().getRootItem().getChildren().clear();
            getFarm().getConfigDialogController().getProductsOverviewController().initRoot();
        }
        for (int i = 0; i < getFarm().getReferences().getProductsData().size(); i++) {
            if (getFarm().getReferences().getProductsData().get(i).getRefClassificationByClassificationId().getId() == newEntity.getId()) {
                getFarm().getReferences().getProductsData().get(i).getRefClassificationByClassificationId().setName(newEntity.getName());
            }
        }
        getFarm().getConfigDialogController().getProductsOverviewController().getEntityTable().refresh();
    }

    public TextField getParent() {
        return parent;
    }

    public RefClassificationEntity getNewParentEntity() {
        return newParentEntity;
    }

    public void setNewParentEntity(RefClassificationEntity newParentEntity) {
        this.newParentEntity = newParentEntity;
    }
}
