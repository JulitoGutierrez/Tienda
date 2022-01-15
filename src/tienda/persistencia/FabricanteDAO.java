/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import Excepciones.MiExcepcion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tienda.entidades.Fabricante;

/**
 *
 * @author Notebook
 */
public class FabricanteDAO extends TiendaDAO {

    public void guardarFabricante(Fabricante fabricante) throws MiExcepcion {
        try {
            if (fabricante == null) {
                throw new MiExcepcion("FABRICANTE INVÁLIDO");
            }

            // SENTENCIA SQL DE INSERCIÓN
            String sql = "INSERT INTO fabricante(codigo, nombre) "
                    + "VALUES(" + fabricante.getCodigo()
                    + ", '" + fabricante.getNombre() + "');";

            // SE MUESTRA LA CADENA RESULTANTE
            System.out.println(sql);
            System.out.println();

            insertarModificarEliminar(sql);
        } catch (MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL GUARDAR FABRICANTE");
        }
    }

    public void modificarFabricante(Fabricante fabricante) throws MiExcepcion {
        try {
            if (fabricante == null) {
                throw new MiExcepcion("FABRICANTE INVÁLIDO");
            }

            // SENTENCIA SQL DE MODIFICACIÓN
            String sql = "UPDATE fabricante SET nombre = '" + fabricante.getNombre() + "', "
                    + "WHERE codigo = " + fabricante.getCodigo() + ";";

            // SE MUESTRA LA CADENA RESULTANTE
            System.out.println(sql);
            System.out.println();

            insertarModificarEliminar(sql);
        } catch (MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL MODIFICAR FABRICANTE");
        }
    }

    public Fabricante buscarFabricantePorCodigo(Integer codigo) throws MiExcepcion {
        try {
            // SENTENCIA SQL DE CONSULTA
            String sql = "SELECT * FROM fabricante WHERE codigo = " + codigo + ";";

            consultarBase(sql);

            Fabricante fabricante = null;

            while (resultado.next()) {
                fabricante = new Fabricante();

                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
            }

            return fabricante;
        } catch (SQLException | MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL OBTENER FABRICANTE");
        } finally {
            desconectarBase();
        }
    }

    public void eliminarFabricante(Integer codigo) throws MiExcepcion {
        try {
            // SENTENCIA SQL DE ELIMINACIÓN
            String sql = "DELETE FROM usuario WHERE correo = " + codigo + ";";

            // SE MUESTRA LA CADENA RESULTANTE
            System.out.println(sql);
            System.out.println();

            insertarModificarEliminar(sql);
        } catch (MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL ELIMINAR FABRICANTE");
        }
    }

    public List<Fabricante> obtenerFabricantes() throws MiExcepcion {
        try {
            // SENTENCIA SQL DE CONSULTA
            String sql = "SELECT * FROM fabricante;"; 

            consultarBase(sql);

            List<Fabricante> fabricantes = new ArrayList<>();
            Fabricante fabricante = null;

            while (resultado.next()) {
                fabricante = new Fabricante();

                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));

                fabricantes.add(fabricante);
            }

            return fabricantes;
        } catch (SQLException | MiExcepcion e) {
            System.out.println(e.getMessage());
            throw new MiExcepcion("ERROR AL OBTENER FABRICANTE");
        } finally {
            desconectarBase();
        }
    }

}
