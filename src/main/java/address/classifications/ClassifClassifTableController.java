package address.classifications;

public class ClassifClassifTableController extends ClassificationProductTableController {
    @Override
    public void setTextEdit() {
        logger.info("before settext");
        getFarm().getConfigDialogController().getClassificationDialogController().getParent().setText(getEntityTable().getSelectionModel().getSelectedItem().getName());
    }
}
