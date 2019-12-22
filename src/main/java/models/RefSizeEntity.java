package models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_size", schema = "public", catalog = "farm")
public class RefSizeEntity extends SuperEntity{
    private Collection<RefNomenklEntity> refNomenklsById;

    @OneToMany(mappedBy = "refSizeBySizeId")
    public Collection<RefNomenklEntity> getRefNomenklsById() {
        return refNomenklsById;
    }

    public void setRefNomenklsById(Collection<RefNomenklEntity> refNomenklsById) {
        this.refNomenklsById = refNomenklsById;
    }
}
