package services;

/**
 * Created by Dell_2 on 5/30/2016.
 */

import modelos.Articulo;
import modelos.Comentario;
import modelos.Etiqueta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class EtiquetaServices {

    public static Etiqueta getEtiqueta(int id) {

        Etiqueta etiqueta = null;
        Connection con = null;
        try {

            String query = "select * from etiquetas where id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                etiqueta = new Etiqueta();
                etiqueta.setId(rs.getInt("id"));
                etiqueta.setEtiqueta(rs.getString("etiqueta"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return etiqueta;
    }


    public static boolean crearEtiqueta(Etiqueta etiqueta){
        boolean ok=false;
        Connection conn=null;
        try {

            String query = "insert into etiquetas(id, etiqueta, articulo) values(?,?,?)";
            conn = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, etiqueta.getId());
            prepareStatement.setString(2, etiqueta.getEtiqueta());
            //OJO check que la tabla tiene tres campos y la clase tiene dos el tercer valor que le mando en el insert es
            //el mismo ID check si es asi
            prepareStatement.setInt(3, etiqueta.getId());

            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    public static void crearEtiquetas(String etiquetas, Articulo articulo){
        Connection conn=null;
        String[] listaEtiquetas = etiquetas.split(",");
        for(String e : listaEtiquetas){
            try {

                String query = "insert into etiquetas(etiqueta, articulo) values(?,?)";
                conn = DataBaseServices.getInstancia().getConexion();
                PreparedStatement prepareStatement = conn.prepareStatement(query);
                prepareStatement.setString(1, e);
                prepareStatement.setInt(2, articulo.getId());

                prepareStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public static void editarEtiquetas(String etiquetas, Articulo articulo){
        Connection conn=null;
            try {

                String query = "delete from  etiquetas where articulo=?";
                conn = DataBaseServices.getInstancia().getConexion();
                PreparedStatement prepareStatement = conn.prepareStatement(query);
                prepareStatement.setInt(1, articulo.getId());
                prepareStatement.executeUpdate();
                crearEtiquetas(etiquetas, articulo);

            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            } finally{
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

}
