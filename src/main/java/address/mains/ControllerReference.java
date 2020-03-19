package address.mains;

import javafx.stage.Stage;
import models.SuperEntity;
import models.references.SuperReferenceEntity;

public interface ControllerReference {
    void handleNewEntity();
    void handleDeleteEntity();
    void handleEditEntity();
    void handleCloseReference();
    void setFarmFX(FarmFX farm);
    void setFarmFX(FarmFX farm, SuperReferenceEntity entity);
    void setReferenceStage(Stage referenceStage);
}
