package address.mains;

import address.classifications.*;
import address.contragents.*;
import address.localities.*;
import address.prices.SuperPriceTableController;

public class ConfigDialogController {
    private LocalityOverviewController localityOverviewController;
    private TerritoryTableController territoryTableController;
    private TypeCityTableController typeCityTableController;
    private LocalityDialogController localityDialogController;
    private ContragentOverviewController contragentOverviewController;
    private ContragentDialogController contragentDialogController;
    private SuperCityTableController superCityTableController;
    private TypeContragentTableController typeContragentTableController;
    private KindContragentTableController kindContragentTableController;
    private MarketViewTableController marketViewTableController;
    private SuperPriceTableController superPriceTableController;
    private ProductsOverviewController productsOverviewController;
    private ProductsDialogController productsDialogController;
    private ClassificationDialogController classificationDialogController;
    private ClassificationProductTableController classificationProductTableController;
    private SizeProductsTableController sizeProductsTableController;

    public SizeProductsTableController getSizeProductsTableController() {
        return sizeProductsTableController;
    }

    public void setSizeProductsTableController(SizeProductsTableController sizeProductsTableController) {
        this.sizeProductsTableController = sizeProductsTableController;
    }

    public void setClassificationProductTableController(ClassificationProductTableController classificationProductTableController) {
        this.classificationProductTableController = classificationProductTableController;
    }

    public ClassificationProductTableController getClassificationProductTableController() {
        return classificationProductTableController;
    }

    public ClassificationDialogController getClassificationDialogController() {
        return classificationDialogController;
    }

    public void setClassificationDialogController(ClassificationDialogController classificationDialogController) {
        this.classificationDialogController = classificationDialogController;
    }

    public ProductsDialogController getProductsDialogController() {
        return productsDialogController;
    }

    public void setProductsDialogController(ProductsDialogController productsDialogController) {
        this.productsDialogController = productsDialogController;
    }

    public ProductsOverviewController getProductsOverviewController() {
        return productsOverviewController;
    }

    public void setProductsOverviewController(ProductsOverviewController productsOverviewController) {
        this.productsOverviewController = productsOverviewController;
    }

    public SuperPriceTableController getSuperPriceTableController() {
        return superPriceTableController;
    }

    public void setSuperPriceTableController(SuperPriceTableController superPriceTableController) {
        this.superPriceTableController = superPriceTableController;
    }

    public MarketViewTableController getMarketViewTableController() {
        return marketViewTableController;
    }

    public void setMarketViewTableController(MarketViewTableController marketViewTableController) {
        this.marketViewTableController = marketViewTableController;
    }

    public KindContragentTableController getKindContragentTableController() {
        return kindContragentTableController;
    }

    public void setKindContragentTableController(KindContragentTableController kindContragentTableController) {
        this.kindContragentTableController = kindContragentTableController;
    }

    public TypeContragentTableController getTypeContragentTableController() {
        return typeContragentTableController;
    }

    public void setTypeContragentTableController(TypeContragentTableController typeContragentTableController) {
        this.typeContragentTableController = typeContragentTableController;
    }

    public SuperCityTableController getSuperCityTableController() {
        return superCityTableController;
    }

    public void setSuperCityTableController(SuperCityTableController superCityTableController) {
        this.superCityTableController = superCityTableController;
    }

    public ContragentDialogController getContragentDialogController() {
        return contragentDialogController;
    }

    public void setContragentDialogController(ContragentDialogController contragentDialogController) {
        this.contragentDialogController = contragentDialogController;
    }

    public LocalityDialogController getLocalityDialogController() {
        return localityDialogController;
    }

    public void setLocalityDialogController(LocalityDialogController localityDialogController) {
        this.localityDialogController = localityDialogController;
    }

    public LocalityOverviewController getLocalityOverviewController() {
        return localityOverviewController;
    }

    public void setLocalityOverviewController(LocalityOverviewController localityOverviewController) {
        this.localityOverviewController = localityOverviewController;
    }

    public TerritoryTableController getTerritoryTableController() {
        return territoryTableController;
    }

    public void setTerritoryTableController(TerritoryTableController territoryTableController) {
        this.territoryTableController = territoryTableController;
    }

    public TypeCityTableController getTypeCityTableController() {
        return typeCityTableController;
    }

    public void setTypeCityTableController(TypeCityTableController typeCityTableController) {
        this.typeCityTableController = typeCityTableController;
    }

    public ContragentOverviewController getContragentOverviewController() {
        return contragentOverviewController;
    }

    public void setContragentOverviewController(ContragentOverviewController contragentOverviewController) {
        this.contragentOverviewController = contragentOverviewController;
    }
}
