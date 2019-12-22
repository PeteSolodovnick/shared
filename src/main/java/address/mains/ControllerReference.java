package address.mains;

import javafx.stage.Stage;

public interface ControllerReference {
    void handleNewEntity();
    void handleDeleteEntity();
    void handleEditEntity();
    void handleCloseReference();
    void setFarmFX(FarmFX farm);
    void setReferenceStage(Stage referenceStage);
}
