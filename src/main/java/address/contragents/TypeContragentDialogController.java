package address.contragents;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.scene.control.TreeItem;
import models.SuperEntity;
import models.TypeContragentEntity;

public class TypeContragentDialogController extends SuperDialogEntityController {
    private TypeContragentEntity newTypeContragent;
    public TypeContragentDialogController(){}

    @Override
    public void setFarmFX(FarmFX farm, SuperEntity selectedTypeContragent) {
        newTypeContragent = (TypeContragentEntity) selectedTypeContragent;
        if (selectedTypeContragent != null) {
            setNew(false);
        } else {
            newTypeContragent = new TypeContragentEntity();
            setNew(true);
        }
        super.setFarmFX(farm,newTypeContragent);
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getTypeContragentData().add(newTypeContragent);
        if (getFarm().getConfigDialogController().getTypeContragentTableController() != null) {
            getFarm().getConfigDialogController().getTypeContragentTableController().getEntityTable().getItems().add(newTypeContragent);
        }
        getFarm().getConfigDialogController().getContragentOverviewController().getRootItem().getChildren().add(new TreeItem<>(newTypeContragent));
        getFarm().getConfigDialogController().getContragentOverviewController().getEntitiesTree().add(newTypeContragent);
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        if (getFarm().getConfigDialogController().getTypeContragentTableController() != null) {
            getFarm().getConfigDialogController().getTypeContragentTableController().getEntityTable().refresh();
            getFarm().getConfigDialogController().getContragentOverviewController().getRootItem().getChildren().clear();
            getFarm().getConfigDialogController().getContragentOverviewController().initRoot();
        } else {
            getFarm().getConfigDialogController().getContragentOverviewController().getTreeView().getSelectionModel().getSelectedItem().setValue(newEntity);
            getFarm().getConfigDialogController().getContragentOverviewController().getTreeView().refresh();
        }
        for (int i = 0; i < getFarm().getReferences().getContragentData().size(); i++) {
            if (getFarm().getReferences().getContragentData().get(i).getRefTypeContragentByTypeContraId().getId() == newEntity.getId()) {
                getFarm().getReferences().getContragentData().get(i).getRefTypeContragentByTypeContraId().setName(newEntity.getName());
            }
        }
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
    }

}
