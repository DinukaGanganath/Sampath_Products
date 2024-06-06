//configure the GUI and window parameters
initLayout("User", "User Add");
sidebarLoader("/privilage");

//Create the table initial values
dataLoadTable("/user/findall/exist", ["user_name", "user_email" ,"supplier_contact_no1",["employee_id","employee_code"]],7);

////////////////////////// 
///// User View //////
//////////////////////////

function viewSupplier(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/supplierview';
}

////////////////////////// 
///// User Edit //////
//////////////////////////

function editSupplier(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/supplieredit';
}

////////////////////////// 
//// User Delete /////
//////////////////////////

function deleteSupplier(){
    var objvalue = getRowObject();
    let userConfirm = window.confirm(`Are you sure to delete ${objvalue.supplier_code} - ${objvalue.supplier_name}`);
    if(userConfirm){
        restFunction('/user/delete', objvalue, "DELETE", "/user", "User");
    }
    window.location.href = '/user';
}