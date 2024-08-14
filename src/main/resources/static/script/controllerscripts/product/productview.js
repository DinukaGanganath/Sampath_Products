var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);


//configure the GUI and window parameters
initLayout("Product", `Product View - ${receivedData.product_code}`);
sidebarLoader("/product");

document.getElementById('producttype_id').value = receivedData.producttype_id.producttype_name.replaceAll("_", " ");
document.getElementById('productsize_id').value = receivedData.productsize_id.productsize_name.replaceAll("_", " ");
document.getElementById('product_unit_price').value = receivedData.product_unit_price;
document.getElementById('product_usable_time').value = receivedData.product_usable_time;
document.getElementById('product_extra').value = receivedData.product_extra;

var tabInner = '';
var tableBdy = document.getElementById('data-output');
for(var material of receivedData.product_has_material_list){
    console.log(material);
    tabInner += `<tr value="${JSON.stringify(material).trim}">
                    <td>${material.material_id.material_name.replaceAll("_", " ")}</td>
                    <td>${material.quantity_needed +" "+material.material_id.material_unit}</td>
                </tr>`;
}
tableBdy.innerHTML = tabInner;

function deleteFormProduct(){
    restFunction('/product/delete', receivedData, "DELETE", "/product", "Product");
    window.location.href = '/product';
}

function editFormProduct(){
    sessionStorage.setItem("dataToSend", JSON.stringify(receivedData));
    window.location.href = '/productedit';
}

function editObj(formId, eleList, url, method, loadAfter, navigator){
    for(ele of document.querySelectorAll("select")){
        if(ele.childNodes.length === 0){
            ele.parentNode.removeChild(ele);
        }
    }

    validForm(formId, eleList, url, method, loadAfter, navigator, idVal);
}

function loadDivisionVal(ele){

    var city = document.getElementById("product_address_city");
    var code = document.getElementById("product_address_postal");

    city.value = JSON.parse(ele.value).postal_division_id.postal_division_name;
    code.value = JSON.parse(ele.value).postal_division_id.postal_division_code;
    
    city.setAttribute("disabled","disabled");
}
