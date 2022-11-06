<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Inicio</title>
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
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos/data-show">Lista de
                            Productos</a></li>
                        <li><a class="dropdown-item"
                               href="<%=request.getContextPath()%>/productos/formulario/save">Registro de
                            Productos</a>
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
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/usuarios/data-show">Lista de Usuarios</a>
                        </li>
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/usuarios/formulario/save">Registro
                            de Usuarios</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Login
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="<%=request.getContextPath()%>/sesion/login">Iniciar
                            Sesión</a></li>
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
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>
