package models.tables;

import models.SuperEntity;
import models.documents.DocInvoiceHeadDocEntity;
import models.references.NomenklEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "table_invoice_nom", schema = "public", catalog = "farm")
public class TableInvoiceNomDocEntity extends SuperEntity {
    private Integer qty;
    private Float sum;
    private Float vat;
    private Float sum_vat;
    private DocInvoiceHeadDocEntity docInvoiceHeadByInvId;
    private NomenklEntity nomenklEntityByNomId;

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
    @Column(name = "vat", nullable = true, precision = 0)
    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }
    @Basic
    @Column(name = "sum_vat", nullable = true, precision = 0)
    public Float getSum_vat() {
        return sum_vat;
    }

    public void setSum_vat(Float sum_vat) {
        this.sum_vat = sum_vat;
    }

    @ManyToOne
    @JoinColumn(name = "inv_id", referencedColumnName = "id", nullable = false)
    public DocInvoiceHeadDocEntity getDocInvoiceHeadByInvId() {
        return docInvoiceHeadByInvId;
    }

    public void setDocInvoiceHeadByInvId(DocInvoiceHeadDocEntity docInvoiceHeadByInvId) {
        this.docInvoiceHeadByInvId = docInvoiceHeadByInvId;
    }
    @ManyToOne
    @JoinColumn(name = "nom_id", referencedColumnName = "id", nullable = false)
    public NomenklEntity getNomenklEntityByNomId() {
        return nomenklEntityByNomId;
    }

    public void setNomenklEntityByNomId(NomenklEntity nomenklEntityByNomId) {
        this.nomenklEntityByNomId = nomenklEntityByNomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableInvoiceNomDocEntity that = (TableInvoiceNomDocEntity) o;
        return getId() == that.getId() &&
                docInvoiceHeadByInvId.getId() == that.docInvoiceHeadByInvId.getId() &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), qty, sum, docInvoiceHeadByInvId.getId());
    }

    @Override
    public String toString() {
        System.out.println("id= " + this.getId());
        System.out.println("nom= " + this.getNomenklEntityByNomId().getId());
        System.out.println("qty= " + this.getQty());
        System.out.println("sum= " + this.getSum());
        System.out.println("head= " + this.getDocInvoiceHeadByInvId().getId());
        System.out.println("vat= " + this.getVat());
        System.out.println("sum_vat= " + this.getSum_vat());
        return super.toString();
    }
}
