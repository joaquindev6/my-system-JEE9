const formLogin = document.getElementById("formLogin");

formLogin.addEventListener("submit", function(e) {
    e.preventDefault();
    const formData = new FormData(formLogin);
    let value = true;
    if (formData.get("username") === "" || formData.get("password") === "") {
        formLogin.submit();
        value = false;
    }
    if (value) {
        Swal.fire({
            icon: 'success',
            title: 'Iniciando sesión...',
            showConfirmButton: false,
            timer: 1500
        });
        setTimeout(function () {
            formLogin.submit();
        }, 1500);
    }
});


/*function deniedSession() {
        Swal.fire({
            icon: 'error',
            title: 'Usuario o contraseña incorrecta',
            text: 'Por favor vuelve a intentarlo.',
            confirmButtonText: 'Ok'
        });
    }

    function successSession() {
        // setTimeout(function () {
        //     location.href = url;
        // }, 1500);
        Swal.fire({
            icon: 'success',
            title: 'Iniciando sesión...',
            showConfirmButton: false,
            timer: 1500
        });
    }*/