package address.contragents;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.scene.control.TreeItem;
import models.RefTypeContragentEntity;
import models.SuperEntity;

public class TypeContragentDialogController extends SuperDialogEntityController {
    private RefTypeContragentEntity typeContragent;
    public TypeContragentDialogController(){}

    @Override
    public void setFarmFX(FarmFX farm, SuperEntity typeContragent) {
        this.typeContragent = (RefTypeContragentEntity) typeContragent;
        if (this.typeContragent != null) {
            setNew(false);
        } else {
            this.typeContragent = new RefTypeContragentEntity();
            setNew(true);
        }
        super.setFarmFX(farm,this.typeContragent);
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getTypeContragentData().add(typeContragent);
        if (getFarm().getConfigDialogController().getTypeContragentTableController() != null) {
            getFarm().getConfigDialogController().getTypeContragentTableController().getEntityTable().getItems().add(typeContragent);
        }
        getFarm().getConfigDialogController().getContragentOverviewController().getRootItem().getChildren().add(new TreeItem<>(typeContragent));
        getFarm().getConfigDialogController().getContragentOverviewController().getEntitiesTree().add(typeContragent);
    }

    @Override
    public void editEntity(SuperEntity entity) {
        if (getFarm().getConfigDialogController().getTypeContragentTableController() != null) {
            getFarm().getConfigDialogController().getTypeContragentTableController().getEntityTable().refresh();
            getFarm().getConfigDialogController().getContragentOverviewController().getRootItem().getChildren().clear();
            getFarm().getConfigDialogController().getContragentOverviewController().initRoot();
        } else getFarm().getConfigDialogController().getContragentOverviewController().getTreeView().getSelectionModel().getSelectedItem().setValue(entity);
        for (int i = 0; i < getFarm().getReferences().getContragentData().size(); i++) {
            if (getFarm().getReferences().getContragentData().get(i).getRefTypeContragentByTypeContraId().getId() == entity.getId()) {
                getFarm().getReferences().getContragentData().get(i).getRefTypeContragentByTypeContraId().setName(entity.getName());
            }
        }
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
    }

}
