package address.mains;

import address.documents.invoices.DocInvoiceHeadDocEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;

public class CollectionsEntities {
    private ObservableList<RefTerritoryEntity> territoryData = FXCollections.observableArrayList();
    private ObservableList<RefCityEntity> citiesData = FXCollections.observableArrayList();
    private ObservableList<RefTypeCityEntity> typeCityData = FXCollections.observableArrayList();
    private ObservableList<RefContragentEntity> contragentData = FXCollections.observableArrayList();
    private ObservableList<RefTypeContragentEntity> typeContragentData = FXCollections.observableArrayList();
    private ObservableList<RefPriceEntity> priceData = FXCollections.observableArrayList();
    private ObservableList<RefMarketViewEntity> marketViewData = FXCollections.observableArrayList();
    private ObservableList<RefNomenklEntity> productsData = FXCollections.observableArrayList();
    private ObservableList<RefClassificationEntity> classificationData = FXCollections.observableArrayList();
    private ObservableList<RefSizeEntity> sizeEntitiesData = FXCollections.observableArrayList();
    private ObservableList<RefKindContragentEntity> kindContragentData = FXCollections.observableArrayList();
    private ObservableList<RefKindLotsEntity> kindLotsData = FXCollections.observableArrayList();
    private ObservableList<RefTypeLotsEntity> typeLotsData = FXCollections.observableArrayList();
    private ObservableList<RefLotsEntity> lotsData = FXCollections.observableArrayList();
    private ObservableList<RefStorageEntity> storageData = FXCollections.observableArrayList();
    private ObservableList<DocInvoiceHeadDocEntity> invoiceData = FXCollections.observableArrayList();

    public ObservableList<DocInvoiceHeadDocEntity> getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(ObservableList<DocInvoiceHeadDocEntity> invoiceData) {
        this.invoiceData = invoiceData;
    }

    public ObservableList<RefStorageEntity> getStorageData() {
        return storageData;
    }

    public ObservableList<RefKindLotsEntity> getKindLotsData() {
        return kindLotsData;
    }

    public ObservableList<RefTypeLotsEntity> getTypeLotsData() {
        return typeLotsData;
    }

    public ObservableList<RefLotsEntity> getLotsData() {
        return lotsData;
    }

    public ObservableList<RefKindContragentEntity> getKindContragentData() {
        return kindContragentData;
    }

    public ObservableList<RefTerritoryEntity> getTerritoryData() {
        return territoryData;
    }

    public ObservableList<RefCityEntity> getCitiesData() {
        return citiesData;
    }

    public ObservableList<RefTypeCityEntity> getTypeCityData() {
        return typeCityData;
    }

    public ObservableList<RefContragentEntity> getContragentData() {
        return contragentData;
    }

    public ObservableList<RefTypeContragentEntity> getTypeContragentData() {
        return typeContragentData;
    }

    public ObservableList<RefPriceEntity> getPriceData() {
        return priceData;
    }

    public ObservableList<RefMarketViewEntity> getMarketViewData() {
        return marketViewData;
    }

    public ObservableList<RefNomenklEntity> getProductsData() {
        return productsData;
    }

    public ObservableList<RefClassificationEntity> getClassificationData() {
        return classificationData;
    }

    public ObservableList<RefSizeEntity> getSizeEntitiesData() {
        return sizeEntitiesData;
    }

    public void setTerritoryData(ObservableList<RefTerritoryEntity> territoryData) {
        this.territoryData = territoryData;
    }

    public void setCitiesData(ObservableList<RefCityEntity> citiesData) {
        this.citiesData = citiesData;
    }

    public void setTypeCityData(ObservableList<RefTypeCityEntity> typeCityData) {
        this.typeCityData = typeCityData;
    }

    public void setContragentData(ObservableList<RefContragentEntity> contragentData) {
        this.contragentData = contragentData;
    }

    public void setTypeContragentData(ObservableList<RefTypeContragentEntity> typeContragentData) {
        this.typeContragentData = typeContragentData;
    }

    public void setPriceData(ObservableList<RefPriceEntity> priceData) {
        this.priceData = priceData;
    }

    public void setMarketViewData(ObservableList<RefMarketViewEntity> marketViewData) {
        this.marketViewData = marketViewData;
    }

    public void setProductsData(ObservableList<RefNomenklEntity> productsData) {
        this.productsData = productsData;
    }

    public void setClassificationData(ObservableList<RefClassificationEntity> classificationData) {
        this.classificationData = classificationData;
    }

    public void setSizeEntitiesData(ObservableList<RefSizeEntity> sizeEntitiesData) {
        this.sizeEntitiesData = sizeEntitiesData;
    }

    public void setKindContragentData(ObservableList<RefKindContragentEntity> kindContragentData) {
        this.kindContragentData = kindContragentData;
    }

    public void setKindLotsData(ObservableList<RefKindLotsEntity> kindLotsData) {
        this.kindLotsData = kindLotsData;
    }

    public void setTypeLotsData(ObservableList<RefTypeLotsEntity> typeLotsData) {
        this.typeLotsData = typeLotsData;
    }

    public void setLotsData(ObservableList<RefLotsEntity> lotsData) {
        this.lotsData = lotsData;
    }

    public void setStorageData(ObservableList<RefStorageEntity> storageData) {
        this.storageData = storageData;
    }
}
