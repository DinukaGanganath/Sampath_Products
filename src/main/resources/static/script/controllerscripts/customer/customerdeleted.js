initLayout("Deleted customer", "Deleted Customers");
sidebarLoader("/customer");

dataLoadTable("/customer/findall/deleted", ["customer_name","customer_code","customer_contact_no1","customer_email","customer_business_name",["customer_area_id","area_name"]],8);

////////////////////////// 
/// Customer Restore /////
//////////////////////////

function restoreCustomer(){
    var objvalue = getRowObject();
    console.log(objvalue);
    restFunction('/customer/restore', objvalue, "PUT", "/customer", "Customer");
    window.location.href = '/customer';
}