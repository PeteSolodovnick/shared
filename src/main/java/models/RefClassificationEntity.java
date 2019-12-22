package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_classification", schema = "public", catalog = "farm")
public class RefClassificationEntity extends SuperEntity{
    private RefClassificationEntity refClassificationByParentId;
    private Collection<RefClassificationEntity> refClassificationsById;
    private Collection<RefNomenklEntity> refNomenklsById;

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
