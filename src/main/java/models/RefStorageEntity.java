package models;

import address.stores.DocDocsHeadEntity;
import models.SuperEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_storage", schema = "public", catalog = "farm")
public class RefStorageEntity extends SuperEntity {
    private Integer attribute;
 //   private Collection<DocDocsHeadEntity> docDocsHeadsById;
 //   private Collection<DocDocsHeadEntity> docDocsHeadsById_0;

    @Basic
    @Column(name = "attribute", nullable = true)
    public Integer getAttribute() {
        return attribute;
    }

    public void setAttribute(Integer attribute) {
        this.attribute = attribute;
    }
}
