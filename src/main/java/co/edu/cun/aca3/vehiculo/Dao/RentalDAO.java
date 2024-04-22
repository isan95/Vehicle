/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.cun.aca3.vehiculo.Dao;

import co.edu.cun.aca3.vehiculo.model.Car;
import co.edu.cun.aca3.vehiculo.model.Customer;
import co.edu.cun.aca3.vehiculo.model.Rental;
import co.edu.cun.aca3.vehiculo.util.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author isan9
 */
public class RentalDAO implements DAO{
     private PreparedStatement ps = null;
    private ResultSet rs = null;
    private List<Rental> list;

    @Override
    public List<Rental> getAll() {
        list = new ArrayList<>();
        try {

            ps = Connection.connect().prepareStatement("SELECT * FROM rental");
            rs = ps.executeQuery();
            while (rs.next()) {
                Rental rental = new Rental(rs.getLong("idrental"), rs.getInt("idcar_rental"), rs.getInt("idcustomer_rental"), (LocalDateTime)rs.getObject("start_date"), (LocalDateTime)rs.getObject("end_date"), rs.getInt("days_rented"));
                list.add(rental);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, "Error al intentar consultar todos los arquileres", ex);
        } finally {
            Connection.closeConnection();
        }
        return list;
    }

    @Override
    public <T> Object getId(T t) {
        Rental rental = (Rental) t;
        try {
            ps = Connection.connect().prepareStatement("SELECT * FROM rental WHERE idrental = ?");
            ps.setLong(1, rental.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                rental = new Rental(rs.getLong("idrental"), rs.getInt("idcar_rental"), rs.getInt("idcustomer_rental"), (LocalDateTime)rs.getObject("start_date"), (LocalDateTime)rs.getObject("end_date"), rs.getInt("days_rented"));
            }

        } catch (SQLException e) {
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, "Error al consultar el alquler por id: " + rental, e);
        } finally {
            Connection.closeConnection();
        }
        return rental;
    }

    @Override
    public <T> Object getUniqueAtrribute(T t) {
        throw new UnsupportedOperationException("No soportado aun.");
    }
    
    public List<Rental> getByCustomer(Customer customer){
        Rental rental;
        list = new ArrayList<>();
        try{
            ps = Connection.connect().prepareStatement("SELECT * FROM rental INNER JOIN customer ON rental.idcustomer_rental = customer.idcustomer WHERE dni = ?");
            ps.setString(1, customer.getDni());
            rs = ps.executeQuery();
            while(rs.next()){
                rental = new Rental (rs.getLong("idrental"), rs.getInt("idcar_rental"), rs.getInt("idcustomer_rental"), (LocalDateTime)rs.getObject("start_date"), (LocalDateTime)rs.getObject("end_date"), rs.getInt("days_rented"));
                list.add(rental);
            }
        }catch(SQLException e){
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, "Error al consultar alquiler por cliente: "+ customer, e);
        }catch(NullPointerException ne){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mensaje");
            alert.setHeaderText("No hay registro");
            alert.setContentText("Este cliente no registra alquiler");

            alert.showAndWait();
        }
        return list;
    }
    
    public List<Rental> getByCar(Car car){
        Rental rental;
        list = new ArrayList<>();
        try{
            ps = Connection.connect().prepareStatement("SELECT * FROM rental INNER JOIN car ON car.idcar = rental.idcar_rental WHERE license_plate = ?");
            ps.setString(1, car.getLicensePlate());
            rs = ps.executeQuery();
            while(rs.next()){
                rental = new Rental (rs.getLong("idrental"), rs.getInt("idcar_rental"), rs.getInt("idcustomer_rental"), (LocalDateTime)rs.getObject("start_date"), (LocalDateTime)rs.getObject("end_date"), rs.getInt("days_rented"));
                list.add(rental);
            }
        }catch(SQLException e){
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, "Error al consultar alquiler por placa: "+ car, e);
        }catch(NullPointerException ne){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mensaje");
            alert.setHeaderText("No hay registro");
            alert.setContentText("Este vehiculo no registra alquiler");

            alert.showAndWait();
        }
        return list;
    }

    @Override
    public <T> void save(T obj) {
        Rental rental = (Rental) obj;
        try {
            ps = Connection.connect().prepareStatement("INSERT INTO rental(idcar_rental, idcustomer_rental, start_date) VALUES(?,?,?)");
            ps.setLong(1, rental.getIdCar());
            ps.setInt(2, rental.getIdCustomer());
            ps.setObject(3, LocalDateTime.now());
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mesaje");
            alert.setHeaderText("Transaccion exitosa");
            alert.setContentText("Alquiler registrado con exito");

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getSQLState().startsWith("23")) {
                Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, "Este alquiler ya esta registrado", ex);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mesaje");
                alert.setHeaderText("Elemento duplicado");
                alert.setContentText("Este alquiler ya esta registrada en nuestro sistema");

                alert.showAndWait();
            }
        } finally {
            Connection.closeConnection();
        }
    }

    @Override
    public <T> void delete(T obj) {
        Rental rental = (Rental) obj;
        try {
            ps = Connection.connect().prepareStatement("DELETE FROM rental WHERE idrental =? ");
            ps.setLong(1, rental.getId());
            ps.execute();
        } catch (SQLException e) {
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, "Error al intentar eliminar un alquiler:" + rental, e);
        } finally {
            Connection.closeConnection();
        }

    }

    @Override
    public <T> Object update(T obj) {
        Rental rental = (Rental) obj;
        try {
            ps = Connection.connect().prepareStatement("UPDATE rental SET idcar_rental=?, idcustomer_rental=?, start_date=?, end_date=?, days_rented=? WHERE idrental =? ");
            ps.setInt(1, rental.getIdCar());
            ps.setInt(2, rental.getIdCustomer());
            ps.setObject(3, rental.getStartDate());
            ps.setObject(4, rental.getEndDate());
            ps.setObject(5, rental.getDaysRented());
            ps.setLong(6, rental.getId());
            ps.execute();
            ps = Connection.connect().prepareStatement("SELECT * FROM rental WHERE idrental = ?");
            ps.setLong(1, rental.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                rental = new Rental(rs.getLong("idrental"), rs.getInt("idcar_rental"), rs.getInt("idcustomer_rental"), (LocalDateTime)rs.getObject("start_date"), (LocalDateTime)rs.getObject("end_date"), rs.getInt("days_rented"));
            }

        } catch (SQLException e) {
            Logger.getLogger(RentalDAO.class.getName()).log(Level.SEVERE, "Error al intentar actualizar un alquiler:" + rental, e);
        } finally {
            Connection.closeConnection();
        }
        return rental;
    }
}
