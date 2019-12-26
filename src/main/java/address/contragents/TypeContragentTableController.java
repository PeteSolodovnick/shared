package address.contragents;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import models.RefTerritoryEntity;
import models.RefTypeContragentEntity;

public class TypeContragentTableController extends SuperTableEntityController {
    public TypeContragentTableController(){}

    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/typeContraEditDialog.fxml");
        getEntities().addAll(farm.getReferences().getTypeContragentData());
        super.setFarmFX(farm);
    }

    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getContragentDialogController().setTypeContragentEntity((RefTypeContragentEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getContragentDialogController().getType().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setTypeContragentTableController(this);
        setTitle("New Contragent's type...");
        super.handleNewEntity();
    }
    @Override
    public  void handleEditEntity() {
        getFarm().getConfigDialogController().setTypeContragentTableController(this);
        setTitle("Edit Contragent's type...");
        super.handleEditEntity();
    }

    @Override
    public void deletedFromArray(int id) {
        getFarm().getReferences().getTypeContragentData().remove(id);
        getFarm().getConfigDialogController().getContragentOverviewController().getEntitiesTree().remove(id);
        getFarm().getConfigDialogController().getContragentOverviewController().getRootItem().getChildren().remove(id);
    }
}
