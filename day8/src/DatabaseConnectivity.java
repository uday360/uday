import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


//Create a table for Movies database having columns ID, NAME, release year, country. Insert 5 movies records into movie database and show them using simple jdbc also show them using SQL. Show all database operations on this table.
//        Have another table called Actor and do the same for actors using prepared statements and data source connections.
//        Also use here both style of try catch try with resources and simple try.



public class DatabaseConnectivity {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";
    static final  String USER ="sa";
    static final  String PASS ="";



    public void printtable(){
        try {

            Statement stmt = null;
            ResultSet rs = stmt.executeQuery("SELECT * FROM Movie");
            System.out.println("ID  NAME  YEAR  COUNTRY");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String country = rs.getString("COUNTRY");
                int yr = rs.getInt("RELEASE_YEAR");
                System.out.println(id + "   " + name + "   " + yr + "  " + country);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        Connection conn=null;
        Statement stmt=null;
        try {
            Class.forName(JDBC_DRIVER);
            conn= DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connection created successfully");
            stmt = conn.createStatement();
            System.out.println("Statement created successfully");
            //work with database by using these two objects
            String  sql;
//             sql= "Drop table Movie";
//            stmt.executeUpdate(sql);

            sql = "CREATE TABLE MOVIE" +
                    "(ID INTEGER not NULL, " +
                    " NAME VARCHAR(255), " +
                    " RELEASE_YEAR INTEGER, " +
                    " COUNTRY VARCHAR(255), " +
                    " PRIMARY KEY ( ID ))";

            stmt.executeUpdate(sql);

//
            sql = "INSERT INTO MOVIE VALUES (101, 'Avtar', 2010, 'India')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO MOVIE VALUES (102, 'Space Odyssey', 1968, 'US')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO MOVIE VALUES (103, 'The Godfather', 1972, 'America')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO MOVIE VALUES (104, 'Dil bechara', 2010, 'India')";
            stmt.executeUpdate(sql);
            sql = "INSERT INTO MOVIE VALUES (105, 'Gangs of Wasseypur', 2010, 'India')";
            stmt.executeUpdate(sql);


            ResultSet rs = stmt.executeQuery("SELECT * FROM Movie");
            System.out.println("ID  NAME  YEAR  COUNTRY");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String country = rs.getString("COUNTRY");
                int yr = rs.getInt("RELEASE_YEAR");
                System.out.println(id+"   "+ name +"   "+ yr +"  "+country);
            }

            sql = "UPDATE MOVIE SET RELEASE_YEAR = 2011 where ID = 104";
            stmt.executeUpdate(sql);

            System.out.println("AFTER UPDATE");

            rs = stmt.executeQuery("SELECT * FROM Movie");
            System.out.println("ID  NAME  YEAR  COUNTRY");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String country = rs.getString("COUNTRY");
                int yr = rs.getInt("RELEASE_YEAR");
                System.out.println(id+"   "+ name +"   "+ yr +"  "+country);
            }


            sql = "DELETE FROM MOVIE where ID = 105";
            stmt.executeUpdate(sql);

            System.out.println("AFTER DELETING RECORD");

            rs = stmt.executeQuery("SELECT * FROM Movie");
            System.out.println("ID  NAME  YEAR  COUNTRY");

            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String country = rs.getString("COUNTRY");
                int yr = rs.getInt("RELEASE_YEAR");
                System.out.println(id+"   "+ name +"   "+ yr +"  "+country);
            }


            sql = "Drop table Movie";
            stmt.executeUpdate(sql);


            conn.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}