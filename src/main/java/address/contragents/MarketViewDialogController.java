package address.contragents;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.references.MarketViewEntity;
import models.SuperEntity;
import models.references.SuperReferenceEntity;

public class MarketViewDialogController extends SuperDialogEntityController {
    private MarketViewEntity newMarketViewEntity;
    public MarketViewDialogController(){}
    @Override
    public void setFarmFX(FarmFX farm, SuperReferenceEntity selectedMarketView) {
        newMarketViewEntity = (MarketViewEntity) selectedMarketView;
        if (selectedMarketView != null) {
            setNew(false);
        } else {
            newMarketViewEntity = new MarketViewEntity();
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
    public void editEntity(SuperReferenceEntity newEntity) {
        for (int i = 0; i < getFarm().getReferences().getMarketViewData().size(); i++) {
            if (getFarm().getReferences().getContragentData().get(i).getRefMarketViewByMarketViewId().getId() == newEntity.getId()) {
                getFarm().getReferences().getContragentData().get(i).getRefMarketViewByMarketViewId().setName(newEntity.getName());
            }
        }
        getFarm().getConfigDialogController().getMarketViewTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
    }
}
