package com.utc.tx.service;

import java.util.List;

public interface GenericService<T> {
   
    /**
     * Ajout d'un objet T
     * @param c
     */
    public void add(T u);

    /**
     * Mise � jour d'un objet T
     * @param c
     */
    public void update(T u);
    
    /**
     * Renvoi une liste d'objet T
     * @return List<T>
     */
    public List<T> list();
    
    /**
     * Renvoi un objet T en fonction de son id
     * @param id
     * @return T
     */
    public T getById(int id);
    
    /**
     * Renvoi un objet T en fonction d'un crit�re
     * @param criteria
     * @param value
     * @return T
     */
    public T getByCriteria(String criteria, String value);

    /**
     * Supprime un objet en fonction de son id
     * @param id
     */
    public void remove(int id);

}
