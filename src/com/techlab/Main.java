package com.techlab;

import com.techlab.articulo.model.Alimenticio;
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.model.Electronico;
import com.techlab.articulo.services.ArticuloService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  private static final ArticuloService articuloServicio = new ArticuloService();
  private static final List<Categoria> listaCategorias = new ArrayList<>(); // donde estara la lista de articulos
  private static final Scanner sc = new Scanner(System.in);
  private static final String SOLO_LETRAS_Y_ESPACIOS = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$";

  private static void cargarCateoria(Categoria... categoria) {
    listaCategorias.addAll(List.of(categoria));

  }

  public static void main(String[] args) {
    Categoria electronica, perifericos, alimentos, limpieza;
    electronica = new Categoria(1, "Electrónica", "Productos tecnológicos y electrónicos");
    perifericos = new Categoria(2, "Periféricos", "Accesorios para computadora");
    alimentos = new Categoria(3, "Alimentos", "Productos alimenticios");
    limpieza = new Categoria(4, "Limpieza", "Artículos de limpieza del hogar");

    cargarCateoria(electronica, perifericos, alimentos, limpieza);

    int opcion;
    do {
      mostrarMenu();
      opcion = leerEnteroSeguro("Ingrese una opcion: ");
      switch (opcion) {
        case 1 -> ingresarArticulo();
        case 2 -> listarArticulo();
        case 3 -> consultarArticulo();
        case 4 -> modificarArticulo();
        case 5 -> eliminarArticulo();
        case 6 -> listarCategorias();
        case 0 -> System.out.println("Saliendo del sistema...");
        default -> System.out.println("Opcion Invalida.");
      }

    } while (opcion != 0);


  }

  private static void mostrarMenu() {
    System.out.println("\n======================================================");
    System.out.println(" SISTEMA DE ARTÍCULOS - CLASE 4 (HERENCIA Y TO_STRING)");
    System.out.println("======================================================");
    System.out.printf("1 - %s%n2 - %s%n3 - %s%n4 - %s%n5 - %s%n6 - %s%n0 - %s%n",
            "Ingresar artículo", "Listar artículos", "Consultar un artículo", "Modificar un artículo", "Eliminar un artículo", "Listar categorías", "Salir" );
    System.out.println("======================================================");
  }

  private static int leerEnteroSeguro(String mensaje) {
    while (true) {
      System.out.print(mensaje);
      try {
        String opcion = sc.nextLine().trim();
        return Integer.parseInt(opcion);
      } catch (NumberFormatException e ) {
        System.out.println("Error: la opción ingresada no es válida");
      }}
  }

  private static String leerStringSeguro(String mensaje) {
    while (true) {
      System.out.print(mensaje);
      String input = sc.nextLine().trim();
      if (input.isEmpty()) {
        System.out.println("No puede estar vacio!");
        continue; // para evaluar nuevamente el while
      }

      if (!input.matches(SOLO_LETRAS_Y_ESPACIOS)) {
        System.out.println("Error: no debe contener numeros, solo letras y espacios");
        continue;
      }

      if (input.length() < 4) { // no hace falta el .trim() porque ya lo ejecutamos apenas recibimos la entrada del sc.
        System.out.println("El articulo debe ser mayor a 3 letras");
        continue;
      }
      return input;
    }
  }



  private static void ingresarArticulo(){
    System.out.println("\n--- INGRESAR ARTÍCULO ---");
    System.out.println("1 - Artículo electrónico\n2 - Artículo alimenticio");
    int tipo = leerEnteroSeguro("Seleccione el tipo de artículo: ");
    int codigo = leerEnteroSeguro("Ingrese el codigo del articulo: ");
    String nombre = leerStringSeguro("Ingrese el nombre del artículo: ");
    double precio = leerEnteroSeguro("Ingrese el precio del artículo: ");

    listarCategorias();
    int codigoCategoria = leerEnteroSeguro("Ingrese el código de la categoría: ");
    Categoria categoria = buscarCategoria(codigoCategoria);

    Articulo nuevoArticulo = null;
    if (tipo == 1) {
      int garantia = leerEnteroSeguro("Ingrese la garantia en meses: ");
      nuevoArticulo = new Electronico(nombre, precio, codigo, categoria, garantia);
    } else if (tipo == 2) {
      int dias = leerEnteroSeguro("Ingrese los días para vencimiento: ");
      nuevoArticulo = new Alimenticio(nombre, precio, codigo, categoria, dias);
    }
    if (nuevoArticulo!= null) {
      articuloServicio.guardar(nuevoArticulo); // validacion necesaria: nuevoArticulo puede ser null porque si el tipo ingresado por el usuario no es 1 ni 2 (los permitidos), se guardaran tipos no validos.
      System.out.println("Artículo ingresado correctamente.\n" +"Resumen del objeto creado:");
      System.out.println(nuevoArticulo);
    }





  }
  private static void listarArticulo(){
    List<Articulo> articulos = articuloServicio.listarTodo();
    System.out.println("\n--- LISTADO DE ARTÍCULOS ---");
    if (!articulos.isEmpty()) {
      for (Articulo a : articulos) {
        System.out.println(a); // imprime cada objeto articulo (se ejecuta el toString() polimorfico)
      }
    }
    System.out.println("No hay articulos cargados.");
  }
  private static void consultarArticulo(){
    List<Articulo> articulos = articuloServicio.listarTodo();
    System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");
    if (!articulos.isEmpty()) {
      int codigo = leerEnteroSeguro("Ingrese el codigo del articulo");
      Articulo articulo = articuloServicio.buscarPorCodigo(codigo);
      System.out.println(articulo);
    } else {
      System.out.println("Error: No hay articulos cargados");
    }
  }

  private static void modificarArticulo(){}
  private static void eliminarArticulo(){}

  private static void listarCategorias() {
    System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");
    for (Categoria c : listaCategorias) {
      System.out.println(c);
    }
  }

  private static Categoria buscarCategoria(int codigo) {
    for (Categoria c : listaCategorias) {
      if (c.getCodigo() == codigo) return c;
    }
    return null;
  }
}
