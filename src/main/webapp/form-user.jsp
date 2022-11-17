<%@ page import="java.util.Map" %>
<%@ page import="com.jfarro.app.models.User" %>
<%@ page import="com.jfarro.app.models.ItemShoppingCar" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Long idUser = (Long)session.getAttribute("idUser");
    List<ItemShoppingCar> items = (List<ItemShoppingCar>) request.getSession().getAttribute("listItems");
    Map<String, String> errors = (Map<String, String>) request.getAttribute("userErrors");
    User user = (User) request.getAttribute("user");

    String names = "";
    String lastNames = "";
    Integer age = 0;
    String sex = "";
    String country = "";
    String role = "";
    String username = "";
    String password = "";
    if (user != null) {
        names = user.getNames() != null ? user.getNames() : "";
        lastNames = user.getLastNames() != null ? user.getLastNames() : "";
        age = user.getAge() != null ? user.getAge() : 0;
        sex = user.getSex() != null ? user.getSex() : "";
        country = user.getCountry() != null ? user.getCountry() : "";
        role = user.getRole() != null ? user.getRole() : "";
        username = user.getUsername() != null ? user.getUsername() : "";
        password = user.getPassword() != null ? user.getPassword() : "";
    }
%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Nuevo Usuario</title>
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
        <div class="row">
            <div class="col-3 home">
                <a href="<%=request.getContextPath()%>/usuarios/data-show">
                    <i class="fa-solid fa-house-user"></i>
                </a>
                <a class="enlace" href="<%=request.getContextPath()%>/usuarios">Volver a usuarios</a>
            </div>
        </div>
        <div class="row justify-content-center"> <!-- para centrar en la pantalla d-flex justify-content-center align-items-center vh-100  -->
            <div class="col-7">
                <% if (errors != null) { %>
                <div class="alert alert-danger" role="alert">
                    <ul>
                        <%= errors.containsKey("namesError") ? "<li>" + errors.get("namesError") + "</li>" : "" %>
                        <%= errors.containsKey("lastNamesError") ? "<li>" + errors.get("lastNamesError") + "</li>" : "" %>
                        <%= errors.containsKey("ageError") ? "<li>" + errors.get("ageError") + "</li>" : "" %>
                        <%= errors.containsKey("sexError") ? "<li>" + errors.get("sexError") + "</li>" : "" %>
                        <%= errors.containsKey("countryError") ? "<li>" + errors.get("countryError") + "</li>" : "" %>
                        <%= errors.containsKey("roleError") ? "<li>" + errors.get("roleError") + "</li>" : "" %>
                        <%= errors.containsKey("usernameError") ? "<li>" + errors.get("usernameError") + "</li>" : "" %>
                        <%= errors.containsKey("passwordError") ? "<li>" + errors.get("passwordError") + "</li>" : "" %>
                    </ul>
                </div>
                <% } %>
                <div class="card mt-4 shadow">
                    <div class="card-header">
                        <h4>Registro de Usuario</h4>
                    </div>
                    <form id="formUser" class="mb-0" action="<%=request.getContextPath()%>/usuarios/formulario/save" method="post">
                        <div class="card-body m-3">
                            <div class="row">
                                <div class="mb-3 col-12">
                                    <label for="names" class="form-label">Nombres:</label>
                                    <input type="text" class="form-control" id="names" name="names" value="<%= !names.isBlank() ? names : "" %>"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="mb-3 col-12">
                                    <label for="lastNames" class="form-label">Apellidos:</label>
                                    <input type="text" class="form-control" id="lastNames" name="lastNames" value="<%= !lastNames.isBlank() ? lastNames : "" %>"/>
                                </div>
                            </div>
                            <div class="row g-2">
                                <div class="mb-3 col">
                                    <label for="age" class="form-label">Edad:</label>
                                    <input type="number" class="form-control" id="age" name="age" value="<%= age != null && age > 0 ? age : "" %>"/>
                                </div>
                                <div class="mb-3 col">
                                    <label class="form-label">Sexo:</label>
                                    <div class="m-1">
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="sex" id="masculino" value="m" <%= sex.equalsIgnoreCase("m") ? "checked" : "" %>/>
                                            <label class="form-check-label" for="masculino">Masculino</label>
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="radio" name="sex" id="femenino" value="f" <%= sex.equalsIgnoreCase("f") ? "checked" : "" %>/>
                                            <label class="form-check-label" for="femenino">Femenino</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row g-2">
                                <div class="mb-3 col">
                                    <label class="form-label">Pais:</label>
                                    <select class="form-select" name="country">
                                        <option value="">-Seleccionar-</option>
                                        <option value="Perú" <%= country.equalsIgnoreCase("Perú") ? "selected" : "" %>>Perú</option>
                                        <option value="Colombia" <%= country.equalsIgnoreCase("Colombia") ? "selected" : "" %>>Colombia</option>
                                    </select>
                                </div>
                                <div class="mb-3 col">
                                    <label class="form-label">Rol:</label>
                                    <select class="form-select" name="role">
                                        <option value="">-Seleccionar-</option>
                                        <option value="ROLE_ADMIN" <%= role.equalsIgnoreCase("ROLE_ADMIN") ? "selected" : "" %>>Administrador</option>
                                        <option value="ROLE_USER" <%= role.equalsIgnoreCase("ROLE_USER") ? "selected" : "" %>>Usuario</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row g-2">
                                <div class="mb-3 col">
                                    <label for="username" class="form-label">Nombre de usuario:</label>
                                    <input type="text" class="form-control" id="username" name="username" value="<%= !username.isBlank() ? username : "" %>"/>
                                </div>
                                <div class="mb-3 col">
                                    <label for="password" class="form-label">Contraseña:</label>
                                    <input type="password" class="form-control" id="password" name="password" value="<%= !password.isBlank() ? password : "" %>"/>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer text-end">
                            <input type="submit" class="btn btn-primary" value="Guardar"/>
                            <input type="hidden" name="id" value="<%=request.getAttribute("id")%>"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/82ec21a6d1.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/funciones_js/FormUserSweetAlert.js" type="text/javascript"></script>
</body>
</html>
