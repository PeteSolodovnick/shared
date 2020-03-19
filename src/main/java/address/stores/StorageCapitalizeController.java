package address.stores;

import models.documents.DocDocsHeadDocEntity;
import models.references.RefTypeDocDocEntity;
import models.references.SuperReferenceEntity;
import models.tables.TableCurrentRestStuffDocEntity;
import models.tables.TableDocsStuffDocEntity;
import models.documents.DocInvoiceHeadDocEntity;
import models.tables.TableInvoiceNomDocEntity;
import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import models.references.StorageEntity;
import models.SuperEntity;
import services.EntityService;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageCapitalizeController extends StorageOverviewController {
    private DocInvoiceHeadDocEntity selectedInvoice;
    @Override
    public void setFarmFX(FarmFX farm, SuperReferenceEntity selectedInvoice) {
        this.selectedInvoice = (DocInvoiceHeadDocEntity) selectedInvoice;
        getEntities().addAll(farm.getReferences().getStorageData());
        super.setFarmFX(farm, selectedInvoice);
    }
    @FXML
    public void handleOkCapitalize() {
        List<TableDocsStuffDocEntity> tableDocEntities = new ArrayList<>();
        getFarm().getReferences().getTableInvoiceData().clear();
        getFarm().getReferences().setTableInvoiceData(new FactoryListEntities<TableInvoiceNomDocEntity>(new TableInvoiceNomDocEntity()).getSomeListEntities(selectedInvoice.getId()));
        StorageEntity selectedStore = (StorageEntity) getEntityTable().getSelectionModel().getSelectedItem();
        EntityService<SuperReferenceEntity, Long> service = new EntityService<>();
        EntityService<TableCurrentRestStuffDocEntity, Long> serviceRest = new EntityService<>();
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
                Map<String, Long> keys = new HashMap<>();
                TableDocsStuffDocEntity tableDoc = new TableDocsStuffDocEntity();
                tableDoc.setDocDocsHeadByDocId(docEntity);
                tableDoc.setNomenklEntityByNomId(tableInv.getNomenklEntityByNomId());
                tableDoc.setQty(tableInv.getQty());
                tableDoc.setSum(tableInv.getSum());
                tableDocEntities.add(tableDoc);
                keys.put("nomenklEntityByNomId", tableInv.getNomenklEntityByNomId().getId());
                keys.put("storageEntityById", selectedStore.getId());
                TableCurrentRestStuffDocEntity currentRestStuffDocEntity = new TableCurrentRestStuffDocEntity();
                try {
                    currentRestStuffDocEntity = serviceRest.readRow(new TableCurrentRestStuffDocEntity(), keys);
                    if (currentRestStuffDocEntity != null) {
                        currentRestStuffDocEntity.setQty(currentRestStuffDocEntity.getQty() + tableInv.getQty());
                        currentRestStuffDocEntity.setSum(currentRestStuffDocEntity.getSum() + tableInv.getSum());
                        serviceRest.update(currentRestStuffDocEntity);
                    } else {
                        currentRestStuffDocEntity.setStorageEntityById(selectedStore);
                        currentRestStuffDocEntity.setNomenklEntityByNomId(tableInv.getNomenklEntityByNomId());
                        currentRestStuffDocEntity.setQty(tableInv.getQty());
                        currentRestStuffDocEntity.setSum(tableInv.getSum());
                        serviceRest.create(currentRestStuffDocEntity);
                    }
                } catch (NoResultException e) {
                    currentRestStuffDocEntity.setStorageEntityById(selectedStore);
                    currentRestStuffDocEntity.setNomenklEntityByNomId(tableInv.getNomenklEntityByNomId());
                    currentRestStuffDocEntity.setQty(tableInv.getQty());
                    currentRestStuffDocEntity.setSum(tableInv.getSum());
                    serviceRest.create(currentRestStuffDocEntity);
                }
            }
            docEntity.setTableDocsStuffsById(tableDocEntities);
            service.create(docEntity);
            selectedInvoice.setEditable(false);
            selectedInvoice.setTableInvoiceNomById(null);
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
