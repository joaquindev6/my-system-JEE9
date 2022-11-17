const formUser = document.getElementById("formUser");

formUser.addEventListener("submit", function (event) {
    event.preventDefault();
    const datos = new FormData(formUser);
    let value = true;
    if (datos.get("names") === "" || datos.get("lastNames") === "" || datos.get("age") === ""
        || datos.get("sex") === "" || datos.get("country") === "" || datos.get("role") === ""
        || datos.get("username") === "" || datos.get("password") === "") {
        formUser.submit();
        value = false;
    } else {
        if (datos.get("age") <= 0) {
            formUser.submit();
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
                    title: 'Datos guardados',
                    showConfirmButton: false,
                    timer: 1500
                });
                setTimeout(function () {
                    formUser.submit();
                }, 1500);
            }
        });
    }
});