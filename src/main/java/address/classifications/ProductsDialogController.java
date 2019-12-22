package address.classifications;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.RefNomenklEntity;
import models.SuperEntity;

public class ProductsDialogController extends SuperDialogEntityController {
    @FXML
    private TextField size;
    @FXML
    private TextField classification;

    private RefNomenklEntity productEntity;
    private String fileSize;
    private String fileClassif;
    public ProductsDialogController() {}

    public void setFarmFX(FarmFX farm, SuperEntity product) {
        fileSize = "/size.fxml";
        fileClassif = "/classificationTable.fxml";
        productEntity = (RefNomenklEntity) product;
        if (product != null) {
            size.setText(productEntity.getRefSizeBySizeId().getName());
            classification.setText(productEntity.getRefClassificationByClassificationId().getName());
            setNew(false);
        } else {
            productEntity = new RefNomenklEntity();
            setNew(true);
        }
        super.setFarmFX(farm, productEntity);
    }
    @Override
    protected boolean isInputValid() {
        if (super.isInputValid()) {
            String errorMessage = "";
            if (size.getText() == null || size.getText().length() == 0) {
                errorMessage += "No valid city\n";
            }
            if (isError(errorMessage))
                return false;
            return true;
        }
        return false;
    }
    @Override
    protected void createEntity() {
        super.createEntity();
        RefNomenklEntity prodEntity = (RefNomenklEntity) getEntity();
        for (int i = 0; i < getFarm().getReferences().getSizeEntitiesData().size(); i++) {
            if (getFarm().getReferences().getSizeEntitiesData().get(i).getName().equals(getSize().getText()))
                prodEntity.setRefSizeBySizeId(getFarm().getReferences().getSizeEntitiesData().get(i));
        }
        for (int i = 0; i < getFarm().getReferences().getClassificationData().size(); i++) {
            if (getFarm().getReferences().getClassificationData().get(i).getName().equals(getClassification().getText()))
                prodEntity.setRefClassificationByClassificationId(getFarm().getReferences().getClassificationData().get(i));
        }
        setEntity(prodEntity);
    }

    @FXML
    private void handleSizeChoose() {
        getFarm().getConfigDialogController().setProductsDialogController(this);
        getFarm().showEntityOverview(fileSize);
    }
    @FXML
    private void handleClassificationChoose() {
        getFarm().getConfigDialogController().setProductsDialogController(this);
        getFarm().showEntityOverview(fileClassif);
    }

    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getProductsData().add(productEntity);
        getFarm().getConfigDialogController().getProductsOverviewController().getEntities().add(productEntity);
    }

    @Override
    public void editEntity(SuperEntity entity) {
        getFarm().getConfigDialogController().getProductsOverviewController().getEntityTable().refresh();
    }

    public TextField getSize() {
        return size;
    }

    public TextField getClassification() {
        return classification;
    }
}
