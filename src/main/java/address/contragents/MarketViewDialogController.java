package address.contragents;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.RefMarketViewEntity;
import models.SuperEntity;

public class MarketViewDialogController extends SuperDialogEntityController {
    private RefMarketViewEntity newMarketViewEntity;
    public MarketViewDialogController(){}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity selectedMarketView) {
        newMarketViewEntity = (RefMarketViewEntity) selectedMarketView;
        if (selectedMarketView != null) {
            setNew(false);
        } else {
            newMarketViewEntity = new RefMarketViewEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newMarketViewEntity);
    }
    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getMarketViewData().add(newMarketViewEntity);
        getFarm().getConfigDialogController().getMarketViewTableController().getEntityTable().getItems().add(newMarketViewEntity);
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        for (int i = 0; i < getFarm().getReferences().getMarketViewData().size(); i++) {
            if (getFarm().getReferences().getContragentData().get(i).getRefMarketViewByMarketViewId().getId() == newEntity.getId()) {
                getFarm().getReferences().getContragentData().get(i).getRefMarketViewByMarketViewId().setName(newEntity.getName());
            }
        }
        getFarm().getConfigDialogController().getMarketViewTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
    }
}
