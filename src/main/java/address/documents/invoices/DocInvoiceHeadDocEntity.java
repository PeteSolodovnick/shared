package address.documents.invoices;

import address.documents.SuperDocumentEntity;
import models.ContragentEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "doc_invoice_head", schema = "public", catalog = "farm")
public class DocInvoiceHeadDocEntity extends SuperDocumentEntity {
    private DocStatusInvoiceDocEntity refStatusInvoiceByStatusId;
    private Collection<TableInvoiceNomDocEntity> tableInvoiceNomById;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public DocStatusInvoiceDocEntity getRefStatusInvoiceByStatusId() {
        return refStatusInvoiceByStatusId;
    }

    public void setRefStatusInvoiceByStatusId(DocStatusInvoiceDocEntity refStatusInvoiceByStatusId) {
        this.refStatusInvoiceByStatusId = refStatusInvoiceByStatusId;
    }

    @OneToMany(mappedBy = "docInvoiceHeadByInvId", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    public Collection<TableInvoiceNomDocEntity> getTableInvoiceNomById() {
        return tableInvoiceNomById;
    }

    public void setTableInvoiceNomById(Collection<TableInvoiceNomDocEntity> tableInvoiceNomById) {
        this.tableInvoiceNomById = tableInvoiceNomById;
    }
}
