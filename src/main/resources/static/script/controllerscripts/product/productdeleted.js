initLayout("Deleted product", "Deleted Products");
sidebarLoader("/product");

//dataLoadTable("/product/findall/deleted", ["product_name","product_code","product_contact_no1","product_email","product_business_name",["product_area_id","area_name"],["product_material_id","material_name"]],8);
loadTable();

////////////////////////// 
/// Product Restore /////
//////////////////////////

function restoreProduct(){
    var objvalue = getRowObject();
    console.log(objvalue);
    restFunction('/product/restore', objvalue, "PUT", "/product", "Product");
    window.location.href = '/product';
}

function loadTable(){
    var out = "";
    fetch("/product/findall/deleted")
    .then(function(response){
        return response.json();
    })
    .then(function(products){
        for(let product of products){
            console.log(product);
            out += `<tr value=${JSON.stringify(product)} onclick='showContextMenu(` + JSON.stringify(product) + `, event)'>
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