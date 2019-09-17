package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_size", schema = "public", catalog = "farm")
public class RefSizeEntity extends SuperEntity{
    private String name;
    private Collection<RefNomenklEntity> refNomenklsById;

    @Basic
    @Column(name = "name", nullable = true, length = 10)
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
        RefSizeEntity that = (RefSizeEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    @OneToMany(mappedBy = "refSizeBySizeId")
    public Collection<RefNomenklEntity> getRefNomenklsById() {
        return refNomenklsById;
    }

    public void setRefNomenklsById(Collection<RefNomenklEntity> refNomenklsById) {
        this.refNomenklsById = refNomenklsById;
    }
}
