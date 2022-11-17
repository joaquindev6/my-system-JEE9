const formProductCategory = document.getElementById("formProductCategory");

formProductCategory.addEventListener("submit", function (event) {
    event.preventDefault();
    let datos = new FormData(formProductCategory);

    let value = true;
    if (datos.get("delete") <= 0) {
        formProductCategory.submit();
        value = false;
    }

    if (value) {
        Swal.fire({
            title: '¿Está seguro de eliminar la categoría seleccionada?',
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
                    title: 'Registro eliminado',
                    showConfirmButton: false,
                    timer: 1500
                });
                setTimeout(function () {
                    formProductCategory.submit();
                }, 1500);
            }
        });
    }
});