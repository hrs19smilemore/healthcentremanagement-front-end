function loadPatientInfoAndPrescriptions(patientId) {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let patientDataList = JSON.parse(this.responseText);
            let patientInfoList;
            let patientPrescriptions = [];
            if (patientDataList.length === 0){
                alert("No info found");
            }
            for (let i = 0; i < patientDataList.length; i++) {
                patientInfoList =
                    ' <td> ' + patientDataList[i].identification.number + ' </td> ' +
                    ' <td> ' + patientDataList[i].identification.sex + ' </td> ' +
                    ' <td> ' + patientDataList[i].adress + ' </td> ' +
                    ' <td> ' + patientDataList[i].contactnumber + ' </td> ' +
                    ' <td> ' + patientDataList[i].firstname + ' </td> ' +
                    ' <td> ' + patientDataList[i].lastname + ' </td> ';
                for (let c = 0; c < patientDataList[i].prescriptions.length; c++) {
                    patientPrescriptions +=
                        ' <tr> ' +
                        ' <td> ' + patientDataList[i].prescriptions[c].dose + ' </td> ' +
                        ' <td> ' + patientDataList[i].prescriptions[c].prescription_date + '</td> ' +
                        ' <td> ' + patientDataList[i].prescriptions[c].medicine.name + ' </td> ' +
                        ' <td> ' + patientDataList[i].prescriptions[c].medicine.brand + ' </td> ' +
                        ' <tr> ';
                }
            }
            if (!(patientInfoList == null)) {
                document.getElementById("row").innerHTML = patientInfoList;
            }
            document.getElementById("list").innerHTML = patientPrescriptions;
        }
    };
    xhttp.open("POST", "/healthcentremanagement-front-end/api/patient/getPatientInfo", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(patientId);
}

function getPatientInfo(){
    let searchButton = document.getElementById("search");
    searchButton.addEventListener("click", getInfo);

    function getInfo(){
        let patientId = document.getElementById("patientId").value;
        document.getElementById("row").innerHTML = "";
        document.getElementById("list").innerHTML = "";
        loadPatientInfoAndPrescriptions(patientId);
    }
}