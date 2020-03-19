package address.mains;

import models.references.RefKindDocDocEntity;
import models.references.RefTypeDocDocEntity;
import models.tables.TableCurrentRestStuffDocEntity;
import models.documents.DocInvoiceHeadDocEntity;
import models.references.RefStatusInvoiceDocEntity;
import models.tables.TableInvoiceNomDocEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.references.*;

public class CollectionsEntities {
    private ObservableList<TerritoryEntity> territoryData = FXCollections.observableArrayList();
    private ObservableList<CityEntity> citiesData = FXCollections.observableArrayList();
    private ObservableList<TypeCityEntity> typeCityData = FXCollections.observableArrayList();
    private ObservableList<ContragentEntity> contragentData = FXCollections.observableArrayList();
    private ObservableList<TypeContragentEntity> typeContragentData = FXCollections.observableArrayList();
    private ObservableList<PriceEntity> priceData = FXCollections.observableArrayList();
    private ObservableList<MarketViewEntity> marketViewData = FXCollections.observableArrayList();
    private ObservableList<NomenklEntity> productsData = FXCollections.observableArrayList();
    private ObservableList<ClassificationEntity> classificationData = FXCollections.observableArrayList();
    private ObservableList<SizeEntity> sizeEntitiesData = FXCollections.observableArrayList();
    private ObservableList<KindContragentEntity> kindContragentData = FXCollections.observableArrayList();
    private ObservableList<KindLotsEntity> kindLotsData = FXCollections.observableArrayList();
    private ObservableList<TypeLotsEntity> typeLotsData = FXCollections.observableArrayList();
    private ObservableList<LotsEntity> lotsData = FXCollections.observableArrayList();
    private ObservableList<StorageEntity> storageData = FXCollections.observableArrayList();
    private ObservableList<DocInvoiceHeadDocEntity> invoiceData = FXCollections.observableArrayList();
    private ObservableList<RefStatusInvoiceDocEntity> statusInvoiceData = FXCollections.observableArrayList();
    private ObservableList<TableInvoiceNomDocEntity> tableInvoiceData = FXCollections.observableArrayList();
    private ObservableList<RefTypeDocDocEntity> typeDocDocEntities = FXCollections.observableArrayList();
    private ObservableList<RefKindDocDocEntity> kindDocDocEntities = FXCollections.observableArrayList();
    private ObservableList<TableCurrentRestStuffDocEntity> currentRestStuffData = FXCollections.observableArrayList();

    public ObservableList<TableCurrentRestStuffDocEntity> getCurrentRestStuffData() {
        return currentRestStuffData;
    }

    public void setCurrentRestStuffData(ObservableList<TableCurrentRestStuffDocEntity> currentRestStuffData) {
        this.currentRestStuffData = currentRestStuffData;
    }

    public ObservableList<RefTypeDocDocEntity> getTypeDocDocEntities() {
        return typeDocDocEntities;
    }

    public void setTypeDocDocEntities(ObservableList<RefTypeDocDocEntity> typeDocDocEntities) {
        this.typeDocDocEntities = typeDocDocEntities;
    }

    public ObservableList<RefKindDocDocEntity> getKindDocDocEntities() {
        return kindDocDocEntities;
    }

    public void setKindDocDocEntities(ObservableList<RefKindDocDocEntity> kindDocDocEntities) {
        this.kindDocDocEntities = kindDocDocEntities;
    }

    public ObservableList<TableInvoiceNomDocEntity> getTableInvoiceData() {
        return tableInvoiceData;
    }

    public void setTableInvoiceData(ObservableList<TableInvoiceNomDocEntity> tableInvoiceData) {
        this.tableInvoiceData = tableInvoiceData;
    }

    public ObservableList<RefStatusInvoiceDocEntity> getStatusInvoiceData() {
        return statusInvoiceData;
    }

    public void setStatusInvoiceData(ObservableList<RefStatusInvoiceDocEntity> statusInvoiceData) {
        this.statusInvoiceData = statusInvoiceData;
    }

    public ObservableList<DocInvoiceHeadDocEntity> getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(ObservableList<DocInvoiceHeadDocEntity> invoiceData) {
        this.invoiceData = invoiceData;
    }

    public ObservableList<StorageEntity> getStorageData() {
        return storageData;
    }

    public ObservableList<KindLotsEntity> getKindLotsData() {
        return kindLotsData;
    }

    public ObservableList<TypeLotsEntity> getTypeLotsData() {
        return typeLotsData;
    }

    public ObservableList<LotsEntity> getLotsData() {
        return lotsData;
    }

    public ObservableList<KindContragentEntity> getKindContragentData() {
        return kindContragentData;
    }

    public ObservableList<TerritoryEntity> getTerritoryData() {
        return territoryData;
    }

    public ObservableList<CityEntity> getCitiesData() {
        return citiesData;
    }

    public ObservableList<TypeCityEntity> getTypeCityData() {
        return typeCityData;
    }

    public ObservableList<ContragentEntity> getContragentData() {
        return contragentData;
    }

    public ObservableList<TypeContragentEntity> getTypeContragentData() {
        return typeContragentData;
    }

    public ObservableList<PriceEntity> getPriceData() {
        return priceData;
    }

    public ObservableList<MarketViewEntity> getMarketViewData() {
        return marketViewData;
    }

    public ObservableList<NomenklEntity> getProductsData() {
        return productsData;
    }

    public ObservableList<ClassificationEntity> getClassificationData() {
        return classificationData;
    }

    public ObservableList<SizeEntity> getSizeEntitiesData() {
        return sizeEntitiesData;
    }

    public void setTerritoryData(ObservableList<TerritoryEntity> territoryData) {
        this.territoryData = territoryData;
    }

    public void setCitiesData(ObservableList<CityEntity> citiesData) {
        this.citiesData = citiesData;
    }

    public void setTypeCityData(ObservableList<TypeCityEntity> typeCityData) {
        this.typeCityData = typeCityData;
    }

    public void setContragentData(ObservableList<ContragentEntity> contragentData) {
        this.contragentData = contragentData;
    }

    public void setTypeContragentData(ObservableList<TypeContragentEntity> typeContragentData) {
        this.typeContragentData = typeContragentData;
    }

    public void setPriceData(ObservableList<PriceEntity> priceData) {
        this.priceData = priceData;
    }

    public void setMarketViewData(ObservableList<MarketViewEntity> marketViewData) {
        this.marketViewData = marketViewData;
    }

    public void setProductsData(ObservableList<NomenklEntity> productsData) {
        this.productsData = productsData;
    }

    public void setClassificationData(ObservableList<ClassificationEntity> classificationData) {
        this.classificationData = classificationData;
    }

    public void setSizeEntitiesData(ObservableList<SizeEntity> sizeEntitiesData) {
        this.sizeEntitiesData = sizeEntitiesData;
    }

    public void setKindContragentData(ObservableList<KindContragentEntity> kindContragentData) {
        this.kindContragentData = kindContragentData;
    }

    public void setKindLotsData(ObservableList<KindLotsEntity> kindLotsData) {
        this.kindLotsData = kindLotsData;
    }

    public void setTypeLotsData(ObservableList<TypeLotsEntity> typeLotsData) {
        this.typeLotsData = typeLotsData;
    }

    public void setLotsData(ObservableList<LotsEntity> lotsData) {
        this.lotsData = lotsData;
    }

    public void setStorageData(ObservableList<StorageEntity> storageData) {
        this.storageData = storageData;
    }
}
