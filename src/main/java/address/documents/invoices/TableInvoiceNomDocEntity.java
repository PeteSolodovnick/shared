package address.documents.invoices;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "table_invoice_nom", schema = "public", catalog = "farm")
public class TableInvoiceNomDocEntity {
    private long id;
    private Long qty;
    private Float sum;
    private DocInvoiceHeadDocEntity docInvoiceHeadByInvId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "qty", nullable = true)
    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
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
        TableInvoiceNomDocEntity that = (TableInvoiceNomDocEntity) o;
        return id == that.id &&
                docInvoiceHeadByInvId.getId() == that.docInvoiceHeadByInvId.getId() &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, qty, sum, docInvoiceHeadByInvId.getId());
    }

    @ManyToOne
    @JoinColumn(name = "inv_id", referencedColumnName = "id", nullable = false)
    public DocInvoiceHeadDocEntity getDocInvoiceHeadByInvId() {
        return docInvoiceHeadByInvId;
    }

    public void setDocInvoiceHeadByInvId(DocInvoiceHeadDocEntity docInvoiceHeadByInvId) {
        this.docInvoiceHeadByInvId = docInvoiceHeadByInvId;
    }
}
