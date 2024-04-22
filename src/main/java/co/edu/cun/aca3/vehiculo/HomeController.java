/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.edu.cun.aca3.vehiculo;

import co.edu.cun.aca3.vehiculo.Dao.CarDAO;
import co.edu.cun.aca3.vehiculo.Dao.CustomerDAO;
import co.edu.cun.aca3.vehiculo.Dao.RentalDAO;
import co.edu.cun.aca3.vehiculo.Dao.VehicleTypeDAO;
import co.edu.cun.aca3.vehiculo.model.Car;
import co.edu.cun.aca3.vehiculo.model.Customer;
import co.edu.cun.aca3.vehiculo.model.Rental;
import co.edu.cun.aca3.vehiculo.model.VehicleType;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isan9
 */
public class HomeController implements Initializable {

    @FXML
    private TextField tFieldDni;
    @FXML
    private TextField tFieldName;
    @FXML
    private TextField tFieldLastName;
    @FXML
    private TextField tFieldContactNumber;
    @FXML
    private TextField tFieldAddress;
    @FXML
    private TableView<Customer> tblViewCustomer;
    @FXML
    private TableColumn tColumnId;
    @FXML
    private TableColumn tColumnDni;
    @FXML
    private TableColumn tColumnName;
    @FXML
    private TableColumn tColumnLastName;
    @FXML
    private TableColumn tColumnContNumb;
    @FXML
    private TableColumn tColumnAddr;
    @FXML
    private TextField tFieldSearchCustomer;
    @FXML
    private ChoiceBox cBoxTypeVehicle;
    @FXML
    private TextField tFieldBrand;
    @FXML
    private TextField tFieldLine;
    @FXML
    private TextField tFieldSearchCar;
    @FXML
    private TextField tFieldLicensePlate;
    @FXML
    private TableView<Car> tblViewCar;
    @FXML
    private TableColumn tColumnIdCar;
    @FXML
    private TableColumn tColumnBrandCar;
    @FXML
    private TableColumn tColumnLicensePlateCar;
    @FXML
    private TableColumn tColumnAvailableCar;
    @FXML
    private TableColumn tColumnTypeCar;
    @FXML
    private TableColumn tColumnLineCar;
    @FXML
    private TextField tFieldDniCustomerRent;
    @FXML
    private ChoiceBox cBoxAvailableCar;
    @FXML
    private ChoiceBox cBoxSearchRental;
    @FXML
    private TextField tFieldSearchRental;
    @FXML
    private TableView<Rental> tblViewRental;
    @FXML
    private TableColumn tColumnIdRental;
    @FXML
    private TableColumn tColumnLicenseRental;
    @FXML
    private TableColumn tcolumnDniRental;
    @FXML
    private TableColumn tColumnStartDate;
    @FXML
    private TableColumn tColumnEndDate;
    @FXML
    private TableColumn tColumnDaysRented;

    private ObservableList<Customer> listObservableCustomer;
    private ObservableList<Car> listObservableCar;
    private ObservableList<Rental> listObservableRental;
    private CustomerDAO customerDao = new CustomerDAO();
    private CarDAO carDao = new CarDAO();
    private VehicleTypeDAO vehicleTypeDao = new VehicleTypeDAO();
    private RentalDAO rentalDao = new RentalDAO();

    @FXML
    public void saveCustomer() {
        Customer customer = new Customer(tFieldDni.getText(), tFieldName.getText(), tFieldLastName.getText(), tFieldContactNumber.getText(), tFieldAddress.getText());
        customerDao.save(customer);
    }

    @FXML
    public void searchCustomer() {
        listObservableCustomer.clear();
        if (tFieldSearchCustomer.getText() == null | tFieldSearchCustomer.getText().equals("")) {
            listObservableCustomer.addAll(customerDao.getAll());
        } else {
            Customer customer = new Customer();
            customer.setDni(tFieldSearchCustomer.getText());
            listObservableCustomer.add((Customer) customerDao.getUniqueAtrribute(customer));
        }
    }

    @FXML
    public void deleteCustomer() {
        Customer customer = tblViewCustomer.getSelectionModel().getSelectedItem();
        if (customer != null) {
            customerDao.delete(customer);
            listObservableCustomer.remove(customer);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mesaje");
            alert.setHeaderText("No hay objecto seleccionado");
            alert.setContentText("Por favor seleccione un cliente de la tabla");

            alert.showAndWait();
        }
    }

    @FXML
    public void showFormUpdateCust() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/updateCustomer.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("No se encontró updateCustomer.fxml");
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.setTitle("Actualizar Cliente");
        UpdateCustomerController updateCustomerController = loader.getController();
        updateCustomerController.addInfoFormUpdateCustomer(tblViewCustomer.getSelectionModel().getSelectedItem());
        stage.showAndWait();
    }

    @FXML
    public void saveVehicle() {
        String vtName = (String) cBoxTypeVehicle.getValue();
        VehicleType vehicleType = new VehicleType();
        vehicleType.setName(vtName);
        VehicleType typeVehicle = (VehicleType) vehicleTypeDao.getUniqueAtrribute(vehicleType);
        int vt = typeVehicle.getId();
        Car car = new Car(tFieldBrand.getText(), tFieldLine.getText(), tFieldLicensePlate.getText(), true, vt);
        carDao.save(car);
    }

    @FXML
    public void searchCar() {
        listObservableCar.clear();
        if (tFieldSearchCar.getText() == null | tFieldSearchCar.getText().equals("")) {
            listObservableCar.addAll(carDao.getAll());
        } else {
            Car car = new Car();
            car.setLicensePlate(tFieldSearchCar.getText());
            listObservableCar.add((Car) carDao.getUniqueAtrribute(car));
        }

    }

    @FXML
    public void deleteCar() {
        Car car = tblViewCar.getSelectionModel().getSelectedItem();
        if (car != null) {
            carDao.delete(car);
            listObservableCar.remove(car);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mesaje");
            alert.setHeaderText("No hay objecto seleccionado");
            alert.setContentText("Por favor seleccione un vehiculo de la tabla");

            alert.showAndWait();
        }
    }

    @FXML
    public void showUpdateCar() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/updateVehicle.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            System.out.println("No se encontró updateVehicle.fxml");
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.setTitle("Actualizar Vehiculo");
        UpdateVehicleController updateVehicleController = loader.getController();
        updateVehicleController.addInfoFormUpdateVehicle(tblViewCar.getSelectionModel().getSelectedItem());
        stage.showAndWait();
    }

    @FXML
    public void rent() {
        Customer customer = new Customer();
        customer.setDni(tFieldDniCustomerRent.getText());
        if (customerDao.existCustomer(customer)) {
            Rental rental = new Rental();
            Car c = new Car();
            c.setLicensePlate((String) cBoxAvailableCar.getSelectionModel().getSelectedItem());
            Car car = (Car) carDao.getUniqueAtrribute(c);
            Customer customerP = (Customer) customerDao.getUniqueAtrribute(customer);
            rental.setIdCar((int) car.getId());
            rental.setIdCustomer((int) customerP.getId());
            
            System.out.println(customerP);
            System.out.println(car);
            rentalDao.save(rental);
            
            car.setAvailable(false);
            carDao.update(car);
            cBoxAvailableCar.getSelectionModel().clearSelection();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Mesaje");
            alert.setHeaderText("Cliente no existe");
            alert.setContentText("Por favor intenta ingresar un cliente registrado");

            alert.showAndWait();
        }
    }
    
    @FXML
    public void searchRental(){
        List<Rental> list = new ArrayList<>();
        listObservableRental.clear();
        
        if(tFieldSearchRental.getText() == null | tFieldSearchRental.getText().equals("") | cBoxSearchRental.getSelectionModel().isEmpty() | cBoxSearchRental.getSelectionModel().getSelectedItem().equals("")){
            list = rentalDao.getAll();
            listObservableRental.addAll(list);
        }
        
        else if(cBoxSearchRental.getSelectionModel().getSelectedItem().equals("Placa")){
            Car car = new Car();
            car.setLicensePlate(tFieldSearchRental.getText());
            list = rentalDao.getByCar(car);
            listObservableRental.addAll(list);            
        }
        else if(cBoxSearchRental.getSelectionModel().getSelectedItem().equals("Cedula")){
            Customer customer = new Customer();
            customer.setDni(tFieldSearchRental.getText());
            list = rentalDao.getByCustomer(customer);
            listObservableRental.addAll(list);            
        }
    }
    
    @FXML
    public void endingRental(){
        Rental rental = tblViewRental.getSelectionModel().getSelectedItem();
        rental.setEndDate(LocalDateTime.now());
        rental.setDaysRented((int)DAYS.between(rental.getStartDate(), rental.getEndDate()));
        rentalDao.update(rental);
        
        Car car = new Car();
        car.setId(rental.getIdCar());
        car = (Car)carDao.getId(car);
        car.setAvailable(true);
        carDao.update(car);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listObservableCustomer = FXCollections.observableArrayList();
        tblViewCustomer.setItems(listObservableCustomer);

        tColumnId.setCellValueFactory(new PropertyValueFactory("id"));
        tColumnDni.setCellValueFactory(new PropertyValueFactory("dni"));
        tColumnName.setCellValueFactory(new PropertyValueFactory("name"));
        tColumnLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
        tColumnContNumb.setCellValueFactory(new PropertyValueFactory("contactNumber"));
        tColumnAddr.setCellValueFactory(new PropertyValueFactory("address"));
        tblViewCustomer.getColumns().setAll(tColumnId, tColumnDni, tColumnName, tColumnLastName, tColumnContNumb, tColumnAddr);

        listObservableCar = FXCollections.observableArrayList();
        tblViewCar.setItems(listObservableCar);

        tColumnIdCar.setCellValueFactory(new PropertyValueFactory("id"));
        tColumnBrandCar.setCellValueFactory(new PropertyValueFactory("brand"));
        tColumnLicensePlateCar.setCellValueFactory(new PropertyValueFactory("licensePlate"));
        tColumnLineCar.setCellValueFactory(new PropertyValueFactory("line"));
        tColumnAvailableCar.setCellValueFactory(new PropertyValueFactory("available"));
        tColumnTypeCar.setCellValueFactory(new PropertyValueFactory("type"));
        
        listObservableRental = FXCollections.observableArrayList();
        tblViewRental.setItems(listObservableRental);
        
        tColumnIdRental.setCellValueFactory(new PropertyValueFactory("id"));
        tColumnLicenseRental.setCellValueFactory(new PropertyValueFactory("idCar"));
        tcolumnDniRental.setCellValueFactory(new PropertyValueFactory("idCustomer"));
        tColumnStartDate.setCellValueFactory(new PropertyValueFactory("startDate"));
        tColumnEndDate.setCellValueFactory(new PropertyValueFactory("endDate"));
        tColumnDaysRented.setCellValueFactory(new PropertyValueFactory("daysRented"));

        List<VehicleType> vehicleTypeList = vehicleTypeDao.getAll();
        for (VehicleType vt : vehicleTypeList) {
            cBoxTypeVehicle.getItems().addAll(vt.getName());
        }
        cBoxSearchRental.getItems().add("Placa");
        cBoxSearchRental.getItems().add("Cedula");
        
        List<Car> availablesCar = carDao.getAll();
        for(Car c : availablesCar){
            if(c.isAvailable()){
                cBoxAvailableCar.getItems().add(c.getLicensePlate());
            }
        }

    }
}
