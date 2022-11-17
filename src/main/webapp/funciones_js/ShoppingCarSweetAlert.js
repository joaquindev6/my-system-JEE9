const formShoppingCar = document.getElementById("formShoppingCar");

formShoppingCar.addEventListener("submit", function (event) {
    event.preventDefault();
    Swal.fire({
        title: '¿Está seguro de finalizar la compra?',
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
                title: 'Procesando la compra',
                showConfirmButton: false,
                timer: 1500
            });
            setTimeout(function () {
                formShoppingCar.submit();
            }, 1500);
        }
    });
});