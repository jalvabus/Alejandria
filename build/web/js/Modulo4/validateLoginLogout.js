/**
 * En este archivo se realiza la validacion del login con javascript
 * Y se incluye una funcion para el logout (cierre de sesion)
 * Solo se incluye en los jsp en el header
 * 
 * Agregar Jquery antes de este archivo para la peticion
 * 
 * Para el logou se llama la funcion en un evento on-click="logout()"
 */
function logout() {
    window.localStorage.clear();
    location.replace('login.jsp');
}

function validateLogin() {
    var usuario = window.localStorage.getItem("user");
    if (usuario != "logueado") {
        location.replace('login.jsp');
    }
}

validateLogin();