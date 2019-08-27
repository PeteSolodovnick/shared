package models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ref_city", schema = "public", catalog = "farm")
public class RefCityEntity extends SuperEntity{
    private String name;
    private RefTerritoryEntity refTerritoryByTerId;
    private RefTypeCityEntity refTypeCityByTypeCityId;

    public RefCityEntity(){
        super();
    }

    public RefCityEntity(Long id) {
        super(id);
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "ter_id", referencedColumnName = "id", nullable = false))
    public RefTerritoryEntity getRefTerritoryByTerId() {
        return refTerritoryByTerId;
    }

    public void setRefTerritoryByTerId(RefTerritoryEntity refTerritoryByTerId) {
        this.refTerritoryByTerId = refTerritoryByTerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefCityEntity that = (RefCityEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getId());
    }

    @ManyToOne
    @JoinColumn(name = "type_city_id", referencedColumnName = "id")
    public RefTypeCityEntity getRefTypeCityByTypeCityId() {
        return refTypeCityByTypeCityId;
    }

    public void setRefTypeCityByTypeCityId(RefTypeCityEntity refTypeCityByTypeCityId) {
        this.refTypeCityByTypeCityId = refTypeCityByTypeCityId;
    }
}
