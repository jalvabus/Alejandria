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
                    border-color: red;
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
                            <li><a href="#">Salir</a></li>
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
                                    <ul class="list-group list-group-item" ng-repeat="libros in wishlist.libros">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <br>
                                                <h4 class="list-group-item-heading">{{libros.libro.nombre}}</h4>
                                                <h5 class="list-group-item-text">Autor: {{ libros.libro.autor.nombre }} </h5>
                                            </div>
                                            <div class="col-xs-6 row">
                                                <div class="col-md-4">
                                                    <button class="btn btn-default btn-block btn-lg" data-toggle="modal" data-target="#modalCompra" ng-click="comprarLibro('modal', libros.libro)">
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
                                                        <button ng-click="updateFavsWishlist(libros.libro, true)" class="btn btn-default btn-block btn-lg" data-toggle="modal" data-target="#modalCompra"
                                                            ng-click="comprarLibro('modal', libros.libro)">
                                                            <i class="fa fa-heart"></i>
                                                        </button>
                                                    </div>
                                                    <div ng-if="libros.favorito">
                                                        <button ng-click="updateFavsWishlist(libros.libro, false)" class="border-red btn btn-default btn-block btn-lg" data-toggle="modal"
                                                            data-target="#modalCompra" ng-click="comprarLibro('modal', libros.libro)">
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
                                </div>
                                <div class="panel panel-default">
                                    <!-- Default panel contents -->
                                    <div class="panel-heading">Wishlist Compartidas</div>

                                    <!-- List group -->
                                    <ul class="list-group list-group-item" ng-repeat="shared in sharedwishlist">
                                        <div class="row">
                                            <div class="col-xs-9">
                                                <br>
                                                <h4 class="list-group-item-heading">{{ shared.wishlist.usuario.persona.nombre }}</h4>
                                                <h5 class="list-group-item-text">Autor: {{ shared.wishlist.usuario.persona.apellido_paterno
                                                    }} </h5>
                                            </div>
                                            <div class="col-xs-3">
                                                <button class="btn btn-default btn-block btn-lg" data-toggle="modal" data-target="#modalCompra" ng-click="comprarLibro('modal', libros.libro)">
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

                    </div>
                </section>
            </div>


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
                                <p>Ingresa el correo electronico de la persona a quien quieras compartir esta lista de deseos</p>
                            </div>
                            <button class="btn btn-secondary btn-block" ng-click="shareWishlist()">Compartir</button>

                        </div>
                    </div>
                </div>
            </div>
            <!--Modal-->

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