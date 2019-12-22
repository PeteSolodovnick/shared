package address.localities;

public class CityTableContragentController extends SuperCityTableController {
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getContragentDialogController().getCity().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }
}
