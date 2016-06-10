package services;

import modelos.Comentario;
import modelos.LikeComentario;

/**
 * Created by Dell_2 on 6/9/2016.
 */
public class LikeComentarioServices extends GestionDb<LikeComentario>{
    private static LikeComentarioServices instancia;

    private LikeComentarioServices() {
        super(LikeComentario.class);
    }
    public static LikeComentarioServices getInstancia() {
        if (instancia == null) {
            instancia = new LikeComentarioServices();
        }
        return instancia;
    }
}
