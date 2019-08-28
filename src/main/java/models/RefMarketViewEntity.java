package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_market_view", schema = "public", catalog = "farm")
public class RefMarketViewEntity extends SuperEntity{
    private String name;
    private Collection<RefTypeContragentEntity> refTypeContragentsById;

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
        RefMarketViewEntity that = (RefMarketViewEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    @OneToMany(mappedBy = "refMarketViewByMarketViewId")
    public Collection<RefTypeContragentEntity> getRefTypeContragentsById() {
        return refTypeContragentsById;
    }

    public void setRefTypeContragentsById(Collection<RefTypeContragentEntity> refTypeContragentsById) {
        this.refTypeContragentsById = refTypeContragentsById;
    }
}
