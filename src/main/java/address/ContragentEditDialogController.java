package address;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.*;
import services.EntityService;

import java.util.HashMap;
import java.util.Map;

public class ContragentEditDialogController {
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField contact;
    @FXML
    private TextField phone;
    @FXML
    private ComboBox typeContragent;
    @FXML
    private ComboBox locality;
    @FXML
    private ComboBox price;

    private Stage dialogStage;
    private RefContragentEntity contragent;
    private boolean okClicked = false;
    private boolean isNew = false;
    private FarmFX farm;
    private Map<String, RefCityEntity> cityEntityMap = new HashMap<String, RefCityEntity>();
    private Map<String, RefTypeContragentEntity> typeContragentEntityMap = new HashMap<String, RefTypeContragentEntity>();
    private Map<String, RefPriceEntity> priceEntityMap = new HashMap<String, RefPriceEntity>();

    @FXML
    private void initialize() {
    }

    public void setFarm(FarmFX farm) {
        this.farm = farm;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setContragent(RefContragentEntity contragent, boolean isNew) {
        this.contragent = contragent;
        this.isNew = isNew;
        for (RefCityEntity city:farm.getCitiesData()) {
            locality.getItems().add(city.getName());
            cityEntityMap.put(city.getName(),city);
        }
        AutoCompleteComboBoxListener autoCity = new AutoCompleteComboBoxListener(locality);
        for (RefTypeContragentEntity typeContra:farm.getTypeContragentData()) {
            typeContragent.getItems().add(typeContra.getName());
            typeContragentEntityMap.put(typeContra.getName(),typeContra);
        }
        AutoCompleteComboBoxListener autoTypeContra = new AutoCompleteComboBoxListener(typeContragent);
        for (RefPriceEntity priceEntity:farm.getPriceData()) {
            price.getItems().add(priceEntity.getName());
            priceEntityMap.put(priceEntity.getName(),priceEntity);
        }
        AutoCompleteComboBoxListener autoPrice = new AutoCompleteComboBoxListener(price);
        if (isNew) {
            name.setText("");
            address.setText("");
            contact.setText("");
            phone.setText("");
        } else {
            name.setText(contragent.getName());
            address.setText(contragent.getAddress());
            contact.setText(contragent.getContact());
            phone.setText(contragent.getPhone());
            locality.setValue(contragent.getRefCityByCityId().getName());
            price.setValue(contragent.getRefPriceByPriceId().getName());
            typeContragent.setValue(contragent.getRefTypeContragentByTypeContraId().getName());
        }
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk(){
        if (isInputValid()) {
            String tempName = name.getText();
            String tempAddress = address.getText();
            String tempContact = contact.getText();
            String tempPhone = phone.getText();
            EntityService service = new EntityService();
            try {
                contragent.setName(name.getText());
                contragent.setAddress(address.getText());
                contragent.setContact(contact.getText());
                contragent.setPhone(phone.getText());
                contragent.setRefCityByCityId(cityEntityMap.get(locality.getValue()));
                contragent.setRefPriceByPriceId(priceEntityMap.get(price.getValue()));
                contragent.setRefTypeContragentByTypeContraId(typeContragentEntityMap.get(typeContragent.getValue()));
                if (isNew) {
                    service.create(contragent);
                } else {
                    service.update(contragent);
                }
                okClicked = true;
            } catch (Exception e) {
                contragent.setName(tempName);
                contragent.setAddress(tempAddress);
                contragent.setContact(tempContact);
                contragent.setPhone(tempPhone);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText("Your inputed values are failed");
                alert.showAndWait();
            }
            dialogStage.close();
        }
    }
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }
    private boolean isInputValid() {
        String errorMessage = "";
        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage+="No valid name";
        }
        if (address.getText() == null || address.getText().length() == 0) {
            errorMessage+="No valid address";
        }
        if (contact.getText() == null || contact.getText().length() == 0) {
            errorMessage+="No valid contact";
        }
        if (locality.getValue() == null || !cityEntityMap.containsKey(locality.getValue())) {
            errorMessage+="No valid locality";
        }
        if (typeContragent.getValue() == null || !typeContragentEntityMap.containsKey(typeContragent.getValue())) {
            errorMessage+="No valid type of Contragent";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
