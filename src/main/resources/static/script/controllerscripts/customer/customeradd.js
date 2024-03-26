//configure the GUI and window parameters
initLayout("Customer", "Customer Details");
sidebarLoader("/customer");

document.getElementById("customer_address_city").value = "";
document.getElementById("customer_address_postal").value = "";

//Load the names options to the Select tag
loadOptionVal("/areas/findall", "customer_area_id", "area_name", "Area");
loadOptionVal("/types/findall", "customer_business_type", "type_name", "Business Type");

function loadDivisionVal(ele){

    var city = document.getElementById("customer_address_city");
    var code = document.getElementById("customer_address_postal");

    city.value = JSON.parse(ele.value).postal_division_id.postal_division_name;
    code.value = JSON.parse(ele.value).postal_division_id.postal_division_code;
    
    city.setAttribute("disabled","disabled");
}