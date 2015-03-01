package com.utc.api01.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class GeneriqueDaoImpl<T> implements GeneriqueDao<T> {

    private SessionFactory sessionFactory;
    private final Class<T> tClass;

    public GeneriqueDaoImpl(Class<T> tClass) {
        super();
        this.tClass = tClass;
    }

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void add(T c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(c);
    }

    @Override
    public void update(T c) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(c);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> list() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from " + this.tClass.getName()).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (T) session.get(this.tClass, new Integer(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        T c = (T) session.load(this.tClass, id);
        if (null != c) {
            session.delete(c);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getByCriteria(String criteria, String value) {
        Session session = this.sessionFactory.getCurrentSession();
        return (T) session.createCriteria(this.tClass).add( Restrictions.eq(criteria, value )).uniqueResult();
    }

}
