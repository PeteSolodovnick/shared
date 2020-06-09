package services;

import dao.implDAO.EntityDaoImpl;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EntityService<Entity, Key> {
    private EntityDaoImpl<Entity, Key> entity = new EntityDaoImpl();
    public EntityService() {}

    public void exit() {
        entity.exit();
    }
    public void create(Entity entity) {
        this.entity.create(entity);
    }
    public void update(Entity entity) {
        this.entity.update(entity);
    }
    public void updateOrSave(List<Entity> entity) {
        this.entity.updateOrSave(entity);
    }
    public void updateOrSave(Entity entity) {
        this.entity.updateOrSave(entity);
    }
    public void delete(Entity entity) {
        this.entity.delete(entity);
    }
    public Entity read(Entity entity, Key id) {
        return (Entity) this.entity.read(entity, id);
    }
    public List<Entity> getAllRows(Entity entity) {
        return this.entity.getAllRows(entity);
    }
    public List<Entity> getDateRows (Entity entity, LocalDate startDate, LocalDate endDate, String field) {
        return this.entity.getDateRows(entity, startDate, endDate, field);
    }
    public List<Entity> getSomeRows (Entity entity, Key key, String field) {
        return this.entity.getSomeRows(entity, key, field);
    }
    public Entity readRow(Entity entity, Map<String, Key> keys) {
        return this.entity.readRow(entity, keys);
    }

    public EntityDaoImpl<Entity, Key> getEntity() {
        return entity;
    }
    public List<Entity> getSomeTypeDateDocs(Entity entity, Key typeId, LocalDate startDate, LocalDate endDate) {
        return this.entity.getSomeTypeDateDocs(entity,typeId,startDate,endDate);
    }
}
