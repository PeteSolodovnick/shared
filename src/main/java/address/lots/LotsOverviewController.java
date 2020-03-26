package address.lots;

import address.mains.ControllerReference;
import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import address.mains.SuperEntityTreeController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import models.*;
import models.references.KindLotsEntity;
import models.references.LotsEntity;
import models.references.SuperReferenceEntity;
import models.references.TypeLotsEntity;

public class LotsOverviewController extends SuperEntityTreeController implements ControllerReference {
    @FXML
    private TableColumn<LotsEntity, TypeLotsEntity> type;
    @FXML
    private TableColumn<LotsEntity, KindLotsEntity> kind;
    @FXML
    private TableColumn<LotsEntity, Integer> count;
    @FXML
    private TableColumn<LotsEntity, Integer> weight;
    @FXML
    private TableColumn<LotsEntity, Integer> age;

    public LotsOverviewController(){}

    @FXML @Override
    protected void initialize() {
        TypeLotsEntity rootTypeLots = new TypeLotsEntity();
        rootTypeLots.setName("Type Lots");
        getRootItem().setValue(rootTypeLots);
        super.initialize();
        type.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefTypeLotsByTypeLotsId()));
        kind.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefKindLotsByKindLotsId()));
        count.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getStartCount()));
        weight.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getStartWeight()));
        age.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getStartAge()));
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/lotsEditDialog.fxml");
        setFileTree("/lot'sTypeEditDialog.fxml");
        initArray(farm);
        getEntitiesTree().addAll(farm.getReferences().getTypeLotsData());
        getEntities().addAll(farm.getReferences().getLotsData());
        super.setFarmFX(farm);
    }
    private void initArray(FarmFX farm){
        farm.getReferences().setTypeLotsData(new FactoryListEntities<>(new TypeLotsEntity()).getListEntities());
        farm.getReferences().setLotsData(new FactoryListEntities<>(new LotsEntity()).getListEntities());
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setLotsOverviewController(this);
        setTitle("New Lot...");
        super.handleNewEntity();
    }
    @Override
    protected void initRoot() {
        super.initRoot();
    }
    @Override
    public void handleEditEntity() {
        getFarm().getConfigDialogController().setLotsOverviewController(this);
        setTitle("Edit Lot...");
        super.handleEditEntity();
    }
    @Override
    public void handleDeleteTreeEntity() {
        super.handleDeleteTreeEntity();
    }
    @Override
    public void handleNewTreeEntity() {
        getFarm().getConfigDialogController().setLotsOverviewController(this);
        setTitle("New Type Lot...");
        super.handleNewTreeEntity();
    }
    @Override
    public void handleEditTreeEntity(){
        getFarm().getConfigDialogController().setLotsOverviewController(this);
        setTitle("Edit Type Lot...");
        super.handleEditTreeEntity();
    }
    @Override
    public void deletedFromTreeArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getTypeLotsData().remove(selectedEntity);
    }
    @Override
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getLotsData().remove(selectedEntity); }
}
