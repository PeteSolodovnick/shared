package address.lots;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.*;

public class LotsDialogController extends SuperDialogEntityController {
    @FXML
    private TextField type;
    @FXML
    private TextField kind;
    @FXML
    private TextField age;
    @FXML
    private TextField count;
    @FXML
    private TextField weight;
    @FXML
    private TextField number;

    private RefLotsEntity newLotEntity;
    private RefTypeLotsEntity newTypeLot;
    private RefKindLotsEntity newKindLot;
    private String fileType;
    private String fileKind;

    public LotsDialogController() {}

    public void setFarmFX(FarmFX farm, SuperEntity selectedLot) {
        fileType = "/lot'sType.fxml";
        fileKind = "/lotsKind.fxml";
        newLotEntity = (RefLotsEntity) selectedLot;
        if (selectedLot != null) {
            type.setText(newLotEntity.getRefTypeLotsByTypeLotsId().getName());
            kind.setText(newLotEntity.getRefKindLotsByKindLotsId().getName());
            age.setText(String.valueOf(newLotEntity.getStartAge()));
            weight.setText(String.valueOf(newLotEntity.getStartWeight()));
            count.setText(String.valueOf(newLotEntity.getStartCount()));
            newTypeLot = newLotEntity.getRefTypeLotsByTypeLotsId();
            newKindLot = newLotEntity.getRefKindLotsByKindLotsId();
            setNew(false);
        } else {
            newLotEntity = new RefLotsEntity();
            setNew(true);
        }
        super.setFarmFX(farm, newLotEntity);
    }
    @Override
    protected boolean isInputValid() {
        if (super.isInputValid()) {
            String errorMessage = "";
            if (type.getText() == null || type.getText().length() == 0) {
                errorMessage += "No valid type\n";
            }
            if (kind.getText() == null || kind.getText().length() == 0) {
                errorMessage += "No valid kind\n";
            }
            try {
                if (Integer.parseInt(weight.getText()) <= 0) {
                    errorMessage += "No correct weight\n";
                }
                if (Integer.parseInt(age.getText()) <= 0) {
                    errorMessage += "No correct age\n";
                }
                if (Integer.parseInt(count.getText()) <=0) {
                    errorMessage += "No correct count \n";
                }
            } catch (ClassCastException e) {
                errorMessage += "No correct fields weight, count, age must be positive whole numbers";
            } catch (NumberFormatException e) {
                errorMessage += "No correct fields weight, count, age \n they must be positive whole numbers";
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
        newLotEntity.setRefTypeLotsByTypeLotsId(getNewTypeLot());
        newLotEntity.setRefKindLotsByKindLotsId(getNewKindLot());
        newLotEntity.setNumber(Integer.parseInt(getNumber().getText()));
        newLotEntity.setStartAge(Integer.parseInt(getAge().getText()));
        newLotEntity.setStartCount(Integer.parseInt(getCount().getText()));
        newLotEntity.setStartWeight(Integer.parseInt(getWeight().getText()));
        setNewEntity(newLotEntity);
    }

    @FXML
    private void handleTypeChoose() {
        getFarm().getConfigDialogController().setLotsDialogController(this);
        getFarm().showEntityOverview(fileType);
    }
    @FXML
    private void handleKindChoose() {
        logger.info("kind choosed");
        getFarm().getConfigDialogController().setLotsDialogController(this);
        logger.info("before farm");
        getFarm().showEntityOverview(fileKind);
    }
    @Override
    public void handleOkDialog() {
        super.handleOkDialog();
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getLotsData().add(getNewLotEntity());
        getFarm().getConfigDialogController().getLotsOverviewController().getEntities().add(getNewLotEntity());
    }

    @Override
    public void editEntity(SuperEntity newEntity) {
        getFarm().getConfigDialogController().getLotsOverviewController().getEntityTable().refresh();
        getFarm().getConfigDialogController().getLotsOverviewController().getEntitiesName().setItems(getFarm().getConfigDialogController().getLotsOverviewController().getEntities());
    }

    public TextField getType() {
        return type;
    }

    public void setType(TextField type) {
        this.type = type;
    }

    public TextField getKind() {
        return kind;
    }

    public TextField getAge() {
        return age;
    }

    public TextField getCount() {
        return count;
    }

    public TextField getWeight() {
        return weight;
    }

    public TextField getNumber() {
        return number;
    }

    public RefLotsEntity getNewLotEntity() {
        return newLotEntity;
    }

    public RefTypeLotsEntity getNewTypeLot() {
        return newTypeLot;
    }

    public void setNewTypeLot(RefTypeLotsEntity newTypeLot) {
        this.newTypeLot = newTypeLot;
    }

    public RefKindLotsEntity getNewKindLot() {
        return newKindLot;
    }

    public void setNewKindLot(RefKindLotsEntity newKindLot) {
        this.newKindLot = newKindLot;
    }


}