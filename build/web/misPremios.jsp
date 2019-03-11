<%-- 
    Document   : Usuario
    Created on : 10/03/2019, 07:08:07 PM
    Author     : Juan
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Mis Premios</title>

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
            <link rel="stylesheet" href="font-awesome/css/font-awesome.css">

            <!-- Favicons -->
            <link href="img/favicon.png" rel="icon">
            <link href="img/apple-touch-icon.png" rel="apple-touch-icon">
        </head>

        <body>

            <div ng-app="premiosApp">
                <div ng-controller="premiosController">
                    <div class="container">
                        <div class="text-center">
                            <h3>Todos los Premios</h3>
                        </div>

                        <div class="row">
                            <div class="col-xs-4" ng-repeat="premio in premios">
                                <div class="panel panel-default">
                                    <div class="panel-heading text-center">
                                        <h4>{{ premio.nombre }}</h4>
                                    </div>
                                    <div class="panel-body text-center">
                                        <img src="" alt=""> Nombre: {{ premio.nombre }} <br> Categoria: {{ premio.categoria
                                        }} <br> Puntos: {{ premio.costo_puntos }}<br> Descripcion: {{ premio.descripcion
                                        }} <br> Stock: {{ premio.stock }}
                                    </div>
                                    <div class="panel-footer">
                                        <button ng-disabled="premio.stock < 1" class="btn btn-block btn-warning" data-toggle="modal" data-target="#modal_cantidad" ng-click="canjearPremio(premio)">Canjear
                                            Premio
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="container">
                        <div class="text-center">
                            <h3>Mis Premios</h3>
                        </div>

                        <div class="row">
                            <div class="col-sm-6 col-md-4" ng-repeat="premios in mispremios">
                                <div class="thumbnail">
                                    <img src="..." alt="...">
                                    <div class="caption">
                                        <h4>Nombre: {{ premios.premio.nombre }}</h4>
                                        <p>
                                            Categoria: {{ premios.premio.categoria }} <br> Puntos: {{ premios.premio.costo_puntos
                                            }} <br> Cantidad: {{ premios.cantidad }}
                                            <br> Descripcion: {{ premios.premio.descripcion }}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Modal Cambiar Cantidad de Premios -->
                    <div class="modal fade" id="modal_cantidad" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h3 class="sec-head">
                                        Cantidad de premios
                                    </h3>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group row">
                                        <div class="col-xs-9">
                                            <input type="number" class="form-control" placeholder="Cantidad de premios" ng-model="cantidad">
                                        </div>
                                        <div class="col-xs-3">
                                            <button class="btn btn-block" ng-click="canjearPremios()">
                                                Canjear
                                            </button>
                                        </div>
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--Modal-->

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
            <script src="js/Modulo4/premios.js"></script>

        </body>

        </html>