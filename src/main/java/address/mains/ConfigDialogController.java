package address.mains;

import address.classifications.*;
import address.contragents.*;
import address.documents.invoices.InvoiceDialogController;
import address.documents.invoices.InvoiceHeadOverviewController;
import address.documents.movies.MovieDialogController;
import address.documents.movies.MovieOverviewController;
import address.localities.*;
import address.lots.KindLotsTableController;
import address.lots.LotsDialogController;
import address.lots.LotsOverviewController;
import address.lots.TypeLotsTableController;
import address.prices.SuperPriceTableController;
import address.stores.StorageCapitalizeController;
import address.stores.StorageOverviewController;

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
    private LotsOverviewController lotsOverviewController;
    private LotsDialogController lotsDialogController;
    private TypeLotsTableController typeLotsTableController;
    private KindLotsTableController kindLotsTableController;
    private StorageOverviewController storageOverviewController;
    private InvoiceHeadOverviewController invoiceHeadOverviewController;
    private InvoiceDialogController invoiceDialogController;
    private StorageCapitalizeController storageCapitalizeController;
    private MovieDialogController movieDialogController;
    private MovieOverviewController movieOverviewController;

    public MovieOverviewController getMovieOverviewController() {
        return movieOverviewController;
    }

    public void setMovieOverviewController(MovieOverviewController movieOverviewController) {
        this.movieOverviewController = movieOverviewController;
    }

    public MovieDialogController getMovieDialogController() {
        return movieDialogController;
    }

    public void setMovieDialogController(MovieDialogController movieDialogController) {
        this.movieDialogController = movieDialogController;
    }

    public StorageCapitalizeController getStorageCapitalizeController() {
        return storageCapitalizeController;
    }

    public void setStorageCapitalizeController(StorageCapitalizeController storageCapitalizeController) {
        this.storageCapitalizeController = storageCapitalizeController;
    }

    public InvoiceDialogController getInvoiceDialogController() {
        return invoiceDialogController;
    }

    public void setInvoiceDialogController(InvoiceDialogController invoiceDialogController) {
        this.invoiceDialogController = invoiceDialogController;
    }

    public InvoiceHeadOverviewController getInvoiceHeadOverviewController() {
        return invoiceHeadOverviewController;
    }

    public void setInvoiceHeadOverviewController(InvoiceHeadOverviewController invoiceHeadOverviewController) {
        this.invoiceHeadOverviewController = invoiceHeadOverviewController;
    }

    public StorageOverviewController getStorageOverviewController() {
        return storageOverviewController;
    }

    public void setStorageOverviewController(StorageOverviewController storageOverviewController) {
        this.storageOverviewController = storageOverviewController;
    }

    public KindLotsTableController getKindLotsTableController() {
        return kindLotsTableController;
    }

    public void setKindLotsTableController(KindLotsTableController kindLotsTableController) {
        this.kindLotsTableController = kindLotsTableController;
    }

    public TypeLotsTableController getTypeLotsTableController() {
        return typeLotsTableController;
    }

    public void setTypeLotsTableController(TypeLotsTableController typeLotsTableController) {
        this.typeLotsTableController = typeLotsTableController;
    }

    public LotsDialogController getLotsDialogController() {
        return lotsDialogController;
    }

    public void setLotsDialogController(LotsDialogController lotsDialogController) {
        this.lotsDialogController = lotsDialogController;
    }

    public LotsOverviewController getLotsOverviewController() {
        return lotsOverviewController;
    }

    public void setLotsOverviewController(LotsOverviewController lotsOverviewController) {
        this.lotsOverviewController = lotsOverviewController;
    }

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
