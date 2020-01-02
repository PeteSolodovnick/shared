package address.localities;

import address.mains.ControllerReference;
import address.mains.FarmFX;
import address.mains.SuperEntityTreeController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TreeItem;
import models.RefCityEntity;
import models.RefTerritoryEntity;
import models.RefTypeCityEntity;
import models.SuperEntity;

public class LocalityOverviewController extends SuperEntityTreeController implements ControllerReference {
    @FXML
    private TableColumn<RefCityEntity, RefTypeCityEntity> typeLocality;
    @FXML
    private TableColumn<RefCityEntity, RefTerritoryEntity> territory;

    public LocalityOverviewController() {}

    @FXML @Override
    protected void initialize() {
            RefTerritoryEntity rootTer = new RefTerritoryEntity();
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
    public void deletedFromTreeArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getTerritoryData().remove(selectedEntity);
    }

    @Override
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getCitiesData().remove(selectedEntity);
    }
}
