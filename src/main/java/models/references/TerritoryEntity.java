package models.references;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ref_territory", schema = "public", catalog = "farm")
public class TerritoryEntity extends SuperReferenceEntity {

    private Set<CityEntity> refCitiesById = new HashSet<>();

    public void addCity(CityEntity city) {
        city.setRefTerritoryByTerId(this);
        refCitiesById.add(city);
    }

    public TerritoryEntity(){
        super();
    }

    @OneToMany(mappedBy = "refTerritoryByTerId", fetch = FetchType.LAZY)
    public Set<CityEntity> getRefCitiesById() {
        return refCitiesById;
    }

    public void setRefCitiesById(Set<CityEntity> refCitiesById) {
        this.refCitiesById = refCitiesById;
    }

}
