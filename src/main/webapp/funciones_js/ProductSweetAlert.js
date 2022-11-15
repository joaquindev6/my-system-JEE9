const formProducts = document.getElementById("formProducts");

formProducts.addEventListener("submit", function (e) {
    e.preventDefault();
    let datos = new FormData(formProducts);

    let value = true;
    if (datos.get("delete") <= 0) {
        formProducts.submit();
        value = false;
    }

    if (value) {
        Swal.fire({
            title: '¿Está seguro de eliminar el producto seleccionado?',
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
                    formProducts.submit();
                }, 1500);
            }
        });
    }
});