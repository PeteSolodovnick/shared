package address.contragents;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.RefMarketViewEntity;
import models.SuperEntity;

public class MarketViewDialogController extends SuperDialogEntityController {
    private RefMarketViewEntity marketViewEntity;
    public MarketViewDialogController(){}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity marketView) {
        marketViewEntity = (RefMarketViewEntity) marketView;
        if (marketViewEntity != null) {
            setNew(false);
        } else {
            marketViewEntity = new RefMarketViewEntity();
            setNew(true);
        }
        super.setFarmFX(farm,marketViewEntity);
    }
    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getMarketViewData().add(marketViewEntity);
        getFarm().getConfigDialogController().getMarketViewTableController().getEntityTable().getItems().add(marketViewEntity);
    }

    @Override
    public void editEntity(SuperEntity entity) {
        for (int i = 0; i < getFarm().getReferences().getMarketViewData().size(); i++) {
            if (getFarm().getReferences().getContragentData().get(i).getRefMarketViewByMarketViewId().getId() == entity.getId()) {
                getFarm().getReferences().getContragentData().get(i).getRefMarketViewByMarketViewId().setName(entity.getName());
            }
        }
        getFarm().getConfigDialogController().getMarketViewTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
    }
}
