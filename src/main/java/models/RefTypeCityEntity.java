package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ref_type_city", schema = "public", catalog = "farm")
public class RefTypeCityEntity extends SuperEntity{
    private Set<RefCityEntity> refCitiesById;

    @OneToMany(mappedBy = "refTypeCityByTypeCityId")
    public Set<RefCityEntity> getRefCitiesById() {
        return refCitiesById;
    }

    public void setRefCitiesById(Set<RefCityEntity> refCitiesById) {
        this.refCitiesById = refCitiesById;
    }
}
