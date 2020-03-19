package models;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class SuperEntity {
    private Long id;
    private String name;
    public SuperEntity() {}
    public SuperEntity(String name) {
        this.name = name;
    }

    //  @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        SuperEntity that = (SuperEntity) o;
        return getId() == that.getId() &&
                Objects.equals(name, that.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, getId());
    }

    @Override
    public String toString() {
        return name;
    }
}
