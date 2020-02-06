package address.stores;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.RefStorageEntity;
import models.SuperEntity;

public class StorageEditController extends SuperDialogEntityController {
    @FXML
    private TextField attribute;
    private RefStorageEntity newStorageEntity;
    public StorageEditController() {}
    public void setFarmFX(FarmFX farm, SuperEntity selectedStorage) {
        newStorageEntity = (RefStorageEntity) selectedStorage;
        if (selectedStorage != null) {
            attribute.setText(String.valueOf(newStorageEntity.getAttribute()));
            setNew(false);
        } else {
            newStorageEntity = new RefStorageEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newStorageEntity);
    }
    @Override
    protected boolean isInputValid() {
        if (super.isInputValid()) {
            String errorMessage = "";
            try {
                if (Integer.parseInt(attribute.getText()) <= 0) {
                    errorMessage += "No correct attribute\n";
                }
            } catch (ClassCastException e) {
                errorMessage += "No correct fields attribute must be positive whole numbers";
            } catch (NumberFormatException e) {
                errorMessage += "No correct fields attribute \n it must be positive whole numbers";
            }
            if (isError(errorMessage))
                return false;
            return true;
        }
        return false;
    }
    @Override
    protected void createEntity() {
        super.createEntity();
        newStorageEntity.setAttribute(Integer.parseInt(attribute.getText()));
        setNewEntity(newStorageEntity);
    }
    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getStorageData().add(getNewStorageEntity());
        getFarm().getConfigDialogController().getStorageOverviewController().getEntities().add(getNewStorageEntity());
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        getFarm().getConfigDialogController().getStorageOverviewController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getStorageOverviewController().getEntitiesName().setItems(getFarm().getConfigDialogController().getStorageOverviewController().getEntities());
    }

    public RefStorageEntity getNewStorageEntity() {
        return newStorageEntity;
    }
}
