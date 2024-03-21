//configure the GUI and window parameters
initLayout("Supplier", "Supplier Details");
sidebarLoader("/supplier");

//Create the table initial values
fetch("/supplier/findall")
.then(function(response){
    return response.json();
})
.then(function(suppliers){
    var placeholder = document.querySelector("#data-output");
    var out = "";
    for(let supplier of suppliers){
        if(supplier.supplier_deleted == 0){
            out += `
                <tr class="sup_raw"  onclick='showContextMenu(` + JSON.stringify(supplier) + `, event)'>
                    <td>${supplier.supplier_name}</td>
                    <td>${supplier.supplier_code}</td>
                    <td>${supplier.supplier_contact_no1}</td>
                    <td>${supplier.supplier_email}</td>
                    <td>${supplier.supplier_business_name}</td>
                    <td>${supplier.supplier_area_id.area_name}</td>
                    <td>${supplier.supplier_material_id.material_name}</td>
                </tr>
            `;
        }
    }
    placeholder.innerHTML = out;
})

//Load the names options to the Select tag
loadOptionVal("/areas/findall", "supplier_area_id", "area_name", "Area");
loadOptionVal("/materials/findall", "supplier_material_id", "material_name", "Material");
loadOptionVal("/types/findall", "supplier_business_type", "type_name", "Business Type");

////////////////////////// 
///// Supplier View //////
//////////////////////////

function viewSupplier(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/supplierview';
}

////////////////////////// 
///// Supplier Edit //////
//////////////////////////

function editSupplier(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/supplieredit';
}

////////////////////////// 
//// Supplier Delete /////
//////////////////////////

function deleteSupplier(){
    var objvalue = getRowObject();
    let userConfirm = window.confirm(`Are you sure to delete ${objvalue.supplier_code} - ${objvalue.supplier_name}`);
    if(userConfirm){
        restFunction('/supplier/delete', objvalue, "DELETE", "/supplier", "Supplier");
    }
    window.location.href = '/supplier';
}