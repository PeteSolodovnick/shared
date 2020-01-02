package address.contragents;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.*;

public class ContragentDialogController extends SuperDialogEntityController {
    @FXML
    private TextField city;
    @FXML
    private TextField address;
    @FXML
    private TextField marketView;
    @FXML
    private TextField contact;
    @FXML
    private TextField phone;
    @FXML
    private TextField type;
    @FXML
    private TextField kind;
    @FXML
    private TextField price;
    @FXML
    private TextField okpo;
    @FXML
    private TextField inn;
    @FXML
    private TextArea comments;
    @FXML
    private CheckBox vat;

    private RefContragentEntity newContragentEntity;
    private RefCityEntity newCityEntity;
    private RefTypeContragentEntity newTypeContragentEntity;
    private RefPriceEntity newPriceEntity;
    private RefMarketViewEntity newMarketViewEntity;
    private RefKindContragentEntity newKindContragentEntity;
    private String fileType;
    private String fileView;
    private String fileCity;
    private String filePrice;
    private String fileKind;

    public ContragentDialogController() {}
    public void setFarmFX(FarmFX farm, SuperEntity selectedContragent) {
        fileType = "/typeContragent.fxml";
        fileView = "/marketView.fxml";
        fileCity = "/city.fxml";
        filePrice = "/price.fxml";
        fileKind = "/kindContragent.fxml";
        newContragentEntity = (RefContragentEntity) selectedContragent;
        if (selectedContragent != null) {
            city.setText(newContragentEntity.getRefCityByCityId().getName());
            address.setText(newContragentEntity.getAddress());
            marketView.setText(newContragentEntity.getRefMarketViewByMarketViewId().getName());
            contact.setText(newContragentEntity.getContact());
            phone.setText(newContragentEntity.getPhone());
            type.setText(newContragentEntity.getRefTypeContragentByTypeContraId().getName());
            kind.setText(newContragentEntity.getRefKindContragentByKindContraId().getName());
            price.setText(newContragentEntity.getRefPriceByPriceId().getName());
            okpo.setText(newContragentEntity.getOkpo());
            inn.setText(newContragentEntity.getInn());
            comments.setText(newContragentEntity.getComments());
            vat.setSelected(newContragentEntity.getNds());
            newCityEntity = newContragentEntity.getRefCityByCityId();
            newTypeContragentEntity = newContragentEntity.getRefTypeContragentByTypeContraId();
            newKindContragentEntity = newContragentEntity.getRefKindContragentByKindContraId();
            newMarketViewEntity = newContragentEntity.getRefMarketViewByMarketViewId();
            newPriceEntity = newContragentEntity.getRefPriceByPriceId();
            setNew(false);
        } else {
            newContragentEntity = new RefContragentEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newContragentEntity);
    }
    @Override
    protected boolean isInputValid() {
        if (super.isInputValid()) {
            String errorMessage = "";
            if (city.getText() == null || city.getText().length() == 0) {
                errorMessage += "No valid city\n";
            }
            if (marketView.getText() == null || marketView.getText().length() == 0) {
                errorMessage += "No valid market view\n";
            }
            if (type.getText() == null || type.getText().length() == 0) {
                errorMessage += "No valid market view\n";
            }
            if (kind.getText() == null || kind.getText().length() == 0) {
                errorMessage += "No valid kind of contragent\n";
            }
            if (price.getText() == null || price.getText().length() == 0) {
                errorMessage += "No valid price\n";
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
        newContragentEntity.setRefTypeContragentByTypeContraId(getNewTypeContragentEntity());
        newContragentEntity.setRefKindContragentByKindContraId(getNewKindContragentEntity());
        newContragentEntity.setRefPriceByPriceId(getNewPriceEntity());
        newContragentEntity.setRefMarketViewByMarketViewId(getNewMarketViewEntity());
        newContragentEntity.setRefCityByCityId(getNewCityEntity());
        newContragentEntity.setAddress(address.getText());
        newContragentEntity.setComments(comments.getText());
        newContragentEntity.setInn(inn.getText());
        newContragentEntity.setContact(contact.getText());
        newContragentEntity.setOkpo(okpo.getText());
        newContragentEntity.setPhone(phone.getText());
        newContragentEntity.setNds(vat.isSelected());
        setNewEntity(newContragentEntity);
    }

    @FXML
    private void handleCityChoose() {
        getFarm().getConfigDialogController().setContragentDialogController(this);
        getFarm().showEntityOverview(fileCity);
    }
    @FXML
    private void handleMarketViewChoose() {
        getFarm().getConfigDialogController().setContragentDialogController(this);
        getFarm().showEntityOverview(fileView);
    }
    @FXML
    private void handleTypeContragentChoose() {
        getFarm().getConfigDialogController().setContragentDialogController(this);
        getFarm().showEntityOverview(fileType);
    }
    @FXML
    private void handleKindContragentChoose() {
        getFarm().getConfigDialogController().setContragentDialogController(this);
        getFarm().showEntityOverview(fileKind);
    }
    @FXML
    private void handlePriceChoose() {
        getFarm().getConfigDialogController().setContragentDialogController(this);
        getFarm().showEntityOverview(filePrice);
    }
    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getContragentData().add(getNewContragentEntity());
        getFarm().getConfigDialogController().getContragentOverviewController().getEntities().add(getNewContragentEntity());
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getContragentOverviewController().getEntitiesName().setItems(getFarm().getConfigDialogController().getContragentOverviewController().getEntities());
    }

    public RefContragentEntity getNewContragentEntity() {
        return newContragentEntity;
    }

    public void setNewContragentEntity(RefContragentEntity newContragentEntity) {
        this.newContragentEntity = newContragentEntity;
    }

    public RefCityEntity getNewCityEntity() {
        return newCityEntity;
    }

    public void setNewCityEntity(RefCityEntity newCityEntity) {
        this.newCityEntity = newCityEntity;
    }

    public RefTypeContragentEntity getNewTypeContragentEntity() {
        return newTypeContragentEntity;
    }

    public void setNewTypeContragentEntity(RefTypeContragentEntity newTypeContragentEntity) {
        this.newTypeContragentEntity = newTypeContragentEntity;
    }

    public RefPriceEntity getNewPriceEntity() {
        return newPriceEntity;
    }

    public void setNewPriceEntity(RefPriceEntity newPriceEntity) {
        this.newPriceEntity = newPriceEntity;
    }

    public RefMarketViewEntity getNewMarketViewEntity() {
        return newMarketViewEntity;
    }

    public void setNewMarketViewEntity(RefMarketViewEntity newMarketViewEntity) {
        this.newMarketViewEntity = newMarketViewEntity;
    }

    public RefKindContragentEntity getNewKindContragentEntity() {
        return newKindContragentEntity;
    }

    public void setNewKindContragentEntity(RefKindContragentEntity newKindContragentEntity) {
        this.newKindContragentEntity = newKindContragentEntity;
    }

    public TextField getCity() {
        return city;
    }

    public TextField getAddress() {
        return address;
    }

    public TextField getMarketView() {
        return marketView;
    }

    public TextField getType() {
        return type;
    }

    public TextField getKind() {
        return kind;
    }

    public TextField getPrice() {
        return price;
    }
}
