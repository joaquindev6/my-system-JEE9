const formSaveCategory = document.getElementById("formSaveCategory");

formSaveCategory.addEventListener("submit", function (event) {
    event.preventDefault();
    const formData = new FormData(formSaveCategory);
    let value = true;
    if (formData.get("name") === "") {
        formSaveCategory.submit();
        value = false;
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
                    formSaveCategory.submit();
                }, 1500);
            }
        });
    }
});