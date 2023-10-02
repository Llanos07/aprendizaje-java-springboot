package com.example.belkaster.prototipos.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.example.belkaster.prototipos.tienda.modelo.Producto;

public class ProductoDao {

    private EntityManager em;

    public ProductoDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Producto producto) {
        this.em.persist(producto);
    }

    public Producto consultaIndividual(Long id) {
        return this.em.find(Producto.class, id);
    }

    public List<Producto> consultaGlobal() {
        String jpql = "SELECT P FROM Producto AS P";
        return em.createQuery(jpql, Producto.class).getResultList();
    }

    public List<Producto> consultaPorNombre(String nombre) {
        String jpql = "SELECT P FROM Producto AS P WHERE P.nombre LIKE :nombre";
        return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Producto> consultaPorCategoria(String nombre) {
        String jpql = "SELECT P FROM Producto AS P WHERE P.categoria.nombre = :nombre";
        return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombre(String nombre) {
       return em.createNamedQuery("Producto.consultaPrecio", BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}
