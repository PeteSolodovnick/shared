package models.references;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_price", schema = "public", catalog = "farm")
public class PriceEntity extends SuperReferenceEntity {
    private Collection<ContragentEntity> refContragentsById;

    @OneToMany(mappedBy = "refPriceByPriceId")
    public Collection<ContragentEntity> getRefContragentsById() {
        return refContragentsById;
    }

    public void setRefContragentsById(Collection<ContragentEntity> refContragentsById) {
        this.refContragentsById = refContragentsById;
    }
}
