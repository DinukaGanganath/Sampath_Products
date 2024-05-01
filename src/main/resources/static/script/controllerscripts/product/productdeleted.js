initLayout("Deleted product", "Deleted Products");
sidebarLoader("/product");

dataLoadTable("/product/findall/deleted", ["product_name","product_code","product_contact_no1","product_email","product_business_name",["product_area_id","area_name"],["product_material_id","material_name"]],8);

////////////////////////// 
/// Product Restore /////
//////////////////////////

function restoreProduct(){
    var objvalue = getRowObject();
    console.log(objvalue);
    restFunction('/product/restore', objvalue, "PUT", "/product", "Product");
    window.location.href = '/product';
}