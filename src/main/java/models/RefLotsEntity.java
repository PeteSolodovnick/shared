package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ref_lots", schema = "public", catalog = "farm")
public class RefLotsEntity extends SuperEntity{ ;
    private int startCount;
    private float startWeight;
    private int startAge;
    private Integer number;
    private RefKindLotsEntity refKindLotsByKindLotsId;
    private RefTypeLotsEntity refTypeLotsByTypeLotsId;

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
    public RefKindLotsEntity getRefKindLotsByKindLotsId() {
        return refKindLotsByKindLotsId;
    }

    public void setRefKindLotsByKindLotsId(RefKindLotsEntity refKindLotsByKindLotsId) {
        this.refKindLotsByKindLotsId = refKindLotsByKindLotsId;
    }

    @ManyToOne
    @JoinColumn(name = "type_lots_id", referencedColumnName = "id", nullable = false)
    public RefTypeLotsEntity getRefTypeLotsByTypeLotsId() {
        return refTypeLotsByTypeLotsId;
    }

    public void setRefTypeLotsByTypeLotsId(RefTypeLotsEntity refTypeLotsByTypeLotsId) {
        this.refTypeLotsByTypeLotsId = refTypeLotsByTypeLotsId;
    }
}
