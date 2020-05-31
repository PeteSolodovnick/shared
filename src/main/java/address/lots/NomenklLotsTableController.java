package address.lots;

import address.documents.invoices.TableNomDocController;
import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import models.SuperEntity;
import models.references.ClassificationEntity;
import models.references.NomenklEntity;
import models.tables.TableCurrentRestStuffDocEntity;
import services.EntityService;

import java.util.HashMap;
import java.util.Map;

public class NomenklLotsTableController extends TableNomDocController {
    @Override
    protected void initArray(FarmFX farm) {
        farm.getReferences().getClassificationData().clear();
        farm.getReferences().getProductsData().clear();
        farm.getReferences().setCurrentRestStuffData(new FactoryListEntities<>(new TableCurrentRestStuffDocEntity()).getSomeListEntities(1L, "storageEntityById"));
        farm.getReferences().setClassificationData(new FactoryListEntities<>(new ClassificationEntity()).getListEntities());
        for (TableCurrentRestStuffDocEntity rest : farm.getReferences().getCurrentRestStuffData()) {
            if (rest.getQty() > 0) {
                farm.getReferences().getProductsData().add(rest.getNomenklEntityByNomId());
            }
        }
    }
    @Override
    public void setTextEdit() {
        Map<String, Long> keys = new HashMap<>();
        EntityService<SuperEntity, Long> service = new EntityService<>();
        keys.put("nomenklEntityByNomId", getEntityTable().getSelectionModel().getSelectedItem().getId());
        keys.put("storageEntityById", 1L);
        getFarm().getConfigDialogController().getLotsDialogController().setNewNomenkl((NomenklEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getLotsDialogController().getNomenkl().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
        getFarm().getConfigDialogController().getLotsDialogController().getRest().setText(String.valueOf(((TableCurrentRestStuffDocEntity) service.readRow(new TableCurrentRestStuffDocEntity(), keys)).getQty()));
    }

}
