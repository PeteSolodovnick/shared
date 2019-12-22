package address.localities;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.RefCityEntity;
import models.SuperEntity;

public class LocalityDialogController extends SuperDialogEntityController {
    @FXML
    private TextField region;
    @FXML
    private TextField typeOfLocality;
    private String fileTer;
    private String fileType;
    private RefCityEntity cityEntity;

    public LocalityDialogController() {}

    @Override
    public void setFarmFX(FarmFX farm, SuperEntity city) {
        fileTer = "/territory.fxml";
        fileType = "/typeOfCity.fxml";
        cityEntity = (RefCityEntity) city;
        if (city != null) {
            region.setText(cityEntity.getRefTerritoryByTerId().getName());
            typeOfLocality.setText(cityEntity.getRefTypeCityByTypeCityId().getName());
            setNew(false);
        } else {
            cityEntity = new RefCityEntity();
            setNew(true);
        }
        super.setFarmFX(farm, cityEntity);
    }
    @Override
    protected boolean isInputValid() {
        logger.info("in is inputValid");
        if (super.isInputValid()) {
            String errorMessage = "";
            if (region.getText() == null || region.getText().length() == 0) {
                errorMessage += "No valid region\n";
            }
            if (typeOfLocality.getText() == null || typeOfLocality.getText().length() == 0) {
                errorMessage += "No valid type of locality\n";
            }
            if (isError(errorMessage))
                return false;
            return true;
        }
        return false;
    }
    @Override
    protected void createEntity() {
        super.createEntity();
        RefCityEntity cityEntity = (RefCityEntity) getEntity();
        for (int i = 0; i < getFarm().getReferences().getTypeCityData().size(); i++) {
            if (getFarm().getReferences().getTypeCityData().get(i).getName().equals(getTypeOfLocality().getText()))
                cityEntity.setRefTypeCityByTypeCityId(getFarm().getReferences().getTypeCityData().get(i));
        }
        for (int i = 0; i < getFarm().getReferences().getTerritoryData().size(); i++) {
            if (getFarm().getReferences().getTerritoryData().get(i).getName().equals(getRegion().getText()))
                cityEntity.setRefTerritoryByTerId(getFarm().getReferences().getTerritoryData().get(i));
        }
        setEntity(cityEntity);
    }
    @FXML
    private void handleTerChoose() {
        getFarm().getConfigDialogController().setLocalityDialogController(this);
        getFarm().showEntityOverview(fileTer);
    }
    @FXML
    private void handleTypeChoose() {
        getFarm().getConfigDialogController().setLocalityDialogController(this);
        getFarm().showEntityOverview(fileType);
    }

    public TextField getRegion() {
        return region;
    }

    public TextField getTypeOfLocality() {
        return typeOfLocality;
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    public RefCityEntity getCityEntity() {
        return cityEntity;
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getCitiesData().add(getCityEntity());
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntities().add(getCityEntity());
    }

    @Override
    public void editEntity(SuperEntity entity) {
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntityTable().refresh();
    }
}
