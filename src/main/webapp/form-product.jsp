<%@ page import="java.util.List" %>
<%@ page import="com.jfarro.app.models.ProductCategory" %>
<%@ page import="com.jfarro.app.models.Product" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.jfarro.app.models.ItemShoppingCar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Long idUser = (Long)session.getAttribute("idUser");
    List<ItemShoppingCar> items = (List<ItemShoppingCar>) request.getSession().getAttribute("listItems");
    List<ProductCategory> categories = (List<ProductCategory>) request.getAttribute("categories");
    Map<String, String> errors = (Map<String, String>) request.getAttribute("productErrors");

    Product product = (Product) request.getAttribute("product");
    String name = "";
    long idCategory = 0;
    int amount = 0;
    double price = 0;
    if (product != null) {
        name = product.getName() != null ? product.getName() : "";
        idCategory = product.getCategory().getId() != null ? product.getCategory().getId() : 0;
        amount = product.getAmount() != null ? product.getAmount() : 0;
        price = product.getPrice() != null ? product.getPrice() : 0;
    }
%>
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
                <a href="<%=request.getContextPath()%>/productos">
                    <i class="fa-solid fa-warehouse"></i>
                </a>
                <a class="enlace" href="<%=request.getContextPath()%>/productos/data-show">Volver a productos</a>
            </div>
        </div>
        <div class="row justify-content-center"> <!-- para centrar en la pantalla d-flex justify-content-center align-items-center vh-100  -->
            <div class="col-7">
                <% if (errors != null) { %>
                <div class="alert alert-danger" role="alert">
                    <ul>
                        <%= errors.containsKey("nameError") ? "<li>" + errors.get("nameError") + "</li>" : "" %>
                        <%= errors.containsKey("categoryError") ? "<li>" + errors.get("categoryError") + "</li>" : "" %>
                        <%= errors.containsKey("amountError") ? "<li>" + errors.get("amountError") + "</li>" : "" %>
                        <%= errors.containsKey("priceError") ? "<li>" + errors.get("priceError") + "</li>" : "" %>
                    </ul>
                </div>
                <% } %>
                <div class="card mt-4 shadow">
                    <div class="card-header">
                        <h4>Registro de Producto</h4>
                    </div>
                    <form class="mb-0" id="formulario" action="<%=request.getContextPath()%>/productos/formulario/save" method="post">
                        <div class="card-body m-3">
                            <div class="row">
                                <div class="mb-3 col-12">
                                    <label for="name" class="form-label">Nombre del producto:</label>
                                    <input type="text" class="form-control" id="name" name="name" value="<%=!name.isBlank() ? name : ""%>"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="mb-3 col-12">
                                    <label class="form-label">Categoría:</label>
                                    <select class="form-select" name="category">
                                        <option value="">-Seleccionar-</option>
                                        <% for (ProductCategory category: categories) { %>
                                        <option value="<%=category.getId()%>" <%= idCategory == category.getId() ? "selected" : ""%>><%=category.getName()%></option>
                                        <% } %>
                                    </select>
                                </div>
                            </div>
                            <div class="row g-2">
                                <div class="col">
                                    <label for="amount" class="form-label">Cantidad:</label>
                                    <input type="number" class="form-control" id="amount" name="amount" value="<%=amount > 0 ? amount : ""%>"/>
                                </div>
                                <div class="col">
                                    <label for="price" class="form-label">Precio:</label>
                                    <input type="number" class="form-control" id="price" name="price" value="<%=price > 0 ? price : ""%>"/>
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
    <script src="<%=request.getContextPath()%>/funciones_js/FormProductSweetAlert.js" type="text/javascript"></script>
</body>
</html>
