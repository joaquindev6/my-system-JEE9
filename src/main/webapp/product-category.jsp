<%@ page import="java.util.List" %>
<%@ page import="com.jfarro.app.models.ProductCategory" %>
<%@ page import="com.jfarro.app.models.ItemShoppingCar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ItemShoppingCar> items = (List<ItemShoppingCar>) request.getSession().getAttribute("listItems");
    List<ProductCategory> categories = (List<ProductCategory>) request.getAttribute("categories");
%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Productos</title>
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
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Productos</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos/data-show">Lista de Productos</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos/formulario/save">Registro de Productos</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos/data-show">Lista de Categorías</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/productos/formulario/save">Registro de Categorías</a></li>
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
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">Login</a>
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
        <% if (request.getAttribute("errorDelete") != null) { %>
        <div class="alert alert-danger mt-4" role="alert">
            <%= request.getAttribute("errorDelete") %>
        </div>
        <% } %>
        <div class="row justify-content-center">
            <div class="col-8">
                <div class="card mt-4 shadow">
                    <div class="card-header">
                        <h4>Lista de Categorías de Productos</h4>
                    </div>
                    <form action="<%=request.getContextPath()%>/categoria-producto/data-show" method="post">
                        <div class="card-body m-3">
                            <div class="row overflow-hidden">
                                <table id="mytable" class="table table-hover">
                                    <thead>
                                    <tr class="text-center">
                                        <th>Id</th>
                                        <th>Nombre</th>
                                        <th>Editar</th>
                                        <th>Eliminar</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <% for (ProductCategory category: categories) { %>
                                    <tr>
                                        <td><%= category.getId() %></td>
                                        <td><%= category.getName() %></td>
                                        <td>
                                            <div class="d-flex">
                                                <div class="mx-auto">
                                                    <a class="btn btn-success" href="<%=request.getContextPath()%>/categoria-producto/formulario/save?id=<%=category.getId()%>">Editar</a>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-check m-1 d-flex justify-content-center align-items-center">
                                                <input class="form-check-input " type="checkbox" name="delete" value="<%=category.getId()%>"/>
                                            </div>
                                        </td>
                                    </tr>
                                    <% } %>
                                    </tbody>
                                </table>
                            </div>
                            <div class="row justify-content-end mt-2 mb-0">
                                <div class="col-12 overflow-hidden">
                                    <form action="<%=request.getContextPath()%>/categoria-producto/data-show" class="mb-0 mt-2">
                                        <div class="d-flex justify-content-end">
                                            <a class="btn btn-primary me-2" href="<%=request.getContextPath()%>/categoria-producto/formulario/save">Nueva Categoría</a>
                                            <input type="submit" class="btn btn-danger ms-1" value="Eliminar"
                                                    onclick="return confirm('¿Está seguro que desea eliminar?');"/>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.js"></script>
    <script>
        $(document).ready( function () {
            $('#mytable').DataTable({
                language: {
                    processing: "Traitement en cours...",
                    search: "Buscar&nbsp;:",
                    lengthMenu: "Elementos por paginación _MENU_",
                    info: "Mostrando del _START_ al _END_ de _TOTAL_ filas",
                    infoEmpty: "Affichage de l'&eacute;lement 0 &agrave; 0 sur 0 &eacute;l&eacute;ments",
                    infoFiltered: "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
                    infoPostFix: "",
                    loadingRecords: "Chargement en cours...",
                    zeroRecords: "Aucun &eacute;l&eacute;ment &agrave; afficher",
                    emptyTable: "Aucune donnée disponible dans le tableau",
                    paginate: {
                        first: "Inicio",
                        previous: "Anterior",
                        next: "Siguiente",
                        last: "Ultimo"
                    },
                    aria: {
                        sortAscending: ": activer pour trier la colonne par ordre croissant",
                        sortDescending: ": activer pour trier la colonne par ordre décroissant"
                    }
                }
            });
        } );
    </script>
</body>
</html>
