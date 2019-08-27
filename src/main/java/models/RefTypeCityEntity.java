package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ref_type_city", schema = "public", catalog = "farm")
public class RefTypeCityEntity extends SuperEntity{
    private String name;
    private Set<RefCityEntity> refCitiesById;

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefTypeCityEntity that = (RefTypeCityEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    @OneToMany(mappedBy = "refTypeCityByTypeCityId")
    public Set<RefCityEntity> getRefCitiesById() {
        return refCitiesById;
    }

    public void setRefCitiesById(Set<RefCityEntity> refCitiesById) {
        this.refCitiesById = refCitiesById;
    }
}
