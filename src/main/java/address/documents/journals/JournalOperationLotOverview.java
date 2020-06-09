package address.documents.journals;

import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import address.mains.SuperEntityController;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import models.references.LotsEntity;
import models.references.RefTypeOperationsDocEntity;
import models.references.SuperReferenceEntity;
import models.tables.JournalOperationsLotsDocEntity;

import java.time.LocalDate;

public class JournalOperationLotOverview extends SuperEntityController {
    @FXML
    private TableColumn<JournalOperationsLotsDocEntity, LotsEntity> lot;
    @FXML
    private TableColumn<JournalOperationsLotsDocEntity, LocalDate> date;
    @FXML
    private TableColumn<JournalOperationsLotsDocEntity, RefTypeOperationsDocEntity> typeOperation;
    @FXML
    private TableColumn<JournalOperationsLotsDocEntity, Float> sum;
    @FXML
    private TableColumn<JournalOperationsLotsDocEntity, Boolean> editable;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private Button deliver;
    private JournalOperationsLotsDocEntity selectedJournal;

    public JournalOperationLotOverview() {}

    @FXML @Override
    protected void initialize() {
        super.initialize();
        lot.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefLotsId()));
        date.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRecTime()));
        typeOperation.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getRefTypeOperationsByTypeOperationsId()));
        sum.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getSum()));
        editable.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getEditable()));
        getEntityTable().getSelectionModel().getSelectedCells().addListener((InvalidationListener) observable -> showButton());
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/journalOperation_table.fxml");
        setFileInfo("/journalOperation_table_info.fxml");
        initArrays(farm);
        getEntities().addAll(farm.getReferences().getJournalOperationsLotsDocEntities());
        super.setFarmFX(farm);
    }
    private void initArrays(FarmFX farm) {
        farm.getReferences().setJournalOperationsLotsDocEntities(new FactoryListEntities<>(new JournalOperationsLotsDocEntity()).getDateListEntities("rec_time"));
    }
    public void showButton() {
        setSelectedJournal((JournalOperationsLotsDocEntity) getEntityTable().getSelectionModel().getSelectedItem());
        deliver.setDisable(!getSelectedJournal().getEditable());
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setJournalOperationLotOverview(this);
        setTitle("New journak...");
        super.handleNewEntity();
    }
    @Override
    public void handleEditEntity() {
        setSelectedJournal((JournalOperationsLotsDocEntity) getEntityTable().getSelectionModel().getSelectedItem());
        if (getSelectedJournal().getEditable()) {
            getFarm().getConfigDialogController().setJournalOperationLotOverview(this);
            setTitle("Edit Journal...");
            super.handleEditEntity();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getFarm().getPrimaryStage());
            alert.setTitle("Sorry,");
            alert.setHeaderText("You can't edit this journal");
            alert.setContentText("Please select an other element in the table for editing");
            alert.showAndWait();
        }
    }
    @Override
    public void handleDeleteEntity() {
        setSelectedJournal((JournalOperationsLotsDocEntity) getEntityTable().getSelectionModel().getSelectedItem());
        if (getSelectedJournal().getEditable()) {
            super.handleDeleteEntity();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getFarm().getPrimaryStage());
            alert.setTitle("Sorry,");
            alert.setHeaderText("You can't delete this journal");
            alert.setContentText("Please select an other element in the table for editing");
            alert.showAndWait();
        }

    }
    @Override
    public void handleInfoEntity() {
        getFarm().getConfigDialogController().setJournalOperationLotOverview(this);
        setTitle("Full Journal Information");
        super.handleInfoEntity();
    }
    public void handleDelivery() {
        setSelectedJournal((JournalOperationsLotsDocEntity) getEntityTable().getSelectionModel().getSelectedItem());
        if (getSelectedJournal() != null) {
            if (getSelectedJournal().getEditable()) {

            }
        }
    }
    @Override
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {
        getFarm().getReferences().getJournalOperationsLotsDocEntities().remove(selectedEntity);
    }
    @FXML
    public void handleFilter() {
        try {
            getEntities().clear();
            getFarm().getReferences().setJournalOperationsLotsDocEntities(new FactoryListEntities<>(new JournalOperationsLotsDocEntity()).getSetDateListEntities(startDate.getValue(), endDate.getValue(),"rec_time"));
            getEntities().addAll(getFarm().getReferences().getJournalOperationsLotsDocEntities());
        } catch (Exception e) {

        }
    }

    public JournalOperationsLotsDocEntity getSelectedJournal() {
        return selectedJournal;
    }

    public void setSelectedJournal(JournalOperationsLotsDocEntity selectedJournal) {
        this.selectedJournal = selectedJournal;
    }
}
