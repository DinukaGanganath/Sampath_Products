//configure the GUI and window parameters
initLayout("Product", "Product Add");
sidebarLoader("/product");

var jsonList = [
    {
        "string" : "size",
        "url" : "/size"
    },
    {
        "string" : "type",
        "url" : "/type"
    },
    {
        "string" : "product",
        "url" : "/product"
    },
]
createThinLongDiv(jsonList);

document.getElementById("product_address_city").value = "";
document.getElementById("product_address_postal").value = "";

//Load the names options to the Select tag
loadOptionVal("/areas/findall", "product_area_id", "area_name", "Area");
loadOptionVal("/materials/findall", "product_material_id", "material_name", "Material");
loadOptionVal("/types/findall", "product_business_type", "type_name", "Business Type");

