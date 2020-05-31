package models.references;

import models.tables.JournalOperationsLotsDocEntity;
import models.tables.JournalOperationsStaffDocEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "ref_type_operations", schema = "public", catalog = "farm")
public class RefTypeOperationsDocEntity extends SuperReferenceEntity {
    private Collection<JournalOperationsStaffDocEntity> journalOperationsStaffsById = new HashSet<>();
    private Collection<JournalOperationsLotsDocEntity> journalOperationsLotsDocEntityCollection = new HashSet<>();
    public void addJournal(JournalOperationsStaffDocEntity journal) {
        journal.setRefTypeOperationsByTypeOperationsId(this);
        this.journalOperationsStaffsById.add(journal);
    }
    public void addJournalLot(JournalOperationsLotsDocEntity journal) {
        journal.setRefTypeOperationsByTypeOperationsId(this);
        this.journalOperationsLotsDocEntityCollection.add(journal);
    }

    @OneToMany(mappedBy = "refTypeOperationsByTypeOperationsId", fetch = FetchType.LAZY)
    public Collection<JournalOperationsStaffDocEntity> getJournalOperationsStaffsById() {
        return journalOperationsStaffsById;
    }

    public void setJournalOperationsStaffsById(Collection<JournalOperationsStaffDocEntity> journalOperationsStaffsById) {
        this.journalOperationsStaffsById = journalOperationsStaffsById;
    }

    @OneToMany(mappedBy = "refTypeOperationsByTypeOperationsId", fetch = FetchType.LAZY)
    public Collection<JournalOperationsLotsDocEntity> getJournalOperationsLotsDocEntityCollection() {
        return journalOperationsLotsDocEntityCollection;
    }

    public void setJournalOperationsLotsDocEntityCollection(Collection<JournalOperationsLotsDocEntity> journalOperationsLotsDocEntityCollection) {
        this.journalOperationsLotsDocEntityCollection = journalOperationsLotsDocEntityCollection;
    }
}
