package address.classifications;

import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import models.references.SizeEntity;
import models.SuperEntity;
import models.references.SuperReferenceEntity;

public class SizeProductsTableController extends SuperTableEntityController {
    public SizeProductsTableController() {
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/sizeEditDialog.fxml");
        initArray(farm);
        getEntities().addAll(farm.getReferences().getSizeEntitiesData());
        super.setFarmFX(farm);
    }
    private void initArray(FarmFX farm) {
        farm.getReferences().setSizeEntitiesData(new FactoryListEntities<>(new SizeEntity()).getListEntities());
    }
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getProductsDialogController().setNewSizeEntity((SizeEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getProductsDialogController().getSize().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }

    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setSizeProductsTableController(this);
        setTitle("New Size...");
        super.handleNewEntity();
    }

    @Override
    public  void handleEditEntity() {
        getFarm().getConfigDialogController().setSizeProductsTableController(this);
        setTitle("Edit Size...");
        super.handleEditEntity();
    }

    @Override
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getSizeEntitiesData().remove(selectedEntity);
    }
}
