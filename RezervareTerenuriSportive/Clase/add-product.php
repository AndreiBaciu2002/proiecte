<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" type="text/css" href="style-page.css">
</head>

<body>
<form id="form" action="add-product-handler.php" method="post" enctype="multipart/form-data">
    <div id="add-product-holder">
        <button id="go-back-add" onclick="goBack()"></button>
        <div id="add-product-wrapper">
            <div id="add-product-container">
                <label id="name-label" for="name">Name</label>
                <input type="text" id="name-add" name="name" placeholder="Enter the name " />
                <label for="price-label">Price</label>
                <input type="number" id="price-add" name="price" required placeholder="Enter the price ">
                <label id="category-label" for="category">Category</label>
                <select id="category-add-dropdown" name="category-add-dropdown">
                    <option id="dropdown-add-placeholder" value="" disabled selected>Select category</option>
                    <option value="Fotbal">Fotbal</option>
                    <option value="Tenis">Tenis</option>
                    <option value="Baschet">Baschet</option>
                    <option value="Volei">Volei</option>
                </select>
                <label id="judet-label" for="role">Judet</label>
                <select id="judet-dropdown" name="judet-dropdown" required>
                    <option id="judet-placeholder" value="" disabled selected>Select judet</option>
                    <option value="Bihor">Bihor</option>
                    <option value="Cluj">Cluj</option>
                    <option value="Timis">Timis</option>
                    <option value="SatuMare">SatuMare</option>
                    <option value="Iasi">Iasi</option>
                    <option value="Constanta">Constanta</option>
                    <option value="Bucuresti">Bucuresti</option>
                </select>
                <label id="oras-label" for="role">Oras</label>
                <select id="oras-dropdown" name="oras-dropdown" required>
                    <option id="oras-placeholder" value="" disabled selected>Select oras</option>
                </select>
                <label for="imagepath">Imagine</label>
                <div id="img-holder"><input type="file" id="image-path" name="imagepath" accept="image/*" required></div>
                <label for="length">Lungime</label>
                <input type="number" id="lungime" name="lungime" required placeholder="Enter the length">
                <label for="width">Latine</label>
                <input type="number" id="latime" name="latime" required placeholder="Enter the width">
                <label for="capacity">Capacitate</label>
                <input type="number" id="capacitate" name="capacitate" required placeholder="Enter the capacity">
                <button id="add-product-button">Add product</button>
            </div>
        </div>
    </div>
</form>
<footer id="footer-element">
    <div id="footer-container">
        <p id="logo">&copy; Teren Sportiv</p>
    </div>
</footer>
<script>
    const judetDropdown = document.getElementById('judet-dropdown');
    const orasDropdown = document.getElementById('oras-dropdown');
    const judetOptions = {
        Bihor: ['Oradea', 'Alesd'],
        Cluj: ['Cluj-Napoca', 'Turda', 'Dej'],
        Timis: ['Timisoara', 'Lugoj'],
        SatuMare: ['SatuMare'],
        Iasi: ['Iasi', 'Pascani'],
        Constanta: ['Constanta'],
        Bucuresti: ['Bucuresti']
    };

    function updateOrasDropdown() {
        const selectedJudet = judetDropdown.value;

        orasDropdown.innerHTML = '<option id="oras-placeholder" value="" disabled selected>Select oras</option>';

        judetOptions[selectedJudet].forEach(oras => {
            const option = document.createElement('option');
            option.value = oras;
            option.text = oras;
            orasDropdown.add(option);
        });
    }

    judetDropdown.addEventListener('change', updateOrasDropdown);

    function goBack() {
        window.history.back();
    }
</script>
</body>

</html>