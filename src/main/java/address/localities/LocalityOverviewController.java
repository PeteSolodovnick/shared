package address.localities;

import address.mains.ControllerReference;
import address.mains.FarmFX;
import address.mains.SuperEntityTreeController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import models.*;
import models.references.CityEntity;
import models.references.SuperReferenceEntity;
import models.references.TerritoryEntity;
import models.references.TypeCityEntity;

public class LocalityOverviewController extends SuperEntityTreeController implements ControllerReference {
    @FXML
    private TableColumn<CityEntity, TypeCityEntity> typeLocality;
    @FXML
    private TableColumn<CityEntity, TerritoryEntity> territory;

    public LocalityOverviewController() {}

    @FXML @Override
    protected void initialize() {
            TerritoryEntity rootTer = new TerritoryEntity();
            rootTer.setName("Territories");
            getRootItem().setValue(rootTer);
            super.initialize();
            typeLocality.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefTypeCityByTypeCityId()));
            territory.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefTerritoryByTerId()));
    }

    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/cityEditDialog.fxml");
        setFileTree("/territoryEditDialog.fxml");
        getEntitiesTree().addAll(farm.getReferences().getTerritoryData());
        getEntities().addAll(farm.getReferences().getCitiesData());
        super.setFarmFX(farm);
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setLocalityOverviewController(this);
        setTitle("New Locaity...");
        super.handleNewEntity();
    }
    @Override
    protected void initRoot() {
        super.initRoot();
    }
    @Override
    public void handleEditEntity() {
        getFarm().getConfigDialogController().setLocalityOverviewController(this);
        setTitle("Edit Locality...");
        super.handleEditEntity();
    }
    @Override
    public void handleDeleteTreeEntity() {
        super.handleDeleteTreeEntity();
    }
    @Override
    public void handleNewTreeEntity() {
        getFarm().getConfigDialogController().setLocalityOverviewController(this);
        setTitle("New Territory...");
        super.handleNewTreeEntity();
    }
    @Override
    public void handleEditTreeEntity(){
        getFarm().getConfigDialogController().setLocalityOverviewController(this);
        setTitle("Edit Territory...");
        super.handleEditTreeEntity();
    }

    @Override
    public void deletedFromTreeArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getTerritoryData().remove(selectedEntity);
    }

    @Override
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getCitiesData().remove(selectedEntity);
    }
}
