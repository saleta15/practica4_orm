package modelos;

/**
 * Created by saleta on 5/30/2016.
 */
public class Etiqueta {
    private int id;
    private String etiqueta;
    private Articulo articulo;

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
}

