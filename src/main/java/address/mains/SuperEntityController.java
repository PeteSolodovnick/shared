package address.mains;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.EntityService;

import java.util.Optional;

public abstract class SuperEntityController implements ControllerReference {
    protected static final Logger logger = LogManager.getLogger();
    @FXML
    private TableView<SuperEntity> entityTable;
    @FXML
    private TableColumn<SuperEntity,Long> idEntity;
    @FXML
    private TableColumn<SuperEntity,String> nameEntity;
    @FXML
    private ComboBox<String> entitiesName;

    private Stage referenceStage;
    private FarmFX farm;

    private String file;
    private String fileInfo;
    private String title;
    private ObservableList<SuperEntity> entities = FXCollections.observableArrayList();

    public SuperEntityController(){

    }

    @FXML
    protected void initialize() {
        try {
            idEntity.setCellValueFactory(cellData -> new SimpleObjectProperty<Long>(cellData.getValue().getId()));
            nameEntity.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
            entitiesName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->showEntities(newValue));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
    public void setFarmFX(FarmFX farm) {
        this.farm = farm;
        entityTable.setItems(entities);
        for (SuperEntity entity:this.entities)
            entitiesName.getItems().add(entity.getName());
        AutoCompleteComboBoxListener autoEntity = new AutoCompleteComboBoxListener(entitiesName);
    }
    @Override
    public void setReferenceStage(Stage referenceStage) {
        this.referenceStage = referenceStage;
    }

    private void showEntities(String nameEntity) {
        ObservableList<SuperEntity> entities = FXCollections.observableArrayList();
        if (nameEntity != null) {
                for (SuperEntity someEntity : this.entities) {
                    logger.info(someEntity.getClass().getName());
                    switch (someEntity.getClass().getName()) {
                        case "models.RefCityEntity":
                            RefCityEntity cityEntity = (RefCityEntity) someEntity;
                            if (cityEntity.getName().equals(nameEntity)) {
                                entities.add(cityEntity);
                            }
                            break;
                        case "models.RefTerritoryEntity":
                            RefTerritoryEntity territoryEntity = (RefTerritoryEntity) someEntity;
                            if (territoryEntity.getName().equals(nameEntity)) {
                                entities.add(territoryEntity);
                            }
                            break;
                        case "models.RefContragentEntity":
                            RefContragentEntity contragentEntity = (RefContragentEntity) someEntity;
                            if (contragentEntity.getName().equals(nameEntity)) {
                                entities.add(contragentEntity);
                            }
                            break;
                        case "models.RefTypeCityEntity":
                            RefTypeCityEntity typeCity = (RefTypeCityEntity) someEntity;
                            if (typeCity.getName().equals(nameEntity)) {
                                entities.add(typeCity);
                            }
                            break;
                        case "models.RefSizeEntity":
                            RefSizeEntity size = (RefSizeEntity) someEntity;
                            if (size.getName().equals(nameEntity)) {
                                entities.add(size);
                            }
                            break;
                        case "models.RefTypeContragentEntity":
                            RefTypeContragentEntity typeContra = (RefTypeContragentEntity) someEntity;
                            if (typeContra.getName().equals(nameEntity)) {
                                entities.add(typeContra);
                            }
                            break;
                        case "models.RefPriceEntity":
                            RefPriceEntity price = (RefPriceEntity) someEntity;
                            if (price.getName().equals(nameEntity)) {
                                entities.add(price);
                            }
                            break;
                        case "models.RefNomenklEntity":
                            RefNomenklEntity nomenkl = (RefNomenklEntity) someEntity;
                            if (nomenkl.getName().equals(nameEntity)) {
                                entities.add(nomenkl);
                            }
                            break;
                        case "models.RefMarketViewEntity":
                            RefMarketViewEntity marketView = (RefMarketViewEntity) someEntity;
                            if (marketView.getName().equals(nameEntity)) {
                                entities.add(marketView);
                            }
                            break;
                        case "models.RefKindContrgentEntity":
                            RefKindContragentEntity kindContragentEntity = (RefKindContragentEntity) someEntity;
                            if (kindContragentEntity.getName().equals(nameEntity)) {
                                entities.add(kindContragentEntity);
                            }
                            break;
                        case "models.RefClassificationEntity":
                            RefClassificationEntity classificationEntity = (RefClassificationEntity) someEntity;
                            if (classificationEntity.getName().equals(nameEntity)) {
                                entities.add(classificationEntity);
                            }
                            break;
                    }
                }
                entityTable.setItems(entities);
        }
    }

    @FXML
    public void handleDeleteEntity() {
        int selectedId = entityTable.getSelectionModel().getSelectedIndex();
        if (selectedId>=0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete " + nameEntity.getText());
            alert.setHeaderText(nameEntity.getText()+" " + entityTable.getItems().get(selectedId).getName() + " will be deleted");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EntityService service = new EntityService();
                try {
                    service.delete(entityTable.getItems().get(selectedId));
                    entities.remove(selectedId);
                    deletedFromArray(selectedId);
                } catch (Exception e) {
                    Alert exAlert = new Alert(Alert.AlertType.ERROR);
                    exAlert.setTitle("Error!");
                    exAlert.setHeaderText("Object couldn't be deleted");
                    exAlert.setContentText("Object can't be deleted because others objects\n\n have references to it.");
                    exAlert.showAndWait();
                }
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(farm.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No "+nameEntity.getText()+" Selected");
            alert.setContentText("Please select a "+nameEntity.getText()+" in the table for deleting");
            alert.showAndWait();
        }
    }
    public abstract void deletedFromArray(int id);

    @FXML
    public void handleNewEntity() {
        farm.showEntityDialog(null, referenceStage,file,title);
    }

    @FXML @Override
    public void handleCloseReference() {
        referenceStage.close();
    }

    @FXML
    public void handleEditEntity() {
        SuperEntity entity = entityTable.getSelectionModel().getSelectedItem();
        if (entity != null) {
            logger.info(entity.getName());
            farm.showEntityDialog(entity, referenceStage, file, title);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(farm.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No one element Selected");
            alert.setContentText("Please select an element in the table for editing");
            alert.showAndWait();
        }
    }
    @FXML
    public void handleInfoEntity() {
        SuperEntity entity = entityTable.getSelectionModel().getSelectedItem();
        if (entity != null) {
            farm.showEntityDialog(entity, referenceStage, fileInfo, title);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(farm.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No one element Selected");
            alert.setContentText("Please select an element in the table for seeing information about it");
            alert.showAndWait();
        }

    }

    public ObservableList<SuperEntity> getEntities() {
        return entities;
    }

    public TableView<SuperEntity> getEntityTable() {
        return entityTable;
    }

    public Stage getReferenceStage() {
        return referenceStage;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FarmFX getFarm() {
        return farm;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }
}
