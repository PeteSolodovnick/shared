package models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ref_territory", schema = "public", catalog = "farm")
public class RefTerritoryEntity extends SuperEntity {

    private String name;
    private Set<RefCityEntity> refCitiesById;
    public RefTerritoryEntity(){
        super();
    }

    public RefTerritoryEntity(Long id){
        super(id);
    }

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "refTerritoryByTerId")
    public Set<RefCityEntity> getRefCitiesById() {
        return refCitiesById;
    }

    public void setRefCitiesById(Set<RefCityEntity> refCitiesById) {
        this.refCitiesById = refCitiesById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefTerritoryEntity that = (RefTerritoryEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }
}
