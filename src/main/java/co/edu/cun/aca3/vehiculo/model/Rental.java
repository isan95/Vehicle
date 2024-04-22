/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.cun.aca3.vehiculo.model;

import java.time.LocalDateTime;

/**
 *
 * @author isan9
 */
public class Rental {
    private long id;
    private int idCar;
    private int idCustomer;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int daysRented;

    public Rental(long id, int idCar, int idCustomer, LocalDateTime startDate, LocalDateTime endDate, int daysRented) {
        this.id = id;
        this.idCar = idCar;
        this.idCustomer = idCustomer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysRented = daysRented;
    }

    public Rental(int idCar, int idCustomer, LocalDateTime startDate, LocalDateTime endDate, int daysRented) {
        this.idCar = idCar;
        this.idCustomer = idCustomer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysRented = daysRented;
    }

    public Rental() {
    }

    public long getId() {
        return id;
    }

    public int getIdCar() {
        return idCar;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }
    
    
}
