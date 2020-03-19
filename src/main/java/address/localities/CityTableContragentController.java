package address.localities;

import models.references.CityEntity;

public class CityTableContragentController extends SuperCityTableController {
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getContragentDialogController().setNewCityEntity((CityEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getContragentDialogController().getCity().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }
}
