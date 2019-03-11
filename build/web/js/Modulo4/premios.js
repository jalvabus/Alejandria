
let app = angular.module('premiosApp', []);

app.controller('premiosController', ($scope, $http) => {

    $scope.usuario = {};
    $scope.mispremios = {};

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

    $scope.premios = [];
    $scope.getPremios = function () {
        var action = 'getAll';
        $http({
            method: 'POST',
            url: 'premios',
            data: 'action=' + action,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        }).then((response, err) => {
            if (err) {
                return console.log(err);
            }
            let data = response.data;
            console.log(data);
            $scope.premios = response.data;
        });
    }

    $scope.premioCanje = {};
    $scope.saldoUsuario = {};
    $scope.validateSaldo = true;

    $scope.canjearPremio = function (premio) {
        var action = 'getPuntos';
        $scope.premioCanje = premio;
        $http({
            method: 'POST',
            url: 'usuario',
            data: 'action=' + action,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        }).then((response, err) => {
            console.log(response);
            $scope.saldoUsuario = response.data;
            if (Number(response.data.puntos) < Number(premio.costo_puntos)) {
                swal({
                    title: "No puedes canjear este premio!",
                    text: "No cuenta con los puntos suficientes",
                    icon: "error",
                    button: "Aceptar"
                });
                $scope.validateSaldo = false;
            }
        })
    }

    $scope.canjearPremios = function () {
        console.log($scope.premioCanje.costo_puntos);
        console.log($scope.cantidad);
        console.log($scope.saldoUsuario.puntoss);
        
        if (Number($scope.premioCanje.stock) < Number($scope.cantidad)) {
            return swal({
                title: "No puedes canjear este premio!",
                text: "No se cuenta con el stock suficientes",
                icon: "error",
                button: "Aceptar"
            });
        }
        if ((Number($scope.premioCanje.costo_puntos) * Number($scope.cantidad)) > Number($scope.saldoUsuario.puntos)) {
            swal({
                title: "No puedes canjear este premio!",
                text: "No cuenta con los puntos suficientes",
                icon: "error",
                button: "Aceptar"
            });
            $scope.validateSaldo = false;
        } else {
            $http({
                method: 'POST',
                url: 'premios',
                data: 'action=canjearPremio&id_premio=' + $scope.premioCanje.id_premio + '&cantidad=' + $scope.cantidad,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then((err, response) => {
                console.log(response);
                $scope.getMisPremios();
            })
            swal({
                title: "Premio canjeado!",
                text: "Ve a la seccion de tus premios",
                icon: "success",
                button: "Aceptar"
            });
        }
    }
    $scope.getMisPremios = function () {
        var action = 'getPremiosByUser';
        $http({
            method: 'POST',
            url: 'premios',
            data: 'action=' + action,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        }).then((response, err) => {
            if (err) {
                return console.log(err);
            }
            let data = response.data;
            console.log(data);
            $scope.mispremios = response.data;
        });
    }

    $scope.getPremios();
    $scope.getMisPremios();
})