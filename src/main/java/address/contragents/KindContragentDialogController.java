package address.contragents;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.RefKindContragentEntity;
import models.SuperEntity;

public class KindContragentDialogController extends SuperDialogEntityController {
    private RefKindContragentEntity newKindContragent;
    public KindContragentDialogController(){}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity selectedKindContragent) {
        newKindContragent = (RefKindContragentEntity) selectedKindContragent;
        if (selectedKindContragent != null) {
            setNew(false);
        } else {
            newKindContragent = new RefKindContragentEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newKindContragent);
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getKindContragentData().add(newKindContragent);
        getFarm().getConfigDialogController().getKindContragentTableController().getEntityTable().getItems().add(newKindContragent);
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        for (int i = 0; i < getFarm().getReferences().getKindContragentData().size(); i++) {
            if (getFarm().getReferences().getContragentData().get(i).getRefKindContragentByKindContraId().getId() == newEntity.getId()) {
                getFarm().getReferences().getContragentData().get(i).getRefKindContragentByKindContraId().setName(newEntity.getName());
            }
        }
        getFarm().getConfigDialogController().getKindContragentTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
    }
}
