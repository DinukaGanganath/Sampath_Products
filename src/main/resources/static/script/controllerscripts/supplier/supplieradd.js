//configure the GUI and window parameters
initLayout("Supplier", "Supplier Add");
sidebarLoader("/supplier");

//clear the city input and postal code input by val= ""
document.getElementById("supplier_address_city").value = "";
document.getElementById("supplier_address_postal").value = "";

//Load the names options to the Select tag
loadOptionVal("/areas/findall", "supplier_area_id", "area_name", "Area");
loadOptionVal("/materials/findall", "supplier_material_id", "material_name", "Material");
loadOptionVal("/types/findall", "supplier_business_type", "type_name", "Business Type");

function typeAddForm(){
    console.log(window.location.href);
    document.getElementById('modal').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
}

function createType(){
    var loadAfter = "/"+ window.location.href.split("/").slice(-1);
    var obj ={};
    obj.type_name = document.getElementById('type_name').value;

    $.ajax("/types/save", {
        async : false,
        type : "POST",
        data : JSON.stringify(obj),
        contentType: 'application/json',

        success : function (data, status, xhr){
            console.log("success " + status + " " + xhr);
            responseStatus = data;
            console.log(responseStatus);
        },

        error : function (xhr, status, errormsg){
            console.log("fail " + errormsg + " " + status +" " + xhr);
            console.log(xhr);
            responseStatus = errormsg;
        },
    });

    window.location.href = loadAfter;

}