function loadMedicineReport(){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let medicineReportDataList = JSON.parse(this.responseText);
            let medicineReport = ' <ul class="w3-ul w3-card-4"> ';

            //prescriptionDataList.reverse();

            for (let i = 0; i <medicineReportDataList.length; i++) {
                medicineReport +=
                    ' <li class="w3-bar"> ' +


                    ' <img src="https://cdn0.iconfinder.com/data/icons/health-icons-rounded/110/Medicine-2-512.png" class="w3-bar-item w3-circle w3-hide-small" style="width:85px"> ' +
                    ' <div class="w3-bar-item"> ' +
                    ' <span class="w3-large"> Medicine Name: ' +  medicineReportDataList[i].medicineName  + ' </span> <br>' +
                    ' <span> Medicine brand: ' +  medicineReportDataList[i].medicineBrand  + ' </span><br> ' +
                    ' <span> Stock: ' +  medicineReportDataList[i].stock  + ' </span><br> ' +
                    ' <span> Current stock: ' +  medicineReportDataList[i].currentStock  + ' </span> <br>' +
                    ' </div> ';
            }
            medicineReport += "</ul>";
            document.getElementById("medicineReport").innerHTML = medicineReport;
        }
    };
    xhttp.open("GET", "/healthcentremanagement-front-end/api/medicine/getReport", true);
    xhttp.send();
}