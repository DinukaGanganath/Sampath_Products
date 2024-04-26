initLayout("Deleted salesrep", "Deleted Salesreps");
sidebarLoader("/salesrep");

dataLoadTable("/salesrep/findall/deleted", ["salesrep_name","salesrep_code","salesrep_contact_no1","salesrep_email",["salesrep_address_area_id","area_name"],"salesrep_address_city"],7);

////////////////////////// 
/// Salesrep Restore /////
//////////////////////////

function restoreSalesrep(){
    var objvalue = getRowObject();
    console.log(objvalue);
    restFunction('/salesrep/restore', objvalue, "PUT", "/salesrep", "Salesrep");
    window.location.href = '/salesrep';
}