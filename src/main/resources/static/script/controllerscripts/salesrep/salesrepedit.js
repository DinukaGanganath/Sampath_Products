var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
//console.log(receivedData);

var idVal = [["salesrepid", `${receivedData["salesrepid"]}`], ["salesrep_code", `${receivedData["salesrep_code"]}`]];
//console.log(idVal);

var optionIdList = [
    ["/areas/findall", "salesrep_area_id", "area_name", "Area"],
    ["/materials/findall", "salesrep_material_id", "material_name", "Material"],
    ["/types/findall", "salesrep_business_type", "type_name", "Business Type"],
];

initLayout("Salesrep Edit", `Salesrep Edit - ${receivedData.salesrep_code}`);
sidebarLoader("/salesrep");

optionInput(optionIdList, receivedData);

objectToForm('salesrepEditForm', receivedData, ["type_name","area_name","material_name"]);

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

