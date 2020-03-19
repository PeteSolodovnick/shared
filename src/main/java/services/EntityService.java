package services;

import dao.implDAO.EntityDaoImpl;
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
    public void delete(Entity entity) {
        this.entity.delete(entity);
    }
    public Entity read(Entity entity, Key id) {
        return (Entity) this.entity.read(entity, id);
    }
    public List<Entity> getAllRows(Entity entity) {
        return this.entity.getAllRows(entity);
    }
    public List<Entity> getDateRows (Entity entity, Key startDate, Key endDate) {
        return this.entity.getDateRows(entity, startDate, endDate);
    }
    public List<Entity> getSomeRows (Entity entity, Key key) {
        return this.entity.getSomeRows(entity, key);
    }
    public Entity readRow(Entity entity, Map<String, Key> keys) {
        return this.entity.readRow(entity, keys);
    }
}
