/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let app = angular.module('wishListApp', []);

app.controller('wishListController', ($scope, $http) => {

    $scope.usuario = {};
    $scope.wishlist = {};
    $scope.listlibros = {};
    $scope.sharedwishlist = {};
    
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

    $scope.getListLibros();

    $scope.getWishList = function () {
        var action = 'getWishlist';
        $http(
            {
                method: 'POST',
                url: 'wishlist',
                data: 'action=' + action,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                console.log(response);
                $scope.wishlist = response.data;
            }, function errorCallback(response) {
                console.log(response);
            });
    };

    $scope.getWishList();

    $scope.addToWishlist = function (id_libro) {
        var action = 'addToWishlist';
        if ($scope.wishlist.libros) {
            if (Number($scope.wishlist.libros.length) <= 10) {
                $http({
                    method: 'POST',
                    url: 'wishlist',
                    data: 'action=' + action + '&id_libro=' + id_libro,
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
                }).then(function successCallback(response) {
                    console.log(response);
                    swal({
                        title: "Agreagado a tu lista",
                        text: "Se agrego el libro a tu listas",
                        icon: "success",
                        button: "Aceptar"
                    });
                    $scope.wishlist = response.data;
                    $scope.getWishList();
                }, function errorCallback(response) {
                    console.log(response);
                });
            } else {
                swal('Oops !', "No puede agregar a la wishlist, el maximo son 10 libros", 'error');
            }
        } else {
            $http({
                method: 'POST',
                url: 'wishlist',
                data: 'action=' + action + '&id_libro=' + id_libro,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                console.log(response);
                swal({
                    title: "Agreagado a tu lista",
                    text: "Se agrego el libro a tu listas",
                    icon: "success",
                    button: "Aceptar"
                });
                $scope.wishlist = response.data;
                $scope.getWishList();
            }, function errorCallback(response) {
                console.log(response);
            });
        }
    };

    $scope.updateFavsWishlist = function (libro, status) {
        var action = 'updateFavsWishlist';
        $http({
            method: 'POST',
            url: 'wishlist',
            data: 'action=' + action + '&id_libro=' + libro.id_libro + '&favorito=' + status,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        }).then(function successCallback(response) {
            console.log(response);
            if (status) {
                swal({
                    title: "Agreagado a tus favoritos",
                    text: "Se agrego el libro a tu lista de favoritos",
                    icon: "success",
                    button: "Aceptar"
                });
            } else {
                swal({
                    title: "Eliminado de tus favorito",
                    text: "Se removio el libro a tu lista de favoritos",
                    icon: "warning",
                    button: "Aceptar"
                });
            }

            $scope.wishlist = response.data;
            $scope.getWishList();
        }, function errorCallback(response) {
            console.log(response);
        });
    }

    $scope.removeToWishList = function (libro) {

        swal({
            title: "Eliminar libro: " + libro.nombre + "?",
            text: "Se eliminara el libro de tu wish list !",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        }).then((willDelete) => {
            if (willDelete) {
                var action = 'removeToWishlist';
                $http({
                    method: 'POST',
                    url: 'wishlist',
                    data: 'action=' + action + '&id_libro=' + libro.id_libro,
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
                }).then(function successCallback(response) {
                    console.log(response);
                    swal({
                        title: "Removido de tu lista",
                        text: "Se removio el libro de tu lista",
                        icon: "success",
                        button: "Aceptar"
                    });
                    $scope.getWishList();
                }, function errorCallback(response) {
                    console.log(response);
                });
            }
        });

    };

    $scope.shareWishlist = function () {
        var action = 'shareWishlist';
        if (Number($scope.wishlist.libros.length) > 10) {
            swal('Oops !', "No puede compartir la wishlist, debe tener 10 libros", 'error');
        } else {
            $http({
                method: 'POST',
                url: 'wishlist',
                data: 'action=' + action + '&correo=' + $scope.share.correo,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                console.log(response);
                if (response.data === 'success') {
                    swal('Finalizado !', 'Se ha compartido exitosamente tu lista de deseos con ' + $scope.share.correo + '.', 'success');
                } else {
                    swal('Oops !', response.data, 'error');
                }
            }, function errorCallback(response) {
                console.log(response);
            });
        }
    };

    $scope.getSharedWishList = function () {
        var action = 'getSharedWishlist';
        $http(
                {
                    method: 'POST',
                    url: 'wishlist',
                    data: 'action=' + action,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
                }).then(function successCallback(response) {
            console.log(response);
            $scope.sharedwishlist = response.data;
        }, function errorCallback(response) {
            console.log(response);
        });
    };

    $scope.getSharedWishList();

    $scope.mislibros = [];

    $scope.getBooksBuy = function () {
        var action = 'getLibrosComprados';
        $http({
            method: 'POST',
            url: 'libro',
            data: 'action=' + action,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        }).then(function successCallback(response) {
            console.log("Mis libros comprados");
            console.log(response);
            console.log("Mis libros comprados");
            $scope.mislibros = response.data;
        }, function errorCallback(response) {
            console.log(response);
        });
    };

})



