initLayout("Deleted supplier", "Deleted Suppliers");
sidebarLoader("/supplier");

dataLoadTable("/supplier/findall/deleted", ["supplier_name","supplier_code","supplier_contact_no1","supplier_email","supplier_business_name",["supplier_area_id","area_name"],["supplier_material_id","material_name"]],8);

////////////////////////// 
/// Supplier Restore /////
//////////////////////////

function restoreSupplier(){
    var objvalue = getRowObject();
    console.log(objvalue);
    restFunction('/supplier/restore', objvalue, "PUT", "/supplier", "Supplier");
    window.location.href = '/supplier';
}