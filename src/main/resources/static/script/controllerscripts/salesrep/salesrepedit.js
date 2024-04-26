var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
//console.log(receivedData);

var idVal = [["salesrep_id", `${receivedData["salesrep_id"]}`], ["salesrep_code", `${receivedData["salesrep_code"]}`]];
//console.log(idVal);

var optionIdList = [
    ["/areas/findall", "salesrep_address_area_id", "area_name", "Area"]
];

initLayout("Salesrep Edit", `Salesrep Edit - ${receivedData.salesrep_code}`);
sidebarLoader("/salesrep");


optionInput(optionIdList, receivedData);

objectToForm('salesrepEditForm', receivedData, ["area_name"]);

loadIdDetails(document.getElementById("salesrep_nic"));

setAge();

document.getElementById('salesrep_gender').value = receivedData['salesrep_gender'];

for(ele of document.querySelectorAll("input")){
    ele.classList.add("valid");
}

function editObj(formId, eleList, url, method, loadAfter, navigator){
    for(ele of document.querySelectorAll("select")){
        if(ele.childNodes.length === 0){
            ele.parentNode.removeChild(ele);
        }
    }
    validForm(formId, eleList, url, method, loadAfter, navigator, idVal);
}

function loadDivisionVal(ele){

    var city = document.getElementById("salesrep_address_city");
    var code = document.getElementById("salesrep_address_postal");

    console.log(JSON.parse(ele.value));
    city.value = JSON.parse(ele.value).postal_division_id.postal_division_name;
    code.value = JSON.parse(ele.value).postal_division_id.postal_division_code;
    
    city.setAttribute("disabled","disabled");
}

