package address.stores;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "doc_docs_head", schema = "public", catalog = "farm")
public class DocDocsHeadEntity {
 /*   private long id;
    private Long contraId;
    private Long storageInId;
    private Long storageOutId;
    private Integer number;
    private Long kindOfTypeDocId;
    private Date date;
    private RefContragentEntity refContragentByContraId;
    private RefStorageEntity refStorageByStorageInId;
    private RefStorageEntity refStorageByStorageOutId;
    private RefKindOfTypeDocEntity refKindOfTypeDocByKindOfTypeDocId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "contra_id", nullable = true)
    public Long getContraId() {
        return contraId;
    }

    public void setContraId(Long contraId) {
        this.contraId = contraId;
    }

    @Basic
    @Column(name = "storage_in_id", nullable = true)
    public Long getStorageInId() {
        return storageInId;
    }

    public void setStorageInId(Long storageInId) {
        this.storageInId = storageInId;
    }

    @Basic
    @Column(name = "storage_out_id", nullable = true)
    public Long getStorageOutId() {
        return storageOutId;
    }

    public void setStorageOutId(Long storageOutId) {
        this.storageOutId = storageOutId;
    }

    @Basic
    @Column(name = "number", nullable = true)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "kind_of_type_doc_id", nullable = true)
    public Long getKindOfTypeDocId() {
        return kindOfTypeDocId;
    }

    public void setKindOfTypeDocId(Long kindOfTypeDocId) {
        this.kindOfTypeDocId = kindOfTypeDocId;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocDocsHeadEntity that = (DocDocsHeadEntity) o;
        return id == that.id &&
                Objects.equals(contraId, that.contraId) &&
                Objects.equals(storageInId, that.storageInId) &&
                Objects.equals(storageOutId, that.storageOutId) &&
                Objects.equals(number, that.number) &&
                Objects.equals(kindOfTypeDocId, that.kindOfTypeDocId) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contraId, storageInId, storageOutId, number, kindOfTypeDocId, date);
    }

    @ManyToOne
    @JoinColumn(name = "contra_id", referencedColumnName = "id")
    public RefContragentEntity getRefContragentByContraId() {
        return refContragentByContraId;
    }

    public void setRefContragentByContraId(RefContragentEntity refContragentByContraId) {
        this.refContragentByContraId = refContragentByContraId;
    }

    @ManyToOne
    @JoinColumn(name = "storage_in_id", referencedColumnName = "id")
    public RefStorageEntity getRefStorageByStorageInId() {
        return refStorageByStorageInId;
    }

    public void setRefStorageByStorageInId(RefStorageEntity refStorageByStorageInId) {
        this.refStorageByStorageInId = refStorageByStorageInId;
    }

    @ManyToOne
    @JoinColumn(name = "storage_out_id", referencedColumnName = "id")
    public RefStorageEntity getRefStorageByStorageOutId() {
        return refStorageByStorageOutId;
    }

    public void setRefStorageByStorageOutId(RefStorageEntity refStorageByStorageOutId) {
        this.refStorageByStorageOutId = refStorageByStorageOutId;
    }

    @ManyToOne
    @JoinColumn(name = "kind_of_type_doc_id", referencedColumnName = "id")
    public RefKindOfTypeDocEntity getRefKindOfTypeDocByKindOfTypeDocId() {
        return refKindOfTypeDocByKindOfTypeDocId;
    }

    public void setRefKindOfTypeDocByKindOfTypeDocId(RefKindOfTypeDocEntity refKindOfTypeDocByKindOfTypeDocId) {
        this.refKindOfTypeDocByKindOfTypeDocId = refKindOfTypeDocByKindOfTypeDocId;
    } */
}
