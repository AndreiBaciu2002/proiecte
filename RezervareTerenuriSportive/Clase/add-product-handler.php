<?php
$servername = "localhost";
$username = "root";
$password = "PAROLASQL";
$dbname = "rezervareterenuri";

$conn = new mysqli($servername, $username, $password, $dbname);

if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $name = $_POST['name'];
    $price = $_POST['price'];
    $category = $_POST['category-add-dropdown'];
    $judet = $_POST['judet-dropdown'];
    $oras = $_POST['oras-dropdown'];
    echo ($category);

    $target_dir = "assets/";
    $target_file = $target_dir . basename($_FILES["imagepath"]["name"]);
    move_uploaded_file($_FILES["imagepath"]["tmp_name"], $target_file);
    $image_path = "assets/" . $_FILES["imagepath"]["name"];
    $lungime = $_POST['lungime'];
    $latime = $_POST['latime'];
    $capacitate = $_POST['capacitate'];

    $sql = "INSERT INTO teren (nume, pret, categorie, judet, oras, imagePath, lungime, latime, capacitate)
VALUES ('$name', '$price', '$category', '$judet', '$oras', '$image_path', $lungime, $latime, $capacitate)";

    if (mysqli_query($conn, $sql)) {
        header("Location: main-view.php");
        exit;
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    }

    mysqli_close($conn);
}
