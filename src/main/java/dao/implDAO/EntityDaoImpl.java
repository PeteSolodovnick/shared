package dao.implDAO;

import dao.DAO;
import models.RefTerritoryEntity;
import models.SuperEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Entity;
import org.hibernate.criterion.Restrictions;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

public class EntityDaoImpl implements DAO<SuperEntity,Long> {
    private SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public void exit() {
        sessionFactory.close();
    }

    @Override
    public void create(SuperEntity entity) {
        System.out.println("IN EntityDaoInp create");
        try (final Session session = sessionFactory.openSession()) {
            System.out.println("session opened");
            session.beginTransaction();
            System.out.println("yayayayayayayya");
            session.save(entity);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public SuperEntity read(Long id) {
        try(final Session session = sessionFactory.openSession()) {
            SuperEntity entity = session.get(SuperEntity.class, id);
            session.close();
            return entity != null ? entity : null;

        }
    }

    @Override
    public void update(SuperEntity entity) {
        try(final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void delete(SuperEntity entity) {
        try(final Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            session.close();
        }
    }
    @Override
    public List<SuperEntity> getAllRows(SuperEntity entity) {
        try (final Session session = sessionFactory.openSession()) {
            return session.createCriteria(entity.getClass()).list();
        }
    }
}
