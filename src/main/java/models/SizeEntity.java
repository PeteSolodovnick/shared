package models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_size", schema = "public", catalog = "farm")
public class SizeEntity extends SuperEntity {
    private Collection<NomenklEntity> refNomenklsById;

    @OneToMany(mappedBy = "refSizeBySizeId")
    public Collection<NomenklEntity> getRefNomenklsById() {
        return refNomenklsById;
    }

    public void setRefNomenklsById(Collection<NomenklEntity> refNomenklsById) {
        this.refNomenklsById = refNomenklsById;
    }
}
