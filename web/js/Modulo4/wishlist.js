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
    $scope.sharedwishlistbyusuario = {};
    $scope.libro = {};
    $scope.wishListCompra = [];

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

    $scope.shareW = {};
    $scope.shareWishlistMasivo = function () {
        var action = 'shareWishlistMasivo';
        if (Number($scope.wishlist.libros.length) > 10) {
            swal('Oops !', "No puede compartir la wishlist, debe tener 10 libros", 'error');
        } else {
            $http({
                method: 'POST',
                url: 'wishlist',
                data: 'action=' + action 
                + '&correo1=' + $scope.shareW.correo1
                + '&correo2=' + $scope.shareW.correo2
                + '&correo3=' + $scope.shareW.correo3
                + '&correo4=' + $scope.shareW.correo4
                + '&correo5=' + $scope.shareW.correo5,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                console.log(response);
                if (response.data === 'success') {
                    swal('Finalizado !', 'Se han invitado exitosamente a tu lista de deseos', 'success');
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
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                console.log(response);
                $scope.sharedwishlist = response.data;
            }, function errorCallback(response) {
                console.log(response);
            });
    };

    $scope.thisWish = function (wish) {
        $scope.sharedwishlistbyusuario = wish;
        console.log(wish);
    }

    $scope.updtaeAliasSharedWishlist = function (id_compartir) {
        var action = 'updateAliasSharedWishlist';
        $http({
            method: 'POST',
            url: 'wishlist',
            data: 'action=' + action + '&id_compartir=' + id_compartir + '&alias=' + $scope.aliasSharedWishlist,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        }).then(function successCallback(response) {
            $scope.getSharedWishList();
            swal('Finalizado !', 'Se ha actualizado el alias para su lista de deseos.', 'success');
            $scope.aliasSharedWishlist = '';
        }, function errorCallback(response) {
            console.log(response);
        });
    }

    $scope.comprarLibro = function (libro, wish) {
        $scope.libro = libro;
        $scope.tipopago = 'prepago';
        $scope.wishListCompra = wish;
    }

    $scope.tarjeta_prepago = {};
    $scope.datosRegalo = {};
    $scope.verificacionTarjeta = false;
    $scope.getDatosTarjeta = function () {

        let action = "getDatosTarjeta";

        $http({
            method: 'POST',
            url: 'usuario',
            data: 'action=' + action + "&numero=" + $scope.tarjeta_prepago.numero,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        }).then((response, err) => {
            console.log(response);
            $scope.tarjeta_prepago = response.data;
            var data = response.data;
            if (data.saldo) {
                $scope.saldo = data.saldo;
                $scope.tarjeta = data.codigo_tarjeta;
                if (data.saldo < ($scope.libro.costo + ($scope.libro.costo * 0.16) + 150)) {
                    swal({
                        title: "No centa con saldo suficiente!",
                        text: "",
                        icon: "warning",
                        button: "Ok!"
                    });
                    $scope.verificacionTarjeta = false;
                } else {
                    swal({
                        title: "Verifique sus datos y confirme la compra!",
                        text: "¿Datos correctos?",
                        icon: "warning",
                        button: "Ok!"
                    });
                    $scope.verificacionTarjeta = true;
                }
            } else {
                swal({
                    title: "Error en la tajeta!",
                    text: "No se encontraron resultados!",
                    icon: "warning",
                    button: "Ok!"
                });
                $scope.verificacionTarjeta = false;
            }

        })
    }

    $scope.reset = function () {
        $scope.tarjeta_prepago = {};
        $scope.datosRegalo = {};
        $scope.tipoPago = 'puntos';
        $scope.verificacionTarjeta = false;

        $scope.getBooksBuy();
        $scope.getListLibros();
    }

    $scope.compraLibro = function (libro) {
        var action = 'comprarLibro';
        var puntos = parseInt(libro.costo / 10);
        var total = libro.costo + (libro.costo * 0.16) + 150;
        console.log($scope.wishListCompra);

        $http({
            method: 'POST',
            url: 'usuario',
            data: 'action=' + action
                + '&numero=' + $scope.tarjeta_prepago.numero
                + '&id_libro=' + libro.id_libro
                + '&puntos=' + puntos
                + "&total=" + total + '&id_wish_list=' + $scope.wishListCompra.usuario.id_Usuario,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        }).then((response, err) => {
            console.log(response);

            swal({
                title: "Has comprado el libro " + libro.nombre,
                text: "Ve a la seccion de tus compras",
                icon: "success",
                button: "Aceptar"
            });

            $scope.getBooksBuy();
            $scope.getListLibros();
        })

    }

    $scope.getPuntos = function () {
        let e = document.getElementById("tipopago");
        let tipoPago = e.options[e.selectedIndex].value;
        $scope.tipopago = tipoPago;

        var action = 'getPuntos';

        if ($scope.tipopago == 'puntos') {
            $http({
                method: 'POST',
                url: 'usuario',
                data: 'action=' + action,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then((response, err) => {
                console.log(response);
            })
        }
    }

    $scope.seeBook = function (libro) {
        $scope.libro = libro;
    }

    $scope.mislibros = [];
    $scope.compras = [];
    $scope.getBooksBuy = function () {
        $scope.mislibros = [];
        $scope.compras = [];
        var action = 'getLibrosComprados';
        $http({
            method: 'POST',
            url: 'libro',
            data: 'action=' + action,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        }).then(function successCallback(response) {
            console.log(response.data);
            var array = response.data;
            $scope.compras = response.data;
            array.forEach((compra) => {
                $scope.mislibros.push(compra.detalle_compra.libro);
            })

        }, function errorCallback(response) {
            console.log(response);
        });
    };

    $scope.bucarWish = function () {

        var action = 'getSharedWishlistByAlias';
        $http(
            {
                method: 'POST',
                url: 'wishlist',
                data: 'action=' + action + '&alias=' + $scope.buscador,
                headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
            }).then(function successCallback(response) {
                console.log(response);
                $scope.sharedwishlist = response.data;
            }, function errorCallback(response) {
                console.log(response);
            });
    }

    $scope.getBooksBuy();
    $scope.validateLogin();
    $scope.getListLibros();
    $scope.getSharedWishList();

    $scope.whatClassIsIt = function (libro) {
        let cont = 0;
        $scope.mislibros.forEach((librito) => {
            if (libro.nombre === librito.nombre)
                cont++;
        });
        if (cont > 0) {
            return "list-group list-group-item border-red";
        } else {
            return "list-group list-group-item";
        }
    };

    $scope.detalles_compra = {};
    $scope.detailsCompta = function (detalles) {
        $scope.detalles_compra = detalles;
    }
})



