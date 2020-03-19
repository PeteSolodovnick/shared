package models.references;

import models.SuperEntity;
import models.documents.DocDocsHeadDocEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_type_doc", schema = "public", catalog = "farm")
public class RefTypeDocDocEntity extends SuperReferenceEntity {
    private Collection<DocDocsHeadDocEntity> docDocsHeadsById;

    @OneToMany(mappedBy = "refTypeDocByTypeDocId")
    public Collection<DocDocsHeadDocEntity> getDocDocsHeadsById() {
        return docDocsHeadsById;
    }

    public void setDocDocsHeadsById(Collection<DocDocsHeadDocEntity> docDocsHeadsById) {
        this.docDocsHeadsById = docDocsHeadsById;
    }
}
