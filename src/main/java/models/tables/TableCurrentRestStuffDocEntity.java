package models.tables;

import models.SuperEntity;
import models.references.NomenklEntity;
import models.references.StorageEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "table_current_rest_stuff", schema = "public", catalog = "farm")
public class TableCurrentRestStuffDocEntity extends SuperEntity {
    private Integer qty;
    private Float sum;
    private NomenklEntity nomenklEntityByNomId;
    private StorageEntity storageEntityById;

    @Basic
    @Column(name = "qty", nullable = true)
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Basic
    @Column(name = "sum", nullable = true, precision = 0)
    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableCurrentRestStuffDocEntity that = (TableCurrentRestStuffDocEntity) o;
        return getId() == that.getId() &&
                Objects.equals(qty, that.qty) &&
                Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), qty, sum);
    }

    @ManyToOne
    @JoinColumn(name = "nomenkl_id", referencedColumnName = "id", nullable = false)
    public NomenklEntity getNomenklEntityByNomId() {
        return nomenklEntityByNomId;
    }

    public void setNomenklEntityByNomId(NomenklEntity nomenklEntityByNomId) {
        this.nomenklEntityByNomId = nomenklEntityByNomId;
    }
    @ManyToOne
    @JoinColumn(name = "storage_id", referencedColumnName = "id")
    public StorageEntity getStorageEntityById() {
        return storageEntityById;
    }
    public void setStorageEntityById(StorageEntity storageEntityById) {
        this.storageEntityById = storageEntityById;
    }

}
