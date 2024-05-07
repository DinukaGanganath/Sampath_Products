var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
//console.log(receivedData);

var idVal = [["employee_id", `${receivedData["employee_id"]}`], ["employee_code", `${receivedData["employee_code"]}`]];
//console.log(idVal);

var optionIdList = [
    ["/areas/findall", "employee_address_area_id", "area_name", "Area"]
];

initLayout("Employee Edit", `Employee Edit - ${receivedData.employee_code}`);
sidebarLoader("/employee");


optionInput(optionIdList, receivedData);

objectToForm('employeeEditForm', receivedData, ["area_name"]);

loadIdDetails(document.getElementById("employee_nic"));

setAge();

document.getElementById('employee_gender').value = receivedData['employee_gender'];

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

    var city = document.getElementById("employee_address_city");
    var code = document.getElementById("employee_address_postal");

    console.log(JSON.parse(ele.value));
    city.value = JSON.parse(ele.value).postal_division_id.postal_division_name;
    code.value = JSON.parse(ele.value).postal_division_id.postal_division_code;
    
    city.setAttribute("disabled","disabled");
}

