var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

var idVal = [["salesrepid", `${receivedData["salesrepid"]}`], ["salesrep_code", `${receivedData["salesrep_code"]}`]];

//configure the GUI and window parameters
initLayout("Salesrep", `Salesrep View - ${receivedData.salesrep_code}`);
sidebarLoader("/salesrep");

objectToForm('salesrepViewForm', receivedData, ["type_name","area_name","material_name"]);
disableForm('salesrepViewForm', ['input', 'select']);

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
