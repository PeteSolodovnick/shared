package models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_classification", schema = "public", catalog = "farm")
public class ClassificationEntity extends SuperEntity {
    private ClassificationEntity refClassificationByParentId;
    private Collection<ClassificationEntity> refClassificationsById;
    private Collection<NomenklEntity> refNomenklsById;

    @ManyToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public ClassificationEntity getRefClassificationByParentId() {
        return refClassificationByParentId;
    }

    public void setRefClassificationByParentId(ClassificationEntity refClassificationByParentId) {
        this.refClassificationByParentId = refClassificationByParentId;
    }

    @OneToMany(mappedBy = "refClassificationByParentId")
    public Collection<ClassificationEntity> getRefClassificationsById() {
        return refClassificationsById;
    }

    public void setRefClassificationsById(Collection<ClassificationEntity> refClassificationsById) {
        this.refClassificationsById = refClassificationsById;
    }

    @OneToMany(mappedBy = "refClassificationByClassificationId")
    public Collection<NomenklEntity> getRefNomenklsById() {
        return refNomenklsById;
    }

    public void setRefNomenklsById(Collection<NomenklEntity> refNomenklsById) {
        this.refNomenklsById = refNomenklsById;
    }
}
