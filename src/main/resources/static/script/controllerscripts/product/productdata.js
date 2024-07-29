//configure the GUI and window parameters
initLayout("Product", "Product Details");
sidebarLoader("/product");

//Create the table initial values
loadTable();
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

function loadTable(){
    var out = "";
    fetch("/product/findall/exist")
    .then(function(response){
        return response.json();
    })
    .then(function(products){
        for(let product of products){
            console.log(product);
            out += `<tr value=${JSON.stringify(product)}>
                <td>${product.producttype_id.producttype_name.replaceAll("_"," ")}</td>
                <td>${product.product_code}</td>
                <td>${product.productsize_id.productsize_name.replaceAll("_", " ")}</td>   
                <td>Rs. ${product.product_unit_price}.00</td> 
                <td>${product.product_usable_time} months</td>           
            </tr>`
        }
        document.getElementById("data-output").innerHTML = out;
    });
}
