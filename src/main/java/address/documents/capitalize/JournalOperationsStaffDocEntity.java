package address.documents.capitalize;

import models.NomenklEntity;
import models.StorageEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "journal_operations_staff", schema = "public", catalog = "farm")
public class JournalOperationsStaffDocEntity {
    private long id;
    private Date recTime;
    private Integer qty;
    private Float sum;
    private Integer restQty;
    private Float restSum;
    private DocDocsHeadDocEntity docDocsHeadByDocId;
    private RefTypeOperationsDocEntity refTypeOperationsByTypeOperationsId;
    private StorageEntity storageEntityById;
    private NomenklEntity nomenklEntityById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    @Basic
    @Column(name = "restQty", nullable = true)
    public Integer getRestQty() {
        return restQty;
    }

    public void setRestQty(Integer restQty) {
        this.restQty = restQty;
    }

    @Basic
    @Column(name = "restSum", nullable = true, precision = 0)
    public Float getRestSum() {
        return restSum;
    }

    public void setRestSum(Float restSum) {
        this.restSum = restSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalOperationsStaffDocEntity that = (JournalOperationsStaffDocEntity) o;
        return id == that.id &&
                docDocsHeadByDocId.getId() == that.docDocsHeadByDocId.getId() &&
                Objects.equals(recTime, that.recTime) &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(sum, that.sum) &&
                Objects.equals(refTypeOperationsByTypeOperationsId.getId(), that.refTypeOperationsByTypeOperationsId.getId()) &&
                Objects.equals(restQty, that.restQty) &&
                Objects.equals(restSum, that.restSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, docDocsHeadByDocId.getId(), recTime, qty, sum, refTypeOperationsByTypeOperationsId.getId(), restQty, restSum);
    }

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
