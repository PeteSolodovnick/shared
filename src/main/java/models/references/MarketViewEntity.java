package models.references;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_market_view", schema = "public", catalog = "farm")
public class MarketViewEntity extends SuperReferenceEntity {
    private Collection<ContragentEntity> refContragentsById;

    @OneToMany(mappedBy = "refMarketViewByMarketViewId")
    public Collection<ContragentEntity> getRefContragentsById() {
        return refContragentsById;
    }

    public void setRefContragentsById(Collection<ContragentEntity> refContragentsById) {
        this.refContragentsById = refContragentsById;
    }
}
