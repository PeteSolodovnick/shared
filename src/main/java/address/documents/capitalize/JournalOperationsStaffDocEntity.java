package address.documents.capitalize;

import models.SuperEntity;
import models.documents.DocDocsHeadDocEntity;
import models.references.NomenklEntity;
import models.references.StorageEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "journal_operations_staff", schema = "public", catalog = "farm")
public class JournalOperationsStaffDocEntity extends SuperEntity {
    private Date recTime;
    private Integer qty;
    private Float sum;
    private DocDocsHeadDocEntity docDocsHeadByDocId;
    private RefTypeOperationsDocEntity refTypeOperationsByTypeOperationsId;
    private StorageEntity storageEntityById;
    private NomenklEntity nomenklEntityById;

    @Basic
    @Column(name = "rec_time", nullable = true)
    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }

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

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalOperationsStaffDocEntity that = (JournalOperationsStaffDocEntity) o;
        return getId() == that.getId() &&
                docDocsHeadByDocId.getId() == that.docDocsHeadByDocId.getId() &&
                Objects.equals(recTime, that.recTime) &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(sum, that.sum) &&
                Objects.equals(refTypeOperationsByTypeOperationsId.getId(), that.refTypeOperationsByTypeOperationsId.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), docDocsHeadByDocId.getId(), recTime, qty, sum, refTypeOperationsByTypeOperationsId.getId());
    } */

    @ManyToOne
    @JoinColumn(name = "doc_id", referencedColumnName = "id", nullable = false)
    public DocDocsHeadDocEntity getDocDocsHeadByDocId() {
        return docDocsHeadByDocId;
    }

    public void setDocDocsHeadByDocId(DocDocsHeadDocEntity docDocsHeadByDocId) {
        this.docDocsHeadByDocId = docDocsHeadByDocId;
    }

    @ManyToOne
    @JoinColumn(name = "type_operations_id", referencedColumnName = "id")
    public RefTypeOperationsDocEntity getRefTypeOperationsByTypeOperationsId() {
        return refTypeOperationsByTypeOperationsId;
    }

    public void setRefTypeOperationsByTypeOperationsId(RefTypeOperationsDocEntity refTypeOperationsByTypeOperationsId) {
        this.refTypeOperationsByTypeOperationsId = refTypeOperationsByTypeOperationsId;
    }
    @ManyToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    public StorageEntity getStorageEntityById() {
        return storageEntityById;
    }
    public void setStorageEntityById(StorageEntity storageEntityById) {
        this.storageEntityById = storageEntityById;
    }
    @ManyToOne
    @JoinColumn(name = "nomenkl_id", referencedColumnName = "id")
    public NomenklEntity getNomenklEntityById() {
        return nomenklEntityById;
    }

    public void setNomenklEntityById(NomenklEntity nomenklEntityById) {
        this.nomenklEntityById = nomenklEntityById;
    }
}
