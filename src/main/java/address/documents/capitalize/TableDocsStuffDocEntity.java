package address.documents.capitalize;

import models.NomenklEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "table_docs_stuff", schema = "public", catalog = "farm")
public class TableDocsStuffDocEntity {
    private long id;
    private Integer qty;
    private Float sum;
    private DocDocsHeadDocEntity docDocsHeadByDocId;
    private NomenklEntity nomenklEntityByNomId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableDocsStuffDocEntity that = (TableDocsStuffDocEntity) o;
        return id == that.id &&
                docDocsHeadByDocId.getId() == that.docDocsHeadByDocId.getId() &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, docDocsHeadByDocId.getId(), qty, sum);
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
    @JoinColumn(name = "nomenkl_id", referencedColumnName = "id", nullable = false)
    public NomenklEntity getNomenklEntityByNomId() {
        return nomenklEntityByNomId;
    }

    public void setNomenklEntityByNomId(NomenklEntity nomenklEntityByNomId) {
        this.nomenklEntityByNomId = nomenklEntityByNomId;
    }
}
