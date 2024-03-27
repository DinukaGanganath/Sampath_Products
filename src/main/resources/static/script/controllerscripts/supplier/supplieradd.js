//configure the GUI and window parameters
initLayout("Supplier", "Supplier Add");
sidebarLoader("/supplier");

document.getElementById("supplier_address_city").value = "";
document.getElementById("supplier_address_postal").value = "";

//Load the names options to the Select tag
loadOptionVal("/areas/findall", "supplier_area_id", "area_name", "Area");
loadOptionVal("/materials/findall", "supplier_material_id", "material_name", "Material");
loadOptionVal("/types/findall", "supplier_business_type", "type_name", "Business Type");

function loadDivisionVal(ele){

    var city = document.getElementById("supplier_address_city");
    var code = document.getElementById("supplier_address_postal");

    console.log(JSON.parse(ele.value));

    city.value = JSON.parse(ele.value).postal_division_id.postal_division_name;
    code.value = JSON.parse(ele.value).postal_division_id.postal_division_code;
    
    city.setAttribute("disabled","disabled");
}