package services;

import dao.implDAO.EntityDaoImpl;
import models.SuperEntity;

import java.util.List;

public class EntityService {
    private EntityDaoImpl entity = new EntityDaoImpl();
    public EntityService() {}

    public void exit() {
        entity.exit();
    }
    public void create(SuperEntity superEntity) {
        System.out.println("In EntityService create");
        this.entity.create(superEntity);
        System.out.println("after EntityService create");
    }
    public void update(SuperEntity superEntity) {
        this.entity.update(superEntity);
    }
    public void delete(SuperEntity superEntity) {
        this.entity.delete(superEntity);
    }
    public SuperEntity read(long id) {
        return this.entity.read(id);
    }
    public List<SuperEntity> getAllRows(SuperEntity entity) {
        return this.entity.getAllRows(entity);
    }
}
