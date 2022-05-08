function loadMedicineList() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let medicineDataList = JSON.parse(this.responseText);
            let medicineList = ' <ul class="w3-ul w3-card-4"> ';

            medicineDataList.reverse();

            for (let index = 0; index < medicineDataList.length; index++) {
                medicineList +=
                    ' <li class="w3-bar"> ' +

                    ' <button id= ' + medicineDataList[index].id + ' onclick="editMedicine(this.id)" ' +
                    ' class="w3-bar-item w3-button w3-small w3-right">Edit</button> ' +

                    ' <button id=' + medicineDataList[index].id + ' onclick="removeMedicine(this.id)" ' +
                    ' class="w3-bar-item w3-button w3-small w3-right">Remove</button> ' +


                    ' <img src="https://png.pngtree.com/element_our/20200610/ourlarge/pngtree-medical-capsule-medicine-image_2242252.jpg" class="w3-bar-item w3-circle w3-hide-small" style="width:85px"> ' +
                    ' <div class="w3-bar-item"> ' +
                    ' <span class="w3-large"> Name: ' +  medicineDataList[index].name  + ' </span><br> ' +
                    ' <span> Brand: ' +  medicineDataList[index].brand  + ' </span> <br>' +
                    ' <span> Description: ' +  medicineDataList[index].description  + ' </span> <br>' +
                    ' <span> Stock: ' +  medicineDataList[index].stock  + ' </span> <br> ' +
                    ' </div> ';
            }
            medicineList += "</ul>";
            document.getElementById("medicineData").innerHTML = medicineList;
        }
    };
    xhttp.open("GET", "/healthcentremanagement-front-end/api/medicine/list", true);
    xhttp.send();
}

function addMedicine() {
    let medicine = {  "id" : null,
        "name" : document.getElementById("name").value,
        "brand" : document.getElementById("brand").value,
        "description" : document.getElementById("description").value,
        "stock" : document.getElementById("stock").value }
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "/healthcentremanagement-front-end/api/medicine/add", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState>3 && xmlhttp.status==200) {
            loadMedicineList(); clearInputFields();}
    };
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify(medicine));
}

function updateMedicine()
{
    let medicine = {  "id" : document.getElementById("medicineId").value, "name" : document.getElementById("name").value,
        "brand" : document.getElementById("brand").value, "description" : document.getElementById("description").value,
        "stock" : document.getElementById("stock").value}
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("PUT", "/healthcentremanagement-front-end/api/medicine/update", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState>3 && xmlhttp.status==200) {
            loadMedicineList();
            clearInputFields();
            document.getElementById("btnSaveMedicine").innerHTML = "Add Medicine";}
    };
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify(medicine));
}

function clearInputFields()
{
    document.getElementById("medicineId").value = "";
    document.getElementById("name").value = "";
    document.getElementById("brand").value = "";
    document.getElementById("description").value = "";
    document.getElementById("stock").value = "";
    document.getElementById("btnSaveMedicine").innerHTML = "Add Medicine";
}

function removeMedicine(medicineId)
{
    if ( confirm("Are you sure you want to delete this medicine, fool?") ) {
        deleteMedicine(medicineId);
    }
}

function deleteMedicine(medicineId)
{
    let medicine = {  "id" : medicineId, "name" : document.getElementById("name").value, "brand" : document.getElementById("brand").value,
        "description" : document.getElementById("description").value,
        "stock" : document.getElementById("stock").value }
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/healthcentremanagement-front-end/api/medicine/remove", true);
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState>3 && xhttp.status==200) {
            loadMedicineList(); }
    };
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(medicine));
}

function getMedicine(medicineId)
{
    let medicine = {  "id" : medicineId, "name" : document.getElementById("name").value, "brand" : document.getElementById("brand").value,
        "description" : document.getElementById("description").value,
        "stock" : document.getElementById("stock").value }
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState>3 && xhttp.status==200) {
            let foundMedicine = JSON.parse(this.responseText);

            document.getElementById("medicineId").value = foundMedicine.id;
            document.getElementById("name").value = foundMedicine.name;
            document.getElementById("brand").value = foundMedicine.brand;
            document.getElementById("description").value = foundMedicine.description;
            document.getElementById("stock").value = foundMedicine.stock;

            document.getElementById("btnSaveMedicine").innerHTML = "Update Medicine";
        }
    };
    xhttp.open("POST", "/healthcentremanagement-front-end/api/medicine/getMedicine", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(medicine));
}

function editMedicine(medicineId)
{
    getMedicine(medicineId);
}

function saveMedicine()
{
    if(validateForm())
    {
        if(document.getElementById("btnSaveMedicine").innerHTML === 'Add Medicine')
        {
            addMedicine();
        }
        else
        {
            updateMedicine();
        }
    }
}

function validateForm()
{
    let pass = true;
    let name = document.getElementById("name").value;
    let brand = document.getElementById("brand").value;
    let description = document.getElementById("description").value;
    let stock = document.getElementById("stock").value;

    if (name == null || name === "", brand == null || brand === "", description == null || description === "", stock == null || stock === "" )
    {
        alert("Please fill in all of the fields.");
        pass = false;
    }

    return pass;
}
