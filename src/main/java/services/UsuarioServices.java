package services;

import modelos.Articulo;
import modelos.Etiqueta;
import modelos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by saleta on 5/30/2016.
 */
public class UsuarioServices {
    public static Usuario getUsuario(String username) {

        Usuario usuario = null;
        Connection con = null;
        try {

            String query = "select * from usuarios where username = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setString(1, username);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setAdministrador(rs.getBoolean("administrator"));
                usuario.setAutor(rs.getBoolean("autor"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUsername(rs.getString("username"));

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

        return usuario;
    }

    public static ArrayList<Usuario> getTodosUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection con = null;
        try {

            String query = "select * from usuarios";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){

                Usuario usuario = new Usuario();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setAdministrador(rs.getBoolean("administrator"));
                usuario.setAutor(rs.getBoolean("autor"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUsername(rs.getString("username"));
                usuarios.add(usuario);

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

        return usuarios;
    }

    public static boolean crearUsuario(Usuario usuario){
        boolean ok=false;
        Connection conn=null;
        try {

            String query = "insert into usuarios(username , nombre , password ,administrator ,autor ) values(?,?,?,?,?)";
            conn = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, usuario.getUsername());
            prepareStatement.setString(2, usuario.getNombre());
            prepareStatement.setString(3, usuario.getPassword());
            prepareStatement.setBoolean(4,usuario.getAdministrador());
            prepareStatement.setBoolean(5,usuario.getAutor());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("error");
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    public static boolean updateUsuario(Usuario usuario){
        boolean ok=false;
        Connection conn=null;
        try {

            String query = "update  usuarios set nombre=? , password=? ,administrator=? ,autor=? where username=?";
            conn = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.

            prepareStatement.setString(1, usuario.getNombre());
            prepareStatement.setString(2, usuario.getPassword());
            prepareStatement.setBoolean(3,usuario.getAdministrador());
            prepareStatement.setBoolean(4,usuario.getAutor());
            prepareStatement.setString(5, usuario.getUsername());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("error");
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ok;
    }

    public static boolean deleteUsuario(Usuario usuario){
        boolean ok=false;
        Connection conn=null;
        try {

            String query = "delete from usuarios where username=?";
            conn = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = conn.prepareStatement(query);

            prepareStatement.setString(1, usuario.getUsername());

            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("error");
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
