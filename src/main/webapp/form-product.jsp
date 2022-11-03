<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Nuevo Producto</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/style.css"/>
</head>
<body>
    <nav class="navbar navbar-expand-lg bg-light">
    <div class="container">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/">MiSystem</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto"> <!-- ms-auto para moverlo a la derecha -->
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/inicio">Inicio</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Productos
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos">Lista de Productos</a></li>
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos/formulario/view-form">Registro de Productos</a>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/">Options</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Usuarios
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/usuarios">Lista de Usuarios</a></li>
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/usuarios/formulario/view-form">Registro de Usuarios</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Login
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/sesion/login">Iniciar Sesión</a></li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/">Salir</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
    <div class="container">
        <div class="row">
            <div class="col-3 home">
                <a href="<%=request.getContextPath()%>/productos">
                    <i class="fa-solid fa-warehouse"></i>
                </a>
                <a class="enlace" href="<%=request.getContextPath()%>/productos">Volver a productos</a>
            </div>
        </div>
        <div class="row justify-content-center"> <!-- para centrar en la pantalla d-flex justify-content-center align-items-center vh-100  -->
            <div class="col-7">
                <div class="card mt-4 shadow">
                    <div class="card-header">
                        <h4>Registro de Producto</h4>
                    </div>
                    <form class="mb-0" action="">
                        <div class="card-body m-3">
                            <div class="row">
                                <div class="mb-3 col-12">
                                    <label for="name" class="form-label">Nombre del producto:</label>
                                    <input type="text" class="form-control" id="name" name="name"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="mb-3 col-12">
                                    <label class="form-label">Categoría:</label>
                                    <select class="form-select" name="category">
                                        <option value="">-Seleccionar-</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row g-2">
                                <div class="col">
                                    <label for="amount" class="form-label">Cantidad:</label>
                                    <input type="number" class="form-control" id="amount" name="amount"/>
                                </div>
                                <div class="col">
                                    <label for="price" class="form-label">Precio:</label>
                                    <input type="text" class="form-control" id="price" name="price"/>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer text-end">
                            <input type="submit" class="btn btn-primary" value="Guardar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/82ec21a6d1.js" crossorigin="anonymous"></script>
</body>
</html>
