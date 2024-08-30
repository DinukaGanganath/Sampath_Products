//configure the GUI and window parameters
initLayout("Employee", "Employee Details");
sidebarLoader("/employee");

//Create the table initial values
dataLoadTable("/employee/findall/exist", ["employee_name","employee_code","employee_contact_no1","employee_email",["employee_address_area_id","area_name"],"employee_address_city"],9);

////////////////////////// 
///// Employee View //////
//////////////////////////

function viewEmployee(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/employeeview';
}

////////////////////////// 
///// Employee Edit //////
//////////////////////////

function editEmployee(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/employeeedit';
}

////////////////////////// 
//// Employee Delete /////
//////////////////////////

function deleteEmployee(){
    var objvalue = getRowObject();
    let userConfirm = window.confirm(`Are you sure to delete ${objvalue.employee_code} - ${objvalue.employee_name}`);
    if(userConfirm){
        restFunction('/employee/delete', objvalue, "DELETE", "/employee", "Employee");
    }
    window.location.href = '/employee';
}

function loadUser(){

    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/useradd';
    
}