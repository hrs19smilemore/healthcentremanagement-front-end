function loadPrescriptionList() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let prescriptionDataList = JSON.parse(this.responseText);
            let prescriptionList = ' <ul class="w3-ul w3-card-4"> ';

            prescriptionDataList.reverse();

            for (let index = 0; index < prescriptionDataList.length; index++) {
                prescriptionList +=
                    ' <li class="w3-bar"> ' +

                    ' <button id= ' + prescriptionDataList[index].prescriptionId + ' onclick="editPrescription(this.id)" ' +
                    ' class="w3-bar-item w3-button w3-small w3-right">Edit</button> ' +

                    ' <button id=' + prescriptionDataList[index].prescriptionId + ' onclick="removePrescription(this.id)" ' +
                    ' class="w3-bar-item w3-button w3-small w3-right">Remove</button> ' +


                    ' <img src="https://cdn.iconscout.com/icon/free/png-256/health-1659502-1410024.png" class="w3-bar-item w3-circle w3-hide-small" style="width:85px"> ' +
                    ' <div class="w3-bar-item"> ' +
                    '' +
                    ' <span class="w3-large"> Dose: ' +  prescriptionDataList[index].dose  + ' </span><br> ' +
                    ' <span> Prescription date: ' +  prescriptionDataList[index].prescription_date + '</span> <br>' +
                    ' <span> Medicine name: ' +  prescriptionDataList[index].medicine.name  + ' </span> <br>' +
                    ' <span> Medicine brand: ' +  prescriptionDataList[index].medicine.brand  + ' </span> <br> ' +
                    ' </div> ';
            }
            prescriptionList += "</ul>";
            document.getElementById("prescriptionData").innerHTML = prescriptionList;
        }
    };
    xhttp.open("GET", "/healthcentremanagement-front-end/api/prescription/list", true);
    xhttp.send();
}

function addPrescription() {
    let prescription = {  "prescriptionId" : null,
        "dose" : document.getElementById("dose").value,
        "prescription_date" : document.getElementById("prescriptionDate").value,
        "medicine" : { "name" : document.getElementById("medicineName").value,
        "brand" : document.getElementById("medicineBrand").value },
        "patient" : { "identification" :{ "number" : document.getElementById("idNumber").value}}}
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "/healthcentremanagement-front-end/api/prescription/add", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState>3 && xmlhttp.status===204){
            alert("Medicine doesn't exist! / Please check name and brand");
        } else if (xmlhttp.readyState>3 && xmlhttp.status===200) {
            loadPrescriptionList(); clearInputFields();
            }
    };
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify(prescription));
}

function updatePrescription()
{
    let prescription = {  "prescriptionId" : document.getElementById("prescriptionId").value,
        "dose" : document.getElementById("dose").value,
        "prescription_date" : document.getElementById("prescriptionDate").value,
        "medicine" :{ "name" : document.getElementById("medicineName").value,
            "brand" : document.getElementById("medicineBrand").value },
        "patient" : { "identification" : { "number" : document.getElementById("idNumber").value}}}
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("PUT", "/healthcentremanagement-front-end/api/prescription/update", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState>3 && xmlhttp.status==200) {
            loadPrescriptionList();
            clearInputFields();
            document.getElementById("btnSavePrescription").innerHTML = "Add Prescription";}
        document.getElementById("idNumber").disabled = false;
        document.getElementById("prescriptionDate").disabled = false;
        document.getElementById("medicineBrand").disabled = false;
        document.getElementById("medicineName").disabled = false;
    };
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify(prescription));
}

function clearInputFields()
{
    document.getElementById("prescriptionId").value = "";
    document.getElementById("dose").value = "";
    document.getElementById("prescriptionDate").value = "";
    document.getElementById("medicineName").value = "";
    document.getElementById("medicineBrand").value = "";
    document.getElementById("idNumber").value = "";
    document.getElementById("btnSavePrescription").innerHTML = "Add Prescription";
}

function removePrescription(prescriptionId)
{
    if ( confirm("Are you sure you want to delete this prescription ?") ) {
        deletePrescription(prescriptionId);
    }
}

function deletePrescription(prescriptionId)
{
    let prescription = {  "prescriptionId" : prescriptionId,
        "dose" : document.getElementById("dose").value,
        "prescription_date" : document.getElementById("prescriptionDate").value,
        "medicine" :{ "name" : document.getElementById("medicineName").value,
            "brand" : document.getElementById("medicineBrand").value },
        "patient" : { "identification" : { "number" : document.getElementById("idNumber").value}}}
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/healthcentremanagement-front-end/api/prescription/remove", true);
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState>3 && xhttp.status==200) {
            loadPrescriptionList(); }
    };
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(prescription));
}

function getPrescription(prescriptionId)
{
    let prescription = {  "prescriptionId" : prescriptionId,
        "dose" : document.getElementById("dose").value,
        "prescription_date" : document.getElementById("prescriptionDate").value,
        "medicine" :{ "name" : document.getElementById("medicineName").value,
            "brand" : document.getElementById("medicineBrand").value },
        "patient" : { "identification" : { "number" : document.getElementById("idNumber").value}}}
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState>3 && xhttp.status==200) {
            let foundPrescription = JSON.parse(this.responseText);

            document.getElementById("prescriptionId").value = foundPrescription.prescriptionId;
            document.getElementById("dose").value = foundPrescription.dose;
            document.getElementById("prescriptionDate").value = foundPrescription.prescription_date;
            document.getElementById("medicineName").value = foundPrescription.medicine.name;
            document.getElementById("medicineBrand").value = foundPrescription.medicine.brand;
            document.getElementById("idNumber").value = foundPrescription.patient.identification.number;

            document.getElementById("btnSavePrescription").innerHTML = "Update Prescription";
        }
    };
    xhttp.open("POST", "/healthcentremanagement-front-end/api/prescription/getPrescription", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(prescription));
}

function editPrescription(prescriptionId)
{
    document.getElementById("idNumber").disabled = true;
    document.getElementById("prescriptionDate").disabled = true;
    document.getElementById("medicineBrand").disabled = true;
    document.getElementById("medicineName").disabled = true;
    getPrescription(prescriptionId);
}

function savePrescription()
{
    if(validateForm())
    {
        if(document.getElementById("btnSavePrescription").innerHTML === 'Add Prescription')
        {
            addPrescription();
        }
        else
        {
            updatePrescription();
        }
    }
}

function validateForm()
{
    let pass = true;
    let dose = document.getElementById("dose").value;
    let prescriptionDate = document.getElementById("prescriptionDate").value;
    let medicineName = document.getElementById("medicineName").value;
    let medicineBrand = document.getElementById("medicineBrand").value;
    let idNumber = document.getElementById("idNumber").value;

    if (dose == null || dose === "", prescriptionDate == null || prescriptionDate === "", medicineName == null || medicineName === "", medicineBrand == null || medicineBrand === "", idNumber == null || idNumber === "" )
    {
        alert("Please fill in all the fields, damn.");
        pass = false;
    }

    return pass;
}