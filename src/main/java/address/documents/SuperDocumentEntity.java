package address.documents;

import models.ContragentEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public abstract class SuperDocumentEntity {

    private Long id;
    private String number;
    private ContragentEntity refContragentEntityByContragentId;
    private Float sum;
    private LocalDate date;

    public SuperDocumentEntity() {}
    public SuperDocumentEntity(Long id, String number, ContragentEntity refContragentEntityByContragentId, Float sum, LocalDate date) {
        this.id = id;
        this.number = number;
        this.refContragentEntityByContragentId = refContragentEntityByContragentId;
        this.date = date;
        this.sum = sum;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Basic
    @Column(name = "number", nullable = false, length = 20)
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperDocumentEntity that = (SuperDocumentEntity) o;
        return getId() == that.getId() &&
                refContragentEntityByContragentId.getId() == that.refContragentEntityByContragentId.getId() &&
                Objects.equals(number, that.number) &&
                Objects.equals(date, that.date) &&
                Objects.equals(sum, that.sum);
    }
    public int hashCode() {
        return Objects.hash(getId(), number, date, refContragentEntityByContragentId.getId(), sum);
    }
    @Override
    public String toString() {
        return number;
    }
}
