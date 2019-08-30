package address;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.*;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class FarmFX extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<RefTerritoryEntity> territoryData = FXCollections.observableArrayList();
    private ObservableList<RefCityEntity> citiesData = FXCollections.observableArrayList();
    private ObservableList<RefTypeCityEntity> typeCityData = FXCollections.observableArrayList();
    private ObservableList<RefContragentEntity> contragentData = FXCollections.observableArrayList();
    private ObservableList<RefTypeContragentEntity> typeContragentData = FXCollections.observableArrayList();
    private ObservableList<RefPriceEntity> priceData = FXCollections.observableArrayList();
    private ObservableList<RefMarketViewEntity> marketViewData = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    public FarmFX(){


    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FarmApp");
        System.out.println("application was run");
        initRootLayout();
  //      showTerritoryOverview();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FarmFX.class.getResource("/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            RootLayoutController controller = loader.getController();
            controller.setFarm(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showTerritoryOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FarmFX.class.getResource("/territoryOverview.fxml"));
            AnchorPane territoryOverview = (AnchorPane) loader.load();
            Stage referenceStage = new Stage();
            referenceStage.setTitle("View References");
            referenceStage.initModality(Modality.WINDOW_MODAL);
            referenceStage.initOwner(primaryStage);
            Scene referenceScene = new Scene(territoryOverview);
            referenceStage.setScene(referenceScene);
            TerritoryOverviewController controller = loader.getController();
            controller.setFarmFX(this);
            controller.setReferenceStage(referenceStage);
            referenceStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showContragentOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FarmFX.class.getResource("/contragentOverview.fxml"));
            AnchorPane contragentOverview = (AnchorPane) loader.load();
            Stage referenceStage = new Stage();
            referenceStage.setTitle("View References");
            referenceStage.initModality(Modality.WINDOW_MODAL);
            referenceStage.initOwner(primaryStage);
            Scene referenceScene = new Scene(contragentOverview);
            referenceStage.setScene(referenceScene);
            ContragentOverviewController controller = loader.getController();
            controller.setFarmFX(this);
            controller.setReferenceStage(referenceStage);
            referenceStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<RefTerritoryEntity> getTerritoryData() {
        return territoryData;
    }
    public ObservableList<RefCityEntity> getCitiesData() {
        return citiesData;
    }
    public ObservableList<RefTypeCityEntity> getTypeCityData() {
        return typeCityData;
    }
    public ObservableList<RefContragentEntity> getContragentData() {
        return contragentData;
    }
    public ObservableList<RefTypeContragentEntity> getTypeContragentData() {
        return typeContragentData;
    }
    public ObservableList<RefPriceEntity> getPriceData() {
        return priceData;
    }
    public ObservableList<RefMarketViewEntity> getMarketViewData() {
        return marketViewData;
    }

    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(FarmFX.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(FarmFX.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }
}
