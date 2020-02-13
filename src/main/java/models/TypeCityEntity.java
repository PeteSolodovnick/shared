package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ref_type_city", schema = "public", catalog = "farm")
public class TypeCityEntity extends SuperEntity {
    private Set<CityEntity> refCitiesById;

    @OneToMany(mappedBy = "refTypeCityByTypeCityId")
    public Set<CityEntity> getRefCitiesById() {
        return refCitiesById;
    }

    public void setRefCitiesById(Set<CityEntity> refCitiesById) {
        this.refCitiesById = refCitiesById;
    }
}
