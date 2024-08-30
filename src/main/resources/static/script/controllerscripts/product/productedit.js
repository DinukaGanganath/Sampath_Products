var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
//console.log(receivedData);


loadOptionVal("/productsizes/findall/exist", "productsize_id", "productsize_name", "Product Type");
loadOptionVal("/producttypes/findall/exist", "producttype_id", "producttype_name", "Product Size");

initLayout("Product Edit", `Product Edit - ${receivedData.product_code}`);
sidebarLoader("/product");

console.log(`'${JSON.stringify(receivedData.productsize_id)}'`);
document.getElementById('producttype_id').value = `'${JSON.stringify(receivedData.producttype_id)}'`;
document.getElementById('productsize_id').value = `'${JSON.stringify(receivedData.productsize_id)}'`;
document.getElementById('product_unit_price').value = receivedData.product_unit_price;
document.getElementById('product_usable_time').value = receivedData.product_usable_time;
document.getElementById('product_extra').value = receivedData.product_extra;

var tabInner = '';
var tableBdy = document.getElementById('data-output');
for(var material of receivedData.product_has_material_list){
    console.log(material);
    tabInner += `<tr value=${JSON.stringify(material)}>
                    <td>${material.material_id.material_name.replaceAll("_", " ")}</td>
                    <td>
                        <input type="number" min="0" value=${material.quantity_needed}>${material.material_id.material_unit}
                    </td>
                </tr>`;
}
tableBdy.innerHTML = tabInner;

for(ele of document.querySelectorAll("input")){
    ele.classList.add("valid");
}

function editObj(formId, eleList, url, method, loadAfter, navigator){
    for(ele of document.querySelectorAll("select")){
        if(ele.childNodes.length === 0){
            ele.parentNode.removeChild(ele);
        }
    }
    validForm(formId, eleList, url, method, loadAfter, navigator, idVal);
}



function productNeed(productList){
    for(var i of productList){
        i[0].product_need = i[0].product_need + i[1];
        var prodObj = i[0];
        console.log(prodObj);
        $.ajax("/product/edit", {
            async : false,
            type : "PUT",
            data : JSON.stringify(prodObj),
            contentType: 'application/json',
    
            success : function (data, status, xhr){
                console.log("success " + status + " " + xhr);
                responseStatus = data;
                console.log(responseStatus);
            },
    
            error : function (xhr, status, errormsg){
                console.log("fail " + errormsg + " " + status +" " + xhr);
                responseStatus = errormsg;
            },
        });
        /*
        for(var j of i[0].productHasMaterialList){
            var matObj = j.material_id;
            matObj.material_want = matObj.material_want + (j.quantity_needed * i[1]);
            console.log(matObj);

            $.ajax("/material/edit", {
                async : false,
                type : "PUT",
                data : JSON.stringify(matObj),
                contentType: 'application/json',
        
                success : function (data, status, xhr){
                    console.log("success " + status + " " + xhr);
                    responseStatus = data;
                    console.log(responseStatus);
                },
        
                error : function (xhr, status, errormsg){
                    console.log("fail " + errormsg + " " + status +" " + xhr);
                    responseStatus = errormsg;
                },
            });

        }
            */
    }
}

function editProduct(){

    var test = receivedData;
    test.product_need = 5;
    test.product_has = 10;

    console.log(test);

    $.ajax("/product/edit", {
        async : false,
        type : "PUT",
        data : JSON.stringify(test),
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

    window.location.href = '/product';
}
