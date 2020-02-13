package models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_type_contragent", schema = "public", catalog = "farm")
public class TypeContragentEntity extends SuperEntity {
    private Collection<ContragentEntity> refContragentsById;

    @OneToMany(mappedBy = "refTypeContragentByTypeContraId")
    public Collection<ContragentEntity> getRefContragentsById() {
        return refContragentsById;
    }

    public void setRefContragentsById(Collection<ContragentEntity> refContragentsById) {
        this.refContragentsById = refContragentsById;
    }
}
