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
            border: 3px solid gray;
            margin: 3%;
            padding: 3%;
            border-radius: 10%;
        }
    </style>
</head>

<body ng-controller="principalLoginController">
    <div class="container">
        <div class="row">
            <div class="col-lg-4"></div>
            <div class="col-lg-4 login-container">
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
            <div class="col-lg-4"></div>
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