package address.lots;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.scene.control.TreeItem;
import models.SuperEntity;
import models.TypeLotsEntity;

public class TypeLotsDialogController extends SuperDialogEntityController {
    private TypeLotsEntity newTypeLot;
    public TypeLotsDialogController() {}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity selectedTypeLot) {
        newTypeLot = (TypeLotsEntity) selectedTypeLot;
        if (selectedTypeLot != null) {
            setNew(false);
        } else {
            newTypeLot = new TypeLotsEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newTypeLot);
    }
    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getTypeLotsData().add(newTypeLot);
        if (getFarm().getConfigDialogController().getTypeLotsTableController() != null) {
            getFarm().getConfigDialogController().getTypeLotsTableController().getEntityTable().getItems().add(newTypeLot);
        }
        getFarm().getConfigDialogController().getLotsOverviewController().getRootItem().getChildren().add(new TreeItem<>(newTypeLot));
        getFarm().getConfigDialogController().getLotsOverviewController().getEntitiesTree().add(newTypeLot);
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        if (getFarm().getConfigDialogController().getTypeLotsTableController() != null) {
            getFarm().getConfigDialogController().getTypeLotsTableController().getEntityTable().refresh();
            getFarm().getConfigDialogController().getLotsOverviewController().getRootItem().getChildren().clear();
            getFarm().getConfigDialogController().getLotsOverviewController().initRoot();
        } else {
            getFarm().getConfigDialogController().getLotsOverviewController().getTreeView().getSelectionModel().getSelectedItem().setValue(newEntity);
            getFarm().getConfigDialogController().getLotsOverviewController().getTreeView().refresh();
        }
        for (int i = 0; i < getFarm().getReferences().getLotsData().size(); i++) {
            if (getFarm().getReferences().getLotsData().get(i).getRefTypeLotsByTypeLotsId().getId() == newEntity.getId()) {
                getFarm().getReferences().getLotsData().get(i).getRefTypeLotsByTypeLotsId().setName(newEntity.getName());
            }
        }
        getFarm().getConfigDialogController().getLotsOverviewController().getEntityTable().refresh();
    }
}

