package services;

import modelos.Comentario;

/**
 * Created by Dell_2 on 6/8/2016.
 */

public class ComentarioServices extends GestionDb<Comentario>{

    private static ComentarioServices instancia;

    private ComentarioServices() {
        super(Comentario.class);
    }
    public static ComentarioServices getInstancia() {
        if (instancia == null) {
            instancia = new ComentarioServices();
        }
        return instancia;
    }
}

