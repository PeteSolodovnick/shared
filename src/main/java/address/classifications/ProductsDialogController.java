package address.classifications;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.*;

public class ProductsDialogController extends SuperDialogEntityController {
    @FXML
    private TextField size;
    @FXML
    private TextField classification;

    private NomenklEntity newProductEntity;
    private SizeEntity newSizeEntity;
    private ClassificationEntity newParentEntity;
    private String fileSize;
    private String fileClassif;
    public ProductsDialogController() {}

    public void setFarmFX(FarmFX farm, SuperEntity selectedProduct) {
        fileSize = "/size.fxml";
        fileClassif = "/classificationTable.fxml";
        newProductEntity = (NomenklEntity) selectedProduct;
        if (selectedProduct != null) {
            size.setText(newProductEntity.getRefSizeBySizeId().getName());
            classification.setText(newProductEntity.getRefClassificationByClassificationId().getName());
            newSizeEntity = newProductEntity.getRefSizeBySizeId();
            newParentEntity = newProductEntity.getRefClassificationByClassificationId();
            setNew(false);
        } else {
            newProductEntity = new NomenklEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newProductEntity);
    }
    @Override
    protected boolean isInputValid() {
        if (super.isInputValid()) {
            String errorMessage = "";
            if (size.getText() == null || size.getText().length() == 0) {
                errorMessage += "No valid size\n";
            }
            if (classification.getText() == null || classification.getText().length() == 0) {
                errorMessage += "No valid classification \n";
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

    public NomenklEntity getNewProductEntity() {
        return newProductEntity;
    }

    public void setNewProductEntity(NomenklEntity newProductEntity) {
        this.newProductEntity = newProductEntity;
    }

    public SizeEntity getNewSizeEntity() {
        return newSizeEntity;
    }

    public void setNewSizeEntity(SizeEntity newSizeEntity) {
        this.newSizeEntity = newSizeEntity;
    }

    public ClassificationEntity getNewParentEntity() {
        return newParentEntity;
    }

    public void setNewParentEntity(ClassificationEntity newParentEntity) {
        this.newParentEntity = newParentEntity;
    }
}
