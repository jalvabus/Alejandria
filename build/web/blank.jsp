<%-- 
    Document   : blank
    Created on : 8/03/2019, 10:18:50 AM
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="nameApp">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title Page</title>

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

<!-- PALIZADA -->
<!-- Quita las directivas ng-contoller y ng-app -->

<body ng-controller="nameAppController">
    <div class="container">
        <h1>Welcome {{ usuario.persona.nombre }}</h1>

        <button ng-click="logout()">Cerrar Sesion</button>

        <!-- PALIZADA -->
        <!-- Cerrar sesion con javascript normal -->
        <button on-click="logout()">Cerrar Sesion</button>
    </div>

    <!-- SCRIPTS -->
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/particles.min.js"></script>
    <script src="js/app.js"></script>
    <script src="js/jquery.parallax.js"></script>
    <script src="js/smoothscroll.js"></script>
    <script src="js/custom.js"></script>

    <!-- PALIZADA -->
    <!-- SCRIPT PARA VALIDAR EL LOGIN -->
    <!-- LO PUEDES AGREGAN EN LOS JSP -->
    <script src="js/Modulo4/validateLoginLogout.js"></script>

    <!-- Esto es para el zavala y para mi -->
    <script>
        let app = angular.module('nameApp', []);

        app.controller('nameAppController', ($scope, $http) => {

            $scope.usuario = {};

            $scope.logout = function () {
                $http({
                    method: 'POST',
                    url: 'auth',
                    data: 'action=logout',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
                }).then((response, err) => {
                    if (err) {
                        return console.log(err);
                    }
                    let data = response.data;
                    console.log(data);
                    location.replace("login.jsp");
                });
            }
            $scope.validateLogin = function () {
                let params = "?action=authlogin";
                $http({
                    method: 'POST',
                    url: 'auth',
                    data: 'action=authlogin',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
                }).then((response, err) => {
                    if (err) {
                        return console.log(err);
                    }
                    let data = response.data;
                    console.log(data);
                    if (data.persona) {
                        $scope.usuario = data;
                    } else {
                        location.replace("login.jsp");
                    }
                });
            };

            $scope.validateLogin();
        })
    </script>
</body>

</html>