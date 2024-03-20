var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

var idVal = ["supplierid", receivedData["supplierid"]];
console.log(idVal);

var optionIdList = [
    ["/areas/findall", "supplier_area_id", "area_name", "Area"],
    ["/materials/findall", "supplier_material_id", "material_name", "Material"],
    ["/types/findall", "supplier_business_type", "type_name", "Business Type"],
];

initLayout("Supplier Edit", `Supplier Edit - ${receivedData.supplier_code}`);
sidebarLoader("/supplier");

optionInput(optionIdList);

objectToForm('supplierEditForm', receivedData, ["type_name","area_name","material_name"]);

for(ele of document.querySelectorAll("input")){
    ele.classList.add("valid");
}

function editObj(formId, eleList, url, method, loadAfter, navigator){
    validForm(formId, eleList, url, method, loadAfter, navigator, idVal);
}

