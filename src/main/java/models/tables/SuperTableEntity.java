package models.tables;

import models.SuperEntity;
import models.documents.DocDocsHeadDocEntity;

import javax.persistence.*;
import java.util.Objects;
@MappedSuperclass
public abstract class SuperTableEntity extends SuperEntity {
    private Integer qty;
    private Float sum;
    private DocDocsHeadDocEntity docDocsHeadByDocId;

    public SuperTableEntity() {}

    @Basic
    @Column(name = "qty", nullable = true)
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Basic
    @Column(name = "sum", nullable = true, precision = 0)
    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    @ManyToOne
    @JoinColumn(name = "doc_id", referencedColumnName = "id", nullable = false)
    public DocDocsHeadDocEntity getDocDocsHeadByDocId() {
        return docDocsHeadByDocId;
    }

    public void setDocDocsHeadByDocId(DocDocsHeadDocEntity docDocsHeadByDocId) {
        this.docDocsHeadByDocId = docDocsHeadByDocId;
    }
}
