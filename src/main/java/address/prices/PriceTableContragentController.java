package address.prices;

import models.RefPriceEntity;

public class PriceTableContragentController extends SuperPriceTableController{
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getContragentDialogController().setPriceEntity((RefPriceEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getContragentDialogController().getPrice().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }
}
