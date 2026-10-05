package com.techlab.articulo.interfaces;

import java.util.List;

// interface CrudRepository para que cualquier clase que la implemente deba definir las 4 operaciones de almacenamiento de datos (Guardar, Listar, Buscar, Modificar, Eliminar).
public interface CrudRepository <T>{
  void guardar(T elemento); // metodo guardar que recibe cualquier cosa tipo de dato (parametro generico), la implementacion de como se guarda estara en services (package).
  List<T> listarTodo(); // metodo para listar tod0 y devolver una lista generica (puede ser de articulo o de cualquier cosa).
  T buscarPorCodigo(int codigo); // metodo para buscar algo generico por un codigo int.
  boolean modificar(int codigo, T elementoModificado); // Metodo el cual espera el codigo int (para buscar cual reemplazar) y elementoModificado es un objeto generic que contiene todos los datos nuevos y que se van a insertar.
  boolean eliminar(int codigo); // para eliminar algo, simplemente con saber el codigo basta.
}
