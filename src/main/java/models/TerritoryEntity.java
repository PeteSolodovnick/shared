package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ref_territory", schema = "public", catalog = "farm")
public class TerritoryEntity extends SuperEntity {

    private Set<CityEntity> refCitiesById;
    public TerritoryEntity(){
        super();
    }

    @OneToMany(mappedBy = "refTerritoryByTerId")
    public Set<CityEntity> getRefCitiesById() {
        return refCitiesById;
    }

    public void setRefCitiesById(Set<CityEntity> refCitiesById) {
        this.refCitiesById = refCitiesById;
    }

}
