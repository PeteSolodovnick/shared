package address.prices;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.RefPriceEntity;
import models.SuperEntity;

public class PriceDialogController extends SuperDialogEntityController {
    private RefPriceEntity refPriceEntity;
    public PriceDialogController() {}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity priceEntity) {
        refPriceEntity = (RefPriceEntity) priceEntity;
        if (refPriceEntity != null) {
            setNew(false);
        } else {
            refPriceEntity = new RefPriceEntity();
            setNew(true);
        }
        super.setFarmFX(farm,refPriceEntity);
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getPriceData().add(refPriceEntity);
        getFarm().getConfigDialogController().getSuperPriceTableController().getEntityTable().getItems().add(refPriceEntity);
    }

    @Override
    public void editEntity(SuperEntity entity) {
        for (int i = 0; i < getFarm().getReferences().getPriceData().size(); i++) {
            if (getFarm().getReferences().getPriceData().get(i).getId() == entity.getId()) {
                getFarm().getReferences().getPriceData().get(i).setName(entity.getName());
            }
        }
        getFarm().getConfigDialogController().getSuperPriceTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
    }
}
