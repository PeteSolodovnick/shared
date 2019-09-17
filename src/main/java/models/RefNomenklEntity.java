package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ref_nomenkl", schema = "public", catalog = "farm")
public class RefNomenklEntity extends SuperEntity{
    private String name;
    private RefClassificationEntity refClassificationByClassificationId;
    private RefSizeEntity refSizeBySizeId;

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
        RefNomenklEntity that = (RefNomenklEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }

    @ManyToOne
    @JoinColumn(name = "classification_id", referencedColumnName = "id", nullable = false)
    public RefClassificationEntity getRefClassificationByClassificationId() {
        return refClassificationByClassificationId;
    }

    public void setRefClassificationByClassificationId(RefClassificationEntity refClassificationByClassificationId) {
        this.refClassificationByClassificationId = refClassificationByClassificationId;
    }

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false)
    public RefSizeEntity getRefSizeBySizeId() {
        return refSizeBySizeId;
    }

    public void setRefSizeBySizeId(RefSizeEntity refSizeBySizeId) {
        this.refSizeBySizeId = refSizeBySizeId;
    }
}
