//configure the GUI and window parameters
initLayout("Supplier", "Supplier Details");
sidebarLoader("/supplier");

//Create the table initial values
dataLoadTable("/supplier/findall/exist", ["supplier_name","supplier_code","supplier_contact_no1","supplier_email","supplier_business_name",["supplier_area_id","area_name"],["supplier_material_id","material_name"]],7);

////////////////////////// 
///// Supplier View //////
//////////////////////////

function viewSupplier(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/supplierview';
}

////////////////////////// 
///// Supplier Edit //////
//////////////////////////

function editSupplier(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/supplieredit';
}

////////////////////////// 
//// Supplier Delete /////
//////////////////////////

function deleteSupplier(){
    var objvalue = getRowObject();
    let userConfirm = window.confirm(`Are you sure to delete ${objvalue.supplier_code} - ${objvalue.supplier_name}`);
    if(userConfirm){
        restFunction('/supplier/delete', objvalue, "DELETE", "/supplier", "Supplier");
    }
    window.location.href = '/supplier';
}