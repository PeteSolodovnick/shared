package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ref_city", schema = "public", catalog = "farm")
public class RefCityEntity extends SuperEntity{
    private RefTerritoryEntity refTerritoryByTerId;
    private RefTypeCityEntity refTypeCityByTypeCityId;
    private Collection<RefContragentEntity> refContragentsById;

    public RefCityEntity(){
        super();
    }

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "ter_id", referencedColumnName = "id", nullable = false))
    public RefTerritoryEntity getRefTerritoryByTerId() {
        return refTerritoryByTerId;
    }

    public void setRefTerritoryByTerId(RefTerritoryEntity refTerritoryByTerId) {
        this.refTerritoryByTerId = refTerritoryByTerId;
    }

    @ManyToOne
    @JoinColumn(name = "type_city_id", referencedColumnName = "id")
    public RefTypeCityEntity getRefTypeCityByTypeCityId() {
        return refTypeCityByTypeCityId;
    }

    public void setRefTypeCityByTypeCityId(RefTypeCityEntity refTypeCityByTypeCityId) {
        this.refTypeCityByTypeCityId = refTypeCityByTypeCityId;
    }

    @OneToMany(mappedBy = "refCityByCityId")
    public Collection<RefContragentEntity> getRefContragentsById() {
        return refContragentsById;
    }

    public void setRefContragentsById(Collection<RefContragentEntity> refContragentsById) {
        this.refContragentsById = refContragentsById;
    }
}
