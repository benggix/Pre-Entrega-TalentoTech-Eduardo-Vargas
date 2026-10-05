package com.techlab.articulo.model;

import com.techlab.articulo.enums.TipoArticulo;

public class Electronico extends Articulo {
  private int garantiaMeses;
  public Electronico(String nombre, double precio, int codigo, Categoria categoria, TipoArticulo tipo) {
    super(nombre, precio, codigo, categoria, tipo);
  }

  public int getGarantiaMeses() {
    return garantiaMeses;
  }

  public void setGarantiaMeses(int garantiaMeses) {
    this.garantiaMeses = garantiaMeses;
  }

  @Override
  public String getDetalleEspecifico() {
    return String.format("Detalle especifico: Garantia: %d meses%n", getGarantiaMeses());
  }

  @Override
  public String toString() {
    return String.format("%s %s", super.toString(), "[subtipo electrónico]");
  }
}
