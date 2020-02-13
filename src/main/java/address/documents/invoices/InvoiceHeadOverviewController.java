package address.documents.invoices;

/*import address.mains.ControllerReference;
import address.mains.FarmFX;
import address.mains.SuperEntityController;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import models.ContragentEntity;

import java.time.LocalDate;

public class InvoiceHeadOverviewController extends SuperEntityController {
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, ContragentEntity> contragent;
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, DocTypeInvoiceDocEntity> type;
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, DocStatusInvoiceDocEntity> status;
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, Float> sum;
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, String> number;
    @FXML
    private TableColumn<DocInvoiceHeadDocEntity, LocalDate> date;

    public InvoiceHeadOverviewController() {}
    @FXML @Override
    protected void initialize() {
        super.initialize();
        contragent.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefContragentEntityByContragentId()));
        type.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getDocTypeInvoiceByTypeInvoiceId()));
        status.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefStatusInvoiceByStatusId()));
        sum.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSum()));
        date.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getDate()));
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/invoice_table.fxml");
        getEntities().addAll(farm.getReferences().getInvoiceData());
        super.setFarmFX(farm);
    }
}*/
