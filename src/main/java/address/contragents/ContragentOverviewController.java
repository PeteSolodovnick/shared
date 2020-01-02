package address.contragents;

import address.mains.ControllerReference;
import address.mains.FarmFX;
import address.mains.SuperEntityTreeController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import models.*;

public class ContragentOverviewController extends SuperEntityTreeController implements ControllerReference {
    @FXML
    private TableColumn<RefContragentEntity, RefCityEntity> city;
    @FXML
    private TableColumn<RefContragentEntity, String> address;
    @FXML
    private TableColumn<RefContragentEntity, String> contact;
    @FXML
    private TableColumn<RefContragentEntity, String> phone;
    @FXML
    private TableColumn<RefContragentEntity, RefMarketViewEntity> market;

    public ContragentOverviewController() {}

    @FXML @Override
    protected void initialize() {
        RefTypeContragentEntity rootTypeContragent = new RefTypeContragentEntity();
        rootTypeContragent.setName("Type Contragent");
        getRootItem().setValue(rootTypeContragent);
        super.initialize();
        city.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefCityByCityId()));
        address.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        market.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefMarketViewByMarketViewId()));
        contact.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContact()));
        phone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/contragentEditDialog.fxml");
        setFileTree("/typeContraEditDialog.fxml");
        setFileInfo("/contragentInfoDialog.fxml");
        getEntitiesTree().addAll(farm.getReferences().getTypeContragentData());
        getEntities().addAll(farm.getReferences().getContragentData());
        super.setFarmFX(farm);
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setContragentOverviewController(this);
        setTitle("New Contragent...");
        super.handleNewEntity();
    }
    @Override
    protected void initRoot() {
        super.initRoot();
    }
    @Override
    public void handleEditEntity() {
        getFarm().getConfigDialogController().setContragentOverviewController(this);
        setTitle("Edit Contragent...");
        super.handleEditEntity();
    }
    @Override
    public void handleDeleteTreeEntity() {
        super.handleDeleteTreeEntity();
    }
    @Override
    public void handleNewTreeEntity() {
        getFarm().getConfigDialogController().setContragentOverviewController(this);
        setTitle("New Type Contragent...");
        super.handleNewTreeEntity();
    }
    @Override
    public void handleEditTreeEntity(){
        getFarm().getConfigDialogController().setContragentOverviewController(this);
        setTitle("Edit Type Contragent...");
        super.handleEditTreeEntity();
    }
    @Override
    public void handleInfoEntity() {
        getFarm().getConfigDialogController().setContragentOverviewController(this);
        setTitle("Full Contragent Information");
        super.handleInfoEntity();
    }

    @Override
    public void deletedFromTreeArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getTypeContragentData().remove(selectedEntity);
    }

    @Override
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getContragentData().remove(selectedEntity); }
}
