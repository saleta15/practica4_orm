package services;

import modelos.Articulo;
import modelos.Comentario;

/**
 * Created by Dell_2 on 6/8/2016.
 */
public class ArticuloServices extends GestionDb<Articulo>{
    private static ArticuloServices instancia;

    private ArticuloServices() {
        super(Articulo.class);
    }
    public static ArticuloServices getInstancia() {
        if (instancia == null) {
            instancia = new ArticuloServices();
        }
        return instancia;
    }
}
