package address.prices;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.references.PriceEntity;
import models.SuperEntity;
import models.references.SuperReferenceEntity;

public class PriceDialogController extends SuperDialogEntityController {
    private PriceEntity refPriceEntity;
    public PriceDialogController() {}

    @Override
    public void setFarmFX(FarmFX farm, SuperReferenceEntity priceEntity) {
        refPriceEntity = (PriceEntity) priceEntity;
        if (refPriceEntity != null) {
            setNew(false);
        } else {
            refPriceEntity = new PriceEntity();
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
    public void editEntity(SuperReferenceEntity entity) {
        for (int i = 0; i < getFarm().getReferences().getPriceData().size(); i++) {
            if (getFarm().getReferences().getPriceData().get(i).getId() == entity.getId()) {
                getFarm().getReferences().getPriceData().get(i).setName(entity.getName());
            }
        }
        getFarm().getConfigDialogController().getSuperPriceTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
    }
}
