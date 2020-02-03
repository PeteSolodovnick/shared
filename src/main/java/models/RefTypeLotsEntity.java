package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_type_lots", schema = "public", catalog = "farm")
public class RefTypeLotsEntity extends SuperEntity{

    private Collection<RefLotsEntity> refLotsById;

    @OneToMany(mappedBy = "refTypeLotsByTypeLotsId")
    public Collection<RefLotsEntity> getRefLotsById() {
        return refLotsById;
    }

    public void setRefLotsById(Collection<RefLotsEntity> refLotsById) {
        this.refLotsById = refLotsById;
    }
}
