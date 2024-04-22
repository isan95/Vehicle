/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.cun.aca3.vehiculo.Dao;

import java.util.List;

/**
 *
 * @author isan9
 */
public interface DAO {
    public <T> List<T> getAll();
    public <T> Object getId (T t);
    public <T> Object getUniqueAtrribute(T t);
    public <T> void save(T obj);
    public <T> void delete (T obj);
    public <T> Object update (T obj);
}
