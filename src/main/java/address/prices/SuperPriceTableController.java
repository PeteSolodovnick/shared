package address.prices;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import models.SuperEntity;
import models.references.SuperReferenceEntity;

public abstract class SuperPriceTableController extends SuperTableEntityController {
    public SuperPriceTableController() {}

    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/priceEditDialog.fxml");
        getEntities().addAll(farm.getReferences().getPriceData());
        super.setFarmFX(farm);
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setSuperPriceTableController(this);
        setTitle("New Price...");
        super.handleNewEntity();
    }
    @Override
    public  void handleEditEntity() {
        getFarm().getConfigDialogController().setSuperPriceTableController(this);
        setTitle("Edit Price...");
        super.handleEditEntity();
    }

    @Override
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getCitiesData().remove(selectedEntity);
    }
}
