package address.mains;

import javafx.fxml.FXML;
import models.SuperEntity;

public abstract class SuperTableEntityController extends SuperEntityController {
    private int countCLick = 0;

    public SuperTableEntityController() {}

    @Override
    public void setFarmFX(FarmFX farm) {
        super.setFarmFX(farm);
    }

    @FXML
    protected void handleChooseTer() {
        countCLick++;
        if (countCLick == 2) {
            setTextEdit();
            getReferenceStage().close();
        }
    }
    public abstract void setTextEdit();

    @FXML
    public void handleMouseMoved() {
        countCLick = 0;
    }
}
