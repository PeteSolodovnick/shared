package address.documents.movies;

import address.mains.FactoryListEntities;
import address.mains.FarmFX;
import address.mains.SuperEntityController;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import models.documents.DocDocsHeadDocEntity;
import models.documents.DocInvoiceHeadDocEntity;
import models.references.*;

import java.time.LocalDate;

public class MovieOverviewController extends SuperEntityController {
    @FXML
    private TableColumn<DocDocsHeadDocEntity, StorageEntity> storeOut;
    @FXML
    private TableColumn<DocDocsHeadDocEntity, StorageEntity> storeIn;
    @FXML
    private TableColumn<DocDocsHeadDocEntity, LocalDate> date;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;

    public MovieOverviewController() {}
    @FXML @Override
    protected void initialize() {
        super.initialize();
        storeOut.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getStorageOutById()));
        storeIn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getStorageInById()));
        date.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getDate()));
    }
    @Override
    public void setFarmFX(FarmFX farm) {
        setFile("/moving_table.fxml");
        setFileInfo("/moving_table_info.fxml");
        initArrays(farm);
        getEntities().addAll(farm.getReferences().getDocsData());
        super.setFarmFX(farm);
    }
    private void initArrays(FarmFX farm) {
        farm.getReferences().setDocsData(new FactoryListEntities<>(new DocDocsHeadDocEntity()).getSomeTypeDateDocs(2L, LocalDate.now().minusWeeks(1), LocalDate.now()));
    }
    @FXML
    public void handleFilter() {
        getEntities().clear();
        getFarm().getReferences().setDocsData(new FactoryListEntities<>(new DocDocsHeadDocEntity()).getSomeTypeDateDocs(2L, startDate.getValue(), endDate.getValue()));
        getEntities().addAll(getFarm().getReferences().getDocsData());
    }
    @FXML
    @Override
    public void handleInfoEntity() {
        getFarm().getConfigDialogController().setMovieOverviewController(this);
        setTitle("Full Movie Information");
        super.handleInfoEntity();
    }
    @Override
    public void handleNewEntity() {
        getFarm().getConfigDialogController().setMovieOverviewController(this);
        setTitle("New Movie document...");
        super.handleNewEntity();
    }
    @Override
    public void deletedFromArray(SuperReferenceEntity selectedEntity) {

    }
}
