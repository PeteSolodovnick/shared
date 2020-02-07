package address.documents.invoices;

import models.SuperEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "doc_type_invoice", schema = "public", catalog = "farm")
public class DocTypeInvoiceDocEntity extends SuperEntity {
    private Collection<DocInvoiceHeadDocEntity> docInvoiceHeadsById;

    @OneToMany(mappedBy = "docTypeInvoiceByTypeInvoiceId")
    public Collection<DocInvoiceHeadDocEntity> getDocInvoiceHeadsById() {
        return docInvoiceHeadsById;
    }

    public void setDocInvoiceHeadsById(Collection<DocInvoiceHeadDocEntity> docInvoiceHeadsById) {
        this.docInvoiceHeadsById = docInvoiceHeadsById;
    }
}
