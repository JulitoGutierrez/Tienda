package tienda.servicios;

import Excepciones.MiExcepcion;
import java.util.List;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDAO;

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
public class FabricanteServicio {

    private FabricanteDAO dao;

    public FabricanteServicio() {
        this.dao = new FabricanteDAO();
    }

    public void nuevoFabric(Integer codigo, String nombre) throws MiExcepcion {

        try {
            if (dao.buscarFabricantePorCodigo(codigo) != null) {
                throw new MiExcepcion("El codigo ya existe");

            }
            if (codigo == null) {
                throw new MiExcepcion("Debe indicar el codigo");
            }

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new MiExcepcion("Debe indicar un nombre");
            }
            Fabricante fabric = new Fabricante();
            fabric.setCodigo(codigo);
            fabric.setNombre(nombre);

            dao.guardarFabricante(fabric);
        } catch (Exception e) {
            throw new MiExcepcion("Problema= " + e.getMessage());
        }
    }

    public void mostrarListaFabricante() {
        try {
            List<Fabricante> fabricantes = dao.obtenerFabricantes();
            if (fabricantes.isEmpty()) {
                throw new MiExcepcion("No hay fabricantes en la lista");
            } else {
                for (Fabricante f : fabricantes) {
                    System.out.println(f);
                }
            }

        } catch (Exception e) {
        }
    }

}
