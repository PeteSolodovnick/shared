package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_price", schema = "public", catalog = "farm")
public class RefPriceEntity extends SuperEntity {
    private Collection<RefContragentEntity> refContragentsById;

    @OneToMany(mappedBy = "refPriceByPriceId")
    public Collection<RefContragentEntity> getRefContragentsById() {
        return refContragentsById;
    }

    public void setRefContragentsById(Collection<RefContragentEntity> refContragentsById) {
        this.refContragentsById = refContragentsById;
    }
}
