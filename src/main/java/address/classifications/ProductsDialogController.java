package address.classifications;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.RefClassificationEntity;
import models.RefNomenklEntity;
import models.RefSizeEntity;
import models.SuperEntity;

public class ProductsDialogController extends SuperDialogEntityController {
    @FXML
    private TextField size;
    @FXML
    private TextField classification;

    private RefNomenklEntity productEntity;
    private RefSizeEntity sizeEntity;
    private RefClassificationEntity classificationEntity;
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
            sizeEntity = productEntity.getRefSizeBySizeId();
            classificationEntity = productEntity.getRefClassificationByClassificationId();
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
      //  RefNomenklEntity prodEntity = (RefNomenklEntity) getEntity();
        for (int i = 0; i < getFarm().getReferences().getSizeEntitiesData().size(); i++) {
            if (getFarm().getReferences().getSizeEntitiesData().get(i).getId() == getSizeEntity().getId())
                productEntity.setRefSizeBySizeId(getFarm().getReferences().getSizeEntitiesData().get(i));
        }
        for (int i = 0; i < getFarm().getReferences().getClassificationData().size(); i++) {
            if (getFarm().getReferences().getClassificationData().get(i).getId() == getClassificationEntity().getId())
                productEntity.setRefClassificationByClassificationId(getFarm().getReferences().getClassificationData().get(i));
        }
        setEntity(productEntity);
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
        getFarm().getReferences().getProductsData().add(getProductEntity());
        getFarm().getConfigDialogController().getProductsOverviewController().getEntities().add(getProductEntity());
    }

    @Override
    public void editEntity(SuperEntity entity) {
        getFarm().getConfigDialogController().getProductsOverviewController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getProductsOverviewController().getEntitiesName().setItems(getFarm().getConfigDialogController().getProductsOverviewController().getEntities());
    }

    public TextField getSize() {
        return size;
    }

    public void setSize(TextField size) {
        this.size = size;
    }

    public TextField getClassification() {
        return classification;
    }

    public RefSizeEntity getSizeEntity() {
        return sizeEntity;
    }

    public void setSizeEntity(RefSizeEntity sizeEntity) {
        this.sizeEntity = sizeEntity;
    }

    public RefClassificationEntity getClassificationEntity() {
        return classificationEntity;
    }

    public void setClassificationEntity(RefClassificationEntity classificationEntity) {
        this.classificationEntity = classificationEntity;
    }

    public RefNomenklEntity getProductEntity() {
        return productEntity;
    }
}
