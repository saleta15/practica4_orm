<!DOCTYPE html>
<html>
<head>
    <title>Información Estudiante </title>
    <link href="/css/bootstrap.css" rel="stylesheet" >
</head>
<body>

    <#include "/header.ftl">
    <div class="container">
        <h2>Artículos</h2>
    <#list articulos as articulo>
        <div class="col-lg-8">
            <a href="verArticulo/${articulo.id}"><h4>${articulo.titulo}</h4></a>
            <p>${articulo.fecha}</p>
            <p>${articulo.preview} </p>
            <hr>
        </div>
    </#list>
</div>






    <!-- /.row -->






</body>
</html>