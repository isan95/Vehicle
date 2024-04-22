/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.cun.aca3.vehiculo.model;

/**
 *
 * @author isan9
 */
public class Customer {
    private long id;
    private String dni;
    private String name;
    private String lastName;
    private String contactNumber;
    private String address;

    public Customer(long id, String dni, String name, String lastName, String contactNumber, String address) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public Customer(String dni, String name, String lastName, String contactNumber, String address) {
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.address = address;
    }

    public Customer() {
    }


    public long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", dni=" + dni + ", nombres=" + name + ", apellidos=" + lastName + ", Numero de contacto=" + contactNumber + ", direccion=" + address + '}';
    }
    
    
    
}
