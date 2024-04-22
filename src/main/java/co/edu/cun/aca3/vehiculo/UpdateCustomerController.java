/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.edu.cun.aca3.vehiculo;

import co.edu.cun.aca3.vehiculo.Dao.CustomerDAO;
import co.edu.cun.aca3.vehiculo.model.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isan9
 */
public class UpdateCustomerController implements Initializable {

    @FXML
    private TextField tFieldNameUpdate;
    @FXML
    private TextField tFieldLastNameUpdate;
    @FXML
    private TextField tFieldDniUpdate;
    @FXML
    private TextField tFieldContNumbUpdate;
    @FXML
    private TextField tFieldAddressUpdate;
    @FXML
    private TextField tFieldIdUpdate;
    
    private CustomerDAO customerDao = new CustomerDAO();
    
    public void addInfoFormUpdateCustomer(Customer customer){
        
        tFieldNameUpdate.setText(customer.getName());
        tFieldLastNameUpdate.setText(customer.getLastName());
        tFieldDniUpdate.setText(customer.getDni());
        tFieldContNumbUpdate.setText(customer.getContactNumber());
        tFieldAddressUpdate.setText(customer.getAddress());
        tFieldIdUpdate.setText(Long.toString(customer.getId()));
       
    }
    
    @FXML
    public void saveUpdate(ActionEvent e){
        Customer customer = new Customer();
        
        customer.setId(Long.parseLong(tFieldIdUpdate.getText()));
        customer.setDni(tFieldDniUpdate.getText());
        customer.setName(tFieldNameUpdate.getText());
        customer.setLastName(tFieldLastNameUpdate.getText());
        customer.setContactNumber(tFieldContNumbUpdate.getText());
        customer.setAddress(tFieldAddressUpdate.getText());
        
        customerDao.update(customer);
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
