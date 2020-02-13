package address.documents.invoices;

import address.documents.SuperDocumentEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "doc_invoice_head", schema = "public", catalog = "farm")
public class DocInvoiceHeadDocEntity extends SuperDocumentEntity {
    private DocStatusInvoiceDocEntity refStatusInvoiceByStatusId;
    private DocTypeInvoiceDocEntity docTypeInvoiceByTypeInvoiceId;
    private Collection<TableInvoiceLotDocEntity> tableInvoiceLotsById;
    private Collection<TableInvoiceNomDocEntity> tableInvoiceNomsById;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public DocStatusInvoiceDocEntity getRefStatusInvoiceByStatusId() {
        return refStatusInvoiceByStatusId;
    }

    public void setRefStatusInvoiceByStatusId(DocStatusInvoiceDocEntity refStatusInvoiceByStatusId) {
        this.refStatusInvoiceByStatusId = refStatusInvoiceByStatusId;
    }

    @ManyToOne
    @JoinColumn(name = "type_invoice_id", referencedColumnName = "id", nullable = false)
    public DocTypeInvoiceDocEntity getDocTypeInvoiceByTypeInvoiceId() {
        return docTypeInvoiceByTypeInvoiceId;
    }

    public void setDocTypeInvoiceByTypeInvoiceId(DocTypeInvoiceDocEntity docTypeInvoiceByTypeInvoiceId) {
        this.docTypeInvoiceByTypeInvoiceId = docTypeInvoiceByTypeInvoiceId;
    }

    @OneToMany(mappedBy = "docInvoiceHeadByInvId")
    public Collection<TableInvoiceLotDocEntity> getTableInvoiceLotsById() {
        return tableInvoiceLotsById;
    }

    public void setTableInvoiceLotsById(Collection<TableInvoiceLotDocEntity> tableInvoiceLotsById) {
        this.tableInvoiceLotsById = tableInvoiceLotsById;
    }

    @OneToMany(mappedBy = "docInvoiceHeadByInvId")
    public Collection<TableInvoiceNomDocEntity> getTableInvoiceNomsById() {
        return tableInvoiceNomsById;
    }

    public void setTableInvoiceNomsById(Collection<TableInvoiceNomDocEntity> tableInvoiceNomsById) {
        this.tableInvoiceNomsById = tableInvoiceNomsById;
    }
}
