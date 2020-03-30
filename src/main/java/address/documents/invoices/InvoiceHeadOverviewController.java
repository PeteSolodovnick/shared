package address.documents.invoices;

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
import models.documents.DocInvoiceHeadDocEntity;
import models.references.RefStatusInvoiceDocEntity;
import models.references.ContragentEntity;
import models.SuperEntity;
import models.references.SuperReferenceEntity;

import java.time.LocalDate;

public class InvoiceHeadOverviewController extends SuperEntityController {
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, ContragentEntity> contragent;
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, RefStatusInvoiceDocEntity> status;
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
    private DocInvoiceHeadDocEntity selectedInvoice;

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
        initArrays(farm);
        getEntities().addAll(farm.getReferences().getInvoiceData());
        super.setFarmFX(farm);
    }
    private void initArrays(FarmFX farm) {
        farm.getReferences().setInvoiceData(new FactoryListEntities<>(new DocInvoiceHeadDocEntity()).getDateListEntities());
    }
    public void showButton() {
        setSelectedInvoice((DocInvoiceHeadDocEntity) getEntityTable().getSelectionModel().getSelectedItem());
        if (!getSelectedInvoice().getEditable()) {
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
        setSelectedInvoice((DocInvoiceHeadDocEntity) getEntityTable().getSelectionModel().getSelectedItem());
        if (getSelectedInvoice().getEditable()) {
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
       setSelectedInvoice((DocInvoiceHeadDocEntity) getEntityTable().getSelectionModel().getSelectedItem());
        if (getSelectedInvoice().getEditable()) {
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
        setSelectedInvoice((DocInvoiceHeadDocEntity) getEntityTable().getSelectionModel().getSelectedItem());
        if (getSelectedInvoice() != null) {
            if (getSelectedInvoice().getEditable()) {
                getFarm().getConfigDialogController().setInvoiceHeadOverviewController(this);
                setTitle("Choose store for capitalize...");
                getFarm().showEntityOverview(fileStore);
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
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getInvoiceData().remove(selectedEntity);
    }
    @FXML
    public void handleFilter() {
        getEntities().clear();
        getFarm().getReferences().setInvoiceData(new FactoryListEntities<>(new DocInvoiceHeadDocEntity()).getSetDateListEntities(startDate.getValue(), endDate.getValue()));
        getEntities().addAll(getFarm().getReferences().getInvoiceData());
    }

    public DocInvoiceHeadDocEntity getSelectedInvoice() {
        return selectedInvoice;
    }

    public void setSelectedInvoice(DocInvoiceHeadDocEntity selectedInvoice) {
        this.selectedInvoice = selectedInvoice;
    }
}
