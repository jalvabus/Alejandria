<%-- 
    Document   : login
    Created on : 8/03/2019, 10:19:04 AM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="principalLoginApp">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>

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

    <style>
        .login-container {
            margin-top: 3%;
            margin-bottom: 3%;
            padding-top: 3%;
            padding-bottom: 3%;
        }

        .space {
            margin-top: 1%;
            margin-bottom: 1%;
            padding-top: 1%;
            padding-bottom: 1%;
        }
    </style>
</head>

<body ng-controller="principalLoginController">

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
                </ul>
            </div>

        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-lg-3"></div>
            <div class="col-lg-6 login-container">
                <div class="panel panel-warning">
                    <div class="panel-heading text-center">
                        <h3>Inicio de Sesion</h3>
                    </div>
                    <div class="form-group space">
                        <label for="">Email</label>
                        <input ng-model="usuario.email" type="text" class="form-control" placeholder="Email">
                    </div>
                    <div class="form-group space">
                        <label for="">Contraseña</label>
                        <input ng-model="usuario.password" type="password" class="form-control"
                            placeholder="Contraseña">
                    </div>
                    <div class="space">
                        <button class="btn btn-block btn-lg btn-success" ng-click="doLogin()">Iniciar</button>
                    </div>
                </div>
                <!--
                <div class="text-center">
                    <h3>Inicio de Sesion</h3>
                </div>
                <div class="form-group">
                    <label for="">Email</label>
                    <input ng-model="usuario.email" type="text" class="form-control" placeholder="Email">
                </div>
                <div class="form-group">
                    <label for="">Contraseña</label>
                    <input ng-model="usuario.password" type="password" class="form-control" placeholder="Contraseña">
                </div>
                <button class="btn btn-block btn-success" ng-click="doLogin()">Iniciar</button>
            </div>
        -->
                <div class="col-lg-6"></div>
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

        <script src="js/Modulo4/login.js"></script>
</body>

</html>