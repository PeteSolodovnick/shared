package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_kind_lots", schema = "public", catalog = "farm")
public class RefKindLotsEntity extends SuperEntity{

    private Collection<RefLotsEntity> refLotsById;

    @OneToMany(mappedBy = "refKindLotsByKindLotsId")
    public Collection<RefLotsEntity> getRefLotsById() {
        return refLotsById;
    }

    public void setRefLotsById(Collection<RefLotsEntity> refLotsById) {
        this.refLotsById = refLotsById;
    }
}
