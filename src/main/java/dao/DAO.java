package dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface DAO<Entity, Key> {
    void exit ();
    void create(Entity entity);
    Entity read(Entity entity, Key key);
    Entity readRow(Entity entity, Map<String, Key> keys);
    void update(Entity entity);
    void updateOrSave(List<Entity> entities);
    void updateOrSave(Entity entities);
    void delete(Entity entity);
    List<Entity> getAllRows(Entity entity);
    List<Entity> getDateRows(Entity entity, LocalDate startDate, LocalDate finDate);
    List<Entity> getSomeRows(Entity entity, Key key, String field);
    List<Entity> getSomeTypeDateDocs(Entity entity, Key type, LocalDate startDate, LocalDate endDate);
}
