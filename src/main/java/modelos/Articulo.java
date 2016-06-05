package modelos;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by saleta on 5/30/2016.
 */

public class Articulo {
    private int id;
    private String titulo;
    private  String cuerpo;
    private String preview;

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    private Usuario autor;
    private Date fecha;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Etiqueta> etiquetas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getEtiquetasString(){
        String et = "";
        for (int i=0; i<etiquetas.size();i++){
            et+=etiquetas.get(i).getEtiqueta();
            if(i< etiquetas.size()-1)
                et+=",";
        }
        return et;
    }
}
