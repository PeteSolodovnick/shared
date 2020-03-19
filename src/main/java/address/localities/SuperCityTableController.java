package address.localities;

import address.mains.FarmFX;
import address.mains.SuperTableEntityController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import models.*;
import models.references.CityEntity;
import models.references.SuperReferenceEntity;
import models.references.TerritoryEntity;
import models.references.TypeCityEntity;

public abstract class SuperCityTableController extends SuperTableEntityController {
    @FXML
    private TableColumn<CityEntity, TerritoryEntity> territory;
    @FXML
    private TableColumn<CityEntity, TypeCityEntity> typeCity;

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
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getCitiesData().remove(selectedEntity);
    }
}
