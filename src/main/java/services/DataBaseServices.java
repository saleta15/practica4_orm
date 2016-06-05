package services;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dell_2 on 5/29/2016.
 */
public class DataBaseServices {

    private static DataBaseServices instancia;
    private String URL = "jdbc:h2:~/practica3";


    private  DataBaseServices(){
        registrarDriver();
    }


    public static DataBaseServices getInstancia(){
        if(instancia==null){
            instancia = new DataBaseServices();
        }
        return instancia;
    }


    private void registrarDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error");
        }
    }

    public Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, "sa", "");
        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return con;
    }

    public static void crearTablas() throws  SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS articulos ( \n" +
                "id integer auto_increment, \n" +
                "titulo varchar(100) NOT NULL, \n" +
                "cuerpo varchar(2000) NOT NULL, \n" +
                "autor varchar(100) NOT NULL, \n" +
                "fecha date NOT NULL, \n" +
                "PRIMARY KEY (id) \n" +
                ");";
        Connection con = DataBaseServices.getInstancia().getConexion();
        Statement statement = con.createStatement();
        statement.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS USUARIOS(\n" +
                "username varchar(50) NOT NULL,\n" +
                "nombre varchar(100) NOT NULL,\n" +
                "password varchar(100) NOT NULL,\n" +
                "administrator BOOLEAN NOT NULL,\n" +
                "autor BOOLEAN NOT NULL,\n" +
                "PRIMARY KEY (username )\n" +
                ")";
        statement.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS COMENTARIOS(\n" +
                "ID integer auto_increment,\n" +
                "comentario varchar(300) NOT NULL,\n" +
                "autor varchar(50) NOT NULL,\n" +
                "articulo integer not null,\n" +
                "PRIMARY KEY (id),\n" +
                "FOREIGN KEY (autor) REFERENCES USUARIOS(username),\n" +
                "FOREIGN KEY (articulo) references articulos(id)\n" +
                ")";
        statement.execute(sql);

        sql = "CREATE TABLE IF NOT EXISTS ETIQUETAS(\n" +
                "ID integer auto_increment,\n" +
                "etiqueta varchar(50) NOT NULL,\n" +
                "articulo integer NOT NULL,\n" +
                "PRIMARY KEY (id),\n" +
                "FOREIGN KEY (articulo) references articulos(id)\n" +
                ")";
        statement.execute(sql);


        statement.close();
        con.close();
    }

    public static void crearAdministrador() throws  SQLException{
        String sql = "select count(*) from usuarios";

        Connection con = DataBaseServices.getInstancia().getConexion();
        Statement statement = con.createStatement();
        ResultSet rs =statement.executeQuery(sql);
        rs.next();
        if(rs.getInt(1) == 0) {
            sql = "insert into Usuarios(username,password,nombre,administrator,autor) values(?,?,?,?,?)";
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            prepareStatement.setString(1,"admin");
            prepareStatement.setString(2,"1234");
            prepareStatement.setString(3,"administrador");
            prepareStatement.setBoolean(4,true);
            prepareStatement.setBoolean(5,true);
            prepareStatement.executeUpdate();
        }



        statement.close();
        con.close();
    }


}
