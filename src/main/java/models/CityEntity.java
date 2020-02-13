package models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_city", schema = "public", catalog = "farm")
public class CityEntity extends SuperEntity {
    private TerritoryEntity refTerritoryByTerId;
    private TypeCityEntity refTypeCityByTypeCityId;
    private Collection<ContragentEntity> refContragentsById;

    public CityEntity(){
        super();
    }

    @ManyToOne
    @JoinColumns(@JoinColumn(name = "ter_id", referencedColumnName = "id", nullable = false))
    public TerritoryEntity getRefTerritoryByTerId() {
        return refTerritoryByTerId;
    }

    public void setRefTerritoryByTerId(TerritoryEntity refTerritoryByTerId) {
        this.refTerritoryByTerId = refTerritoryByTerId;
    }

    @ManyToOne
    @JoinColumn(name = "type_city_id", referencedColumnName = "id")
    public TypeCityEntity getRefTypeCityByTypeCityId() {
        return refTypeCityByTypeCityId;
    }

    public void setRefTypeCityByTypeCityId(TypeCityEntity refTypeCityByTypeCityId) {
        this.refTypeCityByTypeCityId = refTypeCityByTypeCityId;
    }

    @OneToMany(mappedBy = "refCityByCityId")
    public Collection<ContragentEntity> getRefContragentsById() {
        return refContragentsById;
    }

    public void setRefContragentsById(Collection<ContragentEntity> refContragentsById) {
        this.refContragentsById = refContragentsById;
    }
}
