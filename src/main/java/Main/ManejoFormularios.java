//package Main;
//
//import freemarker.template.Configuration;
//import modelos.Articulo;
//import modelos.Comentario;
//import modelos.Usuario;
//import services.ArticuloServices;
//import services.ComentarioServices;
//import services.EtiquetaServices;
//import services.UsuarioServices;
//import spark.ModelAndView;
//import spark.Session;
//import spark.template.freemarker.FreeMarkerEngine;
//import spark.Spark.*;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.SynchronousQueue;
//
//import static spark.Spark.get;
//import static spark.Spark.halt;
//import static spark.Spark.post;
//
///**
// * Created by Dell_2 on 6/1/2016.
// */
//public class ManejoFormularios {
//
//    public void manejarFormularios() {
//
//        post("autenticar/", (request, response) -> {
//
//            Session session=request.session(true);
//            Usuario usuario = UsuarioServices.getUsuario(request.queryParams("username"));
//
//            if (usuario==null ||!request.queryParams("password").equals(usuario.getPassword())){
//                halt(401,"Credenciales no validas...");
//                return "fail";
//            }
//            else {
//                session.attribute("usuario", usuario);
//                response.redirect("/");
//                return "success";
//            }
//        });
//        post("/cerrarsesion/", (request, response) -> {
//            request.session().invalidate();
//            response.redirect("/");
//            return "Cesion cerrada";
//        });
//
//
//
//        post("procesarNuevoUsuario/", (request, response) -> {
//            if(request.queryParams("nombre") == null || request.queryParams("password") == null
//               || request.queryParams("username") == null){
//                response.redirect("../administracion/");
//                return "fail";
//            }
//
//            Boolean esAutor = request.queryParams("autor") != null;
//            Boolean esAdministrador = request.queryParams("administrador").equals("true");
//            Usuario usuario = new Usuario(esAutor,esAdministrador,request.queryParams("nombre"),request.queryParams("password"),
//                                          request.queryParams("username"));
//            UsuarioServices.crearUsuario(usuario);
//            response.redirect("../administracion/");
//           return "success";
//        });
//
//        post("procesarEditarUsuario/", (request, response) -> {
//            Boolean esAutor = request.queryParams("autor") != null;
//            Boolean esAdministrador = request.queryParams("administrador").equals("true");
//            Usuario usuario = new Usuario(esAutor, esAdministrador,request.queryParams("nombre"),request.queryParams("password"),
//                    request.queryParams("username"));
//
//            if(request.queryParams("action").equals("borrarUsuario"))
//                UsuarioServices.deleteUsuario(usuario);
//            else if (request.queryParams("action").equals("editarUsuario"))
//                 UsuarioServices.updateUsuario(usuario);
//            response.redirect("../administracion/");
//            return "success";
//        });
//
//        post("procesarNuevoComentario/", (request, response) -> {
//            Usuario u = request.session().attribute("usuario");
//            if(u == null) {
//                response.redirect("../login");
//            }
//            else{
//                Comentario comentario = new Comentario(request.queryParams("comentario"),
//                                                       UsuarioServices.getUsuario(request.queryParams("username")),
//                                                       ArticuloServices.getArticulo(Integer.parseInt(request.queryParams("articulo"))));
//                ComentarioServices.crearComentario(comentario);
//                response.redirect("/verArticulo/" + comentario.getArticulo().getId());
//            }
//            return "success";
//        });
//
//        post("procesarBorrarComentario/", (request, response) -> {
//
//            Usuario u = request.session().attribute("usuario");
//            if(u == null) {
//                response.redirect("../login");
//            }
//            else{
//                int id = Integer.parseInt(request.queryParams("comentario"));
//                ComentarioServices.borrarComentario(id);
//                response.redirect("/verArticulo/" + Integer.parseInt(request.queryParams("articulo")));
//            }
//            return "success";
//        });
//
//        post("procesarCrearArticulo/", (request, response) -> {
//            Usuario u = request.session().attribute("usuario");
//            Articulo articulo = new Articulo();
//            articulo.setTitulo(request.queryParams("titulo"));
//            articulo.setCuerpo(request.queryParams("cuerpo"));
//            articulo.setAutor(u);
//            articulo.setFecha(new Date());
//            int articuloId = ArticuloServices.crearArticulo(articulo);
//            articulo.setId(articuloId);
//            EtiquetaServices.crearEtiquetas(request.queryParams("etiquetas"),articulo);
//            response.redirect("/");
//
//            return "success";
//        });
//
//        post("procesarEditarArticulo/", (request, response) -> {
//            Usuario u = request.session().attribute("usuario");
//            if(u == null) {
//                response.redirect("../login");
//            }
//            else{
//                Articulo articulo = ArticuloServices.getArticulo(Integer.parseInt(request.queryParams("articulo")));
//                articulo.setCuerpo(request.queryParams("cuerpo"));
//                articulo.setTitulo(request.queryParams("titulo"));
//                ArticuloServices.editarArticulo(articulo);
//                EtiquetaServices.editarEtiquetas(request.queryParams("etiquetas"),articulo);
//                response.redirect("/");
//            }
//            return "success";
//        });
//
//
//    }
//}
