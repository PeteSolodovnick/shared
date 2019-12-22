package address.contragents;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.RefContragentEntity;
import models.SuperEntity;

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
        RefContragentEntity contragentEntity = (RefContragentEntity) getEntity();
        for (int i = 0; i < getFarm().getReferences().getTypeContragentData().size(); i++) {
            if (getFarm().getReferences().getTypeContragentData().get(i).getName().equals(getType().getText()))
                contragentEntity.setRefTypeContragentByTypeContraId(getFarm().getReferences().getTypeContragentData().get(i));
        }
        for (int i = 0; i < getFarm().getReferences().getKindContragentData().size(); i++) {
            if (getFarm().getReferences().getKindContragentData().get(i).getName().equals(getKind().getText()))
                contragentEntity.setRefKindContragentByKindContraId(getFarm().getReferences().getKindContragentData().get(i));
        }
        for (int i = 0; i < getFarm().getReferences().getPriceData().size(); i++) {
            if (getFarm().getReferences().getPriceData().get(i).getName().equals(getPrice().getText()))
                contragentEntity.setRefPriceByPriceId(getFarm().getReferences().getPriceData().get(i));
        }
        for (int i = 0; i < getFarm().getReferences().getMarketViewData().size(); i++) {
            if (getFarm().getReferences().getMarketViewData().get(i).getName().equals(getMarketView().getText()))
                contragentEntity.setRefMarketViewByMarketViewId(getFarm().getReferences().getMarketViewData().get(i));
        }
        for (int i = 0; i < getFarm().getReferences().getCitiesData().size(); i++) {
            if (getFarm().getReferences().getCitiesData().get(i).getName().equals(getCity().getText()))
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
        getFarm().getReferences().getContragentData().add(contragentEntity);
        getFarm().getConfigDialogController().getContragentOverviewController().getEntities().add(contragentEntity);
    }

    @Override
    public void editEntity(SuperEntity entity) {
        getFarm().getConfigDialogController().getContragentOverviewController().getEntityTable().refresh();
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
