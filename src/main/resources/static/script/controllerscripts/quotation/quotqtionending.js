//configure the GUI and window parameters
initLayout("Quotation", "Quotation Details");
sidebarLoader("/quotation");

//submenu created
var jsonList = [
    {
        "str" : "All Quotation",
        "url" : "/quotation"
    },
    {
        "str" : "Requested Quotation",
        "url" : "/requestedquot"
    },
    {
        "str" : "Valid Quotation",
        "url" : "/validquot"
    },
    {
        "str" : "Ending Quotation",
        "url" : "/endingquot",
        "status" : "active",
    },
    {
        "str" : "Expired Quotation",
        "url" : "/expiredquot"
    },
]
createThinLongDiv(jsonList);

//Create the table initial values
dataLoadTable("/quotation/findall/exist", ["quotation_name","quotation_code","quotation_contact_no1","quotation_email","quotation_business_name",["quotation_area_id","area_name"],["quotation_material_id","material_name"]],7);

////////////////////////// 
///// Quotation View //////
//////////////////////////

function viewQuotation(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/quotationview';
}

////////////////////////// 
///// Quotation Edit //////
//////////////////////////

function editQuotation(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/quotationedit';
}

////////////////////////// 
//// Quotation Delete /////
//////////////////////////

function deleteQuotation(){
    var objvalue = getRowObject();
    let userConfirm = window.confirm(`Are you sure to delete ${objvalue.quotation_code} - ${objvalue.quotation_name}`);
    if(userConfirm){
        restFunction('/quotation/delete', objvalue, "DELETE", "/quotation", "Quotation");
    }
    window.location.href = '/quotation';
}