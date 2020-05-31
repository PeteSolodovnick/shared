package address.lots;

import address.mains.FarmFX;
import address.mains.SuperDialogEntityController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.*;
import models.references.*;
import models.tables.TableCurrentRestStuffDocEntity;
import org.hibernate.Session;
import services.EntityService;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.Map;

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
    private TextField nomenkl;
    @FXML
    private TextField rest;

    private LotsEntity newLotEntity;
    private TypeLotsEntity newTypeLot;
    private KindLotsEntity newKindLot;
    private NomenklEntity newNomenkl;
    private String fileType;
    private String fileKind;
    private String fileNom;
    private int oldCount = 0;

    public LotsDialogController() {}

    public void setFarmFX(FarmFX farm, SuperReferenceEntity selectedLot) {
        fileType = "/lot'sType.fxml";
        fileKind = "/lotsKind.fxml";
        fileNom = "/lotsNomenklViewer.fxml";
        newLotEntity = (LotsEntity) selectedLot;
        if (selectedLot != null) {
            oldCount = newLotEntity.getStartCount();
            type.setText(newLotEntity.getRefTypeLotsByTypeLotsId().getName());
            kind.setText(newLotEntity.getRefKindLotsByKindLotsId().getName());
            age.setText(String.valueOf(newLotEntity.getStartAge()));
            weight.setText(String.valueOf(newLotEntity.getStartWeight()));
            count.setText(String.valueOf(newLotEntity.getStartCount()));
            nomenkl.setText(String.valueOf(newLotEntity.getRefNomenklEntityById().getName()));
            newTypeLot = newLotEntity.getRefTypeLotsByTypeLotsId();
            newKindLot = newLotEntity.getRefKindLotsByKindLotsId();
            newNomenkl = newLotEntity.getRefNomenklEntityById();
            EntityService<SuperEntity, Long> service = new EntityService<>();
            Map<String, Long> keys = new HashMap<>();
            keys.put("nomenklEntityByNomId", newLotEntity.getRefNomenklEntityById().getId());
            keys.put("storageEntityById", 1L);
            rest.setText(String.valueOf(((TableCurrentRestStuffDocEntity)service.readRow(new TableCurrentRestStuffDocEntity(), keys)).getQty() + oldCount));

            setNew(false);
        } else {
            newLotEntity = new LotsEntity();
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
            if (nomenkl.getText() == null || nomenkl.getText().length() == 0) {
                errorMessage += "No valid nomenkl\n";
            }
            try {
                if (Float.parseFloat(weight.getText()) <= 0) {
                    errorMessage += "No correct weight\n";
                }
                if (Integer.parseInt(age.getText()) <= 0) {
                    errorMessage += "No correct age\n";
                }
                if (Integer.parseInt(count.getText()) <=0 || Integer.parseInt(count.getText()) > Integer.parseInt(rest.getText())) {
                    errorMessage += "No correct count \n";
                }
            } catch (ClassCastException e) {
                errorMessage += "No correct fields weight, count, age must be positive whole numbers";
            } catch (NumberFormatException e) {
                errorMessage += "No correct fields weight, count, age \n they must be positive whole numbers";
            }
            return !isError(errorMessage);
        }
        return false;
    }
    @Override
    protected void createEntity() {
        super.createEntity();
        newLotEntity.setRefTypeLotsByTypeLotsId(getNewTypeLot());
        newLotEntity.setRefKindLotsByKindLotsId(getNewKindLot());
        newLotEntity.setRefNomenklEntityById(getNewNomenkl());
        newLotEntity.setStartAge(Integer.parseInt(getAge().getText()));
        newLotEntity.setStartCount(Integer.parseInt(getCount().getText()));
        newLotEntity.setStartWeight(Float.parseFloat(getWeight().getText()));
        newLotEntity.setEditable(true);
        newLotEntity.setDeleted(false);
        setNewEntity(newLotEntity);
    }

    @FXML
    private void handleTypeChoose() {
        getFarm().getConfigDialogController().setLotsDialogController(this);
        getFarm().showEntityOverview(fileType);
    }
    @FXML
    private void handleKindChoose() {
        getFarm().getConfigDialogController().setLotsDialogController(this);
        getFarm().showEntityOverview(fileKind);
    }
    @FXML
    private void handleNomenklChoose() {
        getFarm().getConfigDialogController().setLotsDialogController(this);
        getFarm().showEntityOverview(fileNom);
    }

    @Override
    public void handleOkDialog() {
        if (isInputValid()) {
            createEntity();
            EntityService<SuperEntity, Long> service = new EntityService<>();
            Session session = service.getEntity().getSessionFactory().openSession();
            Map<String, Long> keys = new HashMap<>();
            TableCurrentRestStuffDocEntity mainRest;
            TableCurrentRestStuffDocEntity reserveRest = new TableCurrentRestStuffDocEntity();
         //   StorageEntity storeOutSelect = session.load(StorageEntity.class, 1L);
            StorageEntity storeInSelect = session.load(StorageEntity.class, 14L);
            session.beginTransaction();
            keys.put("nomenklEntityByNomId", newLotEntity.getRefNomenklEntityById().getId());
            if (!super.isNew()) {
                keys.put("storageEntityById", 14L);
                reserveRest = (TableCurrentRestStuffDocEntity) service.readRow(new TableCurrentRestStuffDocEntity(), keys);
                float price = reserveRest.getSum() / reserveRest.getQty();
                int addQty = oldCount - Integer.parseInt(count.getText());
                float addSum = addQty*price;
                reserveRest.setQty(reserveRest.getQty() - addQty);
                reserveRest.setSum(reserveRest.getSum() - addSum);
                keys.replace("storageEntityById", 14L, 1L);
                mainRest = (TableCurrentRestStuffDocEntity) service.readRow(new TableCurrentRestStuffDocEntity(), keys);
                mainRest.setQty(mainRest.getQty() + addQty);
                mainRest.setSum(mainRest.getSum() + addSum);
            } else {
                keys.put("storageEntityById", 1L);
                mainRest = (TableCurrentRestStuffDocEntity) service.readRow(new TableCurrentRestStuffDocEntity(), keys);
                float price = mainRest.getSum() / mainRest.getQty();
                mainRest.setQty(mainRest.getQty() - newLotEntity.getStartCount());
                mainRest.setSum(mainRest.getSum() - newLotEntity.getStartCount()*price);
                keys.replace("storageEntityById", 1L, 14L);
                try {
                    reserveRest = (TableCurrentRestStuffDocEntity) service.readRow(new TableCurrentRestStuffDocEntity(), keys);
                    reserveRest.setQty(reserveRest.getQty() + newLotEntity.getStartCount());
                    reserveRest.setSum(reserveRest.getSum() + newLotEntity.getStartCount() * price);
                } catch(NoResultException e) {
                    reserveRest.setStorageEntityById(storeInSelect);
                    reserveRest.setNomenklEntityByNomId(newLotEntity.getRefNomenklEntityById());
                    reserveRest.setQty(newLotEntity.getStartCount());
                    reserveRest.setSum(newLotEntity.getStartCount() * price);
                }
            }
            session.saveOrUpdate(mainRest);
            session.saveOrUpdate(reserveRest);
            session.saveOrUpdate(newLotEntity);
            session.getTransaction().commit();
            session.close();
            getDialogStage().close();
            if (super.isNew()) {
                getFarm().getConfigDialogController().getLotsOverviewController().getEntities().add(newLotEntity);
            }
            getFarm().getConfigDialogController().getLotsOverviewController().getEntityTable().refresh();
        }
    }

    @Override
    public void newEntity() {
        getFarm().getReferences().getLotsData().add(getNewLotEntity());
        getFarm().getConfigDialogController().getLotsOverviewController().getEntities().add(getNewLotEntity());

    }

    @Override
    public void editEntity(SuperReferenceEntity newEntity) {
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

    public LotsEntity getNewLotEntity() {
        return newLotEntity;
    }

    public TypeLotsEntity getNewTypeLot() {
        return newTypeLot;
    }

    public void setNewTypeLot(TypeLotsEntity newTypeLot) {
        this.newTypeLot = newTypeLot;
    }

    public KindLotsEntity getNewKindLot() {
        return newKindLot;
    }

    public void setNewKindLot(KindLotsEntity newKindLot) {
        this.newKindLot = newKindLot;
    }

    public NomenklEntity getNewNomenkl() {
        return newNomenkl;
    }

    public void setNewNomenkl(NomenklEntity newNomenkl) {
        this.newNomenkl = newNomenkl;
    }

    public TextField getNomenkl() {
        return nomenkl;
    }

    public TextField getRest() {
        return rest;
    }
}
