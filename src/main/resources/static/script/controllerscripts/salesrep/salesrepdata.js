//configure the GUI and window parameters
initLayout("Salesrep", "Salesrep Details");
sidebarLoader("/salesrep");

//Create the table initial values
dataLoadTable("/salesrep/findall/exist", ["salesrep_name","salesrep_code","salesrep_contact_no1","salesrep_email",["salesrep_address_area_id","area_name"],"salesrep_address_city"],9);

////////////////////////// 
///// Salesrep View //////
//////////////////////////

function viewSalesrep(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/salesrepview';
}

////////////////////////// 
///// Salesrep Edit //////
//////////////////////////

function editSalesrep(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/salesrepedit';
}

////////////////////////// 
//// Salesrep Delete /////
//////////////////////////

function deleteSalesrep(){
    var objvalue = getRowObject();
    let userConfirm = window.confirm(`Are you sure to delete ${objvalue.salesrep_code} - ${objvalue.salesrep_name}`);
    if(userConfirm){
        restFunction('/salesrep/delete', objvalue, "DELETE", "/salesrep", "Salesrep");
    }
    window.location.href = '/salesrep';
}