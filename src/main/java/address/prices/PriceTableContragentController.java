package address.prices;

public class PriceTableContragentController extends SuperPriceTableController{
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getContragentDialogController().getPrice().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }
}
