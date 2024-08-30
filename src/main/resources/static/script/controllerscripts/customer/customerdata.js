//configure the GUI and window parameters
initLayout("Customer", "Customer Details");
sidebarLoader("/customer");

//Create the table initial values
dataLoadTable("/customer/findall/exist", ["customer_name","customer_code","customer_contact_no1","customer_email","customer_business_name",["customer_area_id","area_name"]],7);

//submenu created
var jsonList = [
    {
        "str" : "Customer",
        "url" : "/customer",
        "status" : "active"
    },
    {
        "str" : "Ordered",
        "url" : "/customerorder"
    },
    {
        "str" : "Ready",
        "url" : "/customerorderready"
    },
    {
        "str" : "Shipped",
        "url" : "/customerordershipped",
    },
    {
        "str" : "Delivered",
        "url" : "/customerorderdelivered"
    },
]
createThinLongDiv(jsonList);

////////////////////////// 
///// Customer View //////
//////////////////////////

function viewCustomer(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/customerview';
}

////////////////////////// 
///// Customer Edit //////
//////////////////////////

function editCustomer(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/customeredit';
}

////////////////////////// 
//// Customer Delete /////
//////////////////////////

function deleteCustomer(){
    var objvalue = getRowObject();
    let userConfirm = window.confirm(`Are you sure to delete ${objvalue.customer_code} - ${objvalue.customer_name}`);
    if(userConfirm){
        restFunction('/customer/delete', objvalue, "DELETE", "/customer", "Customer");
    }
    window.location.href = '/customer';
}