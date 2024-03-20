var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

//configure the GUI and window parameters
initLayout("Supplier", `Supplier View - ${receivedData.supplier_code}`);
sidebarLoader("/supplier");

objectToForm('supplierEditForm', receivedData, ["type_name","area_name","material_name"]);

function deleteFormSupplier(){
    restFunction('/supplier/delete', receivedData, "DELETE", "/supplier", "Supplier");
    window.location.href = '/supplier';
}

//btnConfig("redirectButton", "/supplieredit", "btnEditWide");
//btnConfig("deleteButton", "/supplierdelete", "btnDeleteWide");
