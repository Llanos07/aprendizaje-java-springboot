package com.example.belkaster.prototipos.tienda.vo;

import java.time.LocalDate;

public class RelatorioVentas {
    
    private String nombreProducto;
    private Long cantidadProducto;
    private LocalDate fechaUltimaVenta;
    
    public RelatorioVentas() {
    }

    public RelatorioVentas(String nombreProducto, Long cantidadProducto, LocalDate fechaUltimaVenta) {
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidadProducto;
        this.fechaUltimaVenta = fechaUltimaVenta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Long getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Long cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public LocalDate getFechaUltimaVenta() {
        return fechaUltimaVenta;
    }

    public void setFechaUltimaVenta(LocalDate fechaUltimaVenta) {
        this.fechaUltimaVenta = fechaUltimaVenta;
    }

    @Override
    public String toString() {
        return "RelatorioVentas [nombreProducto=" + nombreProducto + ", cantidadProducto=" + cantidadProducto
                + ", fechaUltimaVenta=" + fechaUltimaVenta + "]";
    }

    
    
}
