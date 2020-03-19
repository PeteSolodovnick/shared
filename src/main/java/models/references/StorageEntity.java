package models.references;

import models.documents.DocDocsHeadDocEntity;
import address.documents.capitalize.JournalOperationsStaffDocEntity;
import models.tables.TableCurrentRestStuffDocEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_storage", schema = "public", catalog = "farm")
public class StorageEntity extends SuperReferenceEntity {
    private Integer attribute;
    private Boolean deleted;
    private Collection<DocDocsHeadDocEntity> docHeadInEntityById;
    private Collection<DocDocsHeadDocEntity> docHeadOutEntityById;
    private Collection<JournalOperationsStaffDocEntity> journalOperationsStaffDocEntities;
    private Collection<TableCurrentRestStuffDocEntity> currentRestStuffById;

    @OneToMany(mappedBy = "storageEntityById")
    public Collection<TableCurrentRestStuffDocEntity> getCurrentRestStuffById() {
        return currentRestStuffById;
    }

    public void setCurrentRestStuffById(Collection<TableCurrentRestStuffDocEntity> currentRestStuffById) {
        this.currentRestStuffById = currentRestStuffById;
    }

    @OneToMany(mappedBy = "storageEntityById")
    public Collection<JournalOperationsStaffDocEntity> getJournalOperationsStaffDocEntities() {
        return journalOperationsStaffDocEntities;
    }
    public void setJournalOperationsStaffDocEntities(Collection<JournalOperationsStaffDocEntity> journalOperationsStaffDocEntities) {
        this.journalOperationsStaffDocEntities = journalOperationsStaffDocEntities;
    }

    @OneToMany(mappedBy = "storageInById")
    public Collection<DocDocsHeadDocEntity> getDocHeadInEntityById() {
        return docHeadInEntityById;
    }
    public void setDocHeadInEntityById(Collection<DocDocsHeadDocEntity> docHeadInEntityById) {
        this.docHeadInEntityById = docHeadInEntityById;
    }
    @OneToMany(mappedBy = "storageOutById")
    public Collection<DocDocsHeadDocEntity> getDocHeadOutEntityById() {
        return docHeadOutEntityById;
    }

    public void setDocHeadOutEntityById(Collection<DocDocsHeadDocEntity> docHeadOutEntityById) {
        this.docHeadOutEntityById = docHeadOutEntityById;
    }

    @Basic
    @Column(name = "attribute", nullable = true)
    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }

    @Basic
    @Column(name = "delete", nullable = true)
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}

