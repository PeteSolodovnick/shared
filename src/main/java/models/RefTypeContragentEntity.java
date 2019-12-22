package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_type_contragent", schema = "public", catalog = "farm")
public class RefTypeContragentEntity extends SuperEntity {
    private Collection<RefContragentEntity> refContragentsById;

    @OneToMany(mappedBy = "refTypeContragentByTypeContraId")
    public Collection<RefContragentEntity> getRefContragentsById() {
        return refContragentsById;
    }

    public void setRefContragentsById(Collection<RefContragentEntity> refContragentsById) {
        this.refContragentsById = refContragentsById;
    }
}
