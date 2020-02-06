package dao;

import models.SuperEntity;

import java.util.Date;
import java.util.List;

public interface DAO<Entity, Key> {
    void exit ();
    void create(Entity entity);
    Entity read(Key key);
    void update(Entity entity);
    void delete(Entity entity);
    List<Entity> getAllRows(SuperEntity entity);
    List<Entity> getDateRows(SuperEntity entity, Date startDate, Date finDate);
}
