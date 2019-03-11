<%-- 
    Document   : wishlist
    Created on : 8/03/2019, 12:39:53 PM
    Author     : hp
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html ng-app="wishListApp">

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>WishList</title>

            <!-- CSS Bootstrap -->
            <link rel="stylesheet" href="css/bootstrap.min.css">
            <link rel="stylesheet" href="css/font-awesome.min.css">

            <!-- Main css -->
            <link rel="stylesheet" href="css/style.css">
            <link href="https://fonts.googleapis.com/css?family=Lora|Merriweather:300,400" rel="stylesheet">

            <!-- AngularJS -->
            <script src="js/angular/angular.js"></script>
            <!-- SweetAlert -->
            <script src="js/sweetalert/sweetalert.js"></script>
            <!-- Iconos -->
            <link rel="stylesheet" href="css/font-awesome.css">

            <!-- Favicons -->
            <link href="img/favicon.png" rel="icon">
            <link href="img/apple-touch-icon.png" rel="apple-touch-icon">

            <style>
                .border-red {
                    border-color: #a94442;
                }

                .bb-red {
                    border-bottom: #a94442 3px;
                }
            </style>
        </head>

        <body ng-controller="wishListController">

            <!-- Navigation section  -->

            <div class="navbar navbar-default navbar-static-top" role="navigation">
                <div class="container">

                    <div class="navbar-header">
                        <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="icon icon-bar"></span>
                            <span class="icon icon-bar"></span>
                            <span class="icon icon-bar"></span>
                        </button>
                        <a href="indexUser.jsp" class="navbar-brand">Alejandr�a</a>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="active"><a href="indexUser.jsp">Inicio</a></li>
                            <li><a href="indexUser.jsp">Regresar</a></li>
                            <li><a ng-click="logout()">Salir</a></li>
                        </ul>
                    </div>

                </div>
            </div>

            <div id="inicio">
                <section id="blog">
                    <div class="container">
                        <div class="row">

                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <div class="blog-post-title">
                                    <h3>
                                        Mi Wish List
                                        <button class="btn" data-toggle="modal" data-target="#modal_share"> <i class="fa fa-share"></i>
                                        </button>
                                    </h3>
                                </div>

                                <div class="panel panel-warning">
                                    <div class="panel-heading">Libros en mi wishlist</div>
                                    <!-- List group -->
                                    <ul ng-class="whatClassIsIt(libros.libro)" ng-repeat="libros in wishlist.libros">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <br>
                                                <h4 class="list-group-item-heading">{{libros.libro.nombre}}</h4>
                                                <h5 class="list-group-item-text">Autor: {{ libros.libro.autor.nombre }} </h5>
                                            </div>
                                            <div class="col-xs-6 row">
                                                <div class="col-md-4">
                                                    <button class="btn btn-default btn-block btn-lg" data-toggle="modal" data-target="#modalCompra" ng-click="comprarLibro(libros.libro)">
                                                        <i class="fa fa-shopping-cart"></i>
                                                    </button>
                                                </div>
                                                <div class="col-md-4">
                                                    <button class="btn btn-default btn-block btn-lg" ng-click="removeToWishList(libros.libro)"><i
                                                            class="fa fa-trash"></i>
                                                    </button>
                                                </div>
                                                <div class="col-md-4">
                                                    <div ng-if="!libros.favorito">
                                                        <button ng-click="updateFavsWishlist(libros.libro, true)" class="btn btn-default btn-block btn-lg">
                                                            <i class="fa fa-heart"></i>
                                                        </button>
                                                    </div>
                                                    <div ng-if="libros.favorito">
                                                        <button ng-click="updateFavsWishlist(libros.libro, false)" class="border-red btn btn-default btn-block btn-lg">
                                                            <i class="fa fa-heart text-danger"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-sm-12 col-md-6 col-lg-6">
                                <div class="blog-post-title">
                                    <h3><a href="single-post.html">Compartidas Conmigo </a></h3>
                                    <div>
                                        <input type="text" class="form-control" ng-keyup="bucarWish()" ng-model="buscador" placeholder="Ingresa nombre de wishlist para buscar">
                                    </div>
                                    <br>
                                </div>
                                <div class="panel panel-default">
                                    <!-- Default panel contents -->
                                    <div class="panel-heading">Wishlist Compartidas</div>

                                    <!-- List group -->
                                    <ul class="list-group list-group-item" ng-repeat="shared in sharedwishlist">
                                        <div class="row">
                                            <div class="col-xs-9">
                                                <br>
                                                <h4 class="list-group-item-heading">{{ shared.alias }}
                                                </h4>
                                                <h5 class="list-group-item-text">Compartida por: {{ shared.wishlist.usuario.usuario
                                                    }} </h5>
                                            </div>
                                            <div class="col-xs-3">
                                                <button class="btn btn-default btn-block btn-lg" data-toggle="modal" data-target="#modal_sharedwishlist" ng-click="thisWish(shared)">
                                                    <i class="fa fa-eye"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </ul>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="section-headline services-head text-center">
                                    <h2>Añadir Libros Wishlist</h2>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-12" ng-repeat="libro in listlibros" style="margin-top: 2em">
                                <div class="single-team-member">
                                    <div class="team-img">
                                        <a href="#" class="text-center">
                                            <img src="img/books/{{libro.foto}}" alt="">
                                        </a>
                                    </div>
                                    <div class="team-content text-center">
                                        <h4>{{libro.nombre}}</h4>
                                        <p>{{libro.autor.nombre}}</p>
                                        <button class="btn btn-secondary btn-sm btn-block" ng-click="addToWishlist(libro.id_libro)">Añadir
                                            a mi lista </button>
                                        <button class="btn btn-secondary btn-sm btn-block" ng-click="seeBook(libro)" data-toggle="modal" data-target="#modalDetails">Detalles
                                            del libro</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="container row">
                            <div class="blog-post-title">
                                <h3>
                                    Historial de Compras
                                </h3>
                            </div>
                            <div>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Fecha</th>
                                            <th>Folio</th>
                                            <th>Detalle</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr ng-repeat="compra in compras">
                                            <th>{{compra.fecha}}</th>
                                            <th>{{compra.folio}}</th>
                                            <th><button class="btn" data-toggle="modal" data-target="#modal_detallesCompra"
                                                    ng-click="detailsCompta(compra)">
                                                    <i class="fa fa-pencil"></i>
                                                </button></th>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </section>
            </div>

            <!-- Modal Compra Libro -->
            <div class="modal fade" id="modal_detallesCompra" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true"
                aria-hidden="true" style="text-align: center;">
                <div class="modal-dialog modal-dialog-centered" role="document" style="display: inline-block; vertical-align: middle; text-align: left; height: 100%;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="exampleModalCenterTitle">Detalles de la compra</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div style="margin-top: 20px;">
                                <div>

                                    <table class="table table-border">
                                        <thead></thead>
                                        <tbody>
                                            <tr>
                                                <td>Direccion de envio:</td>
                                                <td>{{ detalles_compra.usuario.persona.direccion }}</td>
                                            </tr>
                                            <tr>
                                                <td>Nombre del libro:</td>
                                                <td>{{ detalles_compra.detalle_compra.libro.nombre }}</td>
                                            </tr>
                                            <tr>
                                                <td>Autores del libro:</td>
                                                <td>{{ detalles_compra.detalle_compra.libro.autor.nombre }}</td>
                                            </tr>
                                            <tr>
                                                <td>Editorial del libro:</td>
                                                <td>{{ detalles_compra.detalle_compra.libro.editorial }}</td>
                                            </tr>
                                            <tr>
                                                <td>Descripcion del libro:</td>
                                                <td>{{ detalles_compra.detalle_compra.libro.nombre }}</td>
                                            </tr>
                                            <tr>
                                                <td class="text-right">Precio del libro:</td>
                                                <td>$ {{ detalles_compra.detalle_compra.libro.costo }}</td>
                                            </tr>
                                            <tr>
                                                <td class="text-right">Costo de envio:</td>
                                                <td>$ 150 </td>
                                            </tr>
                                            <tr>
                                                <td class="text-right">Iva: (16%)</td>
                                                <td>$ {{ detalles_compra.detalle_compra.libro.costo * 0.16 }} </td>
                                            </tr>
                                            <tr>
                                                <td class="text-right">Total: </td>
                                                <td>$ {{ detalles_compra.detalle_compra.libro.costo + (detalles_compra.detalle_compra.libro.costo
                                                    * 0.16) + 150}}
                                                    </th>
                                            </tr>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal" ng-click="reset()">Close</button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal Compra Libro -->
            <div class="modal fade" id="modalCompra" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true"
                aria-hidden="true" style="text-align: center;">
                <div class="modal-dialog modal-dialog-centered" role="document" style="display: inline-block; vertical-align: middle; text-align: left; height: 100%;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="exampleModalCenterTitle">Comprar Libro "{{libro.nombre}}"</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div style="margin-top: 20px;">
                                <div>
                                    <h3>Precio del libro: $ {{libro.costo}}</h3>
                                    <form class="contactForm">
                                        <!--
                                        <label>Seleccione tipo de pago</label>
                                        <select ng-model="tipopago" ng-change="getPuntos()" id="tipopago" class="form-control">
                                            <option value="puntos">Puntos</option>
                                            <option value="prepago">Tarjeta Prepago</option>
                                        </select>
                                        -->
                                        <div class="form-group" style="margin-top: 20px;" ng-if="tipopago == 'prepago'">
                                            <div ng-if="verificacionTarjeta === false">
                                                <label>Numero de tarjeta de {{tipopago}}</label>
                                                <div class="row">
                                                    <div class="col-xs-6">
                                                        <input type="number" class="form-control" ng-model="tarjeta_prepago.numero" id="saldoTarjeta" placeholder="Numero de tarjeta">

                                                    </div>
                                                    <div class="col-xs-6">
                                                        <button class="btn btn-block" ng-click="getDatosTarjeta()">Verificar</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group" style="margin-top: 20px;" ng-if="tipopago == 'puntos'">

                                            <label>Numero de tarjeta de {{tipopago}}</label>
                                            <input type="number" class="form-control" ng-model="tarjeta_prepago.numero" id="saldoTarjeta" placeholder="Numero de tarjeta">

                                        </div>
                                        <!--
                                        <div ng-if="tipopago">
                                            <label>¿Desea regalar Libro?</label>
                                            <select ng-model="datosRegalo.opcion" class="form-control">
                                                <option value="si">SI</option>
                                                <option value="no">No</option>
                                            </select>
                                        </div>
                                        -->
                                    </form>
                                    <!--
                                    <form ng-if="datosRegalo.opcion === 'si'">
                                        <label>Correo de notificacion</label>
                                        <div>
                                            <input type="email" class="form-control" placeholder="Correo de notificacion" ng-model="datosRegalo.correo">
                                            <label style="margin-top: 20px;">Direccion de envio</label>
                                            <input class="form-control" placeholder="Direccion de envio" ng-model="datosRegalo.direccion">
                                            <label style="margin-top: 20px;">Incluir tarjeta de felicitaciones</label>
                                            <select ng-model="datosRegalo.tarjeta" class="form-control">
                                                <option value="si">Si</option>
                                                <option value="no">No</option>
                                            </select>
                                            <label style="margin-top: 20px;">Seleccionar envoltura</label>
                                            <select ng-model="datosRegalo.envoltura" class="form-control">
                                                <option value="cumpleaños">Cumpleaños</option>
                                                <option value="boda">Boda</option>
                                                <option value="regalo">Regalo</option>
                                            </select>
                                            <div ng-if="datosRegalo.tarjeta === 'si'">
                                                <label style="margin-top: 20px;">Mensaje para la tarjeta: </label>
                                                <input class="form-control" placeholder="Mensaje" ng-model="datosRegalo.mensaje">
                                            </div>
                                        </div>
                                    </form>
                                    -->
                                    <div ng-if="verificacionTarjeta === true">
                                        <div>
                                            <label>Detalles de la compra</label>
                                        </div>
                                        <table class="table table-border">
                                            <thead></thead>
                                            <tbody>
                                                <tr>
                                                    <td>Direccion de envio:</td>
                                                    <td>{{ usuario.persona.direccion }}</td>
                                                </tr>
                                                <tr>
                                                    <td>Nombre del libro:</td>
                                                    <td>{{ libro.nombre }}</td>
                                                </tr>
                                                <tr>
                                                    <td>Autores del libro:</td>
                                                    <td>{{ libro.autor.nombre }}</td>
                                                </tr>
                                                <tr>
                                                    <td>Editorial del libro:</td>
                                                    <td>{{ libro.editorial }}</td>
                                                </tr>
                                                <tr>
                                                    <td>Descripcion del libro:</td>
                                                    <td>{{ libro.nombre }}</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right">Precio del libro:</td>
                                                    <td>$ {{ libro.costo }}</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right">Costo de envio:</td>
                                                    <td>$ 150 </td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right">Iva: (16%)</td>
                                                    <td>$ {{ libro.costo * 0.16 }} </td>
                                                </tr>
                                                <tr>
                                                    <td class="text-right">Total: </td>
                                                    <td>$ {{ libro.costo + (libro.costo * 0.16) + 150}}
                                                        </th>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal" ng-click="reset()">Close</button>
                            <button type="button" class="btn btn-primary" data-dismiss="modal" aria-label="Close" ng-if="verificacionTarjeta === true" ng-click="compraLibro(libro)">Comprar
                                Libro
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal Cambiar Alias WishList-->
            <div class="modal fade" id="modal_sharedwishlist" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="sec-head">
                                {{sharedwishlistbyusuario.alias}}
                            </h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="form-group row">
                                <div class="col-xs-9">
                                    <input type="text" class="form-control" placeholder="Alias de la wishlist" ng-model="aliasSharedWishlist">
                                </div>
                                <div class="col-xs-3"><button class="btn btn-block" ng-click="updtaeAliasSharedWishlist(sharedwishlistbyusuario.id_compartir)"
                                        data-dismiss="modal" aria-label="Close">Cambiar Alias
                                    </button></div>

                            </div>
                            <div class="list-group" ng-repeat="objectlibro in sharedwishlistbyusuario.wishlist.libros">
                                <a class="list-group-item">
                                    <h4 class="list-group-item-heading">{{ objectlibro.libro.nombre }}</h4>
                                    <h5 class="list-group-item-text">Autor: {{ objectlibro.libro.autor.nombre }}</h5>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--Modal-->


            <!-- Modal Compartir Wish List -->
            <div class="modal fade" id="modal_share" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="sec-head"></h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div clas="form-group">
                                <h5 class="modal-title" id="exampleModalLabel">Correo de tu amigo</h5>
                                <input type="text" placeholder="Correo@gmail.com" class="form-control" ng-model="share.correo">
                                <p>Ingresa el correo electronico de la persona a quien quieras compartir esta lista de deseos
                                </p>
                            </div>
                            <button class="btn btn-secondary btn-block" ng-click="shareWishlist()">Compartir</button>

                        </div>
                    </div>
                </div>
            </div>
            <!--Modal-->

            <!-- Modal Libro -->
            <div class="modal fade" id="modalDetails" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true"
                aria-hidden="true" style="text-align: center; padding-top: 10%;">
                <div class="modal-dialog modal-dialog-centered" role="document" style="display: inline-block; vertical-align: middle; text-align: left; height: 100%;">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="exampleModalCenterTitle">Detalles del Libro</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div style="margin-top: 20px;">
                                <div class="row">
                                    <!-- single-well start-->
                                    <div class="col-md-6 col-sm-6 col-xs-12 text-center">
                                        <img src="img/books/{{libro.foto}}">
                                    </div>

                                    <!-- single-well end-->
                                    <div class="col-md-6 col-sm-6 col-xs-12 text-left">
                                        <div class="well-middle">
                                            <div class="single-well">
                                                <strong><p>Nombre del libro: {{libro.nombre}}</p></strong>
                                                <strong><p>Autores del libro: {{libro.autor.nombre}}</p></strong>
                                                <strong><p>Editorial del libro: {{libro.editorial}}</p></strong>
                                                <strong><p>Precio del libro: {{libro.costo}}</p></strong>
                                                <strong><p>Categoria del libro: {{libro.categoria}}</p></strong>
                                                <strong><p>Descripcion del libro: {{libro.descripcion}}</p></strong>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- End col-->
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary" data-dismiss="modal" ng-if="verificacionTarjeta === true" ng-click="comprarLibro('comprar', null)">Comprar
                                Libro
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- SCRIPTS -->
            <script src="js/jquery.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/particles.min.js"></script>
            <script src="js/app.js"></script>
            <script src="js/jquery.parallax.js"></script>
            <script src="js/smoothscroll.js"></script>
            <script src="js/custom.js"></script>

            <script src="js/Modulo4//wishlist.js"></script>
        </body>

        </html>