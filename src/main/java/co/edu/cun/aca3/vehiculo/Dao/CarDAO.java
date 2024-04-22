/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.cun.aca3.vehiculo.Dao;

import co.edu.cun.aca3.vehiculo.model.Car;
import java.util.List;
import co.edu.cun.aca3.vehiculo.util.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author isan9
 */
public class CarDAO implements DAO {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private List<Car> list;

    @Override
    public List<Car> getAll() {
        list = new ArrayList<>();
        try {
            ps = Connection.connect().prepareStatement("SELECT * FROM car");
            rs = ps.executeQuery();
            while (rs.next()) {
                Car car = new Car(rs.getLong("idCar"), rs.getString("brand"), rs.getString("line"), rs.getString("license_plate"), rs.getBoolean("available"), rs.getInt("type"));
                list.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connection.closeConnection();
        }
        return list;
    }

    @Override
    public <T> Object getId(T t) {
        Car car = (Car) t;
        try {
            ps = Connection.connect().prepareStatement("SELECT * FROM car WHERE idcar = ?");
            ps.setLong(1, car.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                car = new Car(rs.getLong("idcar"), rs.getString("brand"), rs.getString("line"), rs.getString("license_plate"), rs.getBoolean("available"), rs.getInt("type"));
            }

        } catch (SQLException e) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, "Error al consultar carro por id", e);
        } finally {
            Connection.closeConnection();
        }
        return car;
    }

    @Override
    public <T> Car getUniqueAtrribute(T t) {
        Car car = (Car) t;
        try {
            ps = Connection.connect().prepareStatement("SELECT * FROM car WHERE license_plate = ?");
            ps.setString(1, car.getLicensePlate());
            rs = ps.executeQuery();
            while (rs.next()) {
                car = new Car(rs.getLong("idcar"), rs.getString("brand"), rs.getString("line"), rs.getString("license_plate"), rs.getBoolean("available"), rs.getInt("type"));
            }
        } catch (SQLException e) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, "Error al consultar carro por placa", e);
        } finally {
            Connection.closeConnection();
        }
        return car;
    }

    @Override
    public <T> void save(T obj) {
        Car car = (Car) obj;
        try {
            ps = Connection.connect().prepareStatement("INSERT INTO car(brand, line, license_plate, available, type) VALUES(?,?,?,?,?)");
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getLine());
            ps.setString(3, car.getLicensePlate());
            ps.setBoolean(4, car.isAvailable());
            ps.setInt(5, car.getType());
            ps.execute();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Mesaje");
            alert.setHeaderText("Transaccion exitosa");
            alert.setContentText("Vehiculo registrado con exito");

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getSQLState().startsWith("23")) {
                Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, "Esta placa pertence a un vehiculo registrado");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Mesaje");
                alert.setHeaderText("Elemento duplicado");
                alert.setContentText("Esta placa ya esta registrada en nuestro sistema");

                alert.showAndWait();
            }
        } finally {
            Connection.closeConnection();
        }
    }

    @Override
    public <T> void delete(T obj) {
        Car car = (Car) obj;
        try {
            ps = Connection.connect().prepareStatement("DELETE FROM car WHERE idcar =? ");
            ps.setLong(1, car.getId());
            ps.execute();
        } catch (SQLException e) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, "Error al intentar eliminar un vehiculo:" +car, e);
        }finally{
            Connection.closeConnection();
        }

    }

    @Override
    public <T> Object update(T obj) {
         Car car = (Car) obj;
        try {
            ps = Connection.connect().prepareStatement("UPDATE car SET brand=?, line=?, license_plate=?, available=?, type=? WHERE idcar =? ");
            ps.setString(1, car.getBrand());
            ps.setString(2, car.getLine());
            ps.setString(3, car.getLicensePlate());
            ps.setBoolean(4, car.isAvailable());
            ps.setInt(5, car.getType());
            ps.setLong(6, car.getId());
            ps.execute();
            ps = Connection.connect().prepareStatement("SELECT * FROM car WHERE idcar = ?");
            ps.setLong(1, car.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                car = new Car(rs.getLong("idcar"), rs.getString("brand"), rs.getString("line"), rs.getString("license_plate"), rs.getBoolean("available"), rs.getInt("type"));
            }
            
        } catch (SQLException e) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, "Error al intentar actualizar un vehiculo:" +car, e);
        }finally{
            Connection.closeConnection();
        }
        return car;
    }

}
