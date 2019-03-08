/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let app = angular.module('principalLoginApp', []);

app.controller('principalLoginController', ($scope, $http) => {

    $scope.usuario = {};

    $scope.doLogin = function () {
        $http({
            method: 'POST',
            url: 'auth',
            data: 'action=login&usuario=' + $scope.usuario.email + "&password=" + $scope.usuario.password,
            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
        }).then((response, err) => {
            if (err) {
                console.log(err)
            } else {
                var data = response.data;
                console.log(response);
                if (data.tipo) {
                    if (String(data.tipo) === "usuario") {
                        location.replace('indexUser.jsp');
                    }
                } else {
                    console.log("NO se c")
                }
            }
        })
    }
})


