package models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_type_lots", schema = "public", catalog = "farm")
public class TypeLotsEntity extends SuperEntity {

    private Collection<LotsEntity> refLotsById;

    @OneToMany(mappedBy = "refTypeLotsByTypeLotsId")
    public Collection<LotsEntity> getRefLotsById() {
        return refLotsById;
    }

    public void setRefLotsById(Collection<LotsEntity> refLotsById) {
        this.refLotsById = refLotsById;
    }
}
