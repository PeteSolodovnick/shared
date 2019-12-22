package address.localities;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;

public class TypeCityTableController extends SuperTableEntityController {
    public TypeCityTableController() {}

    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/typeCityEditDialog.fxml");
        getEntities().addAll(farm.getReferences().getTypeCityData());
        super.setFarmFX(farm);
    }

    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getLocalityDialogController().getTypeOfLocality().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }

    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setTypeCityTableController(this);
        setTitle("New Type Of Locality");
        super.handleNewEntity();
    }

    @Override
    public  void handleEditEntity() {
        getFarm().getConfigDialogController().setTypeCityTableController(this);
        setTitle("Edit Type of Locality...");
        super.handleEditEntity();
    }

    @Override
    public void deletedFromArray(int id) {
        getFarm().getReferences().getTypeCityData().remove(id);
    }
}
