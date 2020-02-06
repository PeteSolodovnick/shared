package address.stores;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_type_doc", schema = "public", catalog = "farm")
public class RefTypeDocEntity {
    private long id;
    private String name;
    private Collection<RefKindOfTypeDocEntity> refKindOfTypeDocsById;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefTypeDocEntity that = (RefTypeDocEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "refTypeDocByTypeDocId")
    public Collection<RefKindOfTypeDocEntity> getRefKindOfTypeDocsById() {
        return refKindOfTypeDocsById;
    }

    public void setRefKindOfTypeDocsById(Collection<RefKindOfTypeDocEntity> refKindOfTypeDocsById) {
        this.refKindOfTypeDocsById = refKindOfTypeDocsById;
    }
}
