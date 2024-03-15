var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

//configure the GUI and window parameters
initLayout("Supplier", `Supplier View - ${receivedData.supplier_code}`);
sidebarLoader("/supplier");

objectToForm('supplierEditForm', receivedData, ["type_name","area_name","material_name"]);

btnConfig("redirectButton", "/supplieredit", "btnEditWide");
btnConfig("deleteButton", "/supplierdelete", "btnDeleteWide")
