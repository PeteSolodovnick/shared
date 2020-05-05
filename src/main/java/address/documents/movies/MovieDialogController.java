package address.documents.movies;

import address.mains.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.SuperEntity;
import models.documents.DocDocsHeadDocEntity;
import models.documents.DocInvoiceHeadDocEntity;
import models.references.*;
import models.tables.JournalOperationsStaffDocEntity;
import models.tables.TableCurrentRestStuffDocEntity;
import models.tables.TableDocsStuffDocEntity;
import org.hibernate.Session;
import services.EntityService;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDialogController extends SuperDialogEntityController {
    @FXML
    private TableView<TableDocsStuffDocEntity> entityTable;
    @FXML
    private TableView<TableCurrentRestStuffDocEntity> restEntityTable;
    @FXML
    private DatePicker date;
    @FXML
    private TextField storeIn;
    @FXML
    private TextField storeOut;
    @FXML
    private Button btnStoreOut;
    @FXML
    private TableColumn<TableDocsStuffDocEntity, Long> id;
    @FXML
    private TableColumn<TableDocsStuffDocEntity, NomenklEntity> nameNomenkl;
    @FXML
    private TableColumn<TableDocsStuffDocEntity, ClassificationEntity> classification;
    @FXML
    private TableColumn<TableDocsStuffDocEntity, SizeEntity> size;
    @FXML
    private TableColumn<TableDocsStuffDocEntity, Integer> qty;
    @FXML
    private TableColumn<TableCurrentRestStuffDocEntity, Integer> restQty;
    @FXML
    private TableColumn<TableCurrentRestStuffDocEntity, Float> restSum;

    private FarmFX farm;
    private DocDocsHeadDocEntity newDoc;
    private StorageEntity store;
    private StorageEntity storeInSelect;
    private StorageEntity storeOutSelect;
    private String fileStore;
    private String fileNomenkl;
    private boolean in;
    private List<TableCurrentRestStuffDocEntity> forDelete = new ArrayList<>();

    public MovieDialogController() {}
    @FXML
    protected void initialize() {
        entityTable.setEditable(true);
        id.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getNomenklEntityByNomId().getId()));
        nameNomenkl.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getNomenklEntityByNomId()));
        classification.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getNomenklEntityByNomId().getRefClassificationByClassificationId()));
        size.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getNomenklEntityByNomId().getRefSizeBySizeId()));
        qty.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getQty()));
        qty.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter(){
            @Override
            public Integer fromString(String s) {
                try {
                    Integer result = Integer.parseInt(s);
                    return result;
                } catch (NumberFormatException e) {
                    String title = "Error!!!";
                    String headerText = "Element isn't integer number";
                    String content = "Please input an integer number";
                    viewError(title,headerText,content);
                    return 0;
                }
            }
        }));
        qty.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TableDocsStuffDocEntity, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TableDocsStuffDocEntity, Integer> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setQty(t.getNewValue());
            }
        });
        restQty.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getQty()));
        restSum.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSum()));
    }
    @Override
    public void setFarmFX(FarmFX farm, SuperReferenceEntity selectedDoc) {
        fileStore = "/storageSelectViewer.fxml";
        fileNomenkl = "/nomenklViewerStore.fxml";
        this.farm = farm;
        newDoc = (DocDocsHeadDocEntity) selectedDoc;
        if (selectedDoc != null) {
            farm.getReferences().setTableDocData(new FactoryListEntities<TableDocsStuffDocEntity>(new TableDocsStuffDocEntity()).getSomeListEntities(newDoc.getId(), "docDocsHeadByDocId"));
            entityTable.setItems(farm.getReferences().getTableDocData());
            date.setValue(newDoc.getDate());
            storeOut.setText(newDoc.getStorageOutById().getName());
            storeIn.setText(newDoc.getStorageInById().getName());
            setNew(false);
        } else {
            farm.getReferences().getTableDocData().clear();
            newDoc = new DocDocsHeadDocEntity();
            setNew(true);
            this.in = false;
            this.storeOutSelect = null;
            this.storeInSelect = null;
        }
        super.setFarmFX(farm,newDoc);
    }
    private void viewError(String title, String headerText, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(getFarm().getPrimaryStage());
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @Override
    protected boolean isInputValid() {
        if (super.isInputValid()) {
            String errorMessage = "";
            if (date.getValue() == null) {
                errorMessage += "No valid Date\n";
            }
            if (storeInSelect == null || storeOutSelect == null) {
                errorMessage += "No valid Store";
            }
            if (entityTable.getItems().size() <= 0) {
                errorMessage += "there are no elements in table\n";
            }
            for (TableCurrentRestStuffDocEntity currentRest:restEntityTable.getItems()) {
                int index = restEntityTable.getItems().indexOf(currentRest);
                if (entityTable.getItems().get(index).getQty()>currentRest.getQty() || entityTable.getItems().get(index).getQty() ==0) {
                    errorMessage += "incorrect qty in table\n";
                }
            }
            if (isError(errorMessage)) {
                return false;
            }
            return true;
        }
        return false;
    }
    @FXML
    public void chooseStoreIn() {
        in = true;
        getFarm().getConfigDialogController().setMovieDialogController(this);
        getFarm().showEntityOverview(fileStore);
    }
    @FXML
    public void chooseStoreOut() {
        in = false;
        getFarm().getConfigDialogController().setMovieDialogController(this);
        getFarm().showEntityOverview(fileStore);
    }
    @FXML
    public void handleAddProduct() {
        if (storeOutSelect != null) {
            getFarm().getConfigDialogController().setMovieDialogController(this);
            getFarm().showEntityOverview(fileNomenkl);
        } else {
            viewError("Error!!!","No one Store out selected","Please choose store out before");
        }
    }
    @FXML
    public void handleMinusProduct() {
        TableDocsStuffDocEntity selectedEntity = entityTable.getSelectionModel().getSelectedItem();
        if (selectedEntity != null) {
            int i = entityTable.getSelectionModel().getSelectedIndex();
            entityTable.getItems().remove(selectedEntity);
            restEntityTable.getItems().remove(i);
        }
        if (entityTable.getItems().size() == 0) {
            btnStoreOut.setDisable(false);
            storeOut.setEditable(true);
        }
    }
    @FXML
    public void handleOk() {
        if (isInputValid()) {
            EntityService<SuperEntity, Long> service = new EntityService<>();
            Session session = service.getEntity().getSessionFactory().openSession();
            storeOutSelect = session.load(StorageEntity.class, storeOutSelect.getId());
            storeInSelect = session.load(StorageEntity.class, storeInSelect.getId());
            RefTypeOperationsDocEntity typeOperation = session.load(RefTypeOperationsDocEntity.class, 2L);
            RefTypeDocDocEntity typeDocDocEntity = session.load(RefTypeDocDocEntity.class, 2L);
            session.beginTransaction();
         //   DocDocsHeadDocEntity docEntity = new DocDocsHeadDocEntity();
            newDoc.setRefContragentEntityByContragentId(null);
            newDoc.setStorageInById(storeInSelect);
            newDoc.setStorageOutById(storeOutSelect);
            newDoc.setDate(date.getValue());
            newDoc.setName("M" + getName().getText());
            newDoc.setRefTypeDocByTypeDocId(typeDocDocEntity);
            newDoc.setRefKindDocByKindDocId(null);
            newDoc.setEditable(false);
            float sumMoving = 0;
            for (TableCurrentRestStuffDocEntity restCurrent : restEntityTable.getItems()) {
                restCurrent = session.load(TableCurrentRestStuffDocEntity.class, restCurrent.getId());
                float price = restCurrent.getSum() / restCurrent.getQty();
                int index = restEntityTable.getItems().indexOf(restCurrent);
                sumMoving = sumMoving + qty.getCellData(index) * price;
                System.out.println("price = " + price);
                TableDocsStuffDocEntity tableDoc = new TableDocsStuffDocEntity();
                tableDoc.setNomenklEntityByNomId(restCurrent.getNomenklEntityByNomId());
                tableDoc.setQty(qty.getCellData(index));
                tableDoc.setSum(qty.getCellData(index) * price);
                newDoc.addProduct(tableDoc);
                Map<String, Long> keys = new HashMap<>();
                keys.put("nomenklEntityByNomId", restCurrent.getNomenklEntityByNomId().getId());
                keys.put("storageEntityById", storeInSelect.getId());
                restCurrent.setQty(restCurrent.getQty() - qty.getCellData(index));
                restCurrent.setSum(restCurrent.getSum() - restCurrent.getQty() * price);
                TableCurrentRestStuffDocEntity currentRestStuffDocEntity = new TableCurrentRestStuffDocEntity();
                try {
                    currentRestStuffDocEntity = (TableCurrentRestStuffDocEntity) service.readRow(new TableCurrentRestStuffDocEntity(), keys);
                    if (currentRestStuffDocEntity != null) {
                        currentRestStuffDocEntity.setQty(currentRestStuffDocEntity.getQty() + qty.getCellData(index));
                        currentRestStuffDocEntity.setSum(currentRestStuffDocEntity.getSum() + qty.getCellData(index) * price);
                    } else {
                        currentRestStuffDocEntity.setStorageEntityById(storeInSelect);
                        currentRestStuffDocEntity.setNomenklEntityByNomId(restCurrent.getNomenklEntityByNomId());
                        currentRestStuffDocEntity.setQty(qty.getCellData(index));
                        currentRestStuffDocEntity.setSum(qty.getCellData(index) * price);
                    }
                } catch (NoResultException e) {
                        currentRestStuffDocEntity.setStorageEntityById(storeInSelect);
                        currentRestStuffDocEntity.setNomenklEntityByNomId(restCurrent.getNomenklEntityByNomId());
                        currentRestStuffDocEntity.setQty(qty.getCellData(index));
                        currentRestStuffDocEntity.setSum(qty.getCellData(index) * price);
                    }
                JournalOperationsStaffDocEntity journalIn = new JournalOperationsStaffDocEntity();
                JournalOperationsStaffDocEntity journalOut = new JournalOperationsStaffDocEntity();
                newDoc.addJournal(journalIn);
                newDoc.addJournal(journalOut);
                storeInSelect.addJournal(journalIn);
                storeOutSelect.addJournal(journalOut);
                restCurrent.getNomenklEntityByNomId().addJournal(journalIn);
                restCurrent.getNomenklEntityByNomId().addJournal(journalOut);
                typeOperation.addJournal(journalIn);
                typeOperation.addJournal(journalOut);
                journalIn.setRecTime(new Date(System.currentTimeMillis()));
                journalOut.setRecTime(new Date(System.currentTimeMillis()));
                journalIn.setQty(currentRestStuffDocEntity.getQty());
                journalIn.setSum(currentRestStuffDocEntity.getSum());
                journalOut.setQty(restCurrent.getQty());
                journalOut.setSum(restCurrent.getSum());
                session.saveOrUpdate(newDoc);
                session.saveOrUpdate(currentRestStuffDocEntity);
                session.save(journalIn);
                session.save(journalOut);
            }
            newDoc.setSum(sumMoving);
            newDoc.setVat(0f);
            newDoc.setSum_vat(0f);
            session.getTransaction().commit();
            session.close();
            getDialogStage().close();
            getFarm().getConfigDialogController().getMovieOverviewController().getEntityTable().getItems().add(newDoc);
            getFarm().getConfigDialogController().getMovieOverviewController().getEntityTable().refresh();
        } else {
            viewError("Error","Something done wrong!!!","Please check in all entered fields");
        }
    }
    @Override
    public void newEntity() {
        getFarm().getReferences().getDocsData().add(newDoc);
        getFarm().getConfigDialogController().getMovieOverviewController().getEntities().add(newDoc);
    }

    @Override
    public void editEntity(SuperReferenceEntity newEntity) {

    }

    public StorageEntity getStore() {
        return store;
    }

    public void setStore(StorageEntity store) {
        this.store = store;
    }

    public StorageEntity getStoreInSelect() {
        return storeInSelect;
    }

    public void setStoreInSelect(StorageEntity storeInSelect) {
        this.storeInSelect = storeInSelect;
    }

    public TextField getStoreIn() {
        return storeIn;
    }

    public TextField getStoreOut() {
        return storeOut;
    }

    public boolean isIn() {
        return in;
    }

    public StorageEntity getStoreOutSelect() {
        return storeOutSelect;
    }

    public void setStoreOutSelect(StorageEntity storeOutSelect) {
        this.storeOutSelect = storeOutSelect;
    }

    public TableView<TableDocsStuffDocEntity> getEntityTable() {
        return entityTable;
    }

    public void setEntityTable(TableView<TableDocsStuffDocEntity> entityTable) {
        this.entityTable = entityTable;
    }

    public Button getBtnStoreOut() {
        return btnStoreOut;
    }

    public void setBtnStoreOut(Button btnStoreOut) {
        this.btnStoreOut = btnStoreOut;
    }

    public DocDocsHeadDocEntity getNewDoc() {
        return newDoc;
    }

    public void setNewDoc(DocDocsHeadDocEntity newDoc) {
        this.newDoc = newDoc;
    }

    public TableView<TableCurrentRestStuffDocEntity> getRestEntityTable() {
        return restEntityTable;
    }

    public void setRestEntityTable(TableView<TableCurrentRestStuffDocEntity> restEntityTable) {
        this.restEntityTable = restEntityTable;
    }

}
