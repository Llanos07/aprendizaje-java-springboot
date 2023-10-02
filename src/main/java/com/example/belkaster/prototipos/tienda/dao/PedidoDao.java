package com.example.belkaster.prototipos.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.example.belkaster.prototipos.tienda.modelo.Pedido;
import com.example.belkaster.prototipos.tienda.vo.RelatorioVentas;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public Pedido consultaIndividual(Long id) {
        return this.em.find(Pedido.class, id);
    }

    public List<Pedido> consultaGlobal() {
        String jpql = "SELECT P FROM Pedido AS P";
        return em.createQuery(jpql, Pedido.class).getResultList();
    }

    public BigDecimal consultarPrecioPorNombre(String nombre) {
        String jpql = "SELECT P.precio FROM Pedido AS P WHERE P.nombre = :nombre";
        return em.createQuery(jpql, BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }

    public BigDecimal valorTotalVentas(){
        String jpql = "select sum(p.total) from Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<RelatorioVentas> relatorioVentasVO (){
        String jpql = "select new com.example.belkaster.prototipos.tienda.vo.RelatorioVentas (producto.nombre, "
        + "sum(pedidoProductos.cantidad), "
        + "max(pedido.fecha)) "
        + "from Pedido pedido "
        + "join pedido.pedidoProductos pedidoProductos "
        + "join pedidoProductos.producto producto "
        + "group by producto.nombre "
        + "order by pedidoProductos.cantidad desc";
        return em.createQuery(jpql, RelatorioVentas.class).getResultList();
    }
}
