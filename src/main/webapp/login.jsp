<%@ page import="com.jfarro.app.models.ItemShoppingCar" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Long idUser = (Long)session.getAttribute("idUser");
    List<ItemShoppingCar> items = (List<ItemShoppingCar>) request.getSession().getAttribute("listItems");

//    //Validaciones
//    String denied = (String) request.getAttribute("denied") != null ? (String) request.getAttribute("denied") : "";
%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Sesión</title>
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
                    <% if (idUser != null) { %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Productos</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos/data-show">Lista de Productos</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos/formulario/save">Registro de Productos</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/categoria-producto/data-show">Lista de Categorías</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/categoria-producto/formulario/save">Registro de Categorías</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">Usuarios</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/usuarios/data-show">Lista de Usuarios</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/usuarios/formulario/save">Registro de Usuarios</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/carro-compra/data-show">(<%= items != null ? items.size() : 0 %>)Carrito</a>
                    </li>
                    <% } %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">Login</a>
                        <ul class="dropdown-menu">
                            <% if (idUser == null) { %>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/sesion/login">Iniciar Sesión</a></li>
                            <% } %>
                            <% if (idUser != null) { %>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/sesion/logout">Salir</a></li>
                            <% } %>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <% if (request.getAttribute("valiLogin") != null) { %>
        <div class="alert alert-danger mt-4" role="alert">
            <%= request.getAttribute("valiLogin") %>
        </div>
        <% } %>
        <div class="row justify-content-center"> <!-- para centrar en la pantalla d-flex justify-content-center align-items-center vh-100  -->
            <div class="col-4">
                <div class="card mt-4 shadow">
                    <div class="card-header text-center">
                        <h4>Inicio de Sesión</h4>
                    </div>
                    <form id="formLogin" class="mb-0" action="<%=request.getContextPath()%>/sesion/login" method="post">
                        <div class="card-body m-3">
                            <div class="row">
                                <div class="mb-3 col-12">
                                    <label for="username" class="form-label">Nombre de usuario:</label>
                                    <input type="text" class="form-control" id="username" name="username"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="mb-3 col-12">
                                    <label for="password" class="form-label">Contraseña:</label>
                                    <input type="password" class="form-control" id="password" name="password"/>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer text-center">
                            <input type="submit" class="btn btn-primary px-5 m-2" id="ingresar" value="Ingresar"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/funciones_js/LoginSweetAlert.js"></script>
<%--    <% if (denied.equals("denied")) { %>--%>
<%--    <script>deniedSession();</script>--%>
<%--    <% } %>--%>
</body>
</html>
