package package1;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {

    public static Connection connectDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connect;
            connect = DriverManager.getConnection("jdbc:mysql://localhost/medicine_shop", "root", "");

            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
