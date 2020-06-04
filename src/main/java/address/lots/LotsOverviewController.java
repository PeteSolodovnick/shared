package address.lots;

import address.mains.ControllerReference;
import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import address.mains.SuperEntityTreeController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import models.*;
import models.documents.DocDocsHeadDocEntity;
import models.references.*;
import models.tables.*;
import org.hibernate.Session;
import services.EntityService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    @FXML
    private TableColumn<LotsEntity, Boolean> editable;

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
        editable.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getEditable()));
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
        farm.getReferences().setLotsData(new FactoryListEntities<>(new LotsEntity()).getSomeListEntities(false,"deleted"));
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
        LotsEntity selectedLot = (LotsEntity) getEntityTable().getSelectionModel().getSelectedItem();
        if (selectedLot.getEditable()) {
            getFarm().getConfigDialogController().setLotsOverviewController(this);
            setTitle("Edit Lot...");
            super.handleEditEntity();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getFarm().getPrimaryStage());
            alert.setTitle("Not Editable element");
            alert.setHeaderText("You can't edit this lot");
            alert.setContentText("Please select other element in the table for editing");
            alert.showAndWait();
        }
    }
    @FXML @Override
    public void handleDeleteEntity() {
        LotsEntity selectedLot = (LotsEntity) getEntityTable().getSelectionModel().getSelectedItem();
        if (selectedLot != null && selectedLot.getEditable()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete " + selectedLot.getName());
            alert.setHeaderText(selectedLot.getName()+ " will be deleted");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result. get() == ButtonType.OK) {
                try {
                    EntityService<SuperEntity, Long> service = new EntityService<>();
                    Session session = service.getEntity().getSessionFactory().openSession();
                    Map<String, Long> keys = new HashMap<>();
                    keys.put("nomenklEntityByNomId", selectedLot.getRefNomenklEntityById().getId());
                    keys.put("storageEntityById", 14L);
                    session.beginTransaction();
                    TableCurrentRestStuffDocEntity reserveRest = (TableCurrentRestStuffDocEntity) service.readRow(new TableCurrentRestStuffDocEntity(), keys);
                    float price = reserveRest.getSum() / reserveRest.getQty();
                    reserveRest.setQty(reserveRest.getQty() - selectedLot.getStartCount());
                    reserveRest.setSum(reserveRest.getSum() - selectedLot.getStartCount()*price);
                    keys.replace("storageEntityById", 14L, 1L);
                    TableCurrentRestStuffDocEntity mainRest = (TableCurrentRestStuffDocEntity) service.readRow(new TableCurrentRestStuffDocEntity(), keys);
                    mainRest.setQty(mainRest.getQty() + selectedLot.getStartCount());
                    mainRest.setSum(mainRest.getSum() + selectedLot.getStartCount()*price);
                    session.saveOrUpdate(reserveRest);
                    session.saveOrUpdate(mainRest);
                    service.delete(selectedLot);
                    session.getTransaction().commit();
                    session.close();
                    getEntityTable().getItems().remove(selectedLot);
                } catch (Exception e) {
                    Alert exAlert = new Alert(Alert.AlertType.ERROR);
                    exAlert.setTitle("Error!");
                    exAlert.setHeaderText("Object couldn't be deleted");
                    exAlert.setContentText("Object can't be deleted because others objects\n\n have references to it.");
                    exAlert.showAndWait();
                }
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getFarm().getPrimaryStage());
            alert.setTitle("No Selection or Not editable Lot");
            alert.setHeaderText("No editable "+"Lots"+" Selected");
            alert.setContentText("Please select other "+" lot"+" in the table for deleting");
            alert.showAndWait();
        }
    }
    @FXML
    public void handleOkFix() {
        LotsEntity selectedLot = (LotsEntity) getEntityTable().getSelectionModel().getSelectedItem();
        if (selectedLot != null && selectedLot.getEditable()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Fix " + selectedLot.getName());
            alert.setHeaderText(selectedLot.getName() + " will be fixed and you can't to change it again");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    EntityService<SuperEntity, Long> service = new EntityService<>();
                    Session session = service.getEntity().getSessionFactory().openSession();
                    Map<String, Long> keys = new HashMap<>();
                    keys.put("nomenklEntityByNomId", selectedLot.getRefNomenklEntityById().getId());
                    keys.put("storageEntityById", 14L);
                    session.beginTransaction();
                    //------------------change current rest-------------------------------------------
                    TableCurrentRestStuffDocEntity reserveRest = (TableCurrentRestStuffDocEntity) service.readRow(new TableCurrentRestStuffDocEntity(), keys);
                    keys.replace("storageEntityById",14L,1L);
                    TableCurrentRestStuffDocEntity mainRest = (TableCurrentRestStuffDocEntity) service.readRow(new TableCurrentRestStuffDocEntity(), keys);
                    RefTypeDocDocEntity typeDoc = session.load(RefTypeDocDocEntity.class, 4L);
                    float price = reserveRest.getSum() / reserveRest.getQty();
                    reserveRest.setQty(reserveRest.getQty() - selectedLot.getStartCount());
                    reserveRest.setSum(reserveRest.getSum() - selectedLot.getStartCount()*price);
                    //----------create Doc document and add product to table docs------------------------------
                    DocDocsHeadDocEntity docHead = new DocDocsHeadDocEntity();
                    docHead.setRefContragentEntityByContragentId(null);
                    docHead.setStorageInById(null);
                    docHead.setStorageOutById(mainRest.getStorageEntityById());
                    docHead.setDate(LocalDate.now());
                    docHead.setName("C"+selectedLot.getName());
                    docHead.setRefTypeDocByTypeDocId(typeDoc);
                    docHead.setRefKindDocByKindDocId(null);
                    docHead.setEditable(false);
                    docHead.setSum(selectedLot.getStartCount()*price);
                    docHead.setVat(0F);
                    docHead.setSum_vat(0F);
                    TableDocsStuffDocEntity tableDocsStuffDocEntity = new TableDocsStuffDocEntity();
                    tableDocsStuffDocEntity.setNomenklEntityByNomId(selectedLot.getRefNomenklEntityById());
                    tableDocsStuffDocEntity.setQty(selectedLot.getStartCount());
                    tableDocsStuffDocEntity.setSum(selectedLot.getStartCount()*price);
                    docHead.addProduct(tableDocsStuffDocEntity);
                    //------------create table lots and add lot to table and to doc-------------------------
                    TableDocLotsDocEntity tableDocLotsDocEntity = new TableDocLotsDocEntity();
                    tableDocLotsDocEntity.setNomenklEntityByNomId(selectedLot.getRefNomenklEntityById());
                    tableDocLotsDocEntity.setQty(selectedLot.getStartCount());
                    tableDocLotsDocEntity.setSum(selectedLot.getStartCount()*price);
                    //----------changing lot's parameters----------------------------------
                    selectedLot.setEditable(false);
                    selectedLot.setCurrentAge(selectedLot.getStartAge());
                    selectedLot.setCurrentCount(selectedLot.getStartCount());
                    selectedLot.setCurrentWeight(selectedLot.getStartWeight());
                    //----------------add changing to journal for history------------------------------
                    JournalOperationsStaffDocEntity journalOperationsStaff = new JournalOperationsStaffDocEntity();
                    journalOperationsStaff.setRecTime(new Date(System.currentTimeMillis()));
                    journalOperationsStaff.setQty(mainRest.getQty() + reserveRest.getQty());
                    journalOperationsStaff.setSum(mainRest.getSum() + reserveRest.getSum());
                    journalOperationsStaff.setNomenklEntityById(selectedLot.getRefNomenklEntityById());
                    journalOperationsStaff.setStorageEntityById(mainRest.getStorageEntityById());
                    journalOperationsStaff.setDocDocsHeadByDocId(docHead);
                    //----------- create joutnal Lot operations --------------------------------------
                    RefTypeOperationsDocEntity typeOperation = session.load(RefTypeOperationsDocEntity.class, 6L);
                    JournalOperationsLotsDocEntity journalOperationLots = new JournalOperationsLotsDocEntity();
                    journalOperationLots.setSum(selectedLot.getStartCount()*price);
                    journalOperationLots.setRefLotsId(selectedLot);
                    journalOperationLots.setRecTime(new Date(System.currentTimeMillis()));
                    journalOperationLots.addProduct(tableDocLotsDocEntity);
                    docHead.addJournalLotOperation(journalOperationLots);
                    typeOperation.addJournalLot(journalOperationLots);
                    typeOperation.addJournal(journalOperationsStaff);
                    session.saveOrUpdate(selectedLot);
                    session.saveOrUpdate(reserveRest);
                    session.saveOrUpdate(docHead);
                    session.saveOrUpdate(journalOperationsStaff);
                    session.getTransaction().commit();
                    logger.info("COMMMIT");
                    session.close();
                } catch (Exception e) {
                    logger.info("Exception");
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setTitle("Error!!! " + selectedLot.getName());
                    alertError.setHeaderText(selectedLot.getName() + " Something being wrong");
                    alertError.setContentText("try again.");
                }
                getEntityTable().refresh();
            }
        }
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
