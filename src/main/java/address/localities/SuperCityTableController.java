package address.localities;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import models.RefCityEntity;
import models.RefTerritoryEntity;
import models.RefTypeCityEntity;
import models.SuperEntity;

public abstract class SuperCityTableController extends SuperTableEntityController {
    @FXML
    private TableColumn<RefCityEntity, RefTerritoryEntity> territory;
    @FXML
    private TableColumn<RefCityEntity, RefTypeCityEntity> typeCity;

    @FXML
    protected void initialize() {
            super.initialize();
            territory.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefTerritoryByTerId()));
            typeCity.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefTypeCityByTypeCityId()));
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
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getCitiesData().remove(selectedEntity);
    }
}
