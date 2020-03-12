package address.localities;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.*;

public class LocalityDialogController extends SuperDialogEntityController {
    @FXML
    private TextField region;
    @FXML
    private TextField typeOfLocality;
    private String fileTer;
    private String fileType;
    private CityEntity newCityEntity;
    private TerritoryEntity newTerritoryEntity;
    private TypeCityEntity newTypeCityEntity;

    public LocalityDialogController() {}

    @Override
    public void setFarmFX(FarmFX farm, SuperEntity selectedCity) {
        fileTer = "/territory.fxml";
        fileType = "/typeOfCity.fxml";
        newCityEntity = (CityEntity) selectedCity;
        if (selectedCity != null) {
            region.setText(newCityEntity.getRefTerritoryByTerId().getName());
            typeOfLocality.setText(newCityEntity.getRefTypeCityByTypeCityId().getName());
            newTypeCityEntity = newCityEntity.getRefTypeCityByTypeCityId();
            newTerritoryEntity = newCityEntity.getRefTerritoryByTerId();
            setNew(false);
        } else {
            newCityEntity = new CityEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newCityEntity);
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
        newCityEntity.setRefTypeCityByTypeCityId(getNewTypeCityEntity());
        newCityEntity.setRefTerritoryByTerId(getNewTerritoryEntity());
        setNewEntity(newCityEntity);
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

    public CityEntity getNewCityEntity() {
        return newCityEntity;
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getCitiesData().add(getNewCityEntity());
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntities().add(getNewCityEntity());
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntitiesName().setItems(getFarm().getConfigDialogController().getLocalityOverviewController().getEntities());
    }

    public TerritoryEntity getNewTerritoryEntity() {
        return newTerritoryEntity;
    }

    public void setNewTerritoryEntity(TerritoryEntity newTerritoryEntity) {
        this.newTerritoryEntity = newTerritoryEntity;
    }

    public TypeCityEntity getNewTypeCityEntity() {
        return newTypeCityEntity;
    }

    public void setNewTypeCityEntity(TypeCityEntity newTypeCityEntity) {
        this.newTypeCityEntity = newTypeCityEntity;
    }
}
