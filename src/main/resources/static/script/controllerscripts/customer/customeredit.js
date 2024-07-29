var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
//console.log(receivedData);

var idVal = [["customer_id", `${receivedData["customer_id"]}`], ["customer_code", `${receivedData["customer_code"]}`]];
//console.log(idVal);

var optionIdList = [
    ["/areas/findall", "customer_area_id", "area_name", "Area"],
    ["/types/findall", "customer_business_type", "type_name", "Business Type"],
];

initLayout("Customer Edit", `Customer Edit - ${receivedData.customer_code}`);
sidebarLoader("/customer");

optionInput(optionIdList, receivedData);

objectToForm('customerEditForm', receivedData, ["type_name","area_name"]);

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

    var city = document.getElementById("customer_address_city");
    var code = document.getElementById("customer_address_postal");

    city.value = JSON.parse(ele.value).postal_division_id.postal_division_name;
    code.value = JSON.parse(ele.value).postal_division_id.postal_division_code;
    
    city.setAttribute("disabled","disabled");
}