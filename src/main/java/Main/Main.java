package Main; /**
 * Created by Dell_2 on 5/29/2016.
 */




import modelos.Usuario;
import services.UsuarioServices;

import javax.persistence.EntityManager;

import static spark.Spark.*;


public class Main {
    private static EntityManager em;
    public static void main(String[] args) throws Exception{

        staticFileLocation("/publico");

//
//        ManejoTemplates mt = new ManejoTemplates();
//        mt.manejarTemplates();
//        ManejoFormularios mf = new ManejoFormularios();
//        mf.manejarFormularios();
//        Filtro ft = new Filtro();
//        ft.aplicarFiltros();



    }
}
