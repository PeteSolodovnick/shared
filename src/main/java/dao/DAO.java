package dao;

import address.documents.SuperDocumentEntity;
import models.SuperEntity;

import java.util.Date;
import java.util.List;

public interface DAO<Entity, Key> {
    void exit ();
    void create(Entity entity);
    Entity read(Entity entity, Key key);
    void update(Entity entity);
    void delete(Entity entity);
    List<Entity> getAllRows(Entity entity);
    List<Entity> getDateRows(Entity entity, Key startDate, Key finDate);
}
