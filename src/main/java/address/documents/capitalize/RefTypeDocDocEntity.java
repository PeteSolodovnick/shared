package address.documents.capitalize;

import models.SuperEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_type_doc", schema = "public", catalog = "farm")
public class RefTypeDocDocEntity extends SuperEntity {
    private Collection<DocDocsHeadDocEntity> docDocsHeadsById;

    @OneToMany(mappedBy = "refTypeDocByTypeDocId")
    public Collection<DocDocsHeadDocEntity> getDocDocsHeadsById() {
        return docDocsHeadsById;
    }

    public void setDocDocsHeadsById(Collection<DocDocsHeadDocEntity> docDocsHeadsById) {
        this.docDocsHeadsById = docDocsHeadsById;
    }
}
