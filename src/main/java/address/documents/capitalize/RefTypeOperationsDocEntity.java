package address.documents.capitalize;

import models.SuperEntity;
import models.references.SuperReferenceEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "ref_type_operations", schema = "public", catalog = "farm")
public class RefTypeOperationsDocEntity extends SuperReferenceEntity {
    private Collection<JournalOperationsStaffDocEntity> journalOperationsStaffsById = new HashSet<>();
    public void addJournal(JournalOperationsStaffDocEntity journal) {
        journal.setRefTypeOperationsByTypeOperationsId(this);
        this.journalOperationsStaffsById.add(journal);
    }

    @OneToMany(mappedBy = "refTypeOperationsByTypeOperationsId", fetch = FetchType.LAZY)
    public Collection<JournalOperationsStaffDocEntity> getJournalOperationsStaffsById() {
        return journalOperationsStaffsById;
    }

    public void setJournalOperationsStaffsById(Collection<JournalOperationsStaffDocEntity> journalOperationsStaffsById) {
        this.journalOperationsStaffsById = journalOperationsStaffsById;
    }
}
