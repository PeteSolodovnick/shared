package models.references;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_kind_lots", schema = "public", catalog = "farm")
public class KindLotsEntity extends SuperReferenceEntity {

    private Collection<LotsEntity> refLotsById;

    @OneToMany(mappedBy = "refKindLotsByKindLotsId")
    public Collection<LotsEntity> getRefLotsById() {
        return refLotsById;
    }

    public void setRefLotsById(Collection<LotsEntity> refLotsById) {
        this.refLotsById = refLotsById;
    }
}
