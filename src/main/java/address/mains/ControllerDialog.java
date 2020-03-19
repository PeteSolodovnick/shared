package address.mains;

import javafx.stage.Stage;
import models.SuperEntity;
import models.references.SuperReferenceEntity;

public interface ControllerDialog {
    void setFarmFX(FarmFX farm, SuperReferenceEntity entity);
    void setDialogStage(Stage dialogStage);
    void handleCloseDialog();
    void handleOkDialog();
}
