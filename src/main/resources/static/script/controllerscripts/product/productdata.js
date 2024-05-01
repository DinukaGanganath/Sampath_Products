//configure the GUI and window parameters
initLayout("Product", "Product Details");
sidebarLoader("/product");

//Create the table initial values
dataLoadTable("/product/findall/exist", ["product_name","product_code","product_unit_quantity","product_unit_price","product_usable_time"],7);

////////////////////////// 
///// Product View //////
//////////////////////////

function viewProduct(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/productview';
}

////////////////////////// 
///// Product Edit //////
//////////////////////////

function editProduct(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/productedit';
}

////////////////////////// 
//// Product Delete /////
//////////////////////////

function deleteProduct(){
    var objvalue = getRowObject();
    let userConfirm = window.confirm(`Are you sure to delete ${objvalue.product_code} - ${objvalue.product_name}`);
    if(userConfirm){
        restFunction('/product/delete', objvalue, "DELETE", "/product", "Product");
    }
    window.location.href = '/product';
}