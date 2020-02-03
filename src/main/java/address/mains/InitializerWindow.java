package address.mains;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.SuperEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class InitializerWindow {
    private static final Logger logger = LogManager.getLogger();
    private String layoutFXML;
    private Stage stage;
    private Pane layout;

    private FXMLLoader initScene() {
        try {
            FXMLLoader loader = new FXMLLoader();
            logger.info("loader complete");
            loader.setLocation(FarmFX.class.getResource(layoutFXML));
            logger.info(layoutFXML);
            layout = loader.load();
            logger.info("after load");
            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();
            return loader;
        } catch (IOException e) {
            return null;
        }
    }

    public InitializerWindow(String layoutFXML, Stage stage, Pane layout) {
        this.layoutFXML = layoutFXML;
        this.stage = stage;
        this.layout = layout;
    }
    public void initRoot(FarmFX farm) {
            RootLayoutController controller = initScene().getController();
            controller.setFarm(farm);
    }

    public void initLayout(FarmFX farm) {
        logger.info("in init layout");
            ControllerReference controller = initScene().getController();
            logger.info(controller.getClass().getName());
            controller.setFarmFX(farm);
            controller.setReferenceStage(stage);

    }

    public void initLayoutDialog(FarmFX farm, SuperEntity selectedEntity, Stage stage) {
        ControllerDialog controller = initScene().getController();
        controller.setFarmFX(farm, selectedEntity);
        controller.setDialogStage(stage);
    }
}
