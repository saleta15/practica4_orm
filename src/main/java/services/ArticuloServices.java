package services;

import modelos.Articulo;
import modelos.Comentario;
import modelos.Etiqueta;
import java.sql.Date;
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
public class ArticuloServices {

    public static Articulo getArticulo(int id) {
        Articulo articulo = null;
        Connection con = null;
            try {

                String query = "select * from articulos where id = ?";
                con = DataBaseServices.getInstancia().getConexion();
                PreparedStatement prepareStatement = con.prepareStatement(query);
                prepareStatement.setInt(1, id);
                ResultSet rs = prepareStatement.executeQuery();
                while(rs.next()){
                    articulo = new Articulo();
                    articulo.setId(rs.getInt("id"));
                    articulo.setAutor(UsuarioServices.getUsuario(rs.getString("autor")));
                    articulo.setComentarios(getComentariosArticulo(articulo));
                    articulo.setTitulo(rs.getString("titulo"));
                    articulo.setCuerpo(rs.getString("cuerpo"));
                    articulo.setFecha(rs.getDate("fecha"));
                    articulo.setEtiquetas(getEtiquetasArticulo(articulo));
                    if(articulo.getCuerpo().length() > 70)
                        articulo.setPreview(articulo.getCuerpo().substring(0,70) + "...");
                    else
                        articulo.setPreview(articulo.getCuerpo());
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

        return articulo;
    }

    public static ArrayList<Articulo> getTodosArticulos() {
        ArrayList<Articulo> articulos = new ArrayList<>();
        Connection con = null;
        try {

            String query = "select * from articulos order by FECHA,id DESC";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Articulo articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setAutor(UsuarioServices.getUsuario(rs.getString("autor")));
                articulo.setComentarios(getComentariosArticulo(articulo));
                articulo.setTitulo(rs.getString("titulo"));
                articulo.setCuerpo(rs.getString("cuerpo"));
                articulo.setFecha(rs.getDate("fecha"));
                if(articulo.getCuerpo().length() > 70)
                    articulo.setPreview(articulo.getCuerpo().substring(0,70) + "...");
                else
                    articulo.setPreview(articulo.getCuerpo());
                articulo.setEtiquetas(getEtiquetasArticulo(articulo));
                articulos.add(articulo);
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

        return articulos;
    }

    private static ArrayList<Comentario> getComentariosArticulo (Articulo  articulo){
        Connection con = null;
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        try {
            String query = "select * from comentarios where articulo = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setInt(1, articulo.getId());
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Comentario c = new Comentario();
                c.setId(rs.getInt("id"));
                c.setArticulo(articulo);
                c.setAutor(UsuarioServices.getUsuario(rs.getString("autor")));
                c.setComentario(rs.getString("comentario"));
                comentarios.add(c);
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

        return comentarios;

    }

    private static ArrayList<Etiqueta> getEtiquetasArticulo (Articulo  articulo){
        Connection con = null;
        ArrayList<Etiqueta> etiquetas = new ArrayList<Etiqueta>();
        try {
            String query = "select * from etiquetas where articulo = ?";
            con = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = con.prepareStatement(query);
            prepareStatement.setInt(1, articulo.getId());
            ResultSet rs = prepareStatement.executeQuery();
            while(rs.next()){
                Etiqueta e = new Etiqueta();
                e.setEtiqueta(rs.getString("etiqueta"));
                e.setId(rs.getInt("id"));
                etiquetas.add(e);
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

        return etiquetas;

    }

    public static int crearArticulo(Articulo articulo){
        int id=-1;
        Connection conn=null;
        try {

            String query = "insert into articulos(titulo, cuerpo, autor, fecha) values(?,?,?,?)";
            conn = DataBaseServices.getInstancia().getConexion();
            //
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, articulo.getTitulo());
            prepareStatement.setString(2, articulo.getCuerpo());
            prepareStatement.setString(3, articulo.getAutor().getUsername());
            prepareStatement.setDate(4, new java.sql.Date(articulo.getFecha().getTime()));


            prepareStatement.executeUpdate();
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            System.out.print(id);


        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

    public static int editarArticulo(Articulo articulo){
        int id=-1;
        Connection conn=null;
        try {

            String query = "update  articulos set titulo=?, cuerpo=?  where id=?";
            conn = DataBaseServices.getInstancia().getConexion();
            PreparedStatement prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1, articulo.getTitulo());
            prepareStatement.setString(2, articulo.getCuerpo());
            System.out.print(articulo.getId());
            prepareStatement.setInt(3,articulo.getId());

            prepareStatement.executeUpdate();
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloServices.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

}
