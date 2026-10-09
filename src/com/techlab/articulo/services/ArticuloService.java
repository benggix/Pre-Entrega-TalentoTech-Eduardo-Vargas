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
  public void guardar(Articulo nuevo) { // esperamos recibir un objeto Articulo

    // 1. Validamos que el codigo del articulo no esté repetido
    for (Articulo a: listaArticulos) {
      if (a.getCodigo() == nuevo.getCodigo()){
        throw new IllegalArgumentException("Error: El articulo ya existe");
      }
    }
    // 2. Validamos que el articulo nuevo a guadar no tenga la misma descripción o nombre
    for (Articulo a: listaArticulos) {
      if (a.getNombre().trim().equalsIgnoreCase(nuevo.getNombre().trim())) {
        throw new IllegalArgumentException("Error: Ya existe un articulo con este nombre");
      }
    }
    // Si paso por todas las validaciones sin lanzar una excepcion, recien se guarda el articulo.
    listaArticulos.add(nuevo);
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
