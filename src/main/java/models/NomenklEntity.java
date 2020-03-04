package models;

import address.documents.invoices.DocInvoiceHeadDocEntity;
import address.documents.invoices.TableInvoiceNomDocEntity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "ref_nomenkl", schema = "public", catalog = "farm")
public class NomenklEntity extends SuperEntity {
    private ClassificationEntity refClassificationByClassificationId;
    private SizeEntity refSizeBySizeId;
    private Collection<TableInvoiceNomDocEntity> tableInvoiceNomDocEntities;

    @OneToMany(mappedBy = "nomenklEntityByNomId")
    public Collection<TableInvoiceNomDocEntity> getTableInvoiceNomDocEntities() {
        return tableInvoiceNomDocEntities;
    }

    public void setTableInvoiceNomDocEntities(Collection<TableInvoiceNomDocEntity> tableInvoiceNomDocEntities) {
        this.tableInvoiceNomDocEntities = tableInvoiceNomDocEntities;
    }

    @ManyToOne
    @JoinColumn(name = "classification_id", referencedColumnName = "id", nullable = false)
    public ClassificationEntity getRefClassificationByClassificationId() {
        return refClassificationByClassificationId;
    }

    public void setRefClassificationByClassificationId(ClassificationEntity refClassificationByClassificationId) {
        this.refClassificationByClassificationId = refClassificationByClassificationId;
    }

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false)
    public SizeEntity getRefSizeBySizeId() {
        return refSizeBySizeId;
    }

    public void setRefSizeBySizeId(SizeEntity refSizeBySizeId) {
        this.refSizeBySizeId = refSizeBySizeId;
    }
}
