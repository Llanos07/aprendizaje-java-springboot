package com.example.belkaster.prototipos.tienda.dao;

import javax.persistence.EntityManager;

import com.example.belkaster.prototipos.tienda.modelo.Categoria;

public class CategoriaDao {
    private EntityManager em;

    public CategoriaDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Categoria categoria) {
        this.em.persist(categoria);
    }

    public void actualizar(Categoria categoria) {
        this.em.merge(categoria);
    }

    public void eliminar(Categoria categoria) {
        categoria = this.em.merge(categoria);
    }
}
