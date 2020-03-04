package address.mains;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
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
    private TableColumn<SuperEntity, SuperEntity> nameEntity;
    @FXML
    private ComboBox<SuperEntity> entitiesName;

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
            nameEntity.setCellValueFactory(cellData -> new SimpleObjectProperty<SuperEntity>(cellData.getValue()));
            entitiesName.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->showEntities(newValue));
            entitiesName.setConverter(new StringConverter<SuperEntity>(){
                @Override
                public String toString(SuperEntity superEntity) {
                    if (superEntity != null) {
                        return superEntity.toString();
                    }
                    return null;
                }
                @Override
                public SuperEntity fromString(String s) {
                    if (s != null) {
                        for (SuperEntity item : entitiesName.getItems()) {
                            if (item.toString().equals(s)) {
                                return item;
                            }
                        }
                    }
                    //Not sure what to do here, return null if item wasn't found in list or add a new one...
                    //return (new SuperEntity(0,s));
                    return null;
                }
            });
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }
    public void setFarmFX(FarmFX farm) {
        this.farm = farm;
        entityTable.setItems(entities);
        entitiesName.setItems(entities);
        AutoCompleteComboBoxListener autoEntity = new AutoCompleteComboBoxListener(entitiesName);
    }
    @Override
    public void setReferenceStage(Stage referenceStage) {
        this.referenceStage = referenceStage;
    }

    private void showEntities(SuperEntity nameEntity) {
        ObservableList<SuperEntity> entities = FXCollections.observableArrayList();
        if (nameEntity != null) {
            for (SuperEntity someEntity : this.entities) {
                if (someEntity.getName().equals(nameEntity.getName()))
                    entities.add(someEntity);
            }
            entityTable.setItems(entities);
        }
    }

    @FXML
    public void handleDeleteEntity() {
        int selectedId = entityTable.getSelectionModel().getSelectedIndex();
        if (selectedId>=0) {
            SuperEntity selectedEntity = entityTable.getItems().get(selectedId);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete " + nameEntity.getText());
            alert.setHeaderText(nameEntity.getText()+" " + entityTable.getItems().get(selectedId).getName() + " will be deleted");
            alert.setContentText("Are you sure?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EntityService<SuperEntity, Long> service = new EntityService<>();
                try {
                    service.delete(entityTable.getItems().get(selectedId));
                    entities.remove(selectedEntity);
                    deletedFromArray(selectedEntity);
                    entityTable.getItems().remove(selectedEntity);
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
    public abstract void deletedFromArray(SuperEntity selectedEntity);

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
        SuperEntity selectedEntity = entityTable.getSelectionModel().getSelectedItem();
        if (selectedEntity != null) {
            farm.showEntityDialog(selectedEntity, referenceStage, file, title);
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
        SuperEntity selectedEntity = entityTable.getSelectionModel().getSelectedItem();
        if (selectedEntity != null) {
            farm.showEntityDialog(selectedEntity, referenceStage, fileInfo, title);
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

    public ComboBox<SuperEntity> getEntitiesName() {
        return entitiesName;
    }
}
