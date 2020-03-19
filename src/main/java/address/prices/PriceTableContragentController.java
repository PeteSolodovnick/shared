package address.prices;

import models.references.PriceEntity;

public class PriceTableContragentController extends SuperPriceTableController{
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getContragentDialogController().setNewPriceEntity((PriceEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getContragentDialogController().getPrice().setText(((PriceEntity) getEntityTable().getSelectionModel().getSelectedItem()).getName());
    }
}
