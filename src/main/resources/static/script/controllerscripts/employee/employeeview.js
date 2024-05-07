var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

var idVal = [["employeeid", `${receivedData["employeeid"]}`], ["employee_code", `${receivedData["employee_code"]}`]];

//configure the GUI and window parameters
initLayout("Employee", `Employee View - ${receivedData.employee_code}`);
sidebarLoader("/employee");

objectToForm('employeeViewForm', receivedData, ["area_name"]);
disableForm('employeeViewForm', ['input', 'select']);

loadIdDetails(document.getElementById("employee_nic"));

setAge();

document.getElementById('employee_gender').value = receivedData['employee_gender'];

function deleteFormEmployee(){
    restFunction('/employee/delete', receivedData, "DELETE", "/employee", "Employee");
    window.location.href = '/employee';
}

function editFormEmployee(){
    sessionStorage.setItem("dataToSend", JSON.stringify(receivedData));
    window.location.href = '/employeeedit';
}

function editObj(formId, eleList, url, method, loadAfter, navigator){
    for(ele of document.querySelectorAll("select")){
        if(ele.childNodes.length === 0){
            ele.parentNode.removeChild(ele);
        }
    }

    validForm(formId, eleList, url, method, loadAfter, navigator, idVal);
}

function setAge(){
    var birthdayInput = document.getElementById("employee_birthdate").value;
    var today = new Date();
    var birthDate = new Date(birthdayInput);
    var age = today.getFullYear() - birthDate.getFullYear();
    console.log(birthdayInput);
    document.getElementById('employee_age').value = age;
}
