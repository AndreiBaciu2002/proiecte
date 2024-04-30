<html>

<head>
    <link rel="stylesheet" type="text/css" href="style-page.css">
</head>
<?php
session_start();
$fullname = $_SESSION['fullname'];
$role = $_SESSION['role'];
?>

<body>
    <header id="header-element">
        <i id="user-profile" class="link-header"><?php echo $fullname; ?></i>
        <a href="products-by-category.php?category=fotbal" id=" fotbal-link" class="link-header">Fotbal</a>
        <a href="products-by-category.php?category=volei" id="volei-link" class="link-header">Volei</a>
        <a href="products-by-category.php?category=baschet" id="baschet-link" class="link-header">Baschet</a>
        <a href="products-by-category.php?category=tenis" id="tenis-link" class="link-header">Tenis</a>
        <button id="logout-button" class="link-header">Logout</button>
    </header>

    <button id="go-back" onclick="goBack()"></button>

    <div id="card-container">
        <?php include 'getProductsByCategory.php'; ?>
    </div>
    <footer id="footer-element">
        <div id="footer-container">
            <p id="logo">&copy; Teren Sportiv</p>
        </div>
    </footer>
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>

</html>