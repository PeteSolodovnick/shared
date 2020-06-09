package address.stores;

import models.tables.JournalOperationsStaffDocEntity;
import models.references.RefTypeOperationsDocEntity;
import models.documents.DocDocsHeadDocEntity;
import models.references.RefTypeDocDocEntity;
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
import org.hibernate.Session;
import services.EntityService;

import javax.persistence.NoResultException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class StorageCapitalizeController extends StorageOverviewController {
    private DocInvoiceHeadDocEntity selectedInvoice;
    private FarmFX farm;
    @Override
    public void setFarmFX(FarmFX farm) {
        this.farm = farm;
        selectedInvoice = farm.getConfigDialogController().getInvoiceHeadOverviewController().getSelectedInvoice();
        initArray(farm);
        getEntities().addAll(farm.getReferences().getStorageData());
        getEntityTable().setItems(getEntities());
        getEntitiesName().setItems(getEntities());
    }
    private void initArray(FarmFX farm){
        ArrayList<Long> keys = new ArrayList<>();
        keys.add(1L);
        keys.add(2L);
        farm.getReferences().setStorageData(new FactoryListEntities<>(new StorageEntity()).getListEntitiesbyId(keys));
        keys.remove(1);
        farm.getReferences().setTypeDocDocEntities(new FactoryListEntities<>(new RefTypeDocDocEntity()).getListEntitiesbyId(keys));

    }
    @FXML
    public void handleOkCapitalize() {
        StorageEntity selectedStore = (StorageEntity) getEntityTable().getSelectionModel().getSelectedItem();
        EntityService<SuperEntity, Long> service = new EntityService<>();
        if (selectedStore != null) {
            Session session = service.getEntity().getSessionFactory().openSession();
            selectedInvoice = session.load(DocInvoiceHeadDocEntity.class, selectedInvoice.getId());
            selectedStore = session.load(StorageEntity.class, selectedStore.getId());
            RefTypeOperationsDocEntity typeOperation = session.load(RefTypeOperationsDocEntity.class, 1L);
            session.beginTransaction();
            DocDocsHeadDocEntity docEntity = new DocDocsHeadDocEntity();
            docEntity.setRefContragentEntityByContragentId(selectedInvoice.getRefContragentEntityByContragentId());
            docEntity.setStorageInById(selectedStore);
            docEntity.setStorageOutById(null);
            docEntity.setDate(LocalDate.now());
            docEntity.setName("P" + selectedInvoice.getId());
            docEntity.setSum(selectedInvoice.getSum());
            docEntity.setVat(selectedInvoice.getVat());
            docEntity.setSum_vat(selectedInvoice.getSum_vat());
            docEntity.setRefTypeDocByTypeDocId(farm.getReferences().getTypeDocDocEntities().get(0));
            docEntity.setRefKindDocByKindDocId(null);
            docEntity.setEditable(false);
            for (TableInvoiceNomDocEntity tableInv: selectedInvoice.getTableInvoiceNomById()) {
                TableDocsStuffDocEntity tableDoc = new TableDocsStuffDocEntity();
                tableDoc.setNomenklEntityByNomId(tableInv.getNomenklEntityByNomId());
                tableDoc.setQty(tableInv.getQty());
                tableDoc.setSum(tableInv.getSum());
                docEntity.addProduct(tableDoc);
                Map<String, Long> keys = new HashMap<>();
                keys.put("nomenklEntityByNomId", tableInv.getNomenklEntityByNomId().getId());
                keys.put("storageEntityById", selectedStore.getId());
                TableCurrentRestStuffDocEntity currentRestStuffDocEntity = new TableCurrentRestStuffDocEntity();
                try {
                    currentRestStuffDocEntity = (TableCurrentRestStuffDocEntity) service.readRow(new TableCurrentRestStuffDocEntity(), keys);
             //       if (currentRestStuffDocEntity != null) {
                        assert currentRestStuffDocEntity != null:new NoResultException();
                        currentRestStuffDocEntity.setQty(currentRestStuffDocEntity.getQty() + tableInv.getQty());
                        currentRestStuffDocEntity.setSum(currentRestStuffDocEntity.getSum() + tableInv.getSum());
             //       } else {
             //           currentRestStuffDocEntity.setStorageEntityById(selectedStore);
             //           currentRestStuffDocEntity.setNomenklEntityByNomId(tableInv.getNomenklEntityByNomId());
             //           currentRestStuffDocEntity.setQty(tableInv.getQty());
             //           currentRestStuffDocEntity.setSum(tableInv.getSum());
             //       }
                } catch (NoResultException e) {
                    currentRestStuffDocEntity.setStorageEntityById(selectedStore);
                    currentRestStuffDocEntity.setNomenklEntityByNomId(tableInv.getNomenklEntityByNomId());
                    currentRestStuffDocEntity.setQty(tableInv.getQty());
                    currentRestStuffDocEntity.setSum(tableInv.getSum());
                }
                JournalOperationsStaffDocEntity journal = new JournalOperationsStaffDocEntity();
                docEntity.addJournal(journal);
                selectedStore.addJournal(journal);
                tableInv.getNomenklEntityByNomId().addJournal(journal);
                typeOperation.addJournal(journal);
                journal.setRecTime(new Date(System.currentTimeMillis()));
                journal.setQty(currentRestStuffDocEntity.getQty());
                journal.setSum(currentRestStuffDocEntity.getSum());
                session.saveOrUpdate(docEntity);
                session.saveOrUpdate(currentRestStuffDocEntity);
                session.save(journal);
            }
            selectedInvoice.setEditable(false);
            session.getTransaction().commit();
            session.close();
            for (int i = 0; i <farm.getReferences().getInvoiceData().size(); i++) {
                if (farm.getReferences().getInvoiceData().get(i).getId() == selectedInvoice.getId())
                    farm.getReferences().getInvoiceData().get(i).setEditable(false);
            }
            farm.getConfigDialogController().getInvoiceHeadOverviewController().getEntityTable().refresh();
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
