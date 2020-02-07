package address.documents.invoices;

import models.SuperEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_status_invoice", schema = "public", catalog = "farm")
public class RefStatusInvoiceDocEntity extends SuperEntity {
    private Collection<DocInvoiceHeadDocEntity> docInvoiceHeadsById;

    @OneToMany(mappedBy = "refStatusInvoiceByStatusId")
    public Collection<DocInvoiceHeadDocEntity> getDocInvoiceHeadsById() {
        return docInvoiceHeadsById;
    }

    public void setDocInvoiceHeadsById(Collection<DocInvoiceHeadDocEntity> docInvoiceHeadsById) {
        this.docInvoiceHeadsById = docInvoiceHeadsById;
    }
}
