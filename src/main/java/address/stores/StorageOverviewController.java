package address.stores;

import address.mains.ControllerReference;
import address.mains.FarmFX;
import address.mains.SuperEntityController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import models.StorageEntity;
import models.SuperEntity;

public class StorageOverviewController extends SuperEntityController implements ControllerReference {
    @FXML
    private TableColumn<StorageEntity, Integer> attribute;
    public StorageOverviewController() {}
    @FXML @Override
    protected void initialize() {
        super.initialize();
        attribute.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getAttribute()));
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/storageEditDialog.fxml");
        getEntities().addAll(farm.getReferences().getStorageData());
        super.setFarmFX(farm);
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setStorageOverviewController(this);
        setTitle("New Storage...");
        super.handleNewEntity();
    }
    @Override
    public void handleEditEntity() {
        StorageEntity selectedStore = (StorageEntity) getEntityTable().getSelectionModel().getSelectedItem();
        if (selectedStore.getDeleted()) {
            getFarm().getConfigDialogController().setStorageOverviewController(this);
            setTitle("Edit Storage...");
            super.handleEditEntity();
        } else {
            Alert exAlert = new Alert(Alert.AlertType.ERROR);
            exAlert.setTitle("Error!");
            exAlert.setHeaderText("Object couldn't be edited");
            exAlert.setContentText("Object can't be edited because it is not editable");
            exAlert.showAndWait();
        }
    }
    @Override
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getStorageData().remove(selectedEntity);
    }
    @Override
    public void handleDeleteEntity() {
        StorageEntity selectedStore = (StorageEntity) getEntityTable().getSelectionModel().getSelectedItem();
        if (selectedStore.getDeleted()) {
            super.handleDeleteEntity();
        } else {
            Alert exAlert = new Alert(Alert.AlertType.ERROR);
            exAlert.setTitle("Error!");
            exAlert.setHeaderText("Object couldn't be deleted");
            exAlert.setContentText("Object can't be deleted because it is not deletable");
            exAlert.showAndWait();
        }
    }
}
