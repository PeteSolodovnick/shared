package address.classifications;

import models.RefCityEntity;
import models.RefClassificationEntity;

public class ClassifClassifTableController extends ClassificationProductTableController {
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getClassificationDialogController().setParentEntity((RefClassificationEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getClassificationDialogController().getParent().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }
}
