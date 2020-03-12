package address.mains;

import javafx.stage.Stage;
import models.SuperEntity;

public interface ControllerReference {
    void handleNewEntity();
    void handleDeleteEntity();
    void handleEditEntity();
    void handleCloseReference();
    void setFarmFX(FarmFX farm);
    void setFarmFX(FarmFX farm, SuperEntity entity);
    void setReferenceStage(Stage referenceStage);
}
