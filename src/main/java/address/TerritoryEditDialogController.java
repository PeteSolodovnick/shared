package address;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.RefTerritoryEntity;
import services.EntityService;

public class TerritoryEditDialogController {
    @FXML
    private TextField region;
    private Stage dialogStage;
    private RefTerritoryEntity territory;
    private boolean okClicked = false;
    private boolean isNew = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setTerritory(RefTerritoryEntity territory, boolean isNew) {
        this.territory = territory;
        this.isNew = isNew;
        if (isNew)
            region.setText("");
        else region.setText(territory.getName());
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    @FXML
    private void handleOk(){
        if (isInputValid()) {
            String temp = region.getText();
            EntityService service = new EntityService();
            try {
                territory.setName(region.getText());
                if (isNew) {
                    service.create(territory);
                } else {
                    service.update(territory);
                }
                okClicked = true;
            } catch (Exception e) {
                territory.setName(temp);
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
        if (region.getText() == null || region.getText().length() == 0) {
            errorMessage+="No valid region";
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
