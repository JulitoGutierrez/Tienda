/*
###########################################################
 SEGUIR ACAAAAAAAAAAAAA  27/9!!!!!!
###########################################################
 */
package tienda.servicios;

import Excepciones.MiExcepcion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import tienda.entidades.Producto;
import tienda.persistencia.ProductoDAO;

/*



d) Buscar y listar todos los Portátiles de la tabla producto.
###SELECT * FROM producto WHERE nombre LIKE 'Portátil%';
e) Listar el nombre y el precio del producto más barato.
###select nombre, precio from producto order by precio asc limit 1;
f) Ingresar un producto a la base de datos.
###INSERT INTO producto VALUES(1, 'Disco duro SATA3 1TB', 86.99, 5);
g) Ingresar un fabricante a la base de datos
###INSERT INTO fabricante VALUES(9, 'Xiaomi');
h) Editar un producto con datos a elección
###
 */
public class ProductoServicio {

    private ProductoDAO prdAO;

    public ProductoServicio() {
        this.prdAO = new ProductoDAO();
    }

//     a) Lista el nombre de todos los productos que hay en la tabla producto.
//         ###select nombre FROM producto;
    public void listaProdPorNombre() throws MiExcepcion {

        try {

            //Listamos los usuarios
            List<Producto> productos = prdAO.obtenerproductosPorNombre();

            //Imprimimos los usuarios
            if (productos.isEmpty()) {
                throw new MiExcepcion("No existen productos en la lista.");
            } else {
                System.out.printf("%-20s\n", "NOMBRE"); // FORMATO DE IMPRESIÓN

                for (Producto p : productos) {
                    System.out.println(p.getNombre());
                }
                System.out.println();
            }
        } catch (Exception e) {
            throw new MiExcepcion("Problema= " + e.getMessage());
        }
    }

    //b) Lista los nombres y los precios de todos los productos de la tabla producto.
//###select nombre, precio FROM producto;
    public void listaProdPorNombrePrecio() throws MiExcepcion {

        try {

            //Listamos los usuarios
            Collection<Producto> productos = prdAO.obtenerproductosPorNombrePrecio();

            //Imprimimos los usuarios
            if (productos.isEmpty()) {
                throw new MiExcepcion("No existen productos en la lista.");
            } else {

                for (Producto p : productos) {
                    System.out.println(p.getNombre() + ">>>>> $" + p.getPrecio());
                }
            }
        } catch (Exception e) {
            throw new MiExcepcion("Problema= " + e.getMessage());
        }
    }

//    c) Listar aquellos productos que su precio esté entre 120 y 202.
//###SELECT * FROM producto WHERE precio BETWEEN 120 AND 202;
    public void listaProdPrecioEntre() throws MiExcepcion {

        try {

            //Listamos los usuarios
            Collection<Producto> productos = prdAO.productosPrecioEntre();

            //Imprimimos los usuarios
            if (productos.isEmpty()) {
                throw new MiExcepcion("No existen productos en la lista.");
            } else {
                //System.out.printf("%-20s%-15s%-15s\n", "CODIGO", "NOMBRE", "PRECIO", "CODIGO_FABRICANTE"); // FORMATO DE IMPRESIÓN

                for (Producto p : productos) {
                    System.out.println("Codigo:" + p.getCodigo() + "\nNombre:" + p.getNombre() + "\nPrecio:" + p.getPrecio() + "\nCod_Fabricante:" + p.getCodigoFabricante());
                    System.out.println("-----------------------");

                }
            }
        } catch (Exception e) {
            throw new MiExcepcion("Problema= " + e.getMessage());
        }
    }

//    d) Buscar y listar todos los Portátiles de la tabla producto.
//###SELECT * FROM producto WHERE nombre LIKE 'Portátil%';
    public void listaProdPortatil() throws MiExcepcion {

        try {

            //Listamos los usuarios
            Collection<Producto> productos = prdAO.productosPortatil();

            //Imprimimos los usuarios
            if (productos.isEmpty()) {
                throw new MiExcepcion("No existen productos en la lista.");
            } else {

                for (Producto p : productos) {
                    System.out.println("Codigo:" + p.getCodigo() + "\nNombre:" + p.getNombre() + "\nPrecio:" + p.getPrecio() + "\nCod_Fabricante:" + p.getCodigoFabricante());
                    System.out.println("-----------------------");

                }
            }
        } catch (Exception e) {
            throw new MiExcepcion("Problema= " + e.getMessage());
        }
    }
//e) Listar el nombre y el precio del producto más barato.
//###select nombre, precio from producto order by precio asc limit 1;

    public void productoBarato() throws MiExcepcion {
        try {
            List<Producto> productos = prdAO.productosBarato();
            if (productos.isEmpty()) {
                throw new MiExcepcion("No existe el producto");
            } else {
                for (Producto p : productos) {
                    System.out.println(p.getNombre() + ">>>>> $" + p.getPrecio());
                }
            }

        } catch (Exception e) {
            throw new MiExcepcion("Problema= " + e.getMessage());
        }

    }

//f) Ingresar un producto a la base de datos.
//###INSERT INTO producto VALUES(1, 'Disco duro SATA3 1TB', 86.99, 5);
    public void nuevoProducto(Integer codigo, String nombre, Double precio, Integer codigoFabricante) throws MiExcepcion {

        try {
            if (prdAO.buscarProductoPorCodigo(codigo) != null) { //PARA VER QUE EL CODIGO NO EXISTA
                throw new MiExcepcion("El codigo ya existe");
            }
            if (codigo == null) {
                throw new MiExcepcion("Debe indicar el codigo");
            }

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new MiExcepcion("Debe indicar un nombre");
            }
            if (precio == null) {
                throw new MiExcepcion("Debe indicar el codigo");
            }
            if (codigoFabricante == null) {
                throw new MiExcepcion("Debe indicar el codigo");
            }
            Producto prod = new Producto();
            prod.setCodigo(codigo);
            prod.setNombre(nombre);
            prod.setPrecio(precio);
            prod.setCodigoFabricante(codigoFabricante);

            prdAO.guardarProducto(prod);
        } catch (Exception e) {
            throw new MiExcepcion("Problema= " + e.getMessage());
        }
    }

    public void modificarProducto(Integer codigo, String nombre, Double precio, Integer codigoFabricante) throws MiExcepcion {
        try {
            // VALIDACIÓN
           
            if (codigo == null) {
                throw new MiExcepcion("DEBE INGRESAR UN CORREO");
            }

            if (nombre == null | nombre.trim().isEmpty()) {
                throw new MiExcepcion("DEBE INGRESAR UN NOMBRE");
            }

            if (precio == null) {
                throw new MiExcepcion("DEBE INGRESAR UN APELLIDO");
            }
            if (codigoFabricante == null) {
                throw new MiExcepcion("DEBE INGRESAR UN APELLIDO");
            }
            Producto producto = prdAO.buscarProductoPorCodigo(codigo);

            if (producto == null) {
                throw new MiExcepcion("EL CODIGO INGRESADO NO ESTÁ ASOCIADO A NINGÚN USUARIO");
            }

            producto.setCodigo(codigo);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCodigoFabricante(codigoFabricante);

            prdAO.modificarProducto(producto);
        } catch (MiExcepcion e) {
            // e.printStackTrace();
            throw e;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new MiExcepcion("ERROR DE SISTEMA");
        }
    }
}
