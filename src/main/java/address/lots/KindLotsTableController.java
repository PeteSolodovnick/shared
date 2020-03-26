package address.lots;

import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import models.references.KindLotsEntity;
import models.SuperEntity;
import models.references.SuperReferenceEntity;

public class KindLotsTableController extends SuperTableEntityController {
    public KindLotsTableController() {}

    @Override
    public void setFarmFX(FarmFX farm) {
        logger.info("in set kind lots table");
        setFile("/lot'sKindEditDialog.fxml");
        initArray(farm);
        getEntities().addAll(farm.getReferences().getKindLotsData());
        super.setFarmFX(farm);
    }
    private void initArray(FarmFX farm){
        farm.getReferences().setKindLotsData(new FactoryListEntities<>(new KindLotsEntity()).getListEntities());
    }
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getLotsDialogController().setNewKindLot((KindLotsEntity) getEntityTable().getSelectionModel().getSelectedItem());
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
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getKindLotsData().remove(selectedEntity);
    }
}
