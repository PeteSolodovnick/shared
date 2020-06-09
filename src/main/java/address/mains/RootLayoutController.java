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
    private final String storageReference = "/storage.fxml";
    private final String invoiceDoc = "/invoice.fxml";
    private final String movingDoc = "/moving.fxml";
    private final String careLot = "/chickenCare.fxml";
    private FarmFX farm;

     public void setFarm(FarmFX farm) {
        this.farm = farm;
    }

    @FXML
    private void handleLocality() {
        farm.showEntityOverview(localityReference);
    }
    @FXML
    private void handleContragent() {
        farm.showEntityOverview(contragentReference);
    }
    @FXML
    private void handleProduct() {
         farm.showEntityOverview(productReference);
    }
    @FXML
    private void handleLots() {
         farm.showEntityOverview(lotsReference);
    }
    @FXML
    private void handleStorage() {
         farm.showEntityOverview(storageReference);
    }
    @FXML
    private void handleInvoices() {
         farm.showEntityOverview(invoiceDoc);
    }
    @FXML
    private void handleMoving() {
         farm.showEntityOverview(movingDoc);
    }
    @FXML
    private void handleCare() {
         farm.showEntityOverview(careLot);
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
