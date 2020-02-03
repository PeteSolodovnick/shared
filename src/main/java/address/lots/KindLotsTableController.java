package address.lots;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import models.RefKindLotsEntity;
import models.SuperEntity;

public class KindLotsTableController extends SuperTableEntityController {
    public KindLotsTableController() {}

    @Override
    public void setFarmFX(FarmFX farm) {
        logger.info("in set kind lots table");
        setFile("/lot'sKindEditDialog.fxml");
        getEntities().addAll(farm.getReferences().getKindLotsData());
        super.setFarmFX(farm);
    }

    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getLotsDialogController().setNewKindLot((RefKindLotsEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getLotsDialogController().getKind().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }

    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setKindLotsTableController(this);
        setTitle("New Kind Of Lot");
        super.handleNewEntity();
    }

    @Override
    public  void handleEditEntity() {
        getFarm().getConfigDialogController().setKindLotsTableController(this);
        setTitle("Edit Kind of Lot...");
        super.handleEditEntity();
    }

    @Override
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getKindLotsData().remove(selectedEntity);
    }
}
