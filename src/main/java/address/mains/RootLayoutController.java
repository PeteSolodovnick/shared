package address.mains;

import models.references.RefKindDocDocEntity;
import models.references.RefTypeDocDocEntity;
import models.documents.DocInvoiceHeadDocEntity;
import models.references.RefStatusInvoiceDocEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import models.references.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class RootLayoutController {
    private static final Logger logger = LogManager.getLogger();
    private final String contragentReference = "/contragent.fxml";
    private final String localityReference = "/cities.fxml";
    private final String productReference = "/classification.fxml";
    private final String lotsReference = "/lots.fxml";
    private final String storageReference = "/storage.fxml";
    private final String invoiceDoc = "/invoice.fxml";
    private FarmFX farm;

     public void setFarm(FarmFX farm) {
        this.farm = farm;
    }

    @FXML
    private void handleLocality() {
        farm.getReferences().getTerritoryData().clear();
        farm.getReferences().getCitiesData().clear();
        farm.getReferences().getTypeCityData().clear();
        farm.getReferences().setCitiesData(new FactoryListEntities<>(new CityEntity()).getListEntities());
        farm.getReferences().setTerritoryData(new FactoryListEntities<>(new TerritoryEntity()).getListEntities());
        farm.getReferences().setTypeCityData(new FactoryListEntities<>(new TypeCityEntity()).getListEntities());
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
        farm.getReferences().setContragentData(new FactoryListEntities<>(new ContragentEntity()).getListEntities());
        farm.getReferences().setTypeContragentData(new FactoryListEntities<>(new TypeContragentEntity()).getListEntities());
        farm.getReferences().setMarketViewData(new FactoryListEntities<>(new MarketViewEntity()).getListEntities());
        farm.getReferences().setPriceData(new FactoryListEntities<>(new PriceEntity()).getListEntities());
        farm.getReferences().setKindContragentData(new FactoryListEntities<>(new KindContragentEntity()).getListEntities());
        farm.getReferences().setCitiesData(new FactoryListEntities<>(new CityEntity()).getListEntities());
        farm.getReferences().setTypeCityData(new FactoryListEntities<>(new TypeCityEntity()).getListEntities());
        farm.showEntityOverview(contragentReference);
    }
    @FXML
    private void handleProduct() {
         farm.getReferences().getProductsData().clear();
         farm.getReferences().getSizeEntitiesData().clear();
         farm.getReferences().getClassificationData().clear();
         farm.getReferences().setProductsData(new FactoryListEntities<>(new NomenklEntity()).getListEntities());
         farm.getReferences().setSizeEntitiesData(new FactoryListEntities<>(new SizeEntity()).getListEntities());
         farm.getReferences().setClassificationData(new FactoryListEntities<>(new ClassificationEntity()).getListEntities());
         farm.showEntityOverview(productReference);
    }
    @FXML
    private void handleLots() {
         farm.getReferences().getLotsData().clear();
         farm.getReferences().getKindLotsData().clear();
         farm.getReferences().getTypeLotsData().clear();
         farm.getReferences().setLotsData(new FactoryListEntities<>(new LotsEntity()).getListEntities());
         farm.getReferences().setKindLotsData(new FactoryListEntities<>(new KindLotsEntity()).getListEntities());
         farm.getReferences().setTypeLotsData(new FactoryListEntities<>(new TypeLotsEntity()).getListEntities());
         farm.showEntityOverview(lotsReference);
    }
    @FXML
    private void handleStorage() {
         farm.getReferences().getStorageData().clear();
         farm.getReferences().setStorageData(new FactoryListEntities<>(new StorageEntity()).getListEntities());
         farm.showEntityOverview(storageReference);
    }
    @FXML
    private void handleInvoices() {

         farm.getReferences().getContragentData().clear();
         farm.getReferences().getStatusInvoiceData().clear();
         farm.getReferences().getInvoiceData().clear();
         farm.getReferences().getProductsData().clear();
        farm.getReferences().getSizeEntitiesData().clear();
        farm.getReferences().getClassificationData().clear();
        farm.getReferences().getTypeContragentData().clear();
        farm.getReferences().getStorageData().clear();
        ArrayList<Long> keys = new ArrayList();
        keys.add(1L);
        keys.add(2L);
        farm.getReferences().setTypeDocDocEntities(new FactoryListEntities<>(new RefTypeDocDocEntity()).getListEntities());
        farm.getReferences().setKindDocDocEntities(new FactoryListEntities<>(new RefKindDocDocEntity()).getListEntities());
        farm.getReferences().setStorageData(new FactoryListEntities<>(new StorageEntity()).getListEntitiesbyId(keys));
        farm.getReferences().setTypeContragentData(new FactoryListEntities<>(new TypeContragentEntity()).getListEntities());
        farm.getReferences().setProductsData(new FactoryListEntities<>(new NomenklEntity()).getListEntities());
        farm.getReferences().setSizeEntitiesData(new FactoryListEntities<>(new SizeEntity()).getListEntities());
        farm.getReferences().setClassificationData(new FactoryListEntities<>(new ClassificationEntity()).getListEntities());
         farm.getReferences().setInvoiceData(new FactoryListEntities<>(new DocInvoiceHeadDocEntity()).getDateListEntities());
         farm.getReferences().setContragentData(new FactoryListEntities<>(new ContragentEntity()).getListEntities());
         farm.getReferences().setStatusInvoiceData(new FactoryListEntities<>(new RefStatusInvoiceDocEntity()).getListEntities());
         farm.showEntityOverview(invoiceDoc);
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
