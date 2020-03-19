package models.references;

import models.SuperEntity;
import models.documents.DocInvoiceHeadDocEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_status_invoice", schema = "public", catalog = "farm")
public class RefStatusInvoiceDocEntity extends SuperReferenceEntity {
    private Collection<DocInvoiceHeadDocEntity> docInvoiceHeadsById;

    @OneToMany(mappedBy = "refStatusInvoiceByStatusId")
    public Collection<DocInvoiceHeadDocEntity> getDocInvoiceHeadsById() {
        return docInvoiceHeadsById;
    }

    public void setDocInvoiceHeadsById(Collection<DocInvoiceHeadDocEntity> docInvoiceHeadsById) {
        this.docInvoiceHeadsById = docInvoiceHeadsById;
    }
}
