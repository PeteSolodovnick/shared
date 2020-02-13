package models;

import javax.persistence.*;

@Entity
@Table(name = "ref_storage", schema = "public", catalog = "farm")
public class StorageEntity extends SuperEntity {
    private Integer attribute;
    @Basic
    @Column(name = "attribute", nullable = true)
    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }
}
