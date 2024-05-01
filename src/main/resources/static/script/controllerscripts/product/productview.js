var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

var idVal = [["product_id", `${receivedData["product_id"]}`], ["product_code", `${receivedData["product_code"]}`]];

//configure the GUI and window parameters
initLayout("Product", `Product View - ${receivedData.product_code}`);
sidebarLoader("/product");

objectToForm('productViewForm', receivedData, ["type_name","area_name","material_name"]);
disableForm('productViewForm', ['input', 'select']);

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
