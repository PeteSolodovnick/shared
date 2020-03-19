package address.localities;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import models.SuperEntity;
import models.references.SuperReferenceEntity;
import models.references.TypeCityEntity;

public class TypeCityDialogController extends SuperDialogEntityController {
    private TypeCityEntity newTypeCityEntity;
    public TypeCityDialogController() {}
    @Override
    public void setFarmFX(FarmFX farm, SuperReferenceEntity selectedTypeCity) {
        newTypeCityEntity = (TypeCityEntity) selectedTypeCity;
        if (selectedTypeCity != null) {
            setNew(false);
        } else {
            newTypeCityEntity = new TypeCityEntity();
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
    public void editEntity(SuperReferenceEntity newEntity) {
        for (int i = 0; i < getFarm().getReferences().getTypeCityData().size(); i++) {
            if (getFarm().getReferences().getCitiesData().get(i).getRefTypeCityByTypeCityId().getId() == newEntity.getId()) {
                getFarm().getReferences().getCitiesData().get(i).getRefTypeCityByTypeCityId().setName(newEntity.getName());
            }
        }
        getFarm().getConfigDialogController().getTypeCityTableController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntityTable().refresh();
    }
}
