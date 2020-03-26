package address.contragents;

import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import models.SuperEntity;
import models.references.SuperReferenceEntity;
import models.references.TypeContragentEntity;

public class TypeContragentTableController extends SuperTableEntityController {
    public TypeContragentTableController(){}

    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/typeContraEditDialog.fxml");
        initArray(farm);
        getEntities().addAll(farm.getReferences().getTypeContragentData());
        super.setFarmFX(farm);
    }
    private void initArray(FarmFX farm) {
        farm.getReferences().setTypeContragentData(new FactoryListEntities<>(new TypeContragentEntity()).getListEntities());
    }

    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getContragentDialogController().setNewTypeContragentEntity((TypeContragentEntity) getEntityTable().getSelectionModel().getSelectedItem());
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
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getTypeContragentData().remove(selectedEntity);
        getFarm().getConfigDialogController().getContragentOverviewController().getEntitiesTree().remove(selectedEntity);
        getFarm().getConfigDialogController().getContragentOverviewController().getRootItem().getChildren().clear();
        getFarm().getConfigDialogController().getContragentOverviewController().initRoot();
    }
}
