var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

var idVal = [["customerid", `${receivedData["customerid"]}`], ["customer_code", `${receivedData["customer_code"]}`]];

//configure the GUI and window parameters
initLayout("Customer", `Customer View - ${receivedData.customer_code}`);
sidebarLoader("/customer");

objectToForm('customerViewForm', receivedData, ["type_name","area_name"]);
disableForm('customerViewForm', ['input', 'select']);

function deleteFormCustomer(){
    restFunction('/customer/delete', receivedData, "DELETE", "/customer", "Customer");
    window.location.href = '/customer';
}

function editFormCustomer(){
    sessionStorage.setItem("dataToSend", JSON.stringify(receivedData));
    window.location.href = '/customeredit';
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

    var city = document.getElementById("customer_address_city");
    var code = document.getElementById("customer_address_postal");

    city.value = JSON.parse(ele.value).postal_division_id.postal_division_name;
    code.value = JSON.parse(ele.value).postal_division_id.postal_division_code;
    
    city.setAttribute("disabled","disabled");
}