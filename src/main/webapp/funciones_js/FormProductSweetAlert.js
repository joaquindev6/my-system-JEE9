const formulario = document.getElementById("formulario");

formulario.addEventListener("submit", function (e) {
    e.preventDefault();
    let datos = new FormData(formulario);

    let value = true;
    if (datos.get("name") === "" || datos.get("category") === "" || datos.get("amount") === ""
        || datos.get("price") === "" || datos.get("id") === "") {
        formulario.submit();
        value = false;
    } else {
        if (datos.get("price") <= 0 || datos.get("amount") <= 0) {
            formulario.submit();
            value = false
        }
    }

    if (value) {
        Swal.fire({
            title: '¿Está seguro de guardar los datos ingresados?',
            icon: 'question',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Aceptar',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                Swal.fire({
                    icon: 'success',
                    title: 'Registro exitoso',
                    showConfirmButton: false,
                    timer: 1500
                });
                setTimeout(function () {
                    formulario.submit();
                }, 1500);
            }
        });
    }
});

//const url = "/my-system/productos/formulario/save?name=" + datos.get("name")
//     + "&category=" + datos.get("category")
//     + "&amount=" + datos.get("amount")
//     + "&price=" + datos.get("price")
//     + "&id=" + datos.get("id");
// fetch(url, {
//     method: "POST"
// });