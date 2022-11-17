<%@ page import="java.util.List" %>
<%@ page import="com.jfarro.app.models.ItemShoppingCar" %>
<%@ page import="com.jfarro.app.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("user");
    List<ItemShoppingCar> items = (List<ItemShoppingCar>) request.getSession().getAttribute("listItems");
    ItemShoppingCar itemData = new ItemShoppingCar(request);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrito</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.css"/>
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
                    <% if (user != null) { %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Productos</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos/data-show">Lista de Productos</a></li>
                            <% if (user.getRole().equals("ROLE_ADMIN")) { %>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos/formulario/save">Registro de Productos</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/categoria-producto/data-show">Lista de Categorías</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/categoria-producto/formulario/save">Registro de Categorías</a></li>
                            <% } %>
                        </ul>
                    </li>
                    <% if (user.getRole().equals("ROLE_ADMIN")) { %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">Usuarios</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/usuarios/data-show">Lista de Usuarios</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/usuarios/formulario/save">Registro de Usuarios</a></li>
                        </ul>
                    </li>
                    <% } %>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/carro-compra/data-show">(<%= items != null ? items.size() : 0 %>)Carrito</a>
                    </li>
                    <% } %>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">Login</a>
                        <ul class="dropdown-menu">
                            <% if (user == null) { %>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/sesion/login">Iniciar Sesión</a></li>
                            <% } %>
                            <% if (user != null) { %>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/sesion/logout">Salir</a></li>
                            <% } %>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <% if (request.getAttribute("sinDatosItems") != null) { %>
        <div class="alert alert-danger mt-4" role="alert">
            <%= request.getAttribute("sinDatosItems") %>
        </div>
        <% } %>
        <div class="row mt-4">
            <div class="col">
                <h3>Mi carrito (<%= items != null ? items.size() : 0 %> items)</h3>
            </div>
        </div>
        <div class="row justify-content-center mb-5">
            <div class="col-md-9 col-lg-8 col-xl-8 mt-3">
                <div class="card shadow">
                    <form action="<%=request.getContextPath()%>/carro-compra/data-show" method="post">
                        <% if (items != null && !items.isEmpty()) { %>
                        <% for(ItemShoppingCar item: items) { %>
                        <div class="row card-body m-2 car-container">
                            <div class="col-lg-6 col-xl-3 car-image">
                                <img class="img-fluid" src="<%=request.getContextPath()%>/images/30043-3.jpg"/>
                            </div>
                            <div class="col-lg-6 col-xl-7 car-data">
                                <h5 class="pe-1"><%= item.getProduct().getName() %></h5>
                                <div class="car-data-price mt-2">
                                    Precio: S/ <%= item.getProduct().getPrice() %>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-xs-8 col-md-8 col-lg-6 col-xl-4 overflow-hidden">
                                        <div class="input-group mb-3">
                                            <a class="btn btn-outline-secondary" type="submit" href="<%=request.getContextPath()%>/carro-compra/udpate-amount?menos=1&idItem=<%=item.getId()%>">-</a>
                                            <input type="text" class="form-control text-center" name="amountCar" value="<%= item.getAmount() %>" disabled/>
                                            <a class="btn btn-outline-secondary" type="submit" href="<%=request.getContextPath()%>/carro-compra/udpate-amount?mas=1&idItem=<%=item.getId()%>">+</a>
                                        </div>
                                    </div>
                                    <div class="col-1">
                                        <a class="text-secondary" href="<%=request.getContextPath()%>/carro-compra/delete?idItemDelete=<%= item.getId() %>">
                                            <i class="fa-solid fa-trash mt-1" style="font-size: 25px;"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6 col-xl-2 text-end">
                                <h5>S/ <%= item.getTotal() %></h5>
                            </div>
                        </div>
                        <% } %>
                        <% } %>
                    </form>
                </div>
            </div>
            <div class="col-md-9 col-lg-4 col-xl-4 mt-3">
                <div class="card">
                    <form id="formShoppingCar" action="<%=request.getContextPath()%>/productos/ventas" method="post">
                        <div class="card-body">
                            <div class="row mb-2">
                                <div class="col">
                                    <h3 class="card-title">Resumen del pedido</h3>
                                </div>
                            </div>
                            <div class="row me-2 ms-2">
                                <div class="col">
                                    <h6 class="card-subtitle mb-2 text-muted">Precio:</h6>
                                </div>
                                <div class="col text-end">
                                    <h6 class="card-subtitle mb-2 text-muted">S/ <%= itemData.calSubTotal() %></h6>
                                </div>
                            </div>
                            <div class="row me-2 ms-2">
                                <div class="col">
                                    <h6 class="card-subtitle mb-2 text-muted">Descuentos:</h6>
                                </div>
                                <div class="col text-end">
                                    <h6 class="card-subtitle mb-2 text-muted">S/ 0</h6>
                                </div>
                            </div>
                            <div class="row mt-3 mb-2">
                                <div class="col">
                                    <h4 class="card-title">Subtotal: </h4>
                                </div>
                                <div class="col text-end">
                                    <h4 class="card-title">S/ <%= itemData.calSubTotal() %></h4>
                                </div>
                            </div>
                                <div class="d-grid gap-2 col-12 mx-auto">
                                    <input class="btn btn-primary p-3" type="submit" value="Finalizar compra"/>
                                    <a class="btn btn-secondary p-3" type="button" href="<%=request.getContextPath()%>/productos/data-show">Continuar comprando</a>
                                </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/82ec21a6d1.js" crossorigin="anonymous"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/funciones_js/ShoppingCarSweetAlert.js" type="text/javascript"></script>
</body>
</html>