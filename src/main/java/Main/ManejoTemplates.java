//package Main;
//
//import freemarker.template.Configuration;
//import modelos.Articulo;
//import modelos.Usuario;
//import services.ArticuloServices;
//import services.UsuarioServices;
//import spark.ModelAndView;
//import spark.template.freemarker.FreeMarkerEngine;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import static spark.Spark.get;
//import static spark.Spark.post;
//
//public class ManejoTemplates {
//
//
//    public void manejarTemplates() {
//
//        Configuration configuration = new Configuration();
//        configuration.setClassForTemplateLoading(ManejoTemplates.class , "/templates");
//        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);
//
//
//        get("/", (request, response) -> {
//            Map<String, Object> attributes = new HashMap<>();
//            Usuario u = request.session().attribute("usuario");
//            if(u == null){
//                u = new Usuario();
//                u.setAutor(false);
//                u.setAdministrador(false);
//                u.setEsInvitado(true);
//            }
//
//            //ArrayList<Articulo> articulos = ArticuloServices.getTodosArticulos();
//            attributes.put("usuario", u);
//            attributes.put("articulos", articulos);
//            return new ModelAndView(attributes, "index.ftl");
//        }, freeMarkerEngine);
//
//        get("/login", (request, response) -> {
//            Map<String, Object> attributes = new HashMap<>();
//            return new ModelAndView(attributes, "login.ftl");
//        }, freeMarkerEngine);
//
//        get("/administracion/", (request, response) -> {
//            Map<String, Object> attributes = new HashMap<>();
//            Usuario u = request.session().attribute("usuario");
//
//
//
//            ArrayList<Usuario> usuarios = UsuarioServices.getTodosUsuarios();
//            attributes.put("usuario", u);
//            attributes.put("usuarios", usuarios);
//            return new ModelAndView(attributes, "administracionHome.ftl");
//        }, freeMarkerEngine);
//
//        get("/administracion/crearUsuario", (request, response) -> {
//            Map<String, Object> attributes = new HashMap<>();
//            Usuario u = new Usuario();
//            u.setAdministrador(false);
//            u.setAutor(false);
//            attributes.put("usuario", u);
//            return new ModelAndView(attributes, "crearUsuario.ftl");
//        }, freeMarkerEngine);
//
//        get("/administracion/editar/:usuario", (request, response) -> {
//            Map<String, Object> attributes = new HashMap<>();
//            Usuario usuario = UsuarioServices.getUsuario(request.params("usuario"));
//            if (usuario == null){
//                response.redirect("../../");
//            }
//            attributes.put("usuario", usuario);
//            return new ModelAndView(attributes, "editarUsuario.ftl");
//        }, freeMarkerEngine);
//
//        get("/redactarArticulo", (request, response) -> {
//            Map<String, Object> attributes = new HashMap<>();
//            Usuario u = new Usuario();
//            u.setAdministrador(false);
//            u.setAutor(false);
//            attributes.put("usuario", u);
//            return new ModelAndView(attributes, "crearArticulo.ftl");
//        }, freeMarkerEngine);
//
//        get("/verArticulo/:articulo", (request, response) -> {
//            Map<String, Object> attributes = new HashMap<>();
//            Usuario u = request.session().attribute("usuario");
//            if(u == null){
//                u = new Usuario();
//                u.setAutor(false);
//                u.setAdministrador(false);
//                u.setEsInvitado(true);
//                u.setUsername("guest");
//            }
//            Articulo a = ArticuloServices.getArticulo(Integer.parseInt(request.params("articulo")));
//
//            attributes.put("usuario", u);
//            attributes.put("articulo", a);
//            return new ModelAndView(attributes, "verArticulo.ftl");
//        }, freeMarkerEngine);
//
//        get("/editarArticulo/:articulo", (request, response) -> {
//            Map<String, Object> attributes = new HashMap<>();
//            Usuario u = request.session().attribute("usuario");
//            Articulo a = ArticuloServices.getArticulo(Integer.parseInt(request.params("articulo")));
//
//            attributes.put("usuario", u);
//            attributes.put("articulo", a);
//            attributes.put("etiquetas", a.getEtiquetasString());
//            return new ModelAndView(attributes, "editarArticulo.ftl");
//        }, freeMarkerEngine   );
//
//
//
//    }
//}
