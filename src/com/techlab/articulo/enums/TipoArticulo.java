package com.techlab.articulo.enums;

public enum TipoArticulo {
  ELECTRONICO("Electronico"),
  ALIMENTICIO("Alimenticio");

  private final String descripcion;

  TipoArticulo(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getDescripcion() {
    return descripcion;
  }
}
