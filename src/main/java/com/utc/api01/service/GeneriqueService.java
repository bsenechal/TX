package com.utc.api01.service;

import java.util.List;

public interface GeneriqueService<T> {
   
    /**
     * Ajout d'un objet T
     * @param c
     */
    public void add(T u);

    /**
     * Mise à jour d'un objet T
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
     * Renvoi un objet T en fonction d'un critère
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
