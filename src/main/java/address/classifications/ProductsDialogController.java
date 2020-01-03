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

    private RefNomenklEntity newProductEntity;
    private RefSizeEntity newSizeEntity;
    private RefClassificationEntity newParentEntity;
    private String fileSize;
    private String fileClassif;
    public ProductsDialogController() {}

    public void setFarmFX(FarmFX farm, SuperEntity selectedProduct) {
        fileSize = "/size.fxml";
        fileClassif = "/classificationTable.fxml";
        newProductEntity = (RefNomenklEntity) selectedProduct;
        if (selectedProduct != null) {
            size.setText(newProductEntity.getRefSizeBySizeId().getName());
            classification.setText(newProductEntity.getRefClassificationByClassificationId().getName());
            newSizeEntity = newProductEntity.getRefSizeBySizeId();
            newParentEntity = newProductEntity.getRefClassificationByClassificationId();
            setNew(false);
        } else {
            newProductEntity = new RefNomenklEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newProductEntity);
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
        newProductEntity.setRefSizeBySizeId(getNewSizeEntity());
        newProductEntity.setRefClassificationByClassificationId(getNewParentEntity());
        setNewEntity(newProductEntity);
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
        getFarm().getReferences().getProductsData().add(getNewProductEntity());
        getFarm().getConfigDialogController().getProductsOverviewController().getEntities().add(getNewProductEntity());
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

    public RefNomenklEntity getNewProductEntity() {
        return newProductEntity;
    }

    public void setNewProductEntity(RefNomenklEntity newProductEntity) {
        this.newProductEntity = newProductEntity;
    }

    public RefSizeEntity getNewSizeEntity() {
        return newSizeEntity;
    }

    public void setNewSizeEntity(RefSizeEntity newSizeEntity) {
        this.newSizeEntity = newSizeEntity;
    }

    public RefClassificationEntity getNewParentEntity() {
        return newParentEntity;
    }

    public void setNewParentEntity(RefClassificationEntity newParentEntity) {
        this.newParentEntity = newParentEntity;
    }
}
