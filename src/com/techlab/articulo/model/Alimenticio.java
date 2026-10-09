package com.techlab.articulo.model;

import com.techlab.articulo.enums.TipoArticulo;

public class Alimenticio extends Articulo {
  private int diasVencimiento;

  public Alimenticio(String nombre, double precio, int codigo, Categoria categoria, int diasVencimiento) {
    super(nombre, precio, codigo, categoria, TipoArticulo.ALIMENTICIO);
    this.diasVencimiento = diasVencimiento;
  }

  public int getDiasVencimiento() {
    return diasVencimiento;
  }

  public void setDiasVencimiento(int diasVencimiento) {
    this.diasVencimiento = diasVencimiento;
  }

  @Override
  public String getDetalleEspecifico() {
    return String.format("Dias para vencimiento: %d dias%n", getDiasVencimiento() );
  }

  @Override
  public String toString() {
    return String.format("%s %s", super.toString(), "[subtipo alimenticio]");
  }
}
