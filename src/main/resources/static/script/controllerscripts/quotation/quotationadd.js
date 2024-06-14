//configure the GUI and window parameters
initLayout("Quotation", "Quotation Add");
sidebarLoader("/quotation");

//submenu created
var jsonList = [
    {
        "str" : "Create",
        "url" : "/createquot",
        "status" : "active",
    },
    {
        "str" : "All",
        "url" : "/quotation"
    },
    {
        "str" : "Requested",
        "url" : "/requestedquot"
    },
    {
        "str" : "Valid",
        "url" : "/validquot"
    },
    {
        "str" : "Ending",
        "url" : "/endingquot"
    },
    {
        "str" : "Expired",
        "url" : "/expiredquot"
    },
]
createThinLongDiv(jsonList);

//clear the city input and postal code input by val= ""
document.getElementById("quotation_address_city").value = "";
document.getElementById("quotation_address_postal").value = "";

//Load the names options to the Select tag
loadOptionVal("/areas/findall", "quotation_area_id", "area_name", "Area");
loadOptionVal("/materials/findall", "quotation_material_id", "material_name", "Material");
loadOptionVal("/types/findall", "quotation_business_type", "type_name", "Business Type");

