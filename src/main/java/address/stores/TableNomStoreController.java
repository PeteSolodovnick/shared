package address.stores;

import address.documents.invoices.TableNomDocController;
import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import javafx.scene.control.Alert;
import models.references.ClassificationEntity;
import models.references.NomenklEntity;
import models.tables.TableCurrentRestStuffDocEntity;
import models.tables.TableDocsStuffDocEntity;
import models.tables.TableInvoiceNomDocEntity;

public class TableNomStoreController extends TableNomDocController {
    @Override
    protected void initArray(FarmFX farm) {
            farm.getReferences().getClassificationData().clear();
            farm.getReferences().getProductsData().clear();
            farm.getReferences().getTableDocData().clear();
            farm.getReferences().setCurrentRestStuffData(new FactoryListEntities<TableCurrentRestStuffDocEntity>(new TableCurrentRestStuffDocEntity()).getSomeListEntities(farm.getConfigDialogController().getMovieDialogController().getStoreOutSelect().getId(), "storageEntityById"));
            farm.getReferences().setClassificationData(new FactoryListEntities<>(new ClassificationEntity()).getListEntities());
            for (TableCurrentRestStuffDocEntity rest : farm.getReferences().getCurrentRestStuffData()) {
                if (rest.getQty() > 0) {
                    farm.getReferences().getProductsData().add(rest.getNomenklEntityByNomId());
                }
            }
    }
    @Override
    public void setTextEdit() {
        TableDocsStuffDocEntity tableDocsStuffDocEntity = new TableDocsStuffDocEntity();
        tableDocsStuffDocEntity.setQty(0);
        tableDocsStuffDocEntity.setSum(0f);
        tableDocsStuffDocEntity.setNomenklEntityByNomId((NomenklEntity) getEntityTable().getSelectionModel().getSelectedItem());
        tableDocsStuffDocEntity.setDocDocsHeadByDocId(getFarm().getConfigDialogController().getMovieDialogController().getNewDoc());
        for (TableCurrentRestStuffDocEntity rest:getFarm().getReferences().getCurrentRestStuffData()) {
            if (tableDocsStuffDocEntity.getNomenklEntityByNomId().equals(rest.getNomenklEntityByNomId())) {
                getFarm().getConfigDialogController().getMovieDialogController().getRestEntityTable().getItems().add(rest);
            }
        }
        getFarm().getConfigDialogController().getMovieDialogController().getEntityTable().getItems().add(tableDocsStuffDocEntity);
        getFarm().getConfigDialogController().getMovieDialogController().getStoreOut().setEditable(false);
        getFarm().getConfigDialogController().getMovieDialogController().getBtnStoreOut().setDisable(true);
    }
}
