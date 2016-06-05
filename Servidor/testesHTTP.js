function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        console.log("hu3")
    };
    xhttp.open("DELETE", "http://localhost:3000/v1/clientes/2", true);
    xhttp.send();
}
