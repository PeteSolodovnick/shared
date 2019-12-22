package address.localities;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import models.RefCityEntity;

public abstract class SuperCityTableController extends SuperTableEntityController {
    @FXML
    private TableColumn<RefCityEntity, String> territory;
    @FXML
    private TableColumn<RefCityEntity, String> typeCity;

    @FXML
    protected void initialize() {
            super.initialize();
            territory.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRefTerritoryByTerId().getName()));
            typeCity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRefTypeCityByTypeCityId().getName()));
    }
    public SuperCityTableController() {}

    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/cityEditDialog.fxml");
        getEntities().addAll(farm.getReferences().getCitiesData());
        super.setFarmFX(farm);
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setSuperCityTableController(this);
        setTitle("New City...");
        super.handleNewEntity();
    }
    @Override
    public  void handleEditEntity() {
        getFarm().getConfigDialogController().setSuperCityTableController(this);
        setTitle("Edit City...");
        super.handleEditEntity();
    }

    @Override
    public void deletedFromArray(int id) {
        getFarm().getReferences().getCitiesData().remove(id);
    }
}
