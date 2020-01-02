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

    private RefContragentEntity contragentEntity;
    private RefCityEntity cityEntity;
    private RefTypeContragentEntity typeContragentEntity;
    private RefPriceEntity priceEntity;
    private RefMarketViewEntity marketViewEntity;
    private RefKindContragentEntity kindContragentEntity;
    private String fileType;
    private String fileView;
    private String fileCity;
    private String filePrice;
    private String fileKind;

    public ContragentDialogController() {}
    public void setFarmFX(FarmFX farm, SuperEntity contragent) {
        fileType = "/typeContragent.fxml";
        fileView = "/marketView.fxml";
        fileCity = "/city.fxml";
        filePrice = "/price.fxml";
        fileKind = "/kindContragent.fxml";
        contragentEntity = (RefContragentEntity) contragent;
        if (contragent != null) {
            city.setText(contragentEntity.getRefCityByCityId().getName());
            address.setText(contragentEntity.getAddress());
            marketView.setText(contragentEntity.getRefMarketViewByMarketViewId().getName());
            contact.setText(contragentEntity.getContact());
            phone.setText(contragentEntity.getPhone());
            type.setText(contragentEntity.getRefTypeContragentByTypeContraId().getName());
            kind.setText(contragentEntity.getRefKindContragentByKindContraId().getName());
            price.setText(contragentEntity.getRefPriceByPriceId().getName());
            okpo.setText(contragentEntity.getOkpo());
            inn.setText(contragentEntity.getInn());
            comments.setText(contragentEntity.getComments());
            vat.setSelected(contragentEntity.getNds());
            cityEntity = contragentEntity.getRefCityByCityId();
            typeContragentEntity = contragentEntity.getRefTypeContragentByTypeContraId();
            kindContragentEntity = contragentEntity.getRefKindContragentByKindContraId();
            marketViewEntity = contragentEntity.getRefMarketViewByMarketViewId();
            priceEntity = contragentEntity.getRefPriceByPriceId();
            setNew(false);
        } else {
            contragentEntity = new RefContragentEntity();
            setNew(true);
        }
        super.setFarmFX(farm, contragentEntity);
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
        for (int i = 0; i < getFarm().getReferences().getTypeContragentData().size(); i++) {
            if (getFarm().getReferences().getTypeContragentData().get(i).getId() == getTypeContragentEntity().getId())
                contragentEntity.setRefTypeContragentByTypeContraId(getFarm().getReferences().getTypeContragentData().get(i));
        }
        for (int i = 0; i < getFarm().getReferences().getKindContragentData().size(); i++) {
            if (getFarm().getReferences().getKindContragentData().get(i).getId() == getKindContragentEntity().getId())
                contragentEntity.setRefKindContragentByKindContraId(getFarm().getReferences().getKindContragentData().get(i));
        }
        for (int i = 0; i < getFarm().getReferences().getPriceData().size(); i++) {
            if (getFarm().getReferences().getPriceData().get(i).getId() == getPriceEntity().getId())
                contragentEntity.setRefPriceByPriceId(getFarm().getReferences().getPriceData().get(i));
        }
        for (int i = 0; i < getFarm().getReferences().getMarketViewData().size(); i++) {
            if (getFarm().getReferences().getMarketViewData().get(i).getId() == getMarketViewEntity().getId())
                contragentEntity.setRefMarketViewByMarketViewId(getFarm().getReferences().getMarketViewData().get(i));
        }
        for (int i = 0; i < getFarm().getReferences().getCitiesData().size(); i++) {
            if (getFarm().getReferences().getCitiesData().get(i).getId() == getCityEntity().getId())
                contragentEntity.setRefCityByCityId(getFarm().getReferences().getCitiesData().get(i));
        }
        contragentEntity.setAddress(address.getText());
        contragentEntity.setComments(comments.getText());
        contragentEntity.setInn(inn.getText());
        contragentEntity.setContact(contact.getText());
        contragentEntity.setOkpo(okpo.getText());
        contragentEntity.setPhone(phone.getText());
        contragentEntity.setNds(vat.isSelected());
        setEntity(contragentEntity);
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
        getFarm().getReferences().getContragentData().add(getContragentEntity());
        getFarm().getConfigDialogController().getContragentOverviewController().getEntities().add(getContragentEntity());
      //  getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
    }

    @Override
    public void editEntity(SuperEntity entity) {
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getContragentOverviewController().getEntitiesName().setItems(getFarm().getConfigDialogController().getContragentOverviewController().getEntities());
    }

    public RefContragentEntity getContragentEntity() {
        return contragentEntity;
    }

    public void setContragentEntity(RefContragentEntity contragentEntity) {
        this.contragentEntity = contragentEntity;
    }

    public RefCityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(RefCityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public RefTypeContragentEntity getTypeContragentEntity() {
        return typeContragentEntity;
    }

    public void setTypeContragentEntity(RefTypeContragentEntity typeContragentEntity) {
        this.typeContragentEntity = typeContragentEntity;
    }

    public RefPriceEntity getPriceEntity() {
        return priceEntity;
    }

    public void setPriceEntity(RefPriceEntity priceEntity) {
        this.priceEntity = priceEntity;
    }

    public RefMarketViewEntity getMarketViewEntity() {
        return marketViewEntity;
    }

    public void setMarketViewEntity(RefMarketViewEntity marketViewEntity) {
        this.marketViewEntity = marketViewEntity;
    }

    public RefKindContragentEntity getKindContragentEntity() {
        return kindContragentEntity;
    }

    public void setKindContragentEntity(RefKindContragentEntity kindContragentEntity) {
        this.kindContragentEntity = kindContragentEntity;
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
