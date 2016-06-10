package services;

import modelos.Comentario;
import modelos.LikeArticulo;
import modelos.LikeComentario;

/**
 * Created by Dell_2 on 6/9/2016.
 */
public class LikeArticuloServices extends GestionDb<LikeArticulo>{
    private static LikeArticuloServices instancia;

    private LikeArticuloServices() {
        super(LikeArticulo.class);
    }
    public static LikeArticuloServices getInstancia() {
        if (instancia == null) {
            instancia = new LikeArticuloServices();
        }
        return instancia;
    }
}
