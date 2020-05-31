package models.tables;

import models.SuperEntity;
import models.references.LotsEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "journal_care_lot", schema = "public", catalog = "farm")
public class JournalCareLotDocEntity extends SuperEntity {
    private Date recTime;
    private Integer age;
    private Float weight;
    private Integer count;
    private LotsEntity lotsEntity;

    @Basic
    @Column(name = "rec_time")
    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "weight")
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @ManyToOne
    @JoinColumn(name="lots_id", referencedColumnName = "id", nullable = false)
    public LotsEntity getLotsEntity() {
        return lotsEntity;
    }

    public void setLotsEntity(LotsEntity lotsEntity) {
        this.lotsEntity = lotsEntity;
    }

}
