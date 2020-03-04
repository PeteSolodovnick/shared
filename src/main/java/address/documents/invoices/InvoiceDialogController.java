package address.documents.invoices;

import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import models.*;
import services.EntityService;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDialogController extends SuperDialogEntityController {
    @FXML
    private TableView<TableInvoiceNomDocEntity> entityTable;
    @FXML
    private DatePicker date;
    @FXML
    private TextField contragent;
    @FXML
    private TextField sumInv;
    @FXML
    private TextField sumVatInv;
    @FXML
    private TextField vat;
    @FXML
    private TextField sum_vat;
    @FXML
    private TableColumn<TableInvoiceNomDocEntity, ClassificationEntity> classification;
    @FXML
    private TableColumn<TableInvoiceNomDocEntity, Long> id;
    @FXML
    private TableColumn<TableInvoiceNomDocEntity, NomenklEntity> nameNomenkl;
    @FXML
    private TableColumn<TableInvoiceNomDocEntity, SizeEntity> size;
    @FXML
    private TableColumn<TableInvoiceNomDocEntity, Float> sum;
    @FXML
    private TableColumn<TableInvoiceNomDocEntity, Float> vatS;
    @FXML
    private TableColumn<TableInvoiceNomDocEntity, Float> sumVat;
    @FXML
    private TableColumn<TableInvoiceNomDocEntity, Integer> qty;

    private ContragentEntity newContragentEntity;
    private DocInvoiceHeadDocEntity newInvoice;
    private DocStatusInvoiceDocEntity statusInvoiceDocEntity;
    private NomenklEntity newNomenklEntity;
    private SizeEntity newSizeEntity;
    private ClassificationEntity newClassificationEntity;
    private String fileContragent;
    private String fileNomenkl;
    private List<TableInvoiceNomDocEntity> forDelete = new ArrayList<>();

    public InvoiceDialogController() {}
    @FXML
    protected void initialize() {
        entityTable.setEditable(true);
        id.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getNomenklEntityByNomId().getId()));
        nameNomenkl.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getNomenklEntityByNomId()));
        classification.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getNomenklEntityByNomId().getRefClassificationByClassificationId()));
        size.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getNomenklEntityByNomId().getRefSizeBySizeId()));
        qty.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getQty()));
        qty.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        qty.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TableInvoiceNomDocEntity, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TableInvoiceNomDocEntity, Integer> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setQty(t.getNewValue());
            }
        });
        sum.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSum()));
        sum.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        sum.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TableInvoiceNomDocEntity, Float>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TableInvoiceNomDocEntity, Float> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setSum(t.getNewValue());
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setVat((float) (t.getTableView().getItems().get(t.getTablePosition().getRow()).getSum()*FarmFX.vat/100));
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setSum_vat((float) (t.getTableView().getItems().get(t.getTablePosition().getRow()).getSum()*(1 + FarmFX.vat/100)));
                double itemsSum = 0;
                for (int i = 0; i < entityTable.getItems().size(); i++) {
                    itemsSum = itemsSum + entityTable.getItems().get(i).getSum();
                }
                sumInv.setText(String.valueOf(itemsSum));
                sumVatInv.setText(String.valueOf(itemsSum*(1 + FarmFX.vat/100)));
                sum_vat.setText(String.valueOf(itemsSum*FarmFX.vat/100));
                entityTable.refresh();
            }
        });
        vatS.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSum()*FarmFX.vat/100));
        sumVat.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSum()*(1 + FarmFX.vat/100)));
    }
 @Override
    public void setFarmFX(FarmFX farm, SuperEntity selectedInvoice) {
        logger.info("in set table invoice");
        fileContragent = "/contragentViewer.fxml";
        fileNomenkl = "/nomenklViewer.fxml";
        vat.setText(String.valueOf(FarmFX.vat));
        statusInvoiceDocEntity = farm.getReferences().getStatusInvoiceData().get(1);
        newInvoice = (DocInvoiceHeadDocEntity) selectedInvoice;
        if (selectedInvoice != null) {
            farm.getReferences().getTableInvoiceData().clear();
            farm.getReferences().setTableInvoiceData(new FactoryListEntities<TableInvoiceNomDocEntity>(new TableInvoiceNomDocEntity()).getSomeListEntities(newInvoice.getId()));
            entityTable.setItems(farm.getReferences().getTableInvoiceData());
            date.setValue(newInvoice.getDate());
            contragent.setText(newInvoice.getRefContragentEntityByContragentId().getName());
            sumInv.setText(String.valueOf(newInvoice.getSum()));
            sumVatInv.setText(String.valueOf(newInvoice.getSum()*(1 + FarmFX.vat/100)));
            sum_vat.setText(String.valueOf(newInvoice.getSum()*FarmFX.vat/100));
            newContragentEntity = newInvoice.getRefContragentEntityByContragentId();
            setNew(false);
        } else {
            farm.getReferences().getTableInvoiceData().clear();
            newInvoice = new DocInvoiceHeadDocEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newInvoice);
    }
    @Override
    protected boolean isInputValid() {
        if (super.isInputValid()) {
            String errorMessage = "";
            if (date.getValue() == null) {
                errorMessage += "No valid date\n";
            }
            if (contragent.getText() == null || contragent.getText().length() == 0) {
                errorMessage += "No valid contragent\n";
            }
            if (sumInv.getText() == null || sumInv.getText().length() == 0) {
                errorMessage += "No valid sum\n";
            }
            if (entityTable.getItems().size() == 0) {
                errorMessage += "there are no elements in table\n";
            }
            if (isError(errorMessage))
                return false;
            return true;
        }
        return false;
    }
    @Override
    protected void createEntity() {
        super.createEntity();
        newInvoice.setRefContragentEntityByContragentId(getNewContragentEntity());
        newInvoice.setDate(date.getValue());
        newInvoice.setSum(Float.parseFloat(sumInv.getText()));
        newInvoice.setTableInvoiceNomById(entityTable.getItems());
        for (TableInvoiceNomDocEntity tableInv:entityTable.getItems()) {
            logger.info(tableInv);
        }
        logger.info("adding");
        if (isNew()) {
            newInvoice.setRefStatusInvoiceByStatusId(statusInvoiceDocEntity);
            newInvoice.setEditable(true);
        } else {
            for (TableInvoiceNomDocEntity tableInv: forDelete) {
                EntityService<TableInvoiceNomDocEntity, Long> service = new EntityService<>();
                service.delete(tableInv);
            }
        }
        setNewEntity(newInvoice);
    }
    @FXML
    private void handleContragentChoose() {
        getFarm().getConfigDialogController().setInvoiceDialogController(this);
        getFarm().showEntityOverview(fileContragent);
    }
    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }
    @FXML
    public void handleAddProduct() {
        getFarm().getConfigDialogController().setInvoiceDialogController(this);
        getFarm().showEntityOverview(fileNomenkl);
    }
    @FXML
    public void handleMinusProduct() {
        TableInvoiceNomDocEntity selectedEntity = entityTable.getSelectionModel().getSelectedItem();
        if (selectedEntity != null) {
            forDelete.add(selectedEntity);
            getFarm().getReferences().getTableInvoiceData().remove(selectedEntity);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getFarm().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No one element Selected");
            alert.setContentText("Please select an element in the table for seeing information about it");
            alert.showAndWait();
        }
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getInvoiceData().add(getNewInvoice());
        getFarm().getConfigDialogController().getInvoiceHeadOverviewController().getEntities().add(getNewInvoice());
    }

    @Override
    public void editEntity(SuperEntity newEntity) {

    }

    public ContragentEntity getNewContragentEntity() {
        return newContragentEntity;
    }

    public void setNewContragentEntity(ContragentEntity newContragentEntity) {
        this.newContragentEntity = newContragentEntity;
    }

    public DocInvoiceHeadDocEntity getNewInvoice() {
        return newInvoice;
    }

    public TableView<TableInvoiceNomDocEntity> getEntityTable() {
        return entityTable;
    }

    public void setEntityTable(TableView<TableInvoiceNomDocEntity> entityTable) {
        this.entityTable = entityTable;
    }

    public void setNewInvoice(DocInvoiceHeadDocEntity newInvoice) {
        this.newInvoice = newInvoice;
    }

    public TextField getContragent() {
        return contragent;
    }

    public void setContragent(TextField contragent) {
        this.contragent = contragent;
    }
}
