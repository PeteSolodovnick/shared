package dao.implDAO;

import address.documents.SuperDocumentEntity;
import dao.DAO;
import models.SuperEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import utils.HibernateSessionFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class EntityDaoImpl<Entity, Key> implements DAO<Entity, Key> {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

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
    public Entity read(Entity entity, Key id) {
        try(final Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Entity> cr = (CriteriaQuery<Entity>) cb.createQuery(entity.getClass());
            Root<Entity> root = (Root<Entity>) cr.from(entity.getClass());
            cr.select(root).where(cb.equal(root.get("Id"), id));
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
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Entity> cr = (CriteriaQuery<Entity>) cb.createQuery(entity.getClass());
            Root<Entity> root = (Root<Entity>) cr.from(entity.getClass());
            cr.select(root);
            Query query = session.createQuery(cr);
            List<Entity> results = query.getResultList();
            return results;
        }
    }
    @Override
    public List<Entity> getDateRows(Entity entity, Key startDate, Key finishDate) {
        try (final Session session = sessionFactory.openSession()) {
            Criteria cr = session.createCriteria(entity.getClass());
            cr.add(Restrictions.between("date", startDate, finishDate));
            return cr.list();
        }
    }
}
