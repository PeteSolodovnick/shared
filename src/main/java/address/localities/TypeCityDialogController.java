package address.localities;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.RefTypeCityEntity;
import models.SuperEntity;

public class TypeCityDialogController extends SuperDialogEntityController {
    private RefTypeCityEntity typeCityEntity;
    public TypeCityDialogController() {}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity typeCity) {
        typeCityEntity = (RefTypeCityEntity) typeCity;
        if (typeCityEntity != null) {
            setNew(false);
        } else {
            typeCityEntity = new RefTypeCityEntity();
            setNew(true);
        }
        super.setFarmFX(farm,typeCityEntity);
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getTypeCityData().add(typeCityEntity);
        getFarm().getConfigDialogController().getTypeCityTableController().getEntityTable().getItems().add(typeCityEntity);
    }

    @Override
    public void editEntity(SuperEntity entity) {
        for (int i = 0; i < getFarm().getReferences().getTypeCityData().size(); i++) {
            if (getFarm().getReferences().getCitiesData().get(i).getRefTypeCityByTypeCityId().getId() == entity.getId()) {
                getFarm().getReferences().getCitiesData().get(i).getRefTypeCityByTypeCityId().setName(entity.getName());
            }
        }
        getFarm().getConfigDialogController().getTypeCityTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntityTable().refresh();
    }
}
