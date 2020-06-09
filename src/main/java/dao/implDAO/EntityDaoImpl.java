package dao.implDAO;

import dao.DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EntityDaoImpl<Entity, Key> implements DAO<Entity, Key> {
    private final SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void exit() {
        sessionFactory.close();
    }

    @Override
    public void create(Entity entity) {
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
            session.close();
        }
    }
    @Override
    public void updateOrSave(Entity entity) {
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
            session.close();
        }
    }
    @Override
    public Entity read(Entity entity, Key id) {
        try(final Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Entity> cr = (CriteriaQuery<Entity>) cb.createQuery(entity.getClass());
            Root<Entity> root = (Root<Entity>) cr.from(entity.getClass());
            cr.select(root).where(cb.equal(root.get("id"), id));
            Query query = session.createQuery(cr);
            entity = (Entity) query.getSingleResult();
            session.close();
            return entity != null ? entity : null;

        }
    }

    @Override
    public void update(Entity entity) {
        try(final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
           // session.saveOrUpdate(entity);
            session.getTransaction().commit();
            session.close();
        }
    }
    @Override
    public void updateOrSave(List<Entity> entities) {
        try(final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (Entity entity: entities) {
                session.saveOrUpdate(entity);
            }
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void delete(Entity entity) {
        try(final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            session.close();
        }
    }
    @Override
    public List<Entity> getAllRows(Entity entity) {
        try (final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM "+ entity.getClass().getName());
            List<Entity> results = query.getResultList();
            session.getTransaction().commit();
            session.close();
            return results;
        }
    }
    @Override
    public List<Entity> getDateRows(Entity entity, LocalDate startDate, LocalDate finishDate, String field) {
        try (final Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Entity> cr = (CriteriaQuery<Entity>) cb.createQuery(entity.getClass());
            Root<Entity> root = (Root<Entity>) cr.from(entity.getClass());
            cr.select(root).where(cb.between(root.get(field), startDate, finishDate));
            Query query = session.createQuery(cr);
            List<Entity> results = query.getResultList();
            session.close();
            return results;
        }
    }
    @Override
    public List<Entity> getSomeTypeDateDocs(Entity entity, Key typeId, LocalDate startDate, LocalDate finishDate) {
        try (final Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Entity> cr = (CriteriaQuery<Entity>) cb.createQuery(entity.getClass());
            Root<Entity> root = (Root<Entity>) cr.from(entity.getClass());
            cr.select(root).where(cb.and(cb.equal(root.get("refTypeDocByTypeDocId"), typeId),cb.between(root.get("date"), startDate, finishDate)));
            Query query = session.createQuery(cr);
            List<Entity> results = query.getResultList();
            session.close();
            return results;
        }
    }
    @Override
    public List<Entity> getSomeRows(Entity entity, Key key, String field) {
        try (final Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Entity> cr = (CriteriaQuery<Entity>) cb.createQuery(entity.getClass());
            Root<Entity> root = (Root<Entity>) cr.from(entity.getClass());
            cr.select(root).where(cb.equal(root.get(field), key));
            Query query = session.createQuery(cr);
            List<Entity> results = query.getResultList();
            session.close();
            return results;
        }
    }
    @Override
    public Entity readRow(Entity entity, Map<String, Key> keys) {
        try (final Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Entity> cr = (CriteriaQuery<Entity>) cb.createQuery(entity.getClass());
            Root<Entity> root = (Root<Entity>) cr.from(entity.getClass());
            Predicate[] predicates = new Predicate[keys.size()];
            int i = 0;
            for (String field: keys.keySet()) {
                predicates[i] = cb.equal(root.get(field), keys.get(field));
                i++;
            }
            cr.select(root).where(predicates);
            Query query = session.createQuery(cr);
            entity = (Entity) query.getSingleResult();
            session.close();
            return  entity != null ? entity : null;
        }
    }
}
