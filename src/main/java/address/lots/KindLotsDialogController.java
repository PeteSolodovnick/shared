package address.lots;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.RefKindLotsEntity;
import models.SuperEntity;

public class KindLotsDialogController extends SuperDialogEntityController {
    private RefKindLotsEntity newKindLots;
    public KindLotsDialogController() {}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity selectedKindLot) {
        newKindLots = (RefKindLotsEntity) selectedKindLot;
        if (selectedKindLot != null) {
            setNew(false);
        } else {
            newKindLots = new RefKindLotsEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newKindLots);
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getKindLotsData().add(newKindLots);
        getFarm().getConfigDialogController().getKindLotsTableController().getEntityTable().getItems().add(newKindLots);
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        for (int i = 0; i < getFarm().getReferences().getKindLotsData().size(); i++) {
            if (getFarm().getReferences().getLotsData().get(i).getRefKindLotsByKindLotsId().getId() == newEntity.getId()) {
                getFarm().getReferences().getLotsData().get(i).getRefKindLotsByKindLotsId().setName(newEntity.getName());
            }
        }
        getFarm().getConfigDialogController().getKindLotsTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getLotsOverviewController().getEntityTable().refresh();
    }
}
