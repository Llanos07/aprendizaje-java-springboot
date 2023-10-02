package com.example.belkaster.prototipos.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.example.belkaster.prototipos.tienda.modelo.Cliente;

public class ClienteDao {

    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public Cliente consultaIndividual(Long id) {
        return this.em.find(Cliente.class, id);
    }

    public List<Cliente> consultaGlobal() {
        String jpql = "SELECT P FROM Cliente AS P";
        return em.createQuery(jpql, Cliente.class).getResultList();
    }

    public List<Cliente> consultaPorNombre(String nombre) {
        String jpql = "SELECT P FROM Cliente AS P WHERE P.nombre LIKE :nombre";
        return em.createQuery(jpql, Cliente.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Cliente> consultaPorCategoria(String nombre) {
        String jpql = "SELECT P FROM Cliente AS P WHERE P.categoria.nombre = :nombre";
        return em.createQuery(jpql, Cliente.class).setParameter("nombre", nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombre(String nombre) {
        String jpql = "SELECT P.precio FROM Cliente AS P WHERE P.nombre = :nombre";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }
}
