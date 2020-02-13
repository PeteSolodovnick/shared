package address.stores;

import address.mains.ControllerReference;
import address.mains.FarmFX;
import address.mains.SuperEntityController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
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
        getFarm().getConfigDialogController().setStorageOverviewController(this);
        setTitle("Edit Storage...");
        super.handleEditEntity();
    }
    @Override
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getStorageData().remove(selectedEntity); }
}
