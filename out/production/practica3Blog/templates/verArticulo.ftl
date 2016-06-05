<!DOCTYPE html>
<html lang="en">

<head>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/miEstilo.css" rel="stylesheet">
</head>

<body>

<#include "/header.ftl">

<div class="container">

    <div class="row">
        <div class="col-lg-8">

            <h1>${articulo.titulo}</h1>


            <p class="lead">
                por <i>${articulo.autor.nombre}</i>
            </p>

            <p> ${articulo.fecha}</p><#if usuario.administrador || usuario.username == articulo.autor.username>
            <a href="/editarArticulo/${articulo.id}">Editar</a>
        </#if>
            <hr>
            <p>${articulo.cuerpo}</p>
            <hr>
            <h3>Etiquetas:</h3>
            <#list etiquetas as e>
                <button id="etiqueta" class="btn btn-primary">${e.etiqueta}</button>
            </#list>
            <br><br>
            <div class="well">
                <h4>¿Quieres decir algo??</h4>
                <form action="/procesarNuevoComentario/" METHOD="post">
                    <div class="form-group">
                        <textarea class="form-control" rows="3" name="comentario"></textarea>
                    </div>
                    <input type="hidden" value="${articulo.id}" name="articulo">
                    <input type="hidden" value="${usuario.username}" name="username">
                    <button type="submit" class="btn btn-primary">Dejar comentario</button>
                </form>
            </div>
            <hr>
            <!-- Posted Comments -->
            <#list articulo.comentarios as comentario>
                <div class="media">
                    <div class="media-body">
                        <h4 class="media-heading">${comentario.autor.nombre}</h4>
                        ${comentario.comentario}
                    </div>
                </div>
                <hr>
            </#list>
        </div>
    </div>
    <!-- /.row -->





</body>

</html>
