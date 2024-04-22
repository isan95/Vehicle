/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.cun.aca3.vehiculo.model;

/**
 *
 * @author isan9
 */
public class Car implements Vehicle{
    private long id;
    private String brand;
    private String line;
    private String licensePlate;
    private boolean available;
    private int type;

    public Car(long id, String brand, String line, String licensePlate, boolean available, int type) {
        this.id = id;
        this.brand = brand;
        this.line = line;
        this.licensePlate = licensePlate;
        this.available = available;
        this.type = type;
    }
    
    public Car(long id, String brand, String line, String licensePlate, int type){
        this.id = id;
        this.brand = brand;
        this.line = line;
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public Car(String brand, String line, String licensePlate, boolean available) {
        this.brand = brand;
        this.line = line;
        this.licensePlate = licensePlate;
        this.available = available;
        this.type = 1;
    }
    
    public Car(String brand, String line, String licensePlate, boolean available, int type) {
        this.brand = brand;
        this.line = line;
        this.licensePlate = licensePlate;
        this.available = available;
        this.type = type;
    }

    public Car(long id) {
        this.id = id;
        this.type = 1;
    }
    
    public Car() {
        this.type = 1;
    }
    
    public long getId(){
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getLine() {
        return line;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getType() {
        return type;
    }
    
    
    
    public void setId(long id){
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    

    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", Marca=" + brand + ", Linea=" + line + ", Placa=" + licensePlate + ", Disponible=" + available + " Tipo="+Car.class.getName()+ '}';
    }
}
