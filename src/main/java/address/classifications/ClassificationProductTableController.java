package address.classifications;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import models.RefCityEntity;
import models.RefClassificationEntity;
import models.SuperEntity;

public class ClassificationProductTableController extends SuperTableEntityController {
    @FXML
    private TableColumn<RefClassificationEntity, RefClassificationEntity> parent;
    public ClassificationProductTableController() {}
    @FXML @Override
    protected void initialize() {
        super.initialize();
        parent.setCellValueFactory(cellData -> {
            if (cellData.getValue().getRefClassificationByParentId() == null) {
                return new SimpleObjectProperty();
            }
            return new SimpleObjectProperty(cellData.getValue().getRefClassificationByParentId().getName());
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
        getFarm().getConfigDialogController().getProductsDialogController().setNewParentEntity((RefClassificationEntity) getEntityTable().getSelectionModel().getSelectedItem());
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
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getClassificationData().remove(selectedEntity);
        getFarm().getConfigDialogController().getProductsOverviewController().getEntitiesTree().remove(selectedEntity);
        getFarm().getConfigDialogController().getProductsOverviewController().getRootItem().getChildren().clear();
        getFarm().getConfigDialogController().getProductsOverviewController().initRoot();
    }
}
