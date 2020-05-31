package models.tables;

import models.documents.DocDocsHeadDocEntity;
import models.references.LotsEntity;
import models.references.NomenklEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "table_doc_lots", schema = "public", catalog = "farm")
public class TableDocLotsDocEntity extends SuperTableEntity{
    private NomenklEntity nomenklEntityByNomId;
    private DocDocsHeadDocEntity docDocsHeadByDocId;
    private LotsEntity lotsEntity;
    private Collection<JournalOperationsLotsDocEntity> journalOperationsLotsById;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableDocLotsDocEntity that = (TableDocLotsDocEntity) o;
        return getId() == that.getId() &&
                Objects.equals(getQty(), that.getQty()) &&
                Objects.equals(getSum(), that.getSum());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQty(), getSum());
    }

    @OneToMany(mappedBy = "tableDocLotsByTableLotsId")
    public Collection<JournalOperationsLotsDocEntity> getJournalOperationsLotsById() {
        return journalOperationsLotsById;
    }

    public void setJournalOperationsLotsById(Collection<JournalOperationsLotsDocEntity> journalOperationsLotsById) {
        this.journalOperationsLotsById = journalOperationsLotsById;
    }

    @ManyToOne
    @JoinColumn(name = "nomenkl_id", referencedColumnName = "id", nullable = false)
    public NomenklEntity getNomenklEntityByNomId() {
        return nomenklEntityByNomId;
    }

    public void setNomenklEntityByNomId(NomenklEntity nomenklEntityByNomId) {
        this.nomenklEntityByNomId = nomenklEntityByNomId;
    }
    @ManyToOne
    @JoinColumn(name="doc_id", referencedColumnName = "id", nullable = false)
    @Override
    public DocDocsHeadDocEntity getDocDocsHeadByDocId() {
        return docDocsHeadByDocId;
    }

    @Override
    public void setDocDocsHeadByDocId(DocDocsHeadDocEntity docDocsHeadByDocId) {
        this.docDocsHeadByDocId = docDocsHeadByDocId;
    }

    @ManyToOne
    @JoinColumn(name="lots_id", referencedColumnName = "id", nullable = false)
    public LotsEntity getLotsEntity() {
        return lotsEntity;
    }

    public void setLotsEntity(LotsEntity lotsEntity) {
        this.lotsEntity = lotsEntity;
    }
}
