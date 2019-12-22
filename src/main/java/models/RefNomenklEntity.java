package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ref_nomenkl", schema = "public", catalog = "farm")
public class RefNomenklEntity extends SuperEntity{
    private RefClassificationEntity refClassificationByClassificationId;
    private RefSizeEntity refSizeBySizeId;

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
