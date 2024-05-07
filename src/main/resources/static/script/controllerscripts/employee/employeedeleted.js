initLayout("Deleted employee", "Deleted Employees");
sidebarLoader("/employee");

dataLoadTable("/employee/findall/deleted", ["employee_name","employee_code","employee_contact_no1","employee_email",["employee_address_area_id","area_name"],"employee_address_city"],7);

////////////////////////// 
/// Employee Restore /////
//////////////////////////

function restoreEmployee(){
    var objvalue = getRowObject();
    console.log(objvalue);
    restFunction('/employee/restore', objvalue, "PUT", "/employee", "Employee");
    window.location.href = '/employee';
}