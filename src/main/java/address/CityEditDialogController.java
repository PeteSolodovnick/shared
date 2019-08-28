package address;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.RefCityEntity;
import models.RefTerritoryEntity;
import models.RefTypeCityEntity;
import services.EntityService;

import java.util.HashMap;
import java.util.Map;


public class CityEditDialogController {
    @FXML
    private TextField name;
    @FXML
    private ComboBox region;
    @FXML
    private ComboBox typeLocality;

    private FarmFX farm;
    private Stage dialogStage;
    private RefCityEntity city;
    private boolean okClicked = false;
    private boolean isNew = false;
    private Map<String, RefTerritoryEntity> indexTer = new HashMap<String, RefTerritoryEntity>();
    private Map<String, RefTypeCityEntity> indexType = new HashMap<String, RefTypeCityEntity>();

    @FXML
    private void initialize() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setFarm(FarmFX farm) {
        this.farm = farm;
    }

    public void setCity(RefCityEntity city, boolean isNew) {
        System.out.println("in set");
        this.city = city;
        this.isNew = isNew;
        for (RefTerritoryEntity ter:farm.getTerritoryData()) {
            region.getItems().add(ter.getName());
            indexTer.put(ter.getName(),ter);
        }
        AutoCompleteComboBoxListener autoRegion = new AutoCompleteComboBoxListener(region);
        for (RefTypeCityEntity type:farm.getTypeCityData()) {
            typeLocality.getItems().add(type.getName());
            indexType.put(type.getName(),type);
        }
        AutoCompleteComboBoxListener autoType = new AutoCompleteComboBoxListener(typeLocality);
        if (isNew) {
            name.setText("");
        } else {
            name.setText(city.getName());
            typeLocality.setValue(city.getRefTypeCityByTypeCityId().getName());
            region.setValue(city.getRefTerritoryByTerId().getName());
        }

    }

    public boolean isOkClicked() {
        return okClicked;
    }
    @FXML
    private void handleOk(){
        if (isInputValid()) {
            String temp = name.getText();
            EntityService service = new EntityService();
            try {
                city.setName(name.getText());
                city.setRefTerritoryByTerId(indexTer.get(region.getValue()));
                city.setRefTypeCityByTypeCityId(indexType.get(typeLocality.getValue()));
                if (isNew) {
                    service.create(city);
                } else {
                    service.update(city);
                }
                okClicked = true;
            } catch (Exception e) {
                city.setName(temp);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText("Your inputed name is failed");
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
            errorMessage+="No valid citie's name";
        }
        if (region.getValue() == null)
            errorMessage+="No valid region ";
        if (typeLocality.getValue()==null)
            errorMessage+="No valid type of locality";
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
