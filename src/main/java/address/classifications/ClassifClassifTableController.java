package address.classifications;

import models.references.ClassificationEntity;

public class ClassifClassifTableController extends ClassificationProductTableController {
    @Override
    public void setTextEdit() {
        getFarm().getConfigDialogController().getClassificationDialogController().setNewParentEntity((ClassificationEntity) getEntityTable().getSelectionModel().getSelectedItem());
        getFarm().getConfigDialogController().getClassificationDialogController().getParent().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }
}
