package Main; /**
 * Created by Dell_2 on 5/29/2016.
 */


import modelos.Articulo;
import modelos.Comentario;
import modelos.Etiqueta;
import services.ArticuloServices;
import services.ComentarioServices;
import services.DataBaseServices;
import services.EtiquetaServices;
import spark.ModelAndView;
import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.Connection;
import java.sql.Statement;

import static spark.Spark.*;
public class Main {

    public static void main(String[] args) throws Exception{


        staticFileLocation("/publico");
        DataBaseServices.crearTablas();
        DataBaseServices.crearAdministrador();
        ManejoTemplates mt = new ManejoTemplates();
        mt.manejarTemplates();
        ManejoFormularios mf = new ManejoFormularios();
        mf.manejarFormularios();
        Filtro ft = new Filtro();
        ft.aplicarFiltros();



    }
}
