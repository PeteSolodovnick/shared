package models.references;

import models.SuperEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;
@MappedSuperclass
public abstract class SuperReferenceEntity extends SuperEntity {
    private String name;
    public SuperReferenceEntity() {}
    public SuperReferenceEntity(String name) {
        this.name = name;
    }

    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperReferenceEntity that = (SuperReferenceEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, getId());
    }
    @Override
    public String toString() {
        return super.toString()+" name= "+ getName();
    }
}
