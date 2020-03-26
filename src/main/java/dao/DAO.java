package dao;

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
    List<Entity> getDateRows(Entity entity, Key startDate, Key finDate);
    List<Entity> getSomeRows(Entity entity, Key key);
}
