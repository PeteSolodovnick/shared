package models.tables;

import models.SuperEntity;
import models.references.RefTypeOperationsDocEntity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "journal_operations_lots", schema = "public", catalog = "farm")
public class JournalOperationsLotsDocEntity extends SuperEntity {
    private Date recTime;
    private Integer qty;
    private Float sum;
    private TableDocLotsDocEntity tableDocLotsByTableLotsId;
    private RefTypeOperationsDocEntity refTypeOperationsByTypeOperationsId;

    @Basic
    @Column(name = "rec_time")
    public Date getRecTime() {
        return recTime;
    }

    public void setRecTime(Date recTime) {
        this.recTime = recTime;
    }

    @Basic
    @Column(name = "qty")
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Basic
    @Column(name = "sum")
    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    @ManyToOne
    @JoinColumn(name = "table_lots_id", referencedColumnName = "id", nullable = false)
    public TableDocLotsDocEntity getTableDocLotsByTableLotsId() {
        return tableDocLotsByTableLotsId;
    }

    public void setTableDocLotsByTableLotsId(TableDocLotsDocEntity tableDocLotsByTableLotsId) {
        this.tableDocLotsByTableLotsId = tableDocLotsByTableLotsId;
    }
    @ManyToOne
    @JoinColumn(name = "type_operation_id", referencedColumnName = "id")
    public RefTypeOperationsDocEntity getRefTypeOperationsByTypeOperationsId() {
        return refTypeOperationsByTypeOperationsId;
    }

    public void setRefTypeOperationsByTypeOperationsId(RefTypeOperationsDocEntity refTypeOperationsByTypeOperationsId) {
        this.refTypeOperationsByTypeOperationsId = refTypeOperationsByTypeOperationsId;
    }
}
