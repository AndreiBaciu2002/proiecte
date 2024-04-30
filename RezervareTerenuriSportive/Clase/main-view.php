<html>

<head>
  <link rel="stylesheet" type="text/css" href="style-page.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function() {
      $("#search-bar").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#card-container #product-card").filter(function() {
          $(this).toggle($(this).find("#product-name").text().toLowerCase().indexOf(value) > -1)
        });
      });
    });
    $(document).ready(function() {
      $("#sort-button").on("click", function() {
        var cards = $("#card-container #product-card");
        cards.sort(function(a, b) {
          var nameA = $(a).find("#product-name").text().toUpperCase();
          var nameB = $(b).find("#product-name").text().toUpperCase();
          if (nameA < nameB) {
            return -1;
          }
          if (nameA > nameB) {
            return 1;
          }
          return 0;
        });
        $("#card-container").append(cards);
      });
    });
    $(document).ready(function() {
      $("#category-dropdown").on("change", function() {
        var selectedCategory = $(this).val().toLowerCase();
        $("#card-container #product-card").filter(function() {
          var productCategory = $(this).find("#product-category").text().toLowerCase();
          if (selectedCategory === productCategory) {
            $(this).show();
          } else {
            $(this).hide();
          }
        });
      });
    });
  </script>
</head>

<body>
  <?php
  session_start();
  $fullname = $_SESSION['fullname'];
  $role = $_SESSION['role'];
  ?>
  <header id="header-element">
    <i id="user-profile" class="link-header"><?php echo $fullname; ?></i>
    <a href="products-by-category.php?category=fotbal" id=" fotbal-link" class="link-header">Fotbal</a>
    <a href="products-by-category.php?category=volei" id="volei-link" class="link-header">Volei</a>
    <a href="products-by-category.php?category=tenis" id="tenis-link" class="link-header">Tenis</a>
    <a href="products-by-category.php?category=baschet" id="baschet-link" class="link-header">Baschet</a>
    <button id="logout-button" class="link-header">Logout</button>
  </header>

  <div id="search-sort-holder">
    <input type="text" id="search-bar" placeholder="Search products">
    <button id="sort-button">Sort by name</button>
    <select id="category-dropdown">
      <option id="dropdown-placeholder" value="" disabled selected>Search by category</option>
      <option value="fotbal">fotbal</option>
      <option value="volei">volei</option>
      <option value="tenis">tenis</option>
      <option value="baschet">baschet</option>
    </select>
    <?php
    if ($role === "admin") {
      echo '<button id="add-product">Add product</button>';
    }
    ?>
  </div>

  <div id="card-container">
    <?php include 'getAllProducts.php'; ?>
  </div>
  <footer id="footer-element">
    <div id="footer-container">
      <p id="logo">&copy; Teren Sportiv</p>
    </div>
  </footer>
</body>

<script>
  const logoutButton = document.getElementById('logout-button');
  logoutButton.addEventListener('click', function(event) {
    event.preventDefault();
    window.location.href = 'login-page.php';
  });
  const addProduct = document.getElementById('add-product');
  addProduct.addEventListener('click', function(event) {
    event.preventDefault();
    window.location.href = 'add-product.php';
  });
</script>

</html>