<?php
$host = 'localhost';
$user = 'root';
$password = 'PAROLASQL';
$database = 'rezervareterenuri';
$mysqli = new mysqli($host, $user, $password, $database);

if ($mysqli->connect_error) {
    die('Connect Error (' . $mysqli->connect_errno . ') '
        . $mysqli->connect_error);
}

$id = $_GET['id'];
$query = 'SELECT * FROM teren WHERE id = ' . $id;
$result = $mysqli->query($query);

if (!$result) {
    die('Query Error: ' . $mysqli->error);
}

$row = $result->fetch_assoc();

$result->free();
$mysqli->close();

$productName = $row['nume'];
$productCategory = $row['categorie'];
$productPrice = $row['pret'];
$productImage = $row['imagePath'];
$productJudet = $row['judet'];
$productOras = $row['oras'];
$Lungime = $row['lungime'];
$Latime = $row['latime'];
$Capacitate = $row['capacitate'];

?>