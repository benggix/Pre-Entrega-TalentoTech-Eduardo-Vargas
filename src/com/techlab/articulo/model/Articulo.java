package com.techlab.articulo.model;

import com.techlab.articulo.enums.TipoArticulo;

public abstract class Articulo {
  private String nombre;
  private double precio;
  private int codigo;
  private Categoria categoria; // objeto Categoria (agregacion - una categoria es parte de una estructura Articulo (aunque una categoria puede vivir sin un articulo).
  private TipoArticulo tipo; // enum de tipo, los tipo de articulos no cambian y buscamos que eligan esas opciones unicamente.

  public Articulo(String nombre, double precio, int codigo, Categoria categoria, TipoArticulo tipo) {
    this.nombre = nombre;
    this.precio = precio;
    this.codigo = codigo;
    this.categoria = categoria;
    this.tipo = tipo;
  }

  public String getNombre() {return nombre;}
  public double getPrecio() {return precio;}
  public int getCodigo() {return codigo;}
  public Categoria getCategoria() {return categoria;}
  public TipoArticulo getTipoArticulo() {return tipo;}

  public void setNombre(String nombre) {this.nombre = nombre;}
  public void setPrecio(double precio) {this.precio = precio;}
  public void setCodigo(int codigo) {this.codigo = codigo;}
  public void setCategoria(Categoria categoria) {this.categoria = categoria;}
  public void setTipo(TipoArticulo tipo) {this.tipo = tipo;}

  public abstract String getDetalleEspecifico(); // metodo getter para que cada clase la implemente y muestre sus atributos a corde a cada clase en especifica

  @Override
  public String toString() {
    return "Articulo {" +
            "nombre='" + nombre + '\'' +
            ", precio=" + precio +
            ", codigo=" + codigo +
            ", categoria='" + categoria.getNombre() + '\'' +
            ", tipo='" + tipo + '\'' +
            ", detalle='" + getDetalleEspecifico() + '\'' +
            '}';
  }
}
