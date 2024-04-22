/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.edu.cun.aca3.vehiculo;

import co.edu.cun.aca3.vehiculo.Dao.CarDAO;
import co.edu.cun.aca3.vehiculo.Dao.VehicleTypeDAO;
import co.edu.cun.aca3.vehiculo.model.Car;
import co.edu.cun.aca3.vehiculo.model.VehicleType;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isan9
 */
public class UpdateVehicleController implements Initializable {
    @FXML
    private TextField tFieldIdUpdCar;
    @FXML
    private TextField tFieldBrandUpdCar;
    @FXML
    private TextField tFieldLineUpdCar;
    @FXML
    private TextField tFieldLicenseUpdCar;
    @FXML
    private ChoiceBox cBoxTypeUpdCar;
    
    private VehicleTypeDAO vehicleTypleDao = new VehicleTypeDAO();
    private CarDAO carDao = new CarDAO();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<VehicleType> list = vehicleTypleDao.getAll();
        for( VehicleType vt : list){
            cBoxTypeUpdCar.getItems().add(vt);
        }
    }

    public void addInfoFormUpdateVehicle(Car car) {
        tFieldIdUpdCar.setText(Long.toString(car.getId()));
        tFieldBrandUpdCar.setText(car.getBrand());
        tFieldLineUpdCar.setText(car.getLine());
        tFieldLicenseUpdCar.setText(car.getLicensePlate());
        VehicleType vehicleType = (VehicleType)vehicleTypleDao.getId(new VehicleType(car.getType()));
        cBoxTypeUpdCar.setValue(vehicleType.getName());
    }
    
    @FXML
    public void saveUpdateCar(ActionEvent e){
        String nameVehicleType = (String)cBoxTypeUpdCar.getSelectionModel().getSelectedItem();
        VehicleType vt = (VehicleType)vehicleTypleDao.getUniqueAtrribute(new VehicleType(nameVehicleType));
        Car car = new Car(Long.parseLong(tFieldIdUpdCar.getText()), tFieldBrandUpdCar.getText(), tFieldLineUpdCar.getText(), tFieldLicenseUpdCar.getText(), vt.getId());
        carDao.update(car);
        
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.close();
    }
}
