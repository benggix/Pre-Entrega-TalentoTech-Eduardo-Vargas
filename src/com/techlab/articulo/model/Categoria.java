package com.techlab.articulo.model;

// Categoria va a ser una clase concreta
public class Categoria {
  private final int codigo;
  private final String nombre;
  private final String descripcion;

  public Categoria(int codigo, String nombre, String descripcion) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.descripcion = descripcion;
  }

  public int getCodigo() {
    return codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  @Override
  public String toString() {
    return String.format("codigo=%d, nombre=%s, descripcion=%s",
            getCodigo(), getNombre(), getDescripcion());
  }
}
