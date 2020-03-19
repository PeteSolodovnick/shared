package models.documents;

import models.references.RefStatusInvoiceDocEntity;
import models.tables.TableInvoiceNomDocEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "doc_invoice_head", schema = "public", catalog = "farm")
public class DocInvoiceHeadDocEntity extends SuperDocumentEntity implements Serializable {
    private RefStatusInvoiceDocEntity refStatusInvoiceByStatusId;
    private Collection<TableInvoiceNomDocEntity> tableInvoiceNomById;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    public RefStatusInvoiceDocEntity getRefStatusInvoiceByStatusId() {
        return refStatusInvoiceByStatusId;
    }

    public void setRefStatusInvoiceByStatusId(RefStatusInvoiceDocEntity refStatusInvoiceByStatusId) {
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
