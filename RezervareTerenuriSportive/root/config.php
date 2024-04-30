
<?php
// Parametrii de conexiune la baza de date
$hostname = 'localhost'; // Adresa serverului MySQL
$username = 'root'; // Numele utilizatorului MySQL
$password = 'PAROLASQL'; // Parola utilizatorului MySQL
$database = 'rezervareterenuri'; // Numele bazei de date MySQL

// Crearea conexiunii folosind PDO
try {
    $dbh = new PDO("mysql:host=$hostname;dbname=$database", $username, $password);
    echo "Conexiune reușită la baza de date $database.";
} catch (PDOException $e) {
    echo "Eroare la conectarea la baza de date: " . $e->getMessage();
}
?>