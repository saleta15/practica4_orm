package modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by saleta on 5/30/2016.
 */

@Entity
public class Comentario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comentario;
    private Usuario autor;
    private Articulo articulo;
    private Integer likes;


    @OneToOne(mappedBy = "comentarios") // La clase Clase es la dueña de la relación.
    private Set<Articulo> listaArticulos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Comentario(String comentario, Usuario autor, Articulo articulo) {
        this.comentario = comentario;
        this.autor = autor;
        this.articulo = articulo;
    }

    public Comentario() {
    }
}
