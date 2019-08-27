package address;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.RefCityEntity;
import models.RefTerritoryEntity;
import services.EntityService;
import java.io.IOException;
import java.util.Optional;

public class TerritoryOverviewController {
    @FXML
    private TableView<RefTerritoryEntity> territoryTable;
    @FXML
    private TableView<RefCityEntity> cityTable;
    @FXML
    private TableColumn<RefTerritoryEntity,Long> idTerritory;
    @FXML
    private TableColumn<RefCityEntity,Long> idCity;
    @FXML
    private TableColumn<RefTerritoryEntity,String> nameTerritory;
    @FXML
    private TableColumn<RefCityEntity,String> nameCity;

    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label typeLabel;
    @FXML
    private Label regionLabel;

    private Stage referenceStage;
    private FarmFX farm;

    public TerritoryOverviewController(){

    }
    @FXML
    private void initialize() {
        System.out.println("initialize");
        idTerritory.setCellValueFactory(cellData -> new SimpleObjectProperty<Long>(cellData.getValue().getId()));
        idCity.setCellValueFactory(cellData -> new SimpleObjectProperty<Long>(cellData.getValue().getId()));
        nameTerritory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        nameCity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        showCitiesDetails(null);
        cityTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCitiesDetails(newValue));
        territoryTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTerritoryDetails(newValue));
        System.out.println("End initialize");
    }
    public void setFarmFX(FarmFX farm) {
        this.farm = farm;
        territoryTable.setItems(farm.getTerritoryData());
        cityTable.setItems(farm.getCitiesData());
    }
    public boolean showTerritoryEditDialog(RefTerritoryEntity territory, String title, boolean isNew){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FarmFX.class.getResource("/territoryEditDialog.fxml"));
            AnchorPane territoryEditDialog = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(referenceStage);
            Scene dialogScene = new Scene(territoryEditDialog);
            dialogStage.setScene(dialogScene);
            TerritoryEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTerritory(territory, isNew);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showCityEditDialog(RefCityEntity city, String title, boolean isNew) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FarmFX.class.getResource("/cityEditDialog.fxml"));
            AnchorPane cityEditDialog = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(referenceStage);
            Scene dialogScene = new Scene(cityEditDialog);
            dialogStage.setScene(dialogScene);
            CityEditDialogController controller = loader.getController();
            controller.setFarm(farm);
            controller.setDialogStage(dialogStage);
            controller.setCity(city, isNew);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void showTerritoryDetails(RefTerritoryEntity territoryEntity) {
        ObservableList<RefCityEntity> citiesOfTerritory = FXCollections.observableArrayList();
        if (territoryEntity != null) {
            for (RefCityEntity city:farm.getCitiesData()) {
                if (city.getRefTerritoryByTerId().getId() == territoryEntity.getId()) {
                    citiesOfTerritory.add(city);
                }
            }
            cityTable.setItems(citiesOfTerritory);
        }
    }
    private void showCitiesDetails(RefCityEntity cityEntity) {
        if (cityEntity != null) {
            idLabel.setText(cityEntity.getId().toString());
            nameLabel.setText(cityEntity.getName());
            regionLabel.setText(cityEntity.getRefTerritoryByTerId().getName());
            typeLabel.setText(cityEntity.getRefTypeCityByTypeCityId().getName());
        } else {
            idLabel.setText("");
            nameLabel.setText("");
            typeLabel.setText("");
            regionLabel.setText("");
        }
    }
    @FXML
    private void handleCloseReferences() {
        referenceStage.close();
    }
    @FXML
    private void handleDeleteTerritory() {
        int selectedId = territoryTable.getSelectionModel().getSelectedIndex();
        if (selectedId>=0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Territory");
            alert.setHeaderText("Territory " + territoryTable.getItems().get(selectedId).getName() + " will be deleted");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EntityService service = new EntityService();
                try {
                    service.delete(territoryTable.getItems().get(selectedId));
                    territoryTable.getItems().remove(selectedId);
                } catch (Exception e) {
                    Alert exAlert = new Alert(Alert.AlertType.ERROR);
                    exAlert.setTitle("Error!");
                    exAlert.setHeaderText("Object couldn't be deleted");
                    exAlert.setContentText("Object can't be deleted because others objects\n\n have references to it.");
                    exAlert.showAndWait();
                }
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(farm.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Territory Selected");
            alert.setContentText("Please select a territory in the table");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleDeleteCity() {
        int selectedId = cityTable.getSelectionModel().getSelectedIndex();
        if (selectedId>=0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete City");
            alert.setHeaderText("City " + cityTable.getItems().get(selectedId).getName() + " will be deleted");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EntityService service = new EntityService();
                try {
                    service.delete(cityTable.getItems().get(selectedId));
                    cityTable.getItems().remove(selectedId);
                } catch (Exception e) {
                    Alert exAlert = new Alert(Alert.AlertType.ERROR);
                    exAlert.setTitle("Error!");
                    exAlert.setHeaderText("Object couldn't be deleted");
                    exAlert.setContentText("Object can't be deleted because others objects\n\n have references to it.");
                    exAlert.showAndWait();
                }
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(farm.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Territory Selected");
            alert.setContentText("Please select a territory in the table");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleNewTerritory() {
        RefTerritoryEntity territory = new RefTerritoryEntity();
        boolean okClicked = showTerritoryEditDialog(territory,"New Territory", true);
        if (okClicked) {
            farm.getTerritoryData().add(territory);
            territoryTable.refresh();
        }
    }
    @FXML
    private void handleNewCity() {
        RefCityEntity city = new RefCityEntity();
        boolean okClicked = showCityEditDialog(city,"New City", true);
        if (okClicked) {
            farm.getCitiesData().add(city);
            cityTable.refresh();
        }
    }
    @FXML
    private void handleEditTerritory() {
        // получаем объект территория (выделенный элемент таблицы territory.table)
        RefTerritoryEntity territory = territoryTable.getSelectionModel().getSelectedItem();
        //запоминаем индекс редактируемой территории в отслеживаемом массиве Farm.territoryData
        if (territory != null) {
            boolean okClicked = showTerritoryEditDialog(territory, "Edit Territory",false);
            //если редактирование прощшло успешно
            if (okClicked) {
                //обновляем отображение таблицы с новыми данными
                territoryTable.refresh();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(farm.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Territory Selected");
            alert.setContentText("Please select a territory in the table.");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleEditCity() {
        RefCityEntity city = cityTable.getSelectionModel().getSelectedItem();
        if (city != null) {
            boolean okClicked = showCityEditDialog(city, "Edit City", false);
            if (okClicked) {
                cityTable.refresh();
                showCitiesDetails(city);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(farm.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No City Selected");
            alert.setContentText("Please select a city in the table.");
            alert.showAndWait();
        }
    }

    public void setReferenceStage(Stage referenceStage) {
        this.referenceStage = referenceStage;
    }

}
