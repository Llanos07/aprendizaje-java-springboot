package com.example.belkaster.prototipos.tienda.prueba;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.example.belkaster.prototipos.tienda.dao.CategoriaDao;
import com.example.belkaster.prototipos.tienda.dao.ProductoDao;
import com.example.belkaster.prototipos.tienda.modelo.Categoria;
import com.example.belkaster.prototipos.tienda.modelo.Producto;
import com.example.belkaster.prototipos.tienda.utils.JPAUtils;

public class RegistroProducto {

    public static void main(String[] args) {
        registrarProducto();

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDao productoDao = new ProductoDao(em);
        Producto producto = productoDao.consultaIndividual(1L);
        System.out.println(producto.getNombre());

        List<Producto> productos = productoDao.consultaPorCategoria("UtilesCocina");
        BigDecimal precio = productoDao.consultarPrecioPorNombre("Aktuall");
        System.out.println(precio);
        productos.forEach(prod -> System.out.println(prod.getDescripcion()));

    }

    private static void registrarProducto() {
        Categoria utilesCocina = new Categoria("UtilesCocina");
        Producto losaSet = new Producto("Aktuall",
                "set de 6 tazas y 6 platillos",
                new BigDecimal("100.000"),
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
