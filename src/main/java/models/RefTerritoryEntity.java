package models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ref_territory", schema = "public", catalog = "farm")
public class RefTerritoryEntity extends SuperEntity {

    private Set<RefCityEntity> refCitiesById;
    public RefTerritoryEntity(){
        super();
    }

    @OneToMany(mappedBy = "refTerritoryByTerId")
    public Set<RefCityEntity> getRefCitiesById() {
        return refCitiesById;
    }

    public void setRefCitiesById(Set<RefCityEntity> refCitiesById) {
        this.refCitiesById = refCitiesById;
    }

}
