/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.cun.aca3.vehiculo.model;

/**
 *
 * @author isan9
 */
public class VehicleType {
    private int id;
    private String name;

    public VehicleType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public VehicleType(String name) {
        this.name = name;
    }
    
    public VehicleType(int id) {
        this.id = id;
    }

    public VehicleType() {
    }
    
    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
