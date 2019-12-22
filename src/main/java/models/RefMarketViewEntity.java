package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_market_view", schema = "public", catalog = "farm")
public class RefMarketViewEntity extends SuperEntity{
    private Collection<RefContragentEntity> refContragentsById;

    @OneToMany(mappedBy = "refMarketViewByMarketViewId")
    public Collection<RefContragentEntity> getRefContragentsById() {
        return refContragentsById;
    }

    public void setRefContragentsById(Collection<RefContragentEntity> refContragentsById) {
        this.refContragentsById = refContragentsById;
    }
}
