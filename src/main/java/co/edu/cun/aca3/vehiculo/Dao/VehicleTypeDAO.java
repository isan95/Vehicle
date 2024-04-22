/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.cun.aca3.vehiculo.Dao;

import co.edu.cun.aca3.vehiculo.model.VehicleType;
import co.edu.cun.aca3.vehiculo.util.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isan9
 */
public class VehicleTypeDAO implements DAO{
    private ResultSet rs;
    private PreparedStatement ps;
    private VehicleType vehicleType;

    @Override
    public  List<VehicleType> getAll() {
        List<VehicleType> list = new ArrayList<>();
        
        try{
            ps = Connection.connect().prepareStatement("SELECT * FROM vehicle_type");
            rs = ps.executeQuery();
            vehicleType = new VehicleType();
            while(rs.next()){
                vehicleType = new VehicleType(rs.getInt("idvehicle_type"), rs.getString("name"));
                list.add(vehicleType);
            }
        }catch(SQLException e){
            Logger.getLogger(VehicleTypeDAO.class.getName()).log(Level.SEVERE, "Error al consultar tipo de vehiculo", e);
        }finally{
            Connection.closeConnection();
        }
        return list;        
    }

    @Override
    public <T> VehicleType getId(T t) {
        vehicleType = (VehicleType) t;
        try{
            ps = Connection.connect().prepareStatement("SELECT *  FROM vehicle_type WHERE idvehicle_type = ?");
            ps.setInt(1, vehicleType.getId());
            rs = ps.executeQuery();
            while(rs.next()){
                vehicleType = new VehicleType(rs.getInt("idvehicle_type"), rs.getString("name"));
            }
        }catch(SQLException e){
            Logger.getLogger(VehicleTypeDAO.class.getName()).log(Level.SEVERE, "Error al consultar tipo de vehiculo por nombre: "+vehicleType, e);
        }
        
        return vehicleType;
    }

    @Override
    public <T> VehicleType getUniqueAtrribute(T t) {
        vehicleType = (VehicleType) t;
        try{
            ps = Connection.connect().prepareStatement("SELECT *  FROM vehicle_type WHERE name = ?");
            ps.setString(1, vehicleType.getName());
            rs = ps.executeQuery();
            while(rs.next()){
                vehicleType = new VehicleType(rs.getInt("idvehicle_type"), rs.getString("name"));
            }
        }catch(SQLException e){
            Logger.getLogger(VehicleTypeDAO.class.getName()).log(Level.SEVERE, "Error al consultar tipo de vehiculo por nombre: "+vehicleType, e);
        }
        
        return vehicleType;
    }

    @Override
    public <T> void save(T obj) {
        throw new UnsupportedOperationException("No soportado aun"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> void delete(T obj) {
        throw new UnsupportedOperationException("No soportado aun"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> Object update(T obj) {
        throw new UnsupportedOperationException("No soportado aun"); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
