package models.tables;


import models.SuperEntity;
import models.references.NomenklEntity;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "table_doc_lots", schema = "public", catalog = "farm")
public class TableDocLotsDocEntity extends SuperEntity {
    private NomenklEntity nomenklEntityByNomId;
    private JournalOperationsLotsDocEntity journalOperationsLotsById;
    private Integer qty;
    private Float sum;

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

    @ManyToOne
    @JoinColumn(name = "nomenkl_id", referencedColumnName = "id", nullable = false)
    public NomenklEntity getNomenklEntityByNomId() {
        return nomenklEntityByNomId;
    }

    public void setNomenklEntityByNomId(NomenklEntity nomenklEntityByNomId) {
        this.nomenklEntityByNomId = nomenklEntityByNomId;
    }
    @ManyToOne
    @JoinColumn(name = "journal_operation_lots_id", referencedColumnName = "id")
    public JournalOperationsLotsDocEntity getJournalOperationsLotsById() {
        return journalOperationsLotsById;
    }

    public void setJournalOperationsLotsById(JournalOperationsLotsDocEntity journalOperationsLotsById) {
        this.journalOperationsLotsById = journalOperationsLotsById;
    }
}
