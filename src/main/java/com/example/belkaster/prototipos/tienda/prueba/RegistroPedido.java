package com.example.belkaster.prototipos.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.example.belkaster.prototipos.tienda.dao.CategoriaDao;
import com.example.belkaster.prototipos.tienda.dao.ClienteDao;
import com.example.belkaster.prototipos.tienda.dao.PedidoDao;
import com.example.belkaster.prototipos.tienda.dao.ProductoDao;
import com.example.belkaster.prototipos.tienda.modelo.Categoria;
import com.example.belkaster.prototipos.tienda.modelo.Cliente;
import com.example.belkaster.prototipos.tienda.modelo.Pedido;
import com.example.belkaster.prototipos.tienda.modelo.PedidoProductos;
import com.example.belkaster.prototipos.tienda.modelo.Producto;
import com.example.belkaster.prototipos.tienda.utils.JPAUtils;
import com.example.belkaster.prototipos.tienda.vo.RelatorioVentas;

public class RegistroPedido {

    public static void main(String[] args) {
        registrarProducto();

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);
        Producto producto = productoDao.consultaIndividual(1L);

        ClienteDao clienteDao = new ClienteDao(em);
        PedidoDao pedidoDao = new PedidoDao(em);

        Cliente cliente = new Cliente("Billy", "123456789");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarProducto(new PedidoProductos(7, producto, pedido));

        em.getTransaction().begin();
        clienteDao.guardar(cliente);
        pedidoDao.guardar(pedido);
        em.getTransaction().commit();
        BigDecimal valorTotal = pedidoDao.valorTotalVentas();
        System.out.println("valorTotal: " + valorTotal);

        List<RelatorioVentas> relatorioVentas = pedidoDao.relatorioVentasVO();
        
        relatorioVentas.forEach(System.out::println);
    }

    private static void registrarProducto() {
        Categoria utilesCocina = new Categoria("UtilesCocina");
        Producto losaSet = new Producto("Aktuall",
                "set de 6 tazas y 6 platillos",
                new BigDecimal("109990"),
                utilesCocina);

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDao productoDao = new ProductoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();
        categoriaDao.guardar(utilesCocina);
        productoDao.guardar(losaSet);
        em.getTransaction().commit();
        em.close();
    }
}
