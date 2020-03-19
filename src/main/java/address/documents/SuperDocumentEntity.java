package address.documents;

import models.ContragentEntity;
import models.SuperEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public abstract class SuperDocumentEntity extends SuperEntity {

    private ContragentEntity refContragentEntityByContragentId;
    private Float sum;
    private LocalDate date;
    private Boolean editable;
    private Float vat;
    private Float sum_vat;

    public SuperDocumentEntity() {}
    public SuperDocumentEntity(Long id, String number, ContragentEntity refContragentEntityByContragentId, Float sum, LocalDate date, boolean editable, Float vat, Float sum_vat) {
        this.refContragentEntityByContragentId = refContragentEntityByContragentId;
        this.date = date;
        this.sum = sum;
        this.editable = editable;
        this.vat = vat;
        this.sum_vat = sum_vat;
    }

    @Basic
    @Column(name = "editable", nullable = true, updatable = true)
    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    @ManyToOne
    @JoinColumn(name = "contra_id", referencedColumnName = "id", nullable = false)
    public ContragentEntity getRefContragentEntityByContragentId() {
        return refContragentEntityByContragentId;
    }

    public void setRefContragentEntityByContragentId(ContragentEntity refContragentEntityByContragentId) {
        this.refContragentEntityByContragentId = refContragentEntityByContragentId;
    }
    @Basic
    @Column(name = "sum", nullable = true)
    public Float getSum() {
        return sum;
    }
    public void setSum(Float sum) {
        this.sum = sum;
    }
    @Basic
    @Column(name="vat", nullable = true)
    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }
    @Basic
    @Column(name = "sum_vat", nullable = true)
    public Float getSum_vat() {
        return sum_vat;
    }

    public void setSum_vat(Float sum_vat) {
        this.sum_vat = sum_vat;
    }
}
