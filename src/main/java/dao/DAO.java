package dao;

import java.util.List;

public interface DAO<Entity, Key> {
    void exit ();
    void create(Entity entity);
    Entity read(Entity entity, Key key);
    void update(Entity entity);
    void delete(Entity entity);
    List<Entity> getAllRows(Entity entity);
    List<Entity> getDateRows(Entity entity, Key startDate, Key finDate);
    List<Entity> getSomeRows(Entity entity, Key key);
}
