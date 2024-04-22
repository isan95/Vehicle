/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.cun.aca3.vehiculo.util;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//</editor-fold>

/**
 *
 * @author isan9
 */
public class Connection {
    private static java.sql.Connection connection= null;
    
    public static java.sql.Connection  connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehicle", "root", "2153");
            Logger.getLogger(Connection.class.getName()).log(Level.INFO, "Conexion a base de datos exitosa");
        }catch(ClassNotFoundException | SQLException e){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, "Error en la conexion a base de datos", e);
        }        
        return connection;
    }
    
    public static void closeConnection(){
        try{
            if(connection != null){
                connection.close();
                Logger.getLogger(Connection.class.getName()).log(Level.INFO, "Conexion cerrada con exito");
            }
            else{
                Logger.getLogger(Connection.class.getName()).log(Level.WARNING, "No existe conexiona base de datos");
            }
        }catch(SQLException e){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, "Error al intentar cerrar la conexion", e);
        }
        
    }
}
