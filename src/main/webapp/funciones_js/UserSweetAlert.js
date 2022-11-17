const formUser = document.getElementById("formUser");

formUser.addEventListener("submit", function (event) {
    event.preventDefault();
    const formData = new FormData(formUser);
    let value = true;
    if (formData.get("delete") <= 0) {
        formUser.submit();
        value = false;
    }
    if (value) {
        Swal.fire({
            title: '¿Está seguro de eliminar el usuario seleccionado?',
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