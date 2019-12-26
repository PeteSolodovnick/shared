package address.localities;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.scene.control.TreeItem;
import models.RefTerritoryEntity;
import models.SuperEntity;

public class TerritoryDialogController extends SuperDialogEntityController {
    private RefTerritoryEntity territoryEntity;
    public TerritoryDialogController() {}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity territory) {
        territoryEntity = (RefTerritoryEntity) territory;
        if (territoryEntity != null) {
            setNew(false);
        } else {
            territoryEntity = new RefTerritoryEntity();
            setNew(true);
        }
        super.setFarmFX(farm,territoryEntity);
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getTerritoryData().add(territoryEntity);
        if (getFarm().getConfigDialogController().getTerritoryTableController() != null) {
            getFarm().getConfigDialogController().getTerritoryTableController().getEntityTable().getItems().add(territoryEntity);
        }
        getFarm().getConfigDialogController().getLocalityOverviewController().getRootItem().getChildren().add(new TreeItem<>(territoryEntity));
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntitiesTree().add(territoryEntity);
    }

    @Override
    public void editEntity(SuperEntity entity) {
        if (getFarm().getConfigDialogController().getTerritoryTableController() != null) {
            getFarm().getConfigDialogController().getTerritoryTableController().getEntityTable().refresh();
            getFarm().getConfigDialogController().getLocalityOverviewController().getRootItem().getChildren().clear();
            getFarm().getConfigDialogController().getLocalityOverviewController().initRoot();
        } else {
            getFarm().getConfigDialogController().getLocalityOverviewController().getTreeView().getSelectionModel().getSelectedItem().setValue(entity);
            getFarm().getConfigDialogController().getLocalityOverviewController().getTreeView().refresh();
        }
        for (int i = 0; i < getFarm().getReferences().getCitiesData().size(); i++) {
          if (getFarm().getReferences().getCitiesData().get(i).getRefTerritoryByTerId().getId() == entity.getId()) {
              getFarm().getReferences().getCitiesData().get(i).getRefTerritoryByTerId().setName(entity.getName());
           }
        }
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntityTable().refresh();
    }
}
