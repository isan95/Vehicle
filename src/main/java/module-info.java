module co.edu.cun.aca3.vehiculo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens co.edu.cun.aca3.vehiculo to javafx.fxml;
    opens co.edu.cun.aca3.vehiculo.model to javafx.fxml;
    opens co.edu.cun.aca3.vehiculo.Dao to javafx.fxml;
    exports co.edu.cun.aca3.vehiculo;
    exports co.edu.cun.aca3.vehiculo.model;
    exports co.edu.cun.aca3.vehiculo.Dao;
    
}
