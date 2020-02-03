package address.mains;

import javafx.scene.control.Alert;
import models.*;
import services.EntityService;

public class FactoryListEntities {
    private FarmFX farm;
    public FactoryListEntities(FarmFX farm) {
        this.farm = farm;
    }
    public void getListEntities(References reference) {
        EntityService service = new EntityService();
        SuperEntity entity = null;
        switch (reference) {
            case CITY:
                entity = new RefCityEntity();
                break;
            case TERRITORY:
                entity = new RefTerritoryEntity();
                break;
            case CONTRAGENT:
                entity = new RefContragentEntity();
                break;
            case TYPE_CITY:
                entity = new RefTypeCityEntity();
                break;
            case TYPE_CONTRAGENT:
                entity = new RefTypeContragentEntity();
                break;
            case PRICE:
                entity = new RefPriceEntity();
                break;
            case MARKET_VIEW:
                entity = new RefMarketViewEntity();
                break;
            case KIND_CONTRAGENT:
                entity = new RefKindContragentEntity();
                break;
            case SIZE:
                entity = new RefSizeEntity();
                break;
            case PRODUCT:
                entity = new RefNomenklEntity();
                break;
            case CLASSIFICATION:
                entity = new RefClassificationEntity();
                break;
            case LOTS:
                entity = new RefLotsEntity();
                break;
            case TYPE_LOTS:
                entity = new RefTypeLotsEntity();
                break;
            case KIND_LOTS:
                entity = new RefKindLotsEntity();
                break;
        }
                for (SuperEntity ent : service.getAllRows(entity)) {
                    switch (reference) {
                        case CITY:
                            farm.getReferences().getCitiesData().add((RefCityEntity) ent);
                            break;
                        case TERRITORY:
                            farm.getReferences().getTerritoryData().add((RefTerritoryEntity) ent);
                            break;
                        case CONTRAGENT:
                            farm.getReferences().getContragentData().add((RefContragentEntity) ent);
                            break;
                        case TYPE_CITY:
                            farm.getReferences().getTypeCityData().add((RefTypeCityEntity) ent);
                            break;
                        case TYPE_CONTRAGENT:
                            farm.getReferences().getTypeContragentData().add((RefTypeContragentEntity) ent);
                            break;
                        case KIND_CONTRAGENT:
                            farm.getReferences().getKindContragentData().add((RefKindContragentEntity) ent);
                            break;
                        case MARKET_VIEW:
                            farm.getReferences().getMarketViewData().add((RefMarketViewEntity) ent);
                            break;
                        case PRICE:
                            farm.getReferences().getPriceData().add((RefPriceEntity) ent);
                            break;
                        case CLASSIFICATION:
                            farm.getReferences().getClassificationData().add((RefClassificationEntity) ent);
                            break;
                        case PRODUCT:
                            farm.getReferences().getProductsData().add((RefNomenklEntity) ent);
                            break;
                        case SIZE:
                            farm.getReferences().getSizeEntitiesData().add((RefSizeEntity) ent);
                            break;
                        case LOTS:
                            farm.getReferences().getLotsData().add((RefLotsEntity) ent);
                            break;
                        case KIND_LOTS:
                            farm.getReferences().getKindLotsData().add((RefKindLotsEntity)ent);
                            break;
                        case TYPE_LOTS:
                            farm.getReferences().getTypeLotsData().add((RefTypeLotsEntity) ent);
                            break;
                    }
                }
    }
}
