package address.mains;

import javafx.stage.Stage;
import models.SuperEntity;

public interface ControllerDialog {
    void setFarmFX(FarmFX farm, SuperEntity entity);
    void setDialogStage(Stage dialogStage);
    void handleCloseDialog();
    void handleOkDialog();
}
