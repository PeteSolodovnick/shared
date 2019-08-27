package address;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import models.RefCityEntity;
import models.RefTerritoryEntity;
import models.RefTypeCityEntity;
import models.SuperEntity;
import services.EntityService;

public class RootLayoutController {
    private FarmFX farm;

    public void setFarm(FarmFX farm) {
        this.farm = farm;
    }
    @FXML
    private void handleLocality() {
        farm.getTerritoryData().clear();
        farm.getCitiesData().clear();
        farm.getTypeCityData().clear();
        EntityService service = new EntityService();
        for (SuperEntity ter:service.getAllRows(new RefTerritoryEntity())) {
            RefTerritoryEntity terr = (RefTerritoryEntity) ter;
            farm.getTerritoryData().add(terr);
        }
        for (SuperEntity city:service.getAllRows(new RefCityEntity())) {
            RefCityEntity cities = (RefCityEntity) city;
            farm.getCitiesData().add(cities);
        }
        for (SuperEntity typeCity:service.getAllRows(new RefTypeCityEntity())) {
            RefTypeCityEntity type = (RefTypeCityEntity) typeCity;
            farm.getTypeCityData().add(type);
        }
        farm.showTerritoryOverview();
    }
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Pete Solodovnick\n Manager of ARM-Trend");
        alert.showAndWait();
    }
}
