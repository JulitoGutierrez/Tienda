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
package tienda.persistencia;

import Excepciones.MiExcepcion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tienda.entidades.Producto;

/**
 *
 * @author Notebook
 */
public class ProductoDAO extends TiendaDAO {

    public void guardarProducto(Producto producto) throws MiExcepcion {
        try {
            if (producto == null) {
                throw new MiExcepcion("PRODUCTO INVÁLIDO");
            }

            // SENTENCIA SQL DE INSERCIÓN
            String sql = "INSERT INTO producto(codigo, nombre, precio, codigo_fabricante) "
                    + "VALUES(" + producto.getCodigo()
                    + ", '" + producto.getNombre() + "', " + producto.getPrecio() + ", " + producto.getCodigoFabricante() + ");";

            // SE MUESTRA LA CADENA RESULTANTE
            System.out.println(sql);
            System.out.println();

            insertarModificarEliminar(sql);
        } catch (MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL GUARDAR PRODUCTO");
        }
    }

    public void modificarProducto(Producto producto) throws MiExcepcion {
        try {
            if (producto == null) {
                throw new MiExcepcion("PRODUCTO INVÁLIDO");
            }
            // UPDATE producto SET codigo= 13, nombre = 'tele', precio= 45.0 , codigo_fabricante= 2 WHERE codigo = 12;
            // SENTENCIA SQL DE MODIFICACIÓN
            String sql = "UPDATE producto SET codigo= " + producto.getCodigo() + ", nombre = '" + producto.getNombre() + "', precio= " + producto.getPrecio()
                    + " , codigo_fabricante= " + producto.getCodigoFabricante() + " WHERE codigo = " + producto.getCodigo() + ";";

            // SE MUESTRA LA CADENA RESULTANTE
            System.out.println(sql);
            System.out.println();

            insertarModificarEliminar(sql);
        } catch (MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL MODIFICAR PRODUCTO");
        }
    }

    public Producto buscarProductoPorCodigo(Integer codigo) throws MiExcepcion {
        try {
            // SENTENCIA SQL DE CONSULTA
            String sql = "SELECT * FROM producto WHERE codigo = " + codigo + ";";

            consultarBase(sql);

            Producto producto = null;

            while (resultado.next()) {
                producto = new Producto();

                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));
            }

            return producto;
        } catch (SQLException | MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL OBTENER PRODUCTO");
        } finally {
            desconectarBase();
        }
    }

    public void eliminarProducto(Integer codigo) throws MiExcepcion {
        try {
            // SENTENCIA SQL DE ELIMINACIÓN
            String sql = "DELETE FROM producto WHERE codigo = " + codigo + ";";

            // SE MUESTRA LA CADENA RESULTANTE
            System.out.println(sql);
            System.out.println();

            insertarModificarEliminar(sql);
        } catch (MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL ELIMINAR PRODUCTO");
        }
    }

    public List<Producto> obtenerproductosPorNombre() throws MiExcepcion {
        try {
            // SENTENCIA SQL DE CONSULTA
            String sql = "SELECT nombre FROM producto;";

            consultarBase(sql); //aca hacemos la consulta para llenar la variable resultado, para despues setear los atributos de producto

            List<Producto> productos = new ArrayList<>();
            Producto producto = null;

            while (resultado.next()) {      //si resultado tiene valores se llenan los atributos de cada nuevo objeto producto
                producto = new Producto();
                producto.setNombre(resultado.getString(1));
                productos.add(producto);
            }
            return productos;

        } catch (SQLException | MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL OBTENER PRODUCTO");
        } finally {
            desconectarBase();
        }
    }

    public List<Producto> obtenerproductosPorNombrePrecio() throws MiExcepcion {
        try {
            // SENTENCIA SQL DE CONSULTA
            String sql = "SELECT nombre, precio FROM producto;";

            consultarBase(sql); //aca hacemos la consulta para llenar la variable resultado, para despues setear los atributos de producto

            List<Producto> productos = new ArrayList<>();
            Producto producto = null;

            while (resultado.next()) {      //si resultado tiene valores se llenan los atributos de cada nuevo objeto producto
                producto = new Producto();

//                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getDouble(2));
//                producto.setCodigoFabricante(resultado.getInt(4));

                productos.add(producto);
            }
            return productos;

        } catch (SQLException | MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL OBTENER PRODUCTO");
        } finally {
            desconectarBase();
        }
    }

//    c) Listar aquellos productos que su precio esté entre 120 y 202.
//###SELECT * FROM producto WHERE precio BETWEEN 120 AND 202;
    public List<Producto> productosPrecioEntre() throws MiExcepcion {

        try {
            String sql = "SELECT * FROM producto WHERE precio BETWEEN 120 AND 202";
            consultarBase(sql);
            Producto producto = null;
            List<Producto> productos = new ArrayList();
            while (resultado.next()) {      //si resultado tiene valores se llenan los atributos de cada nuevo objeto producto
                producto = new Producto();

                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));

                productos.add(producto);
            }
            return productos;

        } catch (SQLException | MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL OBTENER PRODUCTO");
        } finally {
            desconectarBase();
        }
    }

//    d) Buscar y listar todos los Portátiles de la tabla producto.
//###SELECT * FROM producto WHERE nombre LIKE 'Portátil%';
    public List<Producto> productosPortatil() throws MiExcepcion {

        try {
            String sql = "SELECT * FROM producto WHERE nombre LIKE 'Portátil%'";
            consultarBase(sql);
            Producto producto = null;
            List<Producto> productos = new ArrayList();
            while (resultado.next()) {      //si resultado tiene valores se llenan los atributos de cada nuevo objeto producto
                producto = new Producto();

                producto.setCodigo(resultado.getInt(1));
                producto.setNombre(resultado.getString(2));
                producto.setPrecio(resultado.getDouble(3));
                producto.setCodigoFabricante(resultado.getInt(4));

                productos.add(producto);
            }
            return productos;

        } catch (SQLException | MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL OBTENER PRODUCTO");
        } finally {
            desconectarBase();
        }
    }
    //e) Listar el nombre y el precio del producto más barato.
//###select nombre, precio from producto order by precio asc limit 1;

    public List<Producto> productosBarato() throws MiExcepcion {

        try {
            String sql = "SELECT nombre, precio FROM producto order by precio asc limit 1";
            consultarBase(sql);
            Producto producto = null;
            List<Producto> productos = new ArrayList();
            while (resultado.next()) {      //si resultado tiene valores se llenan los atributos de cada nuevo objeto producto
                producto = new Producto();

                producto.setNombre(resultado.getString(1));
                producto.setPrecio(resultado.getDouble(2));
                productos.add(producto);
            }
            return productos;

        } catch (SQLException | MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL OBTENER PRODUCTO");
        } finally {
            desconectarBase();
        }
    }

}
