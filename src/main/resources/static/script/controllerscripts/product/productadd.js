//configure the GUI and window parameters
initLayout("Product", "Product Add");
sidebarLoader("/product");

loadOptionVal("/productsizes/findall/exist", "productsize_id", "productsize_name", "Product Type");
loadOptionVal("/producttypes/findall/exist", "producttype_id", "producttype_name", "Product Size");

loadTabData();

var jsonList = [
    {
        "str" : "Product size",
        "url" : "/productsizes"
    },
    {
        "str" : "Product type",
        "url" : "/producttypes"
    },
    {
        "str" : "Product",
        "url" : "/product",
        "status" : "active",
    },
]
createThinLongDiv(jsonList);

function loadTabData(){
    var out = "";

    fetch("/material/findall/exist")
    .then(function(response){
        return response.json();
    })
    .then(function(materials){
        for(let material of materials){
            out += `<tr value=${JSON.stringify(material)}>
                <td>${material.material_name.replaceAll("_", " ")}</td>
                <td>
                    <div style="display:flex">
                        <input type="number" min="0" id="materialQty" />
                        <div style="padding-left: 15px"> ${material.material_unit}</div>
                    </div>
                </td>            
            </tr>`
        }
        document.getElementById("data-output").innerHTML = out;
    });
}

function createProduct(){
    var object = {};
    object.product_has_material_list = new Array();
    var tab = document.getElementById('data-output')
    var trList = tab.querySelectorAll('tr');

    for(var ele of trList){
        var obj = {};
        if(parseInt(ele.querySelector('input').value) != 0 || parseInt(ele.querySelector('input').value)!= NaN){
            obj.material_id = JSON.parse(ele.getAttribute('value'));
            obj.quantity_needed = parseInt(ele.querySelector('input').value);
            obj.product_id = {};
            object.product_has_material_list.push(obj);
        }
    }
    object.product_usable_time = parseInt(document.getElementById('product_usable_time').value);
    object.product_unit_price = parseFloat(document.getElementById('product_unit_price').value);
    object.productsize_id = JSON.parse(document.getElementById('productsize_id').value);
    object.producttype_id = JSON.parse(document.getElementById('producttype_id').value);
    object.product_created_date ="";
    object.product_updated_date ="";
    object.product_deleted_date ="";
    object.product_deleted = 0;
    object.product_code = 0;
    object.product_needed = 0;
    object.product_extra = parseInt(document.getElementById('product_extra').value);
    object.product_has = 0;

    console.log(object);

    restFunction('/product/save', object, "POST", "/product", "Product");
}

