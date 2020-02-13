package address.classifications;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.SizeEntity;
import models.SuperEntity;

public class SizeProductsDialogController extends SuperDialogEntityController {
    private SizeEntity newSizeEntity;
    public SizeProductsDialogController(){}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity selectedSize) {
        newSizeEntity = (SizeEntity) selectedSize;
        if (selectedSize != null) {
            setNew(false);
        } else {
            newSizeEntity = new SizeEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newSizeEntity);
    }
    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getSizeEntitiesData().add(newSizeEntity);
        getFarm().getConfigDialogController().getSizeProductsTableController().getEntityTable().getItems().add(newSizeEntity);
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        for (int i = 0; i < getFarm().getReferences().getSizeEntitiesData().size(); i++) {
            if (getFarm().getReferences().getProductsData().get(i).getRefSizeBySizeId().getId() == newEntity.getId()) {
                getFarm().getReferences().getProductsData().get(i).getRefSizeBySizeId().setName(newEntity.getName());
            }
        }
        getFarm().getConfigDialogController().getSizeProductsTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getProductsOverviewController().getEntityTable().refresh();
    }
}
