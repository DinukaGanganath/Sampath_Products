initLayout("Deleted salesrep", "Deleted Salesreps");
sidebarLoader("/salesrep");

dataLoadTable("/salesrep/findall/deleted", ["salesrep_name","salesrep_code","salesrep_contact_no1","salesrep_email","salesrep_business_name",["salesrep_area_id","area_name"],["salesrep_material_id","material_name"]],8);

////////////////////////// 
/// Salesrep Restore /////
//////////////////////////

function restoreSalesrep(){
    var objvalue = getRowObject();
    console.log(objvalue);
    restFunction('/salesrep/restore', objvalue, "PUT", "/salesrep", "Salesrep");
    window.location.href = '/salesrep';
}