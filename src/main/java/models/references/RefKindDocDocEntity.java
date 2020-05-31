package models.references;

import models.documents.DocDocsHeadDocEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_kind_doc", schema = "public", catalog = "farm")
public class RefKindDocDocEntity extends SuperReferenceEntity {
    private Collection<DocDocsHeadDocEntity> docDocsHeadsById;
  //  private Collection<JournalOperationsLotsDocEntity> journalOperationsLotsById;

    @OneToMany(mappedBy = "refKindDocByKindDocId")
    public Collection<DocDocsHeadDocEntity> getDocDocsHeadsById() {
        return docDocsHeadsById;
    }

    public void setDocDocsHeadsById(Collection<DocDocsHeadDocEntity> docDocsHeadsById) {
        this.docDocsHeadsById = docDocsHeadsById;
    }

 /*   @OneToMany(mappedBy = "refKindDocByKindTypeDocId")
    public Collection<JournalOperationsLotsDocEntity> getJournalOperationsLotsById() {
        return journalOperationsLotsById;
    }

    public void setJournalOperationsLotsById(Collection<JournalOperationsLotsDocEntity> journalOperationsLotsById) {
        this.journalOperationsLotsById = journalOperationsLotsById;
    } */
}
