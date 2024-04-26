var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

var idVal = [["salesrepid", `${receivedData["salesrepid"]}`], ["salesrep_code", `${receivedData["salesrep_code"]}`]];

//configure the GUI and window parameters
initLayout("Salesrep", `Salesrep View - ${receivedData.salesrep_code}`);
sidebarLoader("/salesrep");

objectToForm('salesrepViewForm', receivedData, ["area_name"]);
disableForm('salesrepViewForm', ['input', 'select']);

loadIdDetails(document.getElementById("salesrep_nic"));

setAge();

document.getElementById('salesrep_gender').value = receivedData['salesrep_gender'];

function deleteFormSalesrep(){
    restFunction('/salesrep/delete', receivedData, "DELETE", "/salesrep", "Salesrep");
    window.location.href = '/salesrep';
}

function editFormSalesrep(){
    sessionStorage.setItem("dataToSend", JSON.stringify(receivedData));
    window.location.href = '/salesrepedit';
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
    var birthdayInput = document.getElementById("salesrep_birthdate").value;
    var today = new Date();
    var birthDate = new Date(birthdayInput);
    var age = today.getFullYear() - birthDate.getFullYear();
    console.log(birthdayInput);
    document.getElementById('salesrep_age').value = age;
}
