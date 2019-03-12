<%-- 
    Document   : wishlist
    Created on : 8/03/2019, 12:39:53 PM
    Author     : hp
--%>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html ng-app="compraApp">

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Compras</title>

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
            <link rel="stylesheet" href="css/font-awesome.min.css">

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

        <body ng-controller="compraController">

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
                            <li class="active" ng-click="openCarrito()"><a href="#"> <i class="fa fa-shopping-cart"></i>Carrito</a></li>
                            <li class="active"><a href="indexUser.jsp">Inicio</a></li>
                            <li><a href="indexUser.jsp">Regresar</a></li>
                            <li><a ng-click="logout()">Salir</a></li>
                        </ul>
                    </div>

                </div>
            </div>
            
            <!--CATALO-->
            <div>
                <div class="container">
                    <div class="row">
                        <h3>Catalogo</h3>
                    </div>
                    
                    <div class="row col-md-8">
                        <ul class="list-group">
                            <li ng-repeat="libro in listlibros" class="list-group-item d-flex justify-content-between align-items-center">
                                <div class="row">
                                    <div class="col-md-10">
                                        <h3>{{libro.nombre}}</h3>
                                        <h5 class="text-muted">Editorial {{libro.editorial}}</h5>
                                        <h5 class="text-muted">Autor - {{libro.autor.nombre}} {{libro.autor.apellido_paterno}} {{libro.autor.apellido_materno}}</h5>
                                        <h5 class="text-muted">$ {{libro.costo}} MNM</h5>
                                        <h5 ng-show="libro.stock == '0'"> Libro no disponible <h5>
                                    </div>
                                     <div class="col-md-2">
                                         <button ng-show="libro.stock != '0'" class="btn btn-default btn-block btn-lg" ng-click="addLibroInCarrito(libro)"> <i class="fa fa-shopping-cart"></i></button>
                                    </div> 
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <!--CATALOGO-->
            
            <!--MODAL CARRITO-->
            <!-- Modal -->
            <div class="modal fade" id="modal_carrito" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Carrito de Compra</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                    
                    
                  <div class="modal-body">
                    <h4 ng-show="carrito.libros.length == 0">No tienes libros en tu carrito de compra :(</h4>
                    <ul ng-show="carrito.libros.length > 0" class="list-group">
                      <li ng-repeat="libro in carrito.libros"class="list-group-item d-flex justify-content-between align-items-center">
                        {{libro.nombre}}
                        
                        <button type="button" class="close mr-3" ng-click="removeLibroInCarrito($index)">
                        <span">&times;</span>
                        </button>
                        
                        <span class="badge badge-primary badge-pill">$ {{libro.costo}}</span>
                      </li>
                      
                    <div class="container-fluid">
                        <h4>Subtotal: $ {{carrito.subtotal}}</h4>
                        <h4>IVA: $ {{carrito.iva}}</h4>
                        <h4>Total: $ {{carrito.total}}</h4>
                        <h6 ng-show="carrito.total < 600">Se cobraran $50 extra por costos de envio</h6>
                    </div>
                      
                    <div class="container-fluid">
                        <label>Tipo pago</label
                        <br>
                        <form>
                            <label class="radio-inline"><input type="radio" ng-model="pago.tipo" ng-click="showContainerTarjeta(false)" value="saldo" name="pago" checked>Saldo electronico</label>
                            <label class="radio-inline"><input type="radio" ng-model="pago.tipo" ng-click="showContainerTarjeta(true)" value="tarjeta" name="pago">Tarjeta (Credito / Debito)</label>
                        </form>
                        
                        <div class="container-fluid" id="container_tarjeta">
                            <div class="row">
                                <div class="col">
                                    <label>No. Tarjeta</label>
                                    <input type="text" id="numero" maxlength="19" placeholder="XXXX-XXXX-XXXX-XXXX" class="form-control">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6" style="padding-left: 0;">
                                    <label>CVV</label>
                                    <input type="text" id="cvv" maxlength="16" placeholder="XXX" class="form-control">
                                </div>
                                <div class="col-md-6" style="padding-right: 0;">
                                    <label>Vigencia</label>
                                    <input type="text" id="vigencia" maxlength="16" placeholder="AAAA-MM-DD" class="form-control">
                                </div>
                            </div>                                   
                        </div>
                        
                        
<!--                        <select selected class="form-control" ng-model="tipo_pago" style="margin-bottom: 10px;">
                            <option val="saldo">Saldo electronico</option>
                            <option val="tarjeta">Tarjeta</option>
                        </select>
                        -->
                        
                        <label>Direcci&oacute;n de envio</label>
                        <input class="form-control" id="dir" ng-model="dir_envio" style="margin-bottom: 10px;">
                        <button ng-show="carrito.libros.length > 0"  ng-click="comprar()" type="button" class="btn btn-secondary btn-block" ng-click="comprar()">Comprar</button>
                    </div>
                      
                    </ul>
                  </div>
                  <div class="modal-footer">
                    <button ng-show="carrito.libros.length > 0" class="btn btn-secondary" ng-click="dropCarrito()"> Vaciar </button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                  </div>
                </div>
              </div>
            </div>
            <!--MODAL CARRITO-->
            
          
            <!-- SCRIPTS -->
            <script src="js/jquery.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/particles.min.js"></script>
            <script src="js/app.js"></script>
            <script src="js/jquery.parallax.js"></script>
            <script src="js/smoothscroll.js"></script>
            <script src="js/custom.js"></script>

            <script src="js/Modulo1/compra.js"></script>
            
            <script>
                $('#container_tarjeta').hide();
            </script>
        </body>

        </html>