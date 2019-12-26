package address.localities;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import models.RefTerritoryEntity;

public class TerritoryTableController extends SuperTableEntityController {
    public TerritoryTableController() {}

    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/territoryEditDialog.fxml");
        getEntities().addAll(farm.getReferences().getTerritoryData());
        super.setFarmFX(farm);
    }

    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getLocalityDialogController().setTerritoryEntity((RefTerritoryEntity)getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getLocalityDialogController().getRegion().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setTerritoryTableController(this);
        setTitle("New Territory...");
        super.handleNewEntity();
    }
    @Override
    public  void handleEditEntity() {
        getFarm().getConfigDialogController().setTerritoryTableController(this);
        setTitle("Edit Territory...");
        super.handleEditEntity();
    }

    @Override
    public void deletedFromArray(int id) {
        getFarm().getReferences().getTerritoryData().remove(id);
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntitiesTree().remove(id);
        getFarm().getConfigDialogController().getLocalityOverviewController().getRootItem().getChildren().remove(id);
    }
}