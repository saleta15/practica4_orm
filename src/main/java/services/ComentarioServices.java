package services;

/**
 * Created by Dell_2 on 5/30/2016.
 */

import modelos.Articulo;
import modelos.Comentario;
import modelos.Usuario;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComentarioServices {

    public static Comentario getComentario(int id) {

        Comentario comentario = null;
        Connection con = null;
        try {

            String query = "select * from comentarios where id = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                comentario = new Comentario();
                comentario.setId(rs.getInt("id"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setArticulo(ArticuloServices.getArticulo(rs.getInt("id")));
                comentario.setAutor(UsuarioServices.getUsuario(rs.getString("autor")));

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

        return comentario;
    }

    public static boolean  crearComentario(Comentario comentario){
        boolean ok=false;
        Connection conn=null;
        try {

            String query = "insert into comentarios(comentario, autor, articulo) values(?,?,?)";
            conn = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, comentario.getComentario());
            prepareStatement.setString(2,comentario.getAutor().getUsername());
            prepareStatement.setInt(3, comentario.getArticulo().getId());
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

    public static boolean  borrarComentario(int id){
        boolean ok=false;
        Connection conn=null;
        try {

            String query = "delete from comentarios where id=?";
            conn = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setInt(1, id);
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


}
