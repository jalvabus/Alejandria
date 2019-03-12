/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('compraApp', []);

app.controller('compraController', ($scope, $http) => {
    $scope.listlibros = {};
    $scope.usuario = {};
    $scope.carrito = {};
    $scope.tipo_pago = '';
    
    $scope.validateLogin = function () {
        var params = "?action=authlogin";
        $http({
            method: 'POST',
            url: 'auth',
            data: 'action=authlogin',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        }).then((response, err) => {
            if (err) {
                return console.log(err);
            }
            var data = response.data;
            console.log(data);
            if (data.persona) {
                $scope.usuario = data;
            } else {
                location.replace("login.jsp");
            }
        });
    };

    $scope.logout = function () {
        let params = "?action=logout";
        $http({
            method: 'POST',
            url: 'auth' + params
        }).then((response, err) => {
            if (err) {
                return console.log(err);
            }
            console.log(response.data);
            $scope.usuario = response.data;
            location.replace("login.jsp");
        });
    };

    $scope.getListLibros = function () {
        var action = 'getLibros';
        $http(
            {
                method: 'POST',
                url: 'libro',
                data: 'action=' + action,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                console.log(response);
                $scope.listlibros = response.data;
            }, function errorCallback(response) {
                console.log(response);
            });
    };
    
    $scope.addLibroInCarrito = function(libro) {
        var action = 'addLibroInCarrito';
        $http(
            {
                method: 'POST',
                url: 'carrito',
                data: 'action=' + action + '&id_libro=' + libro.id_libro + '&nombre=' + libro.nombre + '&costo=' + libro.costo,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                $scope.openCarrito();
            }, function errorCallback(response) {
                console.log(response);
            });
    }
    
    $scope.removeLibroInCarrito = function(index) {
        var action = 'removeLibroInCarrito';
        $http(
            {
                method: 'POST',
                url: 'carrito',
                data: 'action=' + action + '&index=' + index,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                $scope.openCarrito();
            }, function errorCallback(response) {
                console.log(response);
            });
    }
    
    $scope.dropCarrito = function(index) {
        var action = 'dropCarrito';
        $http(
            {
                method: 'POST',
                url: 'carrito',
                data: 'action=' + action,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                $scope.openCarrito();
            }, function errorCallback(response) {
                console.log(response);
            });
    }
    
    
    
    $scope.getCarrito = function() {
        var action = 'getCarrito';
        $http(
            {
                method: 'GET',
                url: 'carrito?action=' + action,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                console.log(response);
                $scope.carrito = response.data;
            }, function errorCallback(response) {
                console.log(response);
            });
    }
    
    $scope.openCarrito = function() {
        $('#modal_carrito').modal('show');
        $scope.getCarrito();
    }
    
    $scope.pago = {
      tipo: 'saldo'  
    };
    
    $scope.comprar = function() {
        console.log($scope.pago.tipo);
        if($scope.pago.tipo == 'saldo'){
            $scope.comprarBySaldo($('#dir').val());
        }else{
            $scope.comprarByTarjeta($('#dir').val());
        }
    }
    
    $scope.showContainerTarjeta = function(mode) {
        if(mode) {
            $('#container_tarjeta').show();
        } else {
            $('#container_tarjeta').hide();
        }
    }
    
    $scope.comprarBySaldo = function(dir_envio) {
        var action = 'comprarBySaldo';
        $http(
            {
                method: 'POST',
                url: 'carrito?action=' + action + '&dir_envio=' + dir_envio,
                data: 'action=' + action,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                console.log(response);
                
                if(response.data === 'succes') {
                     swal("", "Compra realizalada con exito", "success");
                     $('#modal_carrito').modal('hide');
                } else if(response.data === 'menor') {
                      swal("Oops!", "No cuentas con el saldo suficiente", "error");
                }
                
            }, function errorCallback(response) {
                console.log(response);
            });
    }
    
    $scope.comprarByTarjeta = function() {
        var numero = $('#numero').val();
        var cvv = $('#cvv').val();
        var vigencia = $('#vigencia').val();
        var dir = $('#dir').val();
        
        console.log(numero + ' - ' + cvv + ' - ' + vigencia);
        
        if(numero != '' && cvv != '' && vigencia != ''){
            if (numero.length > 19 || numero.length <19) {
                swal("Oops!", "Numero de tarjeta invalido", "warning");
            }else{
                
                var action = 'comprarByTarjeta';
            $http(
            {
                method: 'POST',
                url: 'carrito?action=' + action + '&dir_envio=' + dir + '&numero=' + numero + '&cvv=' + cvv,
                data: 'action=' + action,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                console.log(response);
                
                if(response.data === 'succes') {
                     swal("", "Compra realizalada con exito", "success");
                     $('#modal_carrito').modal('hide');
                } else if(response.data === 'menor') {
                      swal("Oops!", "No cuentas con el saldo suficiente", "error");
                } else if(response.data === 'null') {
                      swal("Oops!", "No se encontre ninguna tarjeta con los datos proporcionados", "error");
                }
                
            }, function errorCallback(response) {
                console.log(response);
            });
                
                
                
            }
        }else{
            swal("Oops!", "Completa todos los campos", "warning");
        }
        var action = 'comprarByTarjeta';
       
    }
    
    //$scope.comprarBySaldo();

    $scope.validateLogin();
    $scope.getListLibros();
})



