package address.contragents;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.RefKindContragentEntity;
import models.SuperEntity;

public class KindContragentDialogController extends SuperDialogEntityController {
    private RefKindContragentEntity kindContragent;
    public KindContragentDialogController(){}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity kindContragent) {
        this.kindContragent = (RefKindContragentEntity) kindContragent;
        if (this.kindContragent != null) {
            setNew(false);
        } else {
            this.kindContragent = new RefKindContragentEntity();
            setNew(true);
        }
        super.setFarmFX(farm,this.kindContragent);
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getKindContragentData().add(kindContragent);
        getFarm().getConfigDialogController().getKindContragentTableController().getEntityTable().getItems().add(kindContragent);
    }

    @Override
    public void editEntity(SuperEntity entity) {
        for (int i = 0; i < getFarm().getReferences().getKindContragentData().size(); i++) {
            if (getFarm().getReferences().getContragentData().get(i).getRefKindContragentByKindContraId().getId() == entity.getId()) {
                getFarm().getReferences().getContragentData().get(i).getRefKindContragentByKindContraId().setName(entity.getName());
            }
        }
        getFarm().getConfigDialogController().getKindContragentTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
    }
}
