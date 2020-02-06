package address.stores;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_kind_of_type_doc", schema = "public", catalog = "farm")
public class RefKindOfTypeDocEntity {
    private long id;
    private long typeDocId;
    private String name;
    private Collection<DocDocsHeadEntity> docDocsHeadsById;
    private RefTypeDocEntity refTypeDocByTypeDocId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type_doc_id", nullable = false)
    public long getTypeDocId() {
        return typeDocId;
    }

    public void setTypeDocId(long typeDocId) {
        this.typeDocId = typeDocId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 20)
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
        RefKindOfTypeDocEntity that = (RefKindOfTypeDocEntity) o;
        return id == that.id &&
                typeDocId == that.typeDocId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeDocId, name);
    }

    @OneToMany(mappedBy = "refKindOfTypeDocByKindOfTypeDocId")
    public Collection<DocDocsHeadEntity> getDocDocsHeadsById() {
        return docDocsHeadsById;
    }

    public void setDocDocsHeadsById(Collection<DocDocsHeadEntity> docDocsHeadsById) {
        this.docDocsHeadsById = docDocsHeadsById;
    }

    @ManyToOne
    @JoinColumn(name = "type_doc_id", referencedColumnName = "id", nullable = false)
    public RefTypeDocEntity getRefTypeDocByTypeDocId() {
        return refTypeDocByTypeDocId;
    }

    public void setRefTypeDocByTypeDocId(RefTypeDocEntity refTypeDocByTypeDocId) {
        this.refTypeDocByTypeDocId = refTypeDocByTypeDocId;
    }
}
