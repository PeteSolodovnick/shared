package address.documents.capitalize;

import models.SuperEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "ref_type_operations", schema = "public", catalog = "farm")
public class RefTypeOperationsDocEntity extends SuperEntity {
    private Collection<JournalOperationsStaffDocEntity> journalOperationsStaffsById;

    @OneToMany(mappedBy = "refTypeOperationsByTypeOperationsId")
    public Collection<JournalOperationsStaffDocEntity> getJournalOperationsStaffsById() {
        return journalOperationsStaffsById;
    }

    public void setJournalOperationsStaffsById(Collection<JournalOperationsStaffDocEntity> journalOperationsStaffsById) {
        this.journalOperationsStaffsById = journalOperationsStaffsById;
    }
}
