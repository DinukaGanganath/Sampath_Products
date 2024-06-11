var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

var idVal = [["supplierid", `${receivedData["supplierid"]}`], ["supplier_code", `${receivedData["supplier_code"]}`]];

//configure the GUI and window parameters
initLayout("Supplier", `Supplier View - ${receivedData.supplier_code}`);
sidebarLoader("/supplier");

objectToForm('supplierViewForm', receivedData, ["type_name","area_name","material_name"]);
disableForm('supplierViewForm', ['input', 'select']);

function deleteFormSupplier(){
    restFunction('/supplier/delete', receivedData, "DELETE", "/supplier", "Supplier");
    window.location.href = '/supplier';
}

function editFormSupplier(){
    sessionStorage.setItem("dataToSend", JSON.stringify(receivedData));
    window.location.href = '/supplieredit';
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

    var city = document.getElementById("supplier_address_city");
    var code = document.getElementById("supplier_address_postal");

    city.value = JSON.parse(ele.value).postal_division_id.postal_division_name;
    code.value = JSON.parse(ele.value).postal_division_id.postal_division_code;
    
    city.setAttribute("disabled","disabled");
}
