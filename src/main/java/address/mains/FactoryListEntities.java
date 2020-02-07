package address.mains;

import address.documents.SuperDocumentEntity;
import address.documents.invoices.DocInvoiceHeadDocEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import models.*;
import services.EntityService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class FactoryListEntities<T> {
    private T t;
    public FactoryListEntities(T t) {
        this.t = t;
    }
    public ObservableList<T> getListEntities() {
        ObservableList<T> entityList = FXCollections.observableArrayList();
        EntityService<T, Long> service = new EntityService<>();
        for (T entity: service.getAllRows(t)) {
            entityList.add(entity);
        }
        return entityList;
    }
    public ObservableList<T> getDateListEntities() {
        ObservableList<T> entityList = FXCollections.observableArrayList();
        EntityService<T, LocalDate> service = new EntityService<>();
        for (T entity: service.getDateRows(t, LocalDate.now().minusWeeks(1), LocalDate.now())) {
            entityList.add(entity);
        }
        return entityList;
    }
}
