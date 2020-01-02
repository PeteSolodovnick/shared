package address.localities;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.RefTypeCityEntity;
import models.SuperEntity;

public class TypeCityDialogController extends SuperDialogEntityController {
    private RefTypeCityEntity newTypeCityEntity;
    public TypeCityDialogController() {}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity selectedTypeCity) {
        newTypeCityEntity = (RefTypeCityEntity) selectedTypeCity;
        if (selectedTypeCity != null) {
            setNew(false);
        } else {
            newTypeCityEntity = new RefTypeCityEntity();
            setNew(true);
        }
        super.setFarmFX(farm,newTypeCityEntity);
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getTypeCityData().add(newTypeCityEntity);
        getFarm().getConfigDialogController().getTypeCityTableController().getEntityTable().getItems().add(newTypeCityEntity);
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        for (int i = 0; i < getFarm().getReferences().getTypeCityData().size(); i++) {
            if (getFarm().getReferences().getCitiesData().get(i).getRefTypeCityByTypeCityId().getId() == newEntity.getId()) {
                getFarm().getReferences().getCitiesData().get(i).getRefTypeCityByTypeCityId().setName(newEntity.getName());
            }
        }
        getFarm().getConfigDialogController().getTypeCityTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntityTable().refresh();
    }
}
