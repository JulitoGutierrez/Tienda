/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda.persistencia;

import Excepciones.MiExcepcion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Notebook
 */
public class TiendaDAO {
    // ADMINISTRA NUESTRA CONEXIÓN
    protected Connection conexion = null;
    // INSTRUCCIÓN DE CONSULTA
    protected Statement sentencia = null;
    // MANIPULA LOS RESULTADOS
    protected ResultSet resultado = null;

    private final String USUARIO = "root";
    private final String CLAVE = "root";
    // USTEDES DEBEN COLOCAR ESTA CADENA EN EL CONTROLADOR: "com.mysql.jdbc.Driver"
    private final String CONTROLADOR = "com.mysql.jdbc.Driver";
    // UBICACIÓN DE LA BASE DE DATOS
    
    private final String DATABASE = "tienda";

    protected void conectarBase() throws MiExcepcion {
        try {
            // CARGA EL CONTROLADOR
            Class.forName(CONTROLADOR);
           String URL = "jdbc:mysql://localhost:3306/" + DATABASE + "?useSSL=false";
            // ESTABLECE LA CONEXIÓN
            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
        } catch (ClassNotFoundException | SQLException e) {
            // e.printStackTrace();
            throw new MiExcepcion("ERROR AL CONECTARSE");
        }
    }

    protected void desconectarBase() throws MiExcepcion {
        try {
            if (resultado != null) {
                resultado.close();
            }
            if (sentencia != null) {
                sentencia.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new MiExcepcion("ERROR AL DESCONECTARSE");
        }
    }

    protected void insertarModificarEliminar(String sql) throws MiExcepcion {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql);
        } catch (SQLException e) {
//            try {
//                conexion.rollback();
//            } catch (SQLException ex) {
//                // ex.printStackTrace();
//                throw new MiExcepcion("ERROR AL REALIZAR ROLLBACK");
//            }
             e.printStackTrace();
            throw new MiExcepcion("ERROR AL EJECUTAR SENTENCIA");
        } finally {
            desconectarBase();
        }
    }

    protected void consultarBase(String sql) throws MiExcepcion {
        try {
            conectarBase();
            sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new MiExcepcion("ERROR AL CONSULTAR");
        }
    }
}
