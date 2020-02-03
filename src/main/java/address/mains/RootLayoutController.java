package address.mains;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RootLayoutController {
    private static final Logger logger = LogManager.getLogger();
    private final String contragentReference = "/contragent.fxml";
    private final String localityReference = "/cities.fxml";
    private final String productReference = "/classification.fxml";
    private final String lotsReference = "/lots.fxml";
    private FarmFX farm;
    private FactoryListEntities listEntities;

     public void setFarm(FarmFX farm) {
        this.farm = farm;
        listEntities = new FactoryListEntities(farm);
    }

    @FXML
    private void handleLocality() {
        farm.getReferences().getTerritoryData().clear();
        farm.getReferences().getCitiesData().clear();
        farm.getReferences().getTypeCityData().clear();
        listEntities.getListEntities(References.TYPE_CITY);
        listEntities.getListEntities(References.CITY);
        listEntities.getListEntities(References.TERRITORY);
        farm.showEntityOverview(localityReference);
    }
    @FXML
    private void handleContragent() {
        farm.getReferences().getContragentData().clear();
        farm.getReferences().getTypeContragentData().clear();
        farm.getReferences().getMarketViewData().clear();
        farm.getReferences().getPriceData().clear();
        farm.getReferences().getKindContragentData().clear();
        farm.getReferences().getCitiesData().clear();
        farm.getReferences().getTypeCityData().clear();
        farm.getReferences().getTypeCityData().clear();
        listEntities.getListEntities(References.CONTRAGENT);
        listEntities.getListEntities(References.CITY);
        listEntities.getListEntities(References.TYPE_CITY);
        listEntities.getListEntities(References.TERRITORY);
        listEntities.getListEntities(References.TYPE_CONTRAGENT);
        listEntities.getListEntities(References.MARKET_VIEW);
        listEntities.getListEntities(References.PRICE);
        listEntities.getListEntities(References.KIND_CONTRAGENT);
        farm.showEntityOverview(contragentReference);
    }
    @FXML
    private void handleProduct() {
         farm.getReferences().getProductsData().clear();
         farm.getReferences().getSizeEntitiesData().clear();
         farm.getReferences().getClassificationData().clear();
         listEntities.getListEntities(References.PRODUCT);
         listEntities.getListEntities(References.CLASSIFICATION);
         listEntities.getListEntities(References.SIZE);
         farm.showEntityOverview(productReference);
    }
    @FXML
    private void handleLots() {
         farm.getReferences().getLotsData().clear();
         farm.getReferences().getKindLotsData().clear();
         farm.getReferences().getTypeLotsData().clear();
         listEntities.getListEntities(References.KIND_LOTS);
         listEntities.getListEntities(References.TYPE_LOTS);
         listEntities.getListEntities(References.LOTS);
         farm.showEntityOverview(lotsReference);
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
