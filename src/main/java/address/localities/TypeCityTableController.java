package address.localities;

import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import models.SuperEntity;
import models.references.CityEntity;
import models.references.SuperReferenceEntity;
import models.references.TypeCityEntity;

public class TypeCityTableController extends SuperTableEntityController {
    public TypeCityTableController() {}

    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/typeCityEditDialog.fxml");
        initArrays(farm);
        getEntities().addAll(farm.getReferences().getTypeCityData());
        super.setFarmFX(farm);
    }
    private void initArrays(FarmFX farm) {
        if (farm.getReferences().getTypeCityData().size()==0)
            farm.getReferences().setTypeCityData(new FactoryListEntities<>(new TypeCityEntity()).getListEntities());
    }
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getLocalityDialogController().setNewTypeCityEntity((TypeCityEntity) getEntityTable().getSelectionModel().getSelectedItem());
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
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getTypeCityData().remove(selectedEntity);
    }
}
