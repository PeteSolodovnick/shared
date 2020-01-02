package address.contragents;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import models.RefMarketViewEntity;
import models.RefTypeCityEntity;
import models.SuperEntity;
import net.bytebuddy.implementation.bind.annotation.Super;

public class MarketViewTableController extends SuperTableEntityController {
    public MarketViewTableController() {
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/marketViewEditDialog.fxml");
        getEntities().addAll(farm.getReferences().getMarketViewData());
        super.setFarmFX(farm);
    }
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getContragentDialogController().setMarketViewEntity((RefMarketViewEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getContragentDialogController().getMarketView().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }

    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setMarketViewTableController(this);
        setTitle("New Market View...");
        super.handleNewEntity();
    }

    @Override
    public  void handleEditEntity() {
        getFarm().getConfigDialogController().setMarketViewTableController(this);
        setTitle("Edit MarketView...");
        super.handleEditEntity();
    }

    @Override
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getMarketViewData().remove(selectedEntity);
    }
}
