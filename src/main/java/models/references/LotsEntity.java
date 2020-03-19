package models.references;

import javax.persistence.*;

@Entity
@Table(name = "ref_lots", schema = "public", catalog = "farm")
public class LotsEntity extends SuperReferenceEntity { ;
    private int startCount;
    private float startWeight;
    private int startAge;
    private Integer number;
    private KindLotsEntity refKindLotsByKindLotsId;
    private TypeLotsEntity refTypeLotsByTypeLotsId;

    @Basic
    @Column(name = "start_count", nullable = false)
    public int getStartCount() {
        return startCount;
    }

    public void setStartCount(int startCount) {
        this.startCount = startCount;
    }

    @Basic
    @Column(name = "start_weight", nullable = false, precision = 0)
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

    @Basic
    @Column(name = "number", nullable = true)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
}
