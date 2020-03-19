package models;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class SuperEntity {
    private Long id;
    public SuperEntity() {}
    //  @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuperEntity that = (SuperEntity) o;
        return getId() == that.getId();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "id= " + getId();
    }
}
