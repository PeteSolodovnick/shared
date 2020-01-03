package address.classifications;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import models.RefMarketViewEntity;
import models.RefSizeEntity;
import models.SuperEntity;

public class SizeProductsTableController extends SuperTableEntityController {
    public SizeProductsTableController() {
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/sizeEditDialog.fxml");
        getEntities().addAll(farm.getReferences().getSizeEntitiesData());
        super.setFarmFX(farm);
    }
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getProductsDialogController().setNewSizeEntity((RefSizeEntity) getEntityTable().getSelectionModel().getSelectedItem());
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
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getSizeEntitiesData().remove(selectedEntity);
    }
}
