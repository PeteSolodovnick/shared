package address;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.RefClassificationEntity;
import models.RefNomenklEntity;

import java.util.*;

public class ClassificationOverviewController {
    @FXML
    private TableView<RefNomenklEntity> productsTable;
    @FXML
    private TableColumn<RefNomenklEntity,Long> idProduct;
    @FXML
    private TableColumn<RefNomenklEntity,String> nameProduct;
    @FXML
    private TreeView<RefClassificationEntity> classificationTree;

    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label classificationLabel;
    @FXML
    private Label sizeLabel;

    private FarmFX farm;
    private Stage referenceStage;
    private TreeItem <RefClassificationEntity> rootItem = new TreeItem("Classification");

    public ClassificationOverviewController(){
    }
    @FXML
    private void initialize() {
     //   System.out.println("initialize");
        idProduct.setCellValueFactory(cellData -> new SimpleObjectProperty<Long>(cellData.getValue().getId()));
        nameProduct.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        classificationTree.setRoot(rootItem);
        showProducts(null);
        productsTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProductsDetails(newValue));
        classificationTree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showProducts(newValue));
    //    System.out.println("End initialize");
    }

    private void initRoot() {
     //   System.out.println("init root");
        for (RefClassificationEntity classification : farm.getClassificationData()) {
      //      System.out.println("Inside for");
            if (classification.getRefClassificationByParentId() == null) {
                TreeItem newRoot = new TreeItem(classification.getName());
                initializeClassification(newRoot, classification);
                rootItem.getChildren().add(newRoot);
            }
            /*try {
                 classification.getRefClassificationByParentId().getId();
            } catch (NullPointerException e) {
                TreeItem newRoot = new TreeItem(classification.getName());
                initializeClassification(newRoot, classification);
                rootItem.getChildren().add(newRoot);
            }*/
        }
    }

    private void initializeClassification(TreeItem root, RefClassificationEntity classification) {
    //    System.out.println("init initialize classification");
        if (classification != null) {
            for (RefClassificationEntity classif : farm.getClassificationData()) {
                try {
                    if (classif.getRefClassificationByParentId().getId() == classification.getId()) {
                        TreeItem newRoot = new TreeItem(classif.getName());
                        initializeClassification(newRoot, classif);
                        root.getChildren().add(newRoot);
                    }
                }catch (NullPointerException e) {

                }
            }
        }
    }

    public void showProducts(TreeItem<RefClassificationEntity> entity) {
        ObservableList<RefNomenklEntity> productsOfClassification = FXCollections.observableArrayList();
        if (entity != null) {
            for (RefNomenklEntity product:farm.getProductsData()) {
                if (product.getRefClassificationByClassificationId().getId() == entity.getValue().getId()) {
                    productsOfClassification.add(product);
                }
            }
            productsTable.setItems(productsOfClassification);
        }

    }

    public void setFarmFX(FarmFX farm) {
        this.farm = farm;
        productsTable.setItems(farm.getProductsData());
        initRoot();
        classificationTree.setRoot(rootItem);
    }

    private void showProductsDetails(RefNomenklEntity productEntity) {
        if (productEntity != null) {
            idLabel.setText(productEntity.getId().toString());
            nameLabel.setText(productEntity.getName());
            classificationLabel.setText(productEntity.getRefClassificationByClassificationId().getName());
            sizeLabel.setText(productEntity.getRefSizeBySizeId().getName());
        } else {
            idLabel.setText("");
            nameLabel.setText("");
            classificationLabel.setText("");
            sizeLabel.setText("");
        }
    }

    public void setReferenceStage(Stage referenceStage) {
        this.referenceStage = referenceStage;
    }
}
