package address;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.RefContragentEntity;
import services.EntityService;

import java.io.IOException;
import java.util.Optional;

public class ContragentOverviewController {
    @FXML
    private TableView<RefContragentEntity> contragentTable;
    @FXML
    private TableColumn<RefContragentEntity,Long> idContragent;
    @FXML
    private TableColumn<RefContragentEntity,String> nameContragent;

    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label addressLabel;
    @FXML
    private Label contactLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label typeContragentLabel;
    @FXML
    private Label localityLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label marketViewLabel;
    @FXML
    private TextArea comments;

    private Stage referenceStage;
    private FarmFX farm;

    public ContragentOverviewController(){

    }
    @FXML
    private void initialize() {
        idContragent.setCellValueFactory(cellData -> new SimpleObjectProperty<Long>(cellData.getValue().getId()));
        nameContragent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        showContragentDetails(null);
        contragentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showContragentDetails(newValue));
    }
    public void setFarmFX(FarmFX farm) {
        this.farm = farm;
        contragentTable.setItems(farm.getContragentData());
    }

    public boolean showContragentEditDialog(RefContragentEntity contragent, String title, boolean isNew) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FarmFX.class.getResource("/contragentEditDialog.fxml"));
            AnchorPane contragentEditDialog = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(referenceStage);
            Scene dialogScene = new Scene(contragentEditDialog);
            dialogStage.setScene(dialogScene);
            ContragentEditDialogController controller = loader.getController();
            controller.setFarm(farm);
            controller.setDialogStage(dialogStage);
            controller.setContragent(contragent, isNew);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void showContragentDetails(RefContragentEntity contragentEntity) {
        if (contragentEntity != null) {
            idLabel.setText(contragentEntity.getId().toString());
            nameLabel.setText(contragentEntity.getName());
            addressLabel.setText(contragentEntity.getAddress());
            contactLabel.setText(contragentEntity.getContact());
            phoneLabel.setText(contragentEntity.getPhone());
            typeContragentLabel.setText(contragentEntity.getRefTypeContragentByTypeContraId().getName());
            priceLabel.setText(contragentEntity.getRefPriceByPriceId().getName());
            localityLabel.setText(contragentEntity.getRefCityByCityId().getName());
            marketViewLabel.setText(contragentEntity.getRefMarketViewByMarketViewId().getName());
            comments.setText(contragentEntity.getComments());

        } else {
            idLabel.setText("");
            nameLabel.setText("");
            addressLabel.setText("");
            contactLabel.setText("");
            phoneLabel.setText("");
            typeContragentLabel.setText("");
            priceLabel.setText("");
            localityLabel.setText("");
            marketViewLabel.setText("");
            comments.setText("");
        }
    }
    @FXML
    private void handleCloseReferences() {
        referenceStage.close();
    }
    @FXML
    private void handleDeleteContragent() {
        int selectedId = contragentTable.getSelectionModel().getSelectedIndex();
        if (selectedId>=0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Contragent");
            alert.setHeaderText("Contragent " + contragentTable.getItems().get(selectedId).getName() + " will be deleted");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EntityService service = new EntityService();
                try {
                    service.delete(contragentTable.getItems().get(selectedId));
                    contragentTable.getItems().remove(selectedId);
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
            alert.setHeaderText("No Contragent Selected");
            alert.setContentText("Please select a contragent in the table for deleting");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewContragent() {
        RefContragentEntity contragentEntity = new RefContragentEntity();
        boolean okClicked = showContragentEditDialog(contragentEntity,"New Contragent", true);
        if (okClicked) {
            farm.getContragentData().add(contragentEntity);
            contragentTable.refresh();
        }
    }

    @FXML
    private void handleEditContragent() {
        // получаем объект территория (выделенный элемент таблицы territory.table)
        RefContragentEntity contragentEntity = contragentTable.getSelectionModel().getSelectedItem();
        //запоминаем индекс редактируемой территории в отслеживаемом Observable массиве Farm.territoryData
        if (contragentEntity != null) {
            boolean okClicked = showContragentEditDialog(contragentEntity, "Edit Contragent",false);
            //если редактирование прощшло успешно
            if (okClicked) {
                //обновляем отображение таблицы с новыми данными
                contragentTable.refresh();
                showContragentDetails(contragentEntity);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(farm.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Contragent Selected");
            alert.setContentText("Please select a contragent in the table.");
            alert.showAndWait();
        }
    }

    public void setReferenceStage(Stage referenceStage) {
        this.referenceStage = referenceStage;
    }

}
