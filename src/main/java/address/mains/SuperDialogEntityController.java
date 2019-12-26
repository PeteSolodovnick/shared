package address.mains;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.SuperEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.EntityService;

public abstract class SuperDialogEntityController implements ControllerDialog {
    protected static final Logger logger = LogManager.getLogger();
    @FXML
    private TextField name;

    private FarmFX farm;
    private SuperEntity entity;
    private Stage dialogStage;
    private boolean isNew;

    public SuperDialogEntityController(){}

    @FXML
    private void initialize() {
    }

    @Override
    public void handleCloseDialog() {
        dialogStage.close();
    }

    @Override
    public void setFarmFX(FarmFX farm, SuperEntity entity) {
        this.farm = farm;
        this.entity = entity;
        if (isNew)
            name.setText("");
        else
            name.setText(entity.getName());
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void handleOkDialog() {
        if (isInputValid()) {
            createEntity();
            EntityService service = new EntityService();
            try {
                if (isNew) {
                    service.create(entity);
                    newEntity();
                } else {
                    service.update(entity);
                    editEntity(entity);
                }
                dialogStage.close();
            } catch (Exception e) {
                showAlert("One or more fields are empty");
            }
        }
    }
    public abstract void newEntity();
    public abstract void editEntity(SuperEntity entity);

    protected void createEntity() {
            entity.setName(name.getText());
    }

    protected boolean isInputValid() {
        String errorMessage = "";
        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage+="No valid name\n";
        }
        if (isError(errorMessage))
            return false;
        return true;
    }
    protected boolean isError(String errorMessage) {
        if (errorMessage.length() == 0) {
            return false;
        } else {
            showAlert(errorMessage);
            return true;
        }
    }
    protected void showAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(dialogStage);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    public FarmFX getFarm() {
        return farm;
    }

    public void setFarm(FarmFX farm) {
        this.farm = farm;
    }

    public SuperEntity getEntity() {
        return entity;
    }

    public void setEntity(SuperEntity entity) {
        this.entity = entity;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
