package address.documents.invoices;

import address.contragents.ContragentOverviewController;
import javafx.fxml.FXML;
import models.references.ContragentEntity;

public class TableContrDocController extends ContragentOverviewController {
    private int countClick = 0;
    public TableContrDocController() {}
    @FXML
    protected void handleChooseTer() {
        countClick++;
        if (countClick == 2) {
            setTextEdit();
            getReferenceStage().close();
        }
    }
    public void setTextEdit(){
        getFarm().getConfigDialogController().getInvoiceDialogController().setNewContragentEntity((ContragentEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getInvoiceDialogController().getContragent().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }

    @FXML
    public void handleMouseMoved() {
        countClick = 0;
    }
}
