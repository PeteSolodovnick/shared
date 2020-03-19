package address.lots;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.references.KindLotsEntity;
import models.SuperEntity;
import models.references.SuperReferenceEntity;

public class KindLotsDialogController extends SuperDialogEntityController {
    private KindLotsEntity newKindLots;
    public KindLotsDialogController() {}
    @Override
    public void setFarmFX(FarmFX farm, SuperReferenceEntity selectedKindLot) {
        newKindLots = (KindLotsEntity) selectedKindLot;
        if (selectedKindLot != null) {
            setNew(false);
        } else {
            newKindLots = new KindLotsEntity();
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
    public void editEntity(SuperReferenceEntity newEntity) {
        for (int i = 0; i < getFarm().getReferences().getKindLotsData().size(); i++) {
            if (getFarm().getReferences().getLotsData().get(i).getRefKindLotsByKindLotsId().getId() == newEntity.getId()) {
                getFarm().getReferences().getLotsData().get(i).getRefKindLotsByKindLotsId().setName(newEntity.getName());
            }
        }
        getFarm().getConfigDialogController().getKindLotsTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getLotsOverviewController().getEntityTable().refresh();
    }
}
