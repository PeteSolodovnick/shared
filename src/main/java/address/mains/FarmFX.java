package address.mains;

import javafx.application.Application;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.SuperEntity;
import models.references.SuperReferenceEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.util.prefs.Preferences;

public class FarmFX extends Application {
    public static final Logger logger = LogManager.getLogger();
    public static final double vat = 20.0;
    private final String rootLayoutFXML = "/rootLayout.fxml";
    private Stage primaryStage;
    private CollectionsEntities references;
    private ConfigDialogController configDialogController;

    public static void main(String[] args) {
        launch(args);
    }

    public FarmFX(){


    }
    @Override
    public void start(Stage primaryStage) {
        references = new CollectionsEntities();
        configDialogController = new ConfigDialogController();
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FarmApp");
        initRootLayout();
    }

    public void initRootLayout() {
            InitializerWindow rootWindow = new InitializerWindow(rootLayoutFXML,primaryStage,new BorderPane());
            rootWindow.initRoot(this);
    }
    public void showEntityOverview(String file) {
            Stage referenceStage = new Stage();
            referenceStage.setTitle("View References");
            referenceStage.initModality(Modality.WINDOW_MODAL);
            referenceStage.initOwner(primaryStage);
            InitializerWindow referenceWindow = new InitializerWindow(file, referenceStage, new AnchorPane());
            referenceWindow.initLayout(this);
    }
    public void showEntityOverview(SuperReferenceEntity selectedEntity, Stage ownerStage, String file, String title) {
        Stage referenceStage = new Stage();
        referenceStage.setTitle("View entities");
        referenceStage.initModality(Modality.WINDOW_MODAL);
        referenceStage.initOwner(ownerStage);
        InitializerWindow referenceWindow = new InitializerWindow(file, referenceStage, new AnchorPane());
        referenceWindow.initLayout(this, selectedEntity, referenceStage);
    }
    public void showEntityDialog(SuperReferenceEntity selectedEntity, Stage ownerStage, String file, String title) {
        Stage dialogStage = new Stage();
        dialogStage.setTitle(title);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(ownerStage);
        InitializerWindow referenceWindow = new InitializerWindow(file, dialogStage, new AnchorPane());
        referenceWindow.initLayoutDialog(this,selectedEntity,dialogStage);
    }

    public CollectionsEntities getReferences() {
        return references;
    }

    public void setReferences(CollectionsEntities references) {
        this.references = references;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(FarmFX.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(FarmFX.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }

    public ConfigDialogController getConfigDialogController() {
        return configDialogController;
    }

    public void setConfigDialogController(ConfigDialogController configDialogController) {
        this.configDialogController = configDialogController;
    }
}
