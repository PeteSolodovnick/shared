package address;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.*;
import services.EntityService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    @FXML
    private ComboBox marketView;
    @FXML
    private TextArea comments;

    private Stage dialogStage;
    private RefContragentEntity contragent;
    private boolean okClicked = false;
    private boolean isNew = false;
    private FarmFX farm;
    private Map<String, RefCityEntity> cityEntityMap = new HashMap<String, RefCityEntity>();
    private Map<String, RefTypeContragentEntity> typeContragentEntityMap = new HashMap<String, RefTypeContragentEntity>();
    private Map<String, RefPriceEntity> priceEntityMap = new HashMap<String, RefPriceEntity>();
    private Map<String, RefMarketViewEntity> marketMap = new HashMap<>();

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
        for (RefMarketViewEntity marketEntity:farm.getMarketViewData()) {
            marketView.getItems().add(marketEntity.getName());
            marketMap.put(marketEntity.getName(),marketEntity);
        }
        AutoCompleteComboBoxListener autoMarket= new AutoCompleteComboBoxListener(marketView);
        if (isNew) {
            name.setText("");
            address.setText("");
            contact.setText("");
            phone.setText("");
            comments.setText("");
        } else {
            name.setText(contragent.getName());
            address.setText(contragent.getAddress());
            contact.setText(contragent.getContact());
            phone.setText(contragent.getPhone());
            comments.setText(contragent.getComments());
            locality.setValue(contragent.getRefCityByCityId().getName());
            price.setValue(contragent.getRefPriceByPriceId().getName());
            typeContragent.setValue(contragent.getRefTypeContragentByTypeContraId().getName());
            marketView.setValue(contragent.getRefMarketViewByMarketViewId().getName());
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
            String tempComments = comments.getText();
            EntityService service = new EntityService();
            try {
                contragent.setName(name.getText());
                contragent.setAddress(address.getText());
                contragent.setContact(contact.getText());
                contragent.setPhone(phone.getText());
                contragent.setComments(comments.getText());
                contragent.setRefCityByCityId(cityEntityMap.get(locality.getValue()));
                contragent.setRefPriceByPriceId(priceEntityMap.get(price.getValue()));
                contragent.setRefMarketViewByMarketViewId(marketMap.get(marketView.getValue()));
                if (!typeContragentEntityMap.containsKey(typeContragent.getValue().toString())) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.initOwner(dialogStage);
                    alert.setTitle("Unknown Contragent Type value");
                    alert.setHeaderText("Unknown value in field type of contragent");
                    alert.setContentText("Do you want to add this value to database?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        RefTypeContragentEntity typeEntity = new RefTypeContragentEntity();
                        typeEntity.setName(typeContragent.getValue().toString());
                        System.out.println(typeEntity.getName());
                        service.create(typeEntity);
                        System.out.println("create type Entity");
                        typeContragentEntityMap.put(typeContragent.getValue().toString(),typeEntity);
                    }
                }
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
                contragent.setComments(tempComments);
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
            errorMessage+="No valid name\n";
        }
        if (address.getText() == null || address.getText().length() == 0) {
            errorMessage+="No valid address\n";
        }
        if (contact.getText() == null || contact.getText().length() == 0) {
            errorMessage+="No valid contact\n";
        }
        if (locality.getValue() == null || !cityEntityMap.containsKey(locality.getValue())) {
            errorMessage+="No valid locality\n";
        }
        if (marketView.getValue() == null || !marketMap.containsKey(marketView.getValue())) {
            errorMessage+="No valid Market View\n";
        }
        if (price.getValue() == null || !priceEntityMap.containsKey(price.getValue())) {
            errorMessage+="No valid price View\n";
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
