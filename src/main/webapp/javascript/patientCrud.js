function loadPatientList() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let patientDataList = JSON.parse(this.responseText);
            let patientList = ' <ul class="w3-ul w3-card-4"> ';

            patientDataList.reverse();

            for (let index = 0; index < patientDataList.length; index++) {
                patientList +=
                    ' <li class="w3-bar"> ' +

                    ' <button id= ' + patientDataList[index].id + ' onclick="editPatient(this.id)" ' +
                    ' class="w3-bar-item w3-button w3-small w3-right">Edit</button> ' +

                    ' <button id=' + patientDataList[index].id + ' onclick="removePatient(this.id)" ' +
                    ' class="w3-bar-item w3-button w3-small w3-right">Remove</button> ' +


                    ' <img src="https://cdn.iconscout.com/icon/free/png-256/health-1659502-1410024.png" class="w3-bar-item w3-circle w3-hide-small" style="width:85px"> ' +
                    ' <div class="w3-bar-item"> ' +
                    ' <span class="w3-large"> Firstname: ' +  patientDataList[index].firstname  + ' </span><br> ' +
                    ' <span> Lastname: ' +  patientDataList[index].lastname  + ' </span> <br>' +
                    ' <span> Address: ' +  patientDataList[index].adress  + ' </span> <br>' +
                    ' <span> Contactnumber: ' +  patientDataList[index].contactnumber  + ' </span> <br> ' +
                    ' <span> Id number: ' +  patientDataList[index].identification.number  + ' </span> <br> ' +
                    ' <span> Sex: ' +  patientDataList[index].identification.sex  + ' </span> <br> ' +
                    ' </div> ';
            }
            patientList += "</ul>";
            document.getElementById("patientData").innerHTML = patientList;
        }
    };
    xhttp.open("GET", "/healthcentremanagement-front-end/api/patient/list", true);
    xhttp.send();
}

function addPatient() {
    let patient = {  "id" : null,
        "adress" : document.getElementById("address").value,
        "contactnumber" : document.getElementById("contactNumber").value,
        "firstname" : document.getElementById("firstName").value,
        "lastname" : document.getElementById("lastName").value,
        "identification" : {
            "number" : document.getElementById("idNumber").value,
            "sex" : document.getElementById("sex").value}}
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "/healthcentremanagement-front-end/api/patient/add", true);
    xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState>3 && xmlhttp.status===204){
                alert("Patient already exists! Use a different idNumber");
            } else if (xmlhttp.readyState>3 && xmlhttp.status===200) {
                loadPatientList(); clearInputFields();
            }}
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify(patient));
}

function updatePatient()
{
    let patient = {  "id" : document.getElementById("patientId").value,
        "adress" : document.getElementById("address").value,
        "contactnumber" : document.getElementById("contactNumber").value,
        "firstname" : document.getElementById("firstName").value,
        "lastname" : document.getElementById("lastName").value,
        "identification" : {
            "number" : document.getElementById("idNumber").value,
            "sex" : document.getElementById("sex").value}}
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("PUT", "/healthcentremanagement-front-end/api/patient/update", true);
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState>3 && xmlhttp.status==200) {
            loadPatientList();
            clearInputFields();
            document.getElementById("btnSavePatient").innerHTML = "Add Patient";}
        document.getElementById("idNumber").disabled = false;
    };
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify(patient));
}

function clearInputFields()
{
    document.getElementById("patientId").value = "";
    document.getElementById("address").value = "";
    document.getElementById("contactNumber").value = "";
    document.getElementById("firstName").value = "";
    document.getElementById("lastName").value = "";
    document.getElementById("idNumber").value = "";
    document.getElementById("sex").value = "";
    document.getElementById("btnSavePatient").innerHTML = "Add Patient";
}

function removePatient(patientId)
{
    if ( confirm("Are you sure you want to delete this patient ?") ) {
        deletePatient(patientId);
    }
}

function deletePatient(patientId)
{
    let patient = {  "id" : patientId,
        "adress" : document.getElementById("address").value,
        "contactnumber" : document.getElementById("contactNumber").value,
        "firstname" : document.getElementById("firstName").value,
        "lastname" : document.getElementById("lastName").value,
        "identification" : {
            "number" : document.getElementById("idNumber").value,
            "sex" : document.getElementById("sex").value}}
    var xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/healthcentremanagement-front-end/api/patient/remove", true);
    xhttp.onreadystatechange = function() {
        if (xhttp.readyState>3 && xhttp.status==200) {
            loadPatientList(); }
    };
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(patient));
}

function getPatient(patientId)
{
    let patient = {  "id" : patientId,
        "adress" : document.getElementById("address").value,
        "contactnumber" : document.getElementById("contactNumber").value,
        "firstname" : document.getElementById("firstName").value,
        "lastname" : document.getElementById("lastName").value,
        "identification" : {
            "number" : document.getElementById("idNumber").value,
            "sex" : document.getElementById("sex").value}}
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState>3 && xhttp.status==200) {
            let foundPatient = JSON.parse(this.responseText);

            document.getElementById("patientId").value = foundPatient.id;
            document.getElementById("address").value = foundPatient.adress;
            document.getElementById("contactNumber").value = foundPatient.contactnumber;
            document.getElementById("firstName").value = foundPatient.firstname;
            document.getElementById("lastName").value = foundPatient.lastname;
            document.getElementById("idNumber").value = foundPatient.identification.number;
            document.getElementById("sex").value = foundPatient.identification.sex;

            document.getElementById("btnSavePatient").innerHTML = "Update Patient";
        }
    };
    xhttp.open("POST", "/healthcentremanagement-front-end/api/patient/getPatient", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(JSON.stringify(patient));
}

function editPatient(patientId)
{
    document.getElementById("idNumber").disabled = true;
    getPatient(patientId);

}

function savePatient()
{
    if(validateForm())
    {
        if(document.getElementById("btnSavePatient").innerHTML === 'Add Patient')
        {
            addPatient();
        }
        else
        {
            updatePatient();
        }
    }
}

function validateForm()
{
    let pass = true;
    let address = document.getElementById("address").value;
    let contactNumber = document.getElementById("contactNumber").value;
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let idNumber = document.getElementById("idNumber").value;
    let sex = document.getElementById("sex").value;

    if (address == null || address === "", contactNumber == null || contactNumber === "", firstName == null || firstName === "", lastName == null || lastName === "",
    idNumber == null || idNumber === "", sex == null || sex === "")
    {
        alert("Please fill in all the fields, damn.");
        pass = false;
    }

    return pass;
}