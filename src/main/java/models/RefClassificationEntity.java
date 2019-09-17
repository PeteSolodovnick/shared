package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_classification", schema = "public", catalog = "farm")
public class RefClassificationEntity extends SuperEntity{
    private String name;
    private RefClassificationEntity refClassificationByParentId;
    private Collection<RefClassificationEntity> refClassificationsById;
    private Collection<RefNomenklEntity> refNomenklsById;

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
        RefClassificationEntity that = (RefClassificationEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public RefClassificationEntity getRefClassificationByParentId() {
        return refClassificationByParentId;
    }

    public void setRefClassificationByParentId(RefClassificationEntity refClassificationByParentId) {
        this.refClassificationByParentId = refClassificationByParentId;
    }

    @OneToMany(mappedBy = "refClassificationByParentId")
    public Collection<RefClassificationEntity> getRefClassificationsById() {
        return refClassificationsById;
    }

    public void setRefClassificationsById(Collection<RefClassificationEntity> refClassificationsById) {
        this.refClassificationsById = refClassificationsById;
    }

    @OneToMany(mappedBy = "refClassificationByClassificationId")
    public Collection<RefNomenklEntity> getRefNomenklsById() {
        return refNomenklsById;
    }

    public void setRefNomenklsById(Collection<RefNomenklEntity> refNomenklsById) {
        this.refNomenklsById = refNomenklsById;
    }
}
