package com.techlab.articulo.model;

import com.techlab.articulo.enums.TipoArticulo;

public class Electronico extends Articulo {
  private int garantiaMeses;
  public Electronico(String nombre, double precio, int codigo, Categoria categoria, int garantiaMeses) {
    super(nombre, precio, codigo, categoria, TipoArticulo.ELECTRONICO);
    this.garantiaMeses = garantiaMeses;
  }

  public int getGarantiaMeses() {
    return garantiaMeses;
  }
  public void setGarantiaMeses(int garantiaMeses) {
    this.garantiaMeses = garantiaMeses;
  }

  @Override
  public String getDetalleEspecifico() {
    return String.format("Garantia: %d meses", getGarantiaMeses());
  }

  @Override
  public String toString() {
    return String.format("%s %s", super.toString(), "[subtipo electrónico]");
  }
}
