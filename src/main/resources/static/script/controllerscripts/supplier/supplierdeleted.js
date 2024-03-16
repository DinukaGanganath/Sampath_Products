initLayout("Deleted supplier", "Deleted Suppliers");
sidebarLoader("/supplier");

fetch("/supplier/findall")
.then(function(response){
    return response.json();
})
.then(function(suppliers){
    var placeholder = document.querySelector("#data-output");
    var out = "";
    for(let supplier of suppliers){
        if(supplier.supplier_deleted == 1){
            out += `
                <tr class="sup_raw" onclick='showContextMenu(` + JSON.stringify(supplier) + `, event)'>
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
});

////////////////////////// 
/// Supplier Restore /////
//////////////////////////

function restoreSupplier(){
    var objvalue = getRowObject();
    console.log(objvalue);
    restFunction('/supplier/restore', objvalue, "PUT", "/supplier", "Supplier");
    //window.location.href = '/supplier';
}