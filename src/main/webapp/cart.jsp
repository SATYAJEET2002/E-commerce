<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Watch Store</title>
<title>Shopping Cart - Watch Store</title>
<link rel="stylesheet" type="text/css" href="style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
</head>
<body>
<body>
<header class="bg-light">
    <div class="container">
        <nav class="navbar navbar-expand-lg navbar-light">
            <a class="navbar-brand" href="/">
                <img src="img/logo.png" alt="CoolGadgets Logo" width="120">
            </a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="products.html">Products</a></li>
                    <li class="nav-item"><a class="nav-link" href="/#contact">Contact</a></li>
                    <li class="nav-item"><a class="nav-link" href="cart.html"><i class="fas fa-shopping-cart"></i></a></li>
                </ul>
            </div>
        </nav>
    </div>
</header>
<header class="py-3">
    <div class="container">
        <h1>Your Shopping Cart</h1>
    </div>
</header>

<section class="container my-5">
    <div class="row">
        <div class="col-md-8">
            <!-- Cart Items List -->
            <% 
            try {
            	Class.forName("oracle.jdbc.driver.OracleDriver");
    			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","4066");
                PreparedStatement ps = con.prepareStatement("SELECT * FROM cart");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String itemName = rs.getString("itemName");
                    double itemPrice = rs.getDouble("itemPrice");
                    int quantity = rs.getInt("quantity");
            %>
            <div class="cart-item">
                <div class="row">
                    <div class="col-md-3">
                        <!-- You can replace the src attribute with the actual path to your product image -->
                        <img src="images/<%= itemName %>.jpg" class="img-fluid" alt="Product">
                    </div>
                    <div class="col-md-5">
                        <h4><%= itemName %></h4>
                        <p>$<%= itemPrice %></p>
                    </div>
                    <div class="col-md-2">
                        <input type="number" class="form-control" value="<%= quantity %>">
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-danger">Remove</button>
                    </div>
                </div>
            </div>
            <% 
                }
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            %>
            <!-- Add more cart items here -->
        </div>

        <div class="col-md-4">
            <!-- Cart Summary -->
            <div class="border p-3 mb-3">
                <h3 class="mb-3">Cart Summary</h3>
                <!-- You can calculate subtotal, tax, and total dynamically here -->
                <div class="d-flex justify-content-between">
                    <span>Subtotal</span>
                    <span>$299.00</span>
                </div>
                <div class="d-flex justify-content-between">
                    <span>Tax</span>
                    <span>$23.92</span>
                </div>
                <div class="d-flex justify-content-between">
                    <span>Total</span>
                    <span>$322.92</span>
                </div>
                <button class="btn btn-warning w-100 mt-3">Proceed to Checkout</button>
            </div>
        </div>
    </div>
</section>

<footer class="bg-dark text-light py-4 mt-5">
    <div class="container text-center">
        &copy; Copyright 2024- Watch Store
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>