//configure the GUI and window parameters
initLayout("Supplier", "Supplier Add");
sidebarLoader("/supplier");

document.getElementById("supplier_address_city").value = "";
document.getElementById("supplier_address_postal").value = "";

//Load the names options to the Select tag
loadOptionVal("/areas/findall", "supplier_area_id", "area_name", "Area");
loadOptionVal("/materials/findall", "supplier_material_id", "material_name", "Material");
loadOptionVal("/types/findall", "supplier_business_type", "type_name", "Business Type");

