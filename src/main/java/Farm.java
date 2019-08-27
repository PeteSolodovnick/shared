import models.RefTerritoryEntity;
import models.SuperEntity;
import services.EntityService;

import java.util.ArrayList;
import java.util.List;

public class Farm {
    public static void main(String[] args) {
        List<SuperEntity> territories = new ArrayList<>();
        EntityService service = new EntityService();
        territories = service.getAllRows(new RefTerritoryEntity());
        for (SuperEntity ter:territories) {
            RefTerritoryEntity terr = (RefTerritoryEntity) ter;
            System.out.println(terr.getName());
        }
    /*    Set<RefCityEntity> citiesTemp = new HashSet<RefCityEntity>();
        EntityService territoryServiceTemp = new EntityService();
        RefTerritoryEntity kyivska = new RefTerritoryEntity();
        kyivska.setName("Kyivska");
        RefCityEntity kyiv = new RefCityEntity();
        kyiv.setName("Kyiv");
        kyiv.setRefTerritoryByTerId(kyivska);
        citiesTemp.add(kyiv);
        RefCityEntity vyshneve = new RefCityEntity();
        vyshneve.setName("Vyshneve");
        vyshneve.setRefTerritoryByTerId(kyivska);
        citiesTemp.add(vyshneve);
        kyivska.setRefCitiesById(citiesTemp);
        territoryServiceTemp.create(kyivska);
        territoryServiceTemp.exit(); */
    //    RootForm rootForm = new RootForm();
      //  rootForm.setVisible(true);
    }
}
