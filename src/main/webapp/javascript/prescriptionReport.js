function loadPrescriptionData(endpoint, year){
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            let prescriptionDataList = JSON.parse(this.responseText);
            let prescriptionList = ' <ul class="w3-ul w3-card-4"> ';

            if (prescriptionDataList.length === 0){
                alert("No result found");
            }

            for (let i = 0; i <prescriptionDataList.length; i++) {
                prescriptionList +=
                    ' <li class="w3-bar"> ' +


                    ' <img src="https://cached.imagescaler.hbpl.co.uk/resize/scaleWidth/800/cached.offlinehbpl.hbpl.co.uk/news/PGH/iStock-643707544_620x415px-20180312121652900.jpg" class="w3-bar-item w3-circle w3-hide-small" style="width:85px"> ' +
                    ' <div class="w3-bar-item"> ' +
                    ' <span class="w3-large"> Medicine Name: ' +  prescriptionDataList[i].medicineName  + ' </span> <br>' +
                    ' <span> Medicine brand: ' +  prescriptionDataList[i].medicineBrand  + ' </span><br> ' +
                    ' <span> Doses: ' +  prescriptionDataList[i].dose  + ' </span><br> ' +
                    ' <span> Prescriptions: ' +  prescriptionDataList[i].prescriptions  + ' </span> <br>' +
                    ' </div> ';
            }
            prescriptionList += "</ul>";
            document.getElementById("prescriptionReport").innerHTML = prescriptionList;
        }
    };
    xhttp.open("POST", "/healthcentremanagement-front-end/api/prescription/" + endpoint, true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.send(year);
}

function selectYear(){
    let dateDropdown = document.getElementById('year');

    let currentYear = new Date().getFullYear();
    let earliestYear = 2015;
    while (currentYear >= earliestYear) {
        let dateOption = document.createElement('option');
        dateOption.text = currentYear;
        dateOption.value = currentYear;
        dateDropdown.add(dateOption);
        currentYear -= 1;
    }
}


function getPrescriptions() {
    let div = document.getElementById("extra");
    let yearButton = document.createElement("button");
    let firstHalfYearButton = document.createElement("button");
    let secondHalfYearButton = document.createElement("button");
    let firstQuarterYearButton = document.createElement("button");
    let secondQuarterYearButton = document.createElement("button");
    let thirdQuarterYearButton = document.createElement("button");
    let fourthQuarterYearButton = document.createElement("button");
    let hr = document.createElement("hr");

    yearButton.innerHTML = "PerYear";
    yearButton.addEventListener("click", getYear);
    yearButton.style.marginRight = "5px";
    yearButton.className = "btn-custom";

    firstHalfYearButton.innerHTML = "1st Half/Year";
    firstHalfYearButton.addEventListener("click", getFirstHalfOfYear);
    firstHalfYearButton.style.marginRight= "5px";
    firstHalfYearButton.className = "btn-custom";

    secondHalfYearButton.innerHTML = "2nd Half/Year";
    secondHalfYearButton.addEventListener("click", getSecondHalfOfYear);
    secondHalfYearButton.style.marginRight= "5px";
    secondHalfYearButton.className = "btn-custom";

    firstQuarterYearButton.innerHTML = "1st Quarter/Year";
    firstQuarterYearButton.addEventListener("click", getFirstQuarterOfYear);
    firstQuarterYearButton.style.marginRight= "5px";
    firstQuarterYearButton.className = "btn-custom";

    secondQuarterYearButton.innerHTML = "2nd Quarter/Year";
    secondQuarterYearButton.addEventListener("click", getSecondQuarterOfYear);
    secondQuarterYearButton.style.marginRight= "5px";
    secondQuarterYearButton.className = "btn-custom";

    thirdQuarterYearButton.innerHTML = "3rd Quarter/Year";
    thirdQuarterYearButton.addEventListener("click", getThirdQuarterOfYear);
    thirdQuarterYearButton.style.marginRight= "5px";
    thirdQuarterYearButton.className = "btn-custom";

    fourthQuarterYearButton.innerHTML = "4th Quarter/Year";
    fourthQuarterYearButton.addEventListener("click", getFourthQuarterOfYear);
    fourthQuarterYearButton.style.marginRight= "5px";
    fourthQuarterYearButton.className = "btn-custom";

    div.appendChild(yearButton);
    div.appendChild(firstHalfYearButton);
    div.appendChild(secondHalfYearButton);
    div.appendChild(firstQuarterYearButton);
    div.appendChild(secondQuarterYearButton);
    div.appendChild(thirdQuarterYearButton);
    div.appendChild(fourthQuarterYearButton);




    function getFirstHalfOfYear() {
        let year = document.getElementById("year").value;
        let endpoint = "getPrescriptionsBy1stHalfOfYear";
        loadPrescriptionData(endpoint, year);
    }

    function getSecondHalfOfYear() {
        let year = document.getElementById("year").value;
        let endpoint = "getPrescriptionsBy2ndHalfOfYear";
        loadPrescriptionData(endpoint, year);
    }

    function getFirstQuarterOfYear() {
        let year = document.getElementById("year").value;
        let endpoint = "getPrescriptionsBy1stQuarterOfYear";
        loadPrescriptionData(endpoint, year);
    }

    function getSecondQuarterOfYear() {
        let year = document.getElementById("year").value;
        let endpoint = "getPrescriptionsBy2ndQuarterOfYear";
        loadPrescriptionData(endpoint, year);
    }

    function getThirdQuarterOfYear() {
        let year = document.getElementById("year").value;
        let endpoint = "getPrescriptionsBy3rdQuarterOfYear";
        loadPrescriptionData(endpoint, year);
    }

    function getFourthQuarterOfYear() {
        let year = document.getElementById("year").value;
        let endpoint = "getPrescriptionsBy4thQuarterOfYear";
        loadPrescriptionData(endpoint, year);
    }

    function getYear() {
        let year = document.getElementById("year").value;
        let endpoint = "getPrescriptionsByYear";
        loadPrescriptionData(endpoint, year);
    }
}
