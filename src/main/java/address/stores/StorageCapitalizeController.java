package address.stores;

import address.documents.capitalize.DocDocsHeadDocEntity;
import address.documents.capitalize.RefTypeDocDocEntity;
import address.documents.capitalize.TableDocsStuffDocEntity;
import address.documents.invoices.DocInvoiceHeadDocEntity;
import address.documents.invoices.TableInvoiceNomDocEntity;
import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import models.StorageEntity;
import models.SuperEntity;
import services.EntityService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StorageCapitalizeController extends StorageOverviewController {
    private DocInvoiceHeadDocEntity selectedInvoice;
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity selectedCity) {
        selectedInvoice = (DocInvoiceHeadDocEntity) selectedCity;
        getEntities().addAll(farm.getReferences().getStorageData());
        super.setFarmFX(farm, selectedInvoice);
    }
    @FXML
    public void handleOkCapitalize() {
        List<TableDocsStuffDocEntity> tableDocEntities = new ArrayList<>();
        getFarm().getReferences().getTableInvoiceData().clear();
        getFarm().getReferences().setTableInvoiceData(new FactoryListEntities<TableInvoiceNomDocEntity>(new TableInvoiceNomDocEntity()).getSomeListEntities(selectedInvoice.getId()));
        StorageEntity selectedStore = (StorageEntity) getEntityTable().getSelectionModel().getSelectedItem();
        EntityService<SuperEntity, Long> service = new EntityService<>();
        if (selectedStore != null) {
            DocDocsHeadDocEntity docEntity = new DocDocsHeadDocEntity();
            docEntity.setRefContragentEntityByContragentId(selectedInvoice.getRefContragentEntityByContragentId());
            docEntity.setStorageInById(selectedStore);
            docEntity.setStorageOutById(null);
            docEntity.setDate(LocalDate.now());
            docEntity.setName("P" + selectedInvoice.getId());
            docEntity.setSum(selectedInvoice.getSum());
            docEntity.setVat(selectedInvoice.getVat());
            docEntity.setSum_vat(selectedInvoice.getSum_vat());
            getFarm().getReferences().getTypeDocDocEntities().clear();
            getFarm().getReferences().getKindDocDocEntities().clear();
            docEntity.setRefTypeDocByTypeDocId((RefTypeDocDocEntity) service.read(new RefTypeDocDocEntity(), 1L));
            docEntity.setRefKindDocByKindDocId(null);
            docEntity.setEditable(false);
            for (TableInvoiceNomDocEntity tableInv: getFarm().getReferences().getTableInvoiceData()) {
                TableDocsStuffDocEntity tableDoc = new TableDocsStuffDocEntity();
                tableDoc.setDocDocsHeadByDocId(docEntity);
                tableDoc.setNomenklEntityByNomId(tableInv.getNomenklEntityByNomId());
                tableDoc.setQty(tableInv.getQty());
                tableDoc.setSum(tableInv.getSum());
                tableDocEntities.add(tableDoc);
            }
            docEntity.setTableDocsStuffsById(tableDocEntities);
            service.create(docEntity);
            selectedInvoice.setEditable(false);
            service.update(selectedInvoice);
            getFarm().getConfigDialogController().getInvoiceHeadOverviewController().getEntityTable().refresh();
            getReferenceStage().close();
        } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(getFarm().getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No one element Selected");
                alert.setContentText("Please select an element in the table");
                alert.showAndWait();
        }
    }
}
