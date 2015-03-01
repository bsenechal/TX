package com.utc.api01.service;

import java.util.List;

import com.utc.api01.dao.GeneriqueDao;

public class GeneriqueServiceImpl<T> implements GeneriqueService<T> {

    private GeneriqueDao<T> dao;

    public GeneriqueServiceImpl(Class<T> tClass) {
        super();
    }

    public GeneriqueServiceImpl() {
        super();
    }

    public void setDao(GeneriqueDao<T> dao) {
        this.dao = dao;
    }

    public GeneriqueDao<T> getDao() {
        return dao;
    }

    @Override
    public void add(T u) {
        this.dao.add(u);
    }

    @Override
    public void update(T u) {
        this.dao.update(u);
    }

    @Override
    public List<T> list() {
        return this.dao.list();
    }

    @Override
    public T getById(int id) {
        return this.dao.getById(id);
    }

    @Override
    public void remove(int id) {
        this.dao.remove(id);
    }

    @Override
    public T getByCriteria(String criteria, String value) {
        return this.dao.getByCriteria(criteria, value);
    }
}
