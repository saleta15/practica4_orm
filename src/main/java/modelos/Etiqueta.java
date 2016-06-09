package modelos;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by saleta on 5/30/2016.
 */
@Entity
public class Etiqueta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String etiqueta;
    private Articulo articulo;


    @OneToOne(mappedBy = "etiquetas") // La clase Clase es la dueña de la relación.
    private Set<Articulo> listaArticulos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public Etiqueta(){

    }
}

