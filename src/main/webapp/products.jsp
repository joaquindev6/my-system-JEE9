<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Productos</title>
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
                        <a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/inicio/view-index">Inicio</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Productos
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos/view-products">Lista de Productos</a></li>
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
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/usuarios/view-users">Lista de Usuarios</a></li>
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
        <div class="card mt-4 shadow">
            <div class="card-header">
                <h4>Lista de Productos</h4>
            </div>
            <div class="card-body m-3">
                <div class="row">
                    <form action="">
                        <div class="col-6">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Buscar por nombre">
                                <button class="btn btn-outline-secondary" type="submit">Buscar</button>
                                <button class="btn btn-outline-secondary" type="submit">Limpiar</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="row overflow-hidden">
                    <table class="table table-hover">
                        <thead>
                        <tr class="text-center">
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Categoría</th>
                            <th>Precio</th>
                            <th>Stock</th>
                            <th>Editar</th>
                            <th>Eliminar</th>
                        </tr>
                        </thead>
                        <tbody>
                        <form action="">
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>
                                    <div class="d-flex">
                                        <div class="mx-auto">
                                            <input type="submit" class="btn btn-success" value="Editar"/>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="form-check m-1 d-flex justify-content-center align-items-center">
                                        <input class="form-check-input " type="checkbox" value=""/>
                                    </div>
                                </td>
                            </tr>
                        </form>
                        </tbody>
                    </table>
                </div>
                <div class="row justify-content-end">
                    <div class="col-12 overflow-hidden">
                        <form action="" class="mb-0">
                            <div class="d-flex justify-content-end">
                                <input type="submit" class="btn btn-primary me-2" value="Nuevo Producto"/>
                                <input type="submit" class="btn btn-danger ms-1" value="Eliminar"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
