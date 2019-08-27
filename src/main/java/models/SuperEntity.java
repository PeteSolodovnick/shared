package models;

import javax.persistence.*;

@MappedSuperclass
public abstract class SuperEntity {

    private Long id;
    public SuperEntity() {}
    public SuperEntity(Long id) {
        this.id = id;
    }

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
