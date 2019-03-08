/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let app = angular.module('wishListApp', []);

app.controller('wishListController', ($scope, $http) => {

    $scope.usuario = {};

     $scope.validateLogin = function () {
        let params = "?action=authlogin";
        $http({
            method: 'POST',
            url: 'auth',
            data: 'action=authlogin',
            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
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



