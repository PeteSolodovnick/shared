package models.references;

import models.tables.JournalCareLotDocEntity;
import models.tables.JournalOperationsLotsDocEntity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "ref_lots", schema = "public", catalog = "farm")
public class LotsEntity extends SuperReferenceEntity {
    private Date date;
    private int startCount;
    private float startWeight;
    private int startAge;
    private boolean editable;
    private boolean deleted;
    private Integer currentCount;
    private Float currentWeight;
    private Integer currentAge;
    private KindLotsEntity refKindLotsByKindLotsId;
    private TypeLotsEntity refTypeLotsByTypeLotsId;
    private NomenklEntity refNomenklEntityById;
    private Collection<JournalOperationsLotsDocEntity> journalOperationsLotsDocEntities = new HashSet<>();
    private Collection<JournalCareLotDocEntity> journalCareLotEntities = new HashSet<>();

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "start_count", nullable = false)
    public int getStartCount() {
        return startCount;
    }

    public void setStartCount(int startCount) {
        this.startCount = startCount;
    }

    @Basic
    @Column(name = "editable")
    public Boolean getEditable() {return editable; }
    public void setEditable(Boolean editable) {this.editable = editable; }

    @Basic
    @Column(name = "deleted", updatable = false)
    public Boolean getDeleted() {return deleted; }
    public void setDeleted(Boolean deleted) {this.deleted = deleted; }

    @Basic
    @Column(name = "current_weight")
    public Float getCurrentWeight() {
        return currentWeight;
    }
    public void setCurrentWeight(Float currentWeight) {
        this.currentWeight = currentWeight;
    }

    @Basic
    @Column(name = "current_count")
    public Integer getCurrentCount() {
        return currentCount;
    }
    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }

    @Basic
    @Column(name = "current_age")
    public Integer getCurrentAge() {
        return currentAge;
    }
    public void setCurrentAge(Integer currentAge) {
        this.currentAge = currentAge;
    }

    @Basic
    @Column(name = "start_weight", nullable = false)
    public float getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(float startWeight) {
        this.startWeight = startWeight;
    }

    @Basic
    @Column(name = "start_age", nullable = false)
    public int getStartAge() {
        return startAge;
    }

    public void setStartAge(int startAge) {
        this.startAge = startAge;
    }

    @ManyToOne
    @JoinColumn(name = "kind_lots_id", referencedColumnName = "id", nullable = false)
    public KindLotsEntity getRefKindLotsByKindLotsId() {
        return refKindLotsByKindLotsId;
    }

    public void setRefKindLotsByKindLotsId(KindLotsEntity refKindLotsByKindLotsId) {
        this.refKindLotsByKindLotsId = refKindLotsByKindLotsId;
    }

    @ManyToOne
    @JoinColumn(name = "type_lots_id", referencedColumnName = "id", nullable = false)
    public TypeLotsEntity getRefTypeLotsByTypeLotsId() {
        return refTypeLotsByTypeLotsId;
    }

    public void setRefTypeLotsByTypeLotsId(TypeLotsEntity refTypeLotsByTypeLotsId) {
        this.refTypeLotsByTypeLotsId = refTypeLotsByTypeLotsId;
    }

    @ManyToOne
    @JoinColumn(name = "nomenkl_id", referencedColumnName = "id", nullable = false)
    public NomenklEntity getRefNomenklEntityById() {
        return refNomenklEntityById;
    }

    public void setRefNomenklEntityById(NomenklEntity refNomenklEntityById) {
        this.refNomenklEntityById = refNomenklEntityById;
    }

    @OneToMany(mappedBy = "refLotsId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Collection<JournalOperationsLotsDocEntity> getJournalOperationsLotsDocEntities() {
        return journalOperationsLotsDocEntities;
    }
    public void setJournalOperationsLotsDocEntities(Collection<JournalOperationsLotsDocEntity> journalOperationsLotsDocEntities) {
        this.journalOperationsLotsDocEntities = journalOperationsLotsDocEntities;
    }

    @OneToMany(mappedBy = "lotsEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Collection<JournalCareLotDocEntity> getJournalCareLotEntities() {
        return journalCareLotEntities;
    }

    public void setJournalCareLotEntities(Collection<JournalCareLotDocEntity> journalCareLotEntities) {
        this.journalCareLotEntities = journalCareLotEntities;
    }
}
