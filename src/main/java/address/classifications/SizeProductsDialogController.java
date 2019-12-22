package address.classifications;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.RefSizeEntity;
import models.SuperEntity;

public class SizeProductsDialogController extends SuperDialogEntityController {
    private RefSizeEntity sizeEntity;
    public SizeProductsDialogController(){}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity size) {
        sizeEntity = (RefSizeEntity) size;
        if (sizeEntity != null) {
            setNew(false);
        } else {
            sizeEntity = new RefSizeEntity();
            setNew(true);
        }
        super.setFarmFX(farm,sizeEntity);
    }
    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getSizeEntitiesData().add(sizeEntity);
        getFarm().getConfigDialogController().getSizeProductsTableController().getEntityTable().getItems().add(sizeEntity);
    }

    @Override
    public void editEntity(SuperEntity entity) {
        for (int i = 0; i < getFarm().getReferences().getSizeEntitiesData().size(); i++) {
            if (getFarm().getReferences().getProductsData().get(i).getRefSizeBySizeId().getId() == entity.getId()) {
                getFarm().getReferences().getProductsData().get(i).getRefSizeBySizeId().setName(entity.getName());
            }
        }
        getFarm().getConfigDialogController().getSizeProductsTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getProductsOverviewController().getEntityTable().refresh();
    }
}
