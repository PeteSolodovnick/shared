package address.documents.invoices;

import address.documents.capitalize.DocDocsHeadDocEntity;
import address.documents.capitalize.RefTypeDocDocEntity;
import address.documents.capitalize.TableDocsStuffDocEntity;
import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import address.mains.SuperEntityController;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import models.ContragentEntity;
import models.SuperEntity;
import services.EntityService;

import java.time.LocalDate;

public class InvoiceHeadOverviewController extends SuperEntityController {
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, ContragentEntity> contragent;
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, DocStatusInvoiceDocEntity> status;
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, Float> sum;
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, Float> sumVat;
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, Boolean> editable;
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, LocalDate> date;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button capitalize;

    private String fileStore;

    public InvoiceHeadOverviewController() {}

    @FXML @Override
    protected void initialize() {
        super.initialize();
        contragent.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefContragentEntityByContragentId()));
        status.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefStatusInvoiceByStatusId()));
        sum.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSum()));
        sumVat.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSum()*(FarmFX.vat/100+1)));
        date.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getDate()));
        editable.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getEditable()));
        getEntityTable().getSelectionModel().getSelectedCells().addListener((InvalidationListener) observable -> showButton());
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/invoice_table.fxml");
        setFileInfo("/invoice_table_info.fxml");
    //    getEntities().clear();
        getEntities().addAll(farm.getReferences().getInvoiceData());
        super.setFarmFX(farm);
    }
    public void showButton() {
        DocInvoiceHeadDocEntity selectedInvoice = (DocInvoiceHeadDocEntity) getEntityTable().getSelectionModel().getSelectedItem();
        if (!selectedInvoice.getEditable()) {
            capitalize.setDisable(true);
        } else capitalize.setDisable(false);
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setInvoiceHeadOverviewController(this);
        setTitle("New Invoice...");
        super.handleNewEntity();
    }
    @Override
    public void handleEditEntity() {
        DocInvoiceHeadDocEntity selectedInvoice = (DocInvoiceHeadDocEntity) getEntityTable().getSelectionModel().getSelectedItem();
        System.out.println(selectedInvoice.getTableInvoiceNomById().size());
        if (selectedInvoice.getEditable()) {
            getFarm().getConfigDialogController().setInvoiceHeadOverviewController(this);
            setTitle("Edit Invoice...");
            super.handleEditEntity();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getFarm().getPrimaryStage());
            alert.setTitle("Sorry,");
            alert.setHeaderText("You can't edit this document");
            alert.setContentText("Please select an other element in the table for editing");
            alert.showAndWait();
        }
    }
    @Override
    public void handleDeleteEntity() {
        DocInvoiceHeadDocEntity selectedInvoice = (DocInvoiceHeadDocEntity) getEntityTable().getSelectionModel().getSelectedItem();
        if (selectedInvoice.getEditable()) {
            super.handleDeleteEntity();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getFarm().getPrimaryStage());
            alert.setTitle("Sorry,");
            alert.setHeaderText("You can't delete this document");
            alert.setContentText("Please select an other element in the table for editing");
            alert.showAndWait();
        }

    }
    @Override
    public void handleInfoEntity() {
        getFarm().getConfigDialogController().setInvoiceHeadOverviewController(this);
        setTitle("Full Invoice Information");
        super.handleInfoEntity();
    }
    public void handleCapitalize() {
        fileStore = "/storageViewer.fxml";
        DocInvoiceHeadDocEntity selectedInvoice = (DocInvoiceHeadDocEntity) getEntityTable().getSelectionModel().getSelectedItem();
        if (selectedInvoice != null) {
            if (selectedInvoice.getEditable()) {
                getFarm().getConfigDialogController().setInvoiceHeadOverviewController(this);
                setTitle("Choose store for capitalize...");
                getFarm().showEntityOverview(selectedInvoice, getReferenceStage(), fileStore, "Choose store");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(getFarm().getPrimaryStage());
                alert.setTitle("Sorry,");
                alert.setHeaderText("You can't edit this document");
                alert.setContentText("Please select an other element in the table for editing");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getFarm().getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No one element Selected");
            alert.setContentText("Please select an element in the table for editing");
            alert.showAndWait();
        }
    }
    @Override
    public void deletedFromArray(SuperEntity selectedEntity) {
        getFarm().getReferences().getInvoiceData().remove(selectedEntity);
    }
    @FXML
    public void handleFilter() {
        getFarm().getReferences().getInvoiceData().clear();
        getEntities().clear();
        getFarm().getReferences().setInvoiceData(new FactoryListEntities<>(new DocInvoiceHeadDocEntity()).getSetDateListEntities(startDate.getValue(), endDate.getValue()));
        getEntities().addAll(getFarm().getReferences().getInvoiceData());
    }
}
