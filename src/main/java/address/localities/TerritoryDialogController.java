package address.localities;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.scene.control.TreeItem;
import models.SuperEntity;
import models.TerritoryEntity;

public class TerritoryDialogController extends SuperDialogEntityController {
    private TerritoryEntity newTerritoryEntity;
    public TerritoryDialogController() {}
    @Override
    public void setFarmFX(FarmFX farm, SuperEntity selectedTerritory) {
        newTerritoryEntity = (TerritoryEntity) selectedTerritory;
        if (selectedTerritory != null) {
            setNew(false);
        } else {
            newTerritoryEntity = new TerritoryEntity();
            setNew(true);
        }
        super.setFarmFX(farm,newTerritoryEntity);
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getTerritoryData().add(newTerritoryEntity);
        if (getFarm().getConfigDialogController().getTerritoryTableController() != null) {
            getFarm().getConfigDialogController().getTerritoryTableController().getEntityTable().getItems().add(newTerritoryEntity);
        }
        getFarm().getConfigDialogController().getLocalityOverviewController().getRootItem().getChildren().add(new TreeItem<>(newTerritoryEntity));
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntitiesTree().add(newTerritoryEntity);
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        if (getFarm().getConfigDialogController().getTerritoryTableController() != null) {
            getFarm().getConfigDialogController().getTerritoryTableController().getEntityTable().refresh();
            getFarm().getConfigDialogController().getLocalityOverviewController().getRootItem().getChildren().clear();
            getFarm().getConfigDialogController().getLocalityOverviewController().initRoot();
        } else {
            getFarm().getConfigDialogController().getLocalityOverviewController().getTreeView().getSelectionModel().getSelectedItem().setValue(newEntity);
            getFarm().getConfigDialogController().getLocalityOverviewController().getTreeView().refresh();
        }
        for (int i = 0; i < getFarm().getReferences().getCitiesData().size(); i++) {
          if (getFarm().getReferences().getCitiesData().get(i).getRefTerritoryByTerId().getId() == newEntity.getId()) {
              getFarm().getReferences().getCitiesData().get(i).getRefTerritoryByTerId().setName(newEntity.getName());
           }
        }
        getFarm().getConfigDialogController().getLocalityOverviewController().getEntityTable().refresh();
    }
}
