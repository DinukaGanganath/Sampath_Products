var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

var idVal = [["supplierid", `${receivedData["supplierid"]}`], ["supplier_code", `${receivedData["supplier_code"]}`]];

//configure the GUI and window parameters
initLayout("Supplier", `Supplier View - ${receivedData.supplier_code}`);
sidebarLoader("/supplier");

objectToForm('supplierEditForm', receivedData, ["type_name","area_name","material_name"]);

function deleteFormSupplier(){
    restFunction('/supplier/delete', receivedData, "DELETE", "/supplier", "Supplier");
    window.location.href = '/supplier';
}

function editFormSupplier(){
    sessionStorage.setItem("dataToSend", JSON.stringify(receivedData));
    window.location.href = '/supplieredit';
}


//btnConfig("redirectButton", "/supplieredit", "btnEditWide");
//btnConfig("deleteButton", "/supplierdelete", "btnDeleteWide");
