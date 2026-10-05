package com.techlab.articulo.services;

import com.techlab.articulo.interfaces.CrudRepository; // interface del CRUD
import com.techlab.articulo.model.Alimenticio;
import com.techlab.articulo.model.Articulo; // modelo Articulo
import com.techlab.articulo.model.Electronico;

import java.util.ArrayList;
import java.util.List;

public class ArticuloService implements CrudRepository<Articulo> {
  private final List<Articulo> listaArticulos = new ArrayList<>(); // la referencia a la lista sera constante para evitar que se destruya la misma o sea reasignada por una nueva coleccion.

  @Override
  public void guardar(Articulo elemento) { // esperamos recibir un objeto Articulo
    listaArticulos.add(elemento);
  }

  @Override
  public List<Articulo> listarTodo() {
    return listaArticulos;
  }

  @Override
  public Articulo buscarPorCodigo(int codigo) {
    for (Articulo a : listaArticulos) {
      if (a.getCodigo() == codigo) return a; // buscamos por codigo a un elemento Articulo y lo devolvemos. Si no existe devolvemos null.
    }
    return null;
  }

  @Override
  public boolean modificar(int codigo, Articulo elementoModificado) { // como elementoNuevo es un Articulo, sabemos que es un objeto que tendra sus propi
    Articulo existente = buscarPorCodigo(codigo);
    if (existente!= null) {
      existente.setNombre(elementoModificado.getNombre());
      existente.setPrecio(elementoModificado.getPrecio());
      existente.setCodigo(elementoModificado.getCodigo());
      existente.setCategoria(elementoModificado.getCategoria());

      if (existente instanceof Electronico && elementoModificado instanceof  Electronico) {
        Electronico existenteElectronico = (Electronico) existente;
        existenteElectronico.setGarantiaMeses(((Electronico) elementoModificado).getGarantiaMeses());
      } else if (existente instanceof Alimenticio && elementoModificado instanceof Alimenticio) {
        Alimenticio existenteAlimenticio = (Alimenticio) existente;
        existenteAlimenticio.setDiasVencimiento(((Alimenticio) elementoModificado).getDiasVencimiento());
      }
      return true;
    }
    return false;
  }

  @Override
  public boolean eliminar(int codigo) {
    Articulo existente = buscarPorCodigo(codigo);
    if (existente!= null) {
      listaArticulos.removeIf((a)-> a.getCodigo()== codigo);
      return true;
    }
    return false;
  }
}
