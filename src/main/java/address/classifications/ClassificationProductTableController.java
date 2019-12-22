package address.classifications;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import models.RefClassificationEntity;

public class ClassificationProductTableController extends SuperTableEntityController {
    @FXML
    private TableColumn<RefClassificationEntity, String> parent;
    public ClassificationProductTableController() {}
    @FXML @Override
    protected void initialize() {
        super.initialize();
        logger.info("in initialize Classif table controller");
        parent.setCellValueFactory(cellData -> {
            if (cellData.getValue().getRefClassificationByParentId() == null) {
                logger.info("before return parent null");
                return new SimpleStringProperty();
            }
            logger.info("return parent not null");
            return new SimpleStringProperty(cellData.getValue().getRefClassificationByParentId().getName());
        });
    }

    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/classificationEditDialog.fxml");
        getEntities().addAll(farm.getReferences().getClassificationData());
        super.setFarmFX(farm);
    }

    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getProductsDialogController().getClassification().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setClassificationProductTableController(this);
        setTitle("New Classification...");
        super.handleNewEntity();
    }
    @Override
    public  void handleEditEntity() {
        getFarm().getConfigDialogController().setClassificationProductTableController(this);
        setTitle("Edit Classification...");
        super.handleEditEntity();
    }

    @Override
    public void deletedFromArray(int id) {
        getFarm().getReferences().getClassificationData().remove(id);
        getFarm().getConfigDialogController().getProductsOverviewController().getEntitiesTree().remove(id);
        getFarm().getConfigDialogController().getProductsOverviewController().getRootItem().getChildren().remove(id);
    }
}
