package address.documents.invoices;

import address.classifications.ProductsOverviewController;
import javafx.fxml.FXML;
import models.NomenklEntity;

public class TableNomDocController extends ProductsOverviewController {
    private int countClick = 0;
    public TableNomDocController() {}
    @FXML
    protected void handleChooseTer() {
        countClick++;
        if (countClick == 2) {
            setTextEdit();
            getReferenceStage().close();
        }
    }
    public void setTextEdit(){
        TableInvoiceNomDocEntity invoiceNomDocEntity = new TableInvoiceNomDocEntity();;
        invoiceNomDocEntity.setDocInvoiceHeadByInvId(getFarm().getConfigDialogController().getInvoiceDialogController().getNewInvoice());
        invoiceNomDocEntity.setNomenklEntityByNomId((NomenklEntity) getEntityTable().getSelectionModel().getSelectedItem());
        invoiceNomDocEntity.setQty(0);
        invoiceNomDocEntity.setSum(0.00f);
        getFarm().getReferences().getTableInvoiceData().add(invoiceNomDocEntity);
        getFarm().getConfigDialogController().getInvoiceDialogController().getEntityTable().setItems(getFarm().getReferences().getTableInvoiceData());
    }

    @FXML
    public void handleMouseMoved() {
        countClick = 0;
    }


}
