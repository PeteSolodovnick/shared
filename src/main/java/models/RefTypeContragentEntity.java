package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_type_contragent", schema = "public", catalog = "farm")
public class RefTypeContragentEntity extends SuperEntity {
    private String name;
    private Collection<RefContragentEntity> refContragentsById;
    private RefMarketViewEntity refMarketViewByMarketViewId;

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
        RefTypeContragentEntity that = (RefTypeContragentEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    @OneToMany(mappedBy = "refTypeContragentByTypeContraId")
    public Collection<RefContragentEntity> getRefContragentsById() {
        return refContragentsById;
    }

    public void setRefContragentsById(Collection<RefContragentEntity> refContragentsById) {
        this.refContragentsById = refContragentsById;
    }

    @ManyToOne
    @JoinColumn(name = "market_view_id", referencedColumnName = "id", nullable = false)
    public RefMarketViewEntity getRefMarketViewByMarketViewId() {
        return refMarketViewByMarketViewId;
    }

    public void setRefMarketViewByMarketViewId(RefMarketViewEntity refMarketViewByMarketViewId) {
        this.refMarketViewByMarketViewId = refMarketViewByMarketViewId;
    }
}
