package address.stores;

import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import models.references.StorageEntity;
import models.references.SuperReferenceEntity;

public class StorageTableViewController extends SuperTableEntityController {
    @FXML
    private TableColumn<StorageEntity, Integer> attribute;

    public StorageTableViewController() {}

    @FXML @Override
    protected void initialize() {
        super.initialize();
        attribute.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getAttribute()));
    }

    @Override
    public void setFarmFX(FarmFX farm) {
        initArray(farm);
        getEntities().addAll(farm.getReferences().getStorageData());
        super.setFarmFX(farm);
    }
    private void initArray(FarmFX farm){
        farm.getReferences().setStorageData(new FactoryListEntities<>(new StorageEntity()).getListEntities());
    }

    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getMovieDialogController().setStore((StorageEntity) getEntityTable().getSelectionModel().getSelectedItem());
        if (getFarm().getConfigDialogController().getMovieDialogController().isIn()) {
            getFarm().getConfigDialogController().getMovieDialogController().getStoreIn().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
            getFarm().getConfigDialogController().getMovieDialogController().setStoreInSelect((StorageEntity) getEntityTable().getSelectionModel().getSelectedItem());
        } else {
            getFarm().getConfigDialogController().getMovieDialogController().getStoreOut().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
            getFarm().getConfigDialogController().getMovieDialogController().setStoreOutSelect((StorageEntity) getEntityTable().getSelectionModel().getSelectedItem());
        }
    }
    @FXML
    public void handleOk() {
        setTextEdit();
        getReferenceStage().close();
    }

    @Override
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {
    }
}
