package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_price", schema = "public", catalog = "farm")
public class RefPriceEntity extends SuperEntity {
    private String name;
    private Collection<RefContragentEntity> refContragentsById;

    @Basic
    @Column(name = "name", nullable = true, length = 20)
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
        RefPriceEntity that = (RefPriceEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    @OneToMany(mappedBy = "refPriceByPriceId")
    public Collection<RefContragentEntity> getRefContragentsById() {
        return refContragentsById;
    }

    public void setRefContragentsById(Collection<RefContragentEntity> refContragentsById) {
        this.refContragentsById = refContragentsById;
    }
}
