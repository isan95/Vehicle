/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.cun.aca3.vehiculo.Dao;

import co.edu.cun.aca3.vehiculo.model.Customer;
import co.edu.cun.aca3.vehiculo.util.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author isan9
 */
public class CustomerDAO implements DAO {

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private List<Customer> list;

    @Override
    public List<Customer> getAll() {
        list = new ArrayList<>();
        try {

            ps = Connection.connect().prepareStatement("SELECT * FROM customer");
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(rs.getLong("idcustomer"), rs.getString("dni"), rs.getString("name"), rs.getString("last_name"), rs.getString("contact_number"), rs.getString("address"));
                list.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Error al intentar consultar todos los clientes", ex);
        } finally {
            Connection.closeConnection();
        }
        for(Customer c : list){
            System.out.println(c);
        }
        return list;
    }

    @Override
    public <T> Object getId(T t) {
        Customer customer = (Customer) t;
        try {
            ps = Connection.connect().prepareStatement("SELECT * FROM customer WHERE idcustomer = ?");
            ps.setLong(1, customer.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer(rs.getLong("idcustomer"), rs.getString("dni"), rs.getString("name"), rs.getString("last_name"), rs.getString("contact_number"), rs.getString("address"));
            }

        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Error al consultar el cliente por id: " + customer, e);
        } finally {
            Connection.closeConnection();
        }
        return customer;
    }

    @Override
    public <T> Object getUniqueAtrribute(T t) {
        Customer customer = (Customer) t;
        try {
            ps = Connection.connect().prepareStatement("SELECT * FROM customer WHERE dni = ?");
            ps.setString(1, customer.getDni());
            rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer(rs.getLong("idcustomer"), rs.getString("dni"), rs.getString("name"), rs.getString("last_name"), rs.getString("contact_number"), rs.getString("address"));
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Error al consultar cliente por dni:" + customer, e);
        } finally {
            Connection.closeConnection();
        }
        return customer;
    }

    @Override
    public <T> void save(T obj) {
        Customer customer = (Customer) obj;
        try {
            ps = Connection.connect().prepareStatement("INSERT INTO customer(idcustomer, dni, name, last_name, contact_number, address) VALUES(?,?,?,?,?,?)");
            ps.setLong(1, customer.getId());
            ps.setString(2, customer.getDni());
            ps.setString(3, customer.getName());
            ps.setString(4, customer.getLastName());
            ps.setString(5, customer.getContactNumber());
            ps.setString(6, customer.getAddress());
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mesaje");
            alert.setHeaderText("Transaccion exitosa");
            alert.setContentText("Cliente registrado con exito");

            alert.showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getSQLState().startsWith("23")) {
                Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Este DNI pertence a un cliente registrado");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Mesaje");
                alert.setHeaderText("Elemento duplicado");
                alert.setContentText("Este DNI ya esta registrada en nuestro sistema");

                alert.showAndWait();
            }
        } finally {
            Connection.closeConnection();
        }
    }

    @Override
    public <T> void delete(T obj) {
        Customer customer = (Customer) obj;
        try {
            ps = Connection.connect().prepareStatement("DELETE FROM customer WHERE idcustomer =? ");
            ps.setLong(1, customer.getId());
            ps.execute();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mesaje");
            alert.setHeaderText("Transaccion exitosa");
            alert.setContentText("Cliente eliminado con exito");

            alert.showAndWait();
        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Error al intentar eliminar un cliente:" + customer, e);
        } finally {
            Connection.closeConnection();
        }

    }

    @Override
    public <T> Object update(T obj) {
        Customer customer = (Customer) obj;
        try {
            ps = Connection.connect().prepareStatement("UPDATE customer SET dni=?, name=?, last_name=?, contact_number=?, address=? WHERE idcustomer =? ");
            ps.setString(1, customer.getDni());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getLastName());
            ps.setString(4, customer.getContactNumber());
            ps.setString(5, customer.getAddress());
            ps.setLong(6, customer.getId());
            ps.execute();
            ps = Connection.connect().prepareStatement("SELECT * FROM customer WHERE idcustomer = ?");
            ps.setLong(1, customer.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                customer = new Customer(rs.getLong("idcustomer"), rs.getString("dni"), rs.getString("name"), rs.getString("last_name"), rs.getString("contact_number"), rs.getString("address"));
            }

        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Error al intentar actualizar un cliente:" + customer, e);
        } finally {
            Connection.closeConnection();
        }
        return customer;
    }
    
    public boolean existCustomer(Customer customer){
        try{
            ps = Connection.connect().prepareStatement("SELECT * FROM customer WHERE dni = ?");
            ps.setString(1, customer.getDni());
            rs = ps.executeQuery();
            if(!rs.isBeforeFirst()) return false; 
        }catch(SQLException e){
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Error al consultar la existencia del cliente", e);
        }finally{
            Connection.closeConnection();
        }
        return true;
    }
}
