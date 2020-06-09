package models.tables;

import models.documents.DocDocsHeadDocEntity;
import models.references.LotsEntity;
import models.references.RefTypeOperationsDocEntity;
import models.references.SuperReferenceEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "journal_operations_lots", schema = "public", catalog = "farm")
public class JournalOperationsLotsDocEntity extends SuperReferenceEntity {
    private Date recTime;
    private Float sum;
    private LotsEntity refLotsId;
    private RefTypeOperationsDocEntity refTypeOperationsByTypeOperationsId;
    private DocDocsHeadDocEntity headDocEntity;
    private Collection<TableDocLotsDocEntity> tableDocLotsDocEntities = new HashSet<>();
    private Boolean editable;

    public void addProduct(TableDocLotsDocEntity tableDocLotsDocEntity) {
        tableDocLotsDocEntity.setJournalOperationsLotsById(this);
        this.tableDocLotsDocEntities.add(tableDocLotsDocEntity);
    }

    @Basic
    @Column(name = "editable")
    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    @Basic
    @Column(name = "rec_time")
    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }

    @Basic
    @Column(name = "sum")
    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    @ManyToOne
    @JoinColumn(name = "lots_id", referencedColumnName = "id", nullable = false)
    public LotsEntity getRefLotsId() {
        return refLotsId;
    }

    public void setRefLotsId(LotsEntity refLotsId) {
        this.refLotsId = refLotsId;
    }

    @ManyToOne
    @JoinColumn(name = "type_operation_id", referencedColumnName = "id")
    public RefTypeOperationsDocEntity getRefTypeOperationsByTypeOperationsId() {
        return refTypeOperationsByTypeOperationsId;
    }

    public void setRefTypeOperationsByTypeOperationsId(RefTypeOperationsDocEntity refTypeOperationsByTypeOperationsId) {
        this.refTypeOperationsByTypeOperationsId = refTypeOperationsByTypeOperationsId;
    }

    @ManyToOne
    @JoinColumn(name = "doc_id", referencedColumnName = "id")

    public DocDocsHeadDocEntity getHeadDocEntity() {
        return headDocEntity;
    }

    public void setHeadDocEntity(DocDocsHeadDocEntity headDocEntity) {
        this.headDocEntity = headDocEntity;
    }

    @OneToMany(mappedBy = "journalOperationsLotsById", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Collection<TableDocLotsDocEntity> getTableDocLotsDocEntities() {
        return tableDocLotsDocEntities;
    }

    public void setTableDocLotsDocEntities(Collection<TableDocLotsDocEntity> tableDocLotsDocEntities) {
        this.tableDocLotsDocEntities = tableDocLotsDocEntities;
    }
}
