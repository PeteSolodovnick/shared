package address.lots;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import models.RefTypeLotsEntity;
import models.SuperEntity;

public class TypeLotsTableController extends SuperTableEntityController {
    public TypeLotsTableController() {}
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/lot'sTypeEditDialog.fxml");
        getEntities().addAll(farm.getReferences().getTypeLotsData());
        super.setFarmFX(farm);
    }
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getLotsDialogController().setNewTypeLot((RefTypeLotsEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getLotsDialogController().getType().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setTypeLotsTableController(this);
        setTitle("New Lot's type...");
        super.handleNewEntity();
    }
    @Override
    public  void handleEditEntity() {
        getFarm().getConfigDialogController().setTypeLotsTableController(this);
        setTitle("Edit Lot's type...");
        super.handleEditEntity();
    }

    @Override
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getTypeLotsData().remove(selectedEntity);
        getFarm().getConfigDialogController().getLotsOverviewController().getEntitiesTree().remove(selectedEntity);
        getFarm().getConfigDialogController().getLotsOverviewController().getRootItem().getChildren().clear();
        getFarm().getConfigDialogController().getLotsOverviewController().initRoot();
    }
}
