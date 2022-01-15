/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuTienda;

import Excepciones.MiExcepcion;
import java.util.InputMismatchException;
import java.util.Scanner;
import tienda.servicios.FabricanteServicio;
import tienda.servicios.ProductoServicio;

/**
 *
 * @author Notebook
 */
public class TiendaMenu {

    private Scanner sc;
    private ProductoServicio ps;
    private FabricanteServicio fs;
    private int opcion;

    public TiendaMenu() {
        // ISO-8859-1 PERMITE USAR CARACTERES ESPECIALES
        sc = new Scanner(System.in).useDelimiter("\n");
        ps = new ProductoServicio();
        fs = new FabricanteServicio();

        opcion = 0;
    }

    /*
 a) Lista el nombre de todos los productos que hay en la tabla producto.
b) Lista los nombres y los precios de todos los productos de la tabla producto.
c) Listar aquellos productos que su precio esté entre 120 y 202.
d) Buscar y listar todos los Portátiles de la tabla producto.
e) Listar el nombre y el precio del producto más barato.
f) Ingresar un producto a la base de datos.
g) Ingresar un fabricante a la base de datos
h) Editar un producto con datos a elección
     */
    public void menuPrincipal() {
        do {
            try {
                System.out.println("<<<<<<<<<<<<<<<ELIJA UNA OPCIÓN>>>>>>>>>>>>>>>");
                System.out.println("1. Lista el nombre de todos los productos que hay en la tabla producto.");
                System.out.println("2.  Lista los nombres y los precios de todos los productos de la tabla producto.");
                System.out.println("3. Listar aquellos productos que su precio esté entre 120 y 202.");
                System.out.println("4. Buscar y listar todos los Portátiles de la tabla producto.");
                System.out.println("5. Listar el nombre y el precio del producto más barato.");
                System.out.println("6. Ingresar un producto a la base de datos.");
                System.out.println("7. Ingresar un fabricante a la base de datos");
                System.out.println("8. Editar un producto con datos a elección");
                System.out.println("9. SALIR");

                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        ps.listaProdPorNombre();
                        break;
                    case 2:
                        ps.listaProdPorNombrePrecio();
                        break;
                    case 3:
                        ps.listaProdPrecioEntre();
                        break;
                    case 4:
                        ps.listaProdPortatil();
                        break;
                    case 5:
                        ps.productoBarato();
                        break;
                    case 6:
                        ingresoProd();
                        break;
                    case 7:
                        ingresoFabricante();
                        fs.mostrarListaFabricante();
                        break;
                    case 8:
                        modificarProducto();
                        break;
                    case 9:
                        System.out.println("*** SESIÓN FINALIZADA ***");
                        break;
                    default:
                        System.out.println("LA OPCIÓN INGRESADA ES INVÁLIDA");
                }
            } catch (InputMismatchException e) {
                System.out.println("NO SE ADMITEN CARACTERES\n INTENTE DE NUEVO");
                sc.next();
            } catch (MiExcepcion e) {
                System.out.println("algo "+e.getMessage());
            }
        } while (opcion != 9);
    }

    // MÉTODO LECTURA DE DATOS
    public void ingresoProd() {
        System.out.println("#########INGRESANDO NUEVO PRODUCTO#########");
        try {
            System.out.println("INGRESE NUEVO CODIGO: ");
            Integer codigo = sc.nextInt();

            System.out.println("INGRESE NUEVO NOMBRE: ");
            String nombre = sc.next();

            System.out.println("INGRESE NUEVO PRECIO: ");
            Double precio = sc.nextDouble();

            System.out.println("INGRESE NUEVO CODIGO_FABRICANTE: ");
            Integer codFabr= sc.nextInt();

            ps.nuevoProducto(codigo, nombre, precio, codFabr);
        } catch (MiExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    public void ingresoFabricante() {
                System.out.println("#########INGRESANDO NUEVO FABRICANTE#########");

        try {
            System.out.println("INGRESE NUEVO CODIGO: ");
            Integer codigo = sc.nextInt();

            System.out.println("INGRESE NUEVO NOMBRE: ");
            String nombre = sc.next();

            fs.nuevoFabric(codigo, nombre);
        } catch (MiExcepcion e) {
            System.out.println(e.getMessage());
        }
    }
    
     public void modificarProducto() {
                 System.out.println("#########MODIFICANDO PRODUCTO#########");

        try {
            System.out.println("INGRESE CODIGO DEL PRODUCTO: ");
            Integer codigo = sc.nextInt();

            System.out.println("INGRESE NUEVO NOMBRE: ");
            String nombre = sc.next();

            System.out.println("INGRESE NUEVO PRECIO: ");
            Double precio = sc.nextDouble();

            System.out.println("INGRESE NUEVO CODIGO_FABRICANTE: ");
            Integer codFabr= sc.nextInt();

           ps.modificarProducto(codigo, nombre, precio, codFabr);
        } catch (MiExcepcion e) {
            System.out.println(e.getMessage());
        }
    }
}
