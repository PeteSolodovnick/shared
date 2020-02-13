package models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_kind_contragent", schema = "public", catalog = "farm")
public class KindContragentEntity extends SuperEntity {
    private Collection<ContragentEntity> refContragentsById;

    @OneToMany(mappedBy = "refKindContragentByKindContraId")
    public Collection<ContragentEntity> getRefContragentsById() {
        return refContragentsById;
    }

    public void setRefContragentsById(Collection<ContragentEntity> refContragentsById) {
        this.refContragentsById = refContragentsById;
    }
}
