package address;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import models.*;
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
    private void handleContragent() {
        farm.getContragentData().clear();
        farm.getCitiesData().clear();
        farm.getPriceData().clear();
        farm.getTypeContragentData().clear();
        farm.getMarketViewData().clear();
        EntityService service = new EntityService();
        for (SuperEntity contragent:service.getAllRows(new RefContragentEntity())) {
            RefContragentEntity contra = (RefContragentEntity) contragent;
            farm.getContragentData().add(contra);
        }
        for (SuperEntity city:service.getAllRows(new RefCityEntity())) {
            RefCityEntity cities = (RefCityEntity) city;
            farm.getCitiesData().add(cities);
        }
        for (SuperEntity pr:service.getAllRows(new RefPriceEntity())) {
            RefPriceEntity priceEntity = (RefPriceEntity) pr;
            farm.getPriceData().add(priceEntity);
        }
        for (SuperEntity mv:service.getAllRows(new RefMarketViewEntity())) {
            RefMarketViewEntity marketEntity = (RefMarketViewEntity) mv;
            farm.getMarketViewData().add(marketEntity);
        }
        for (SuperEntity typeContra:service.getAllRows(new RefTypeContragentEntity())) {
            RefTypeContragentEntity typeEntity = (RefTypeContragentEntity) typeContra;
            farm.getTypeContragentData().add(typeEntity);
        }
        farm.showContragentOverview();
    }
    @FXML
    private void handleClassification() {
        farm.getSizeEntitiesData().clear();
        farm.getClassificationData().clear();
        farm.getProductsData().clear();
        EntityService service = new EntityService();
        for (SuperEntity size:service.getAllRows(new RefSizeEntity())) {
            RefSizeEntity s = (RefSizeEntity) size;
            farm.getSizeEntitiesData().add(s);
        }
        for (SuperEntity cl:service.getAllRows(new RefClassificationEntity())) {
            RefClassificationEntity classification = (RefClassificationEntity) cl;
            farm.getClassificationData().add(classification);
        }
        for (SuperEntity nom:service.getAllRows(new RefNomenklEntity())) {
            RefNomenklEntity product = (RefNomenklEntity) nom;
            farm.getProductsData().add(product);
        }
        farm.showClassificationOverview();
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
