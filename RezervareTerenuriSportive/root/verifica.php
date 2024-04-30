<?php
include 'config.php'; // Include fișierul de configurare pentru conexiunea la baza de date

try {
    // Codul pentru conexiunea la baza de date
    $query = $dbh->query("SELECT * FROM user");
    $result = $query->fetchAll(PDO::FETCH_ASSOC);
    print_r($result);
    echo "Conexiune reușită la baza de date.";
} catch (PDOException $e) {
    // Mesaj de eroare în cazul în care există o problemă la conexiunea la baza de date
    echo "Eroare la execuția interogării: " . $e->getMessage();
}
?>