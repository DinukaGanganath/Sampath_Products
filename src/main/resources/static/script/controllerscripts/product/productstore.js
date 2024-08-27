//configure the GUI and window parameters
initLayout("Product", "Product Details");
sidebarLoader("/product");

var jsonList = [
    {
        "str" : "Products",
        "url" : "/product"
    },
    {
        "str" : "Products Store",
        "url" : "/productstores",
        "status" : "active"
    },
    {
        "str" : "Batch process",
        "url" : "/batchprocess"
    },
    {
        "str" : "Batch finished",
        "url" : "/batch"
    }
]
createThinLongDiv(jsonList);

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


function loadInnerTable(){
    var tbdy = document.getElementById('tabBody');
    var out="";
    fetch("/product/findall/exist")
    .then(function(response){
        return response.json();
    })
    .then(function(products){
        for(var p of products){
            if((p.product_has + p.product_progress)-(p.product_extra+p.product_need)<0){
                out += 
                `<tr id=${JSON.stringify(p)} class="batchProd">
                    <td>${p.producttype_id.producttype_name.replaceAll("_", " ") + " " +p.productsize_id.productsize_name.replaceAll("_", " ")}</td>
                    <td>${Math.abs((p.product_has + p.product_progress)-(p.product_extra+p.product_need))}</td>
                    <td>
                        <input class="planQty" type="number" min="0" step="1" value=0 onchange='calibrate(this)'; />
                    </td>
                </tr>`
            }
        }
        tbdy.innerHTML = out;
    });
}

function calibrate(ele){
    var productEle = JSON.parse(ele.parentElement.parentElement.id);
    var materialInput = document.getElementsByClassName('materialSumary');
    for(var mat of productEle.product_has_material_list){
        
        for(var matInput of materialInput){
            console.log(matInput.id);

            if(matInput.id == mat.material_id.material_id){
                matInput.value = (mat.material_id.material_has - (mat.material_id.material_extra + mat.material_id.material_want)) - (ele.value * mat.quantity_needed);

                matInput.value = matInput.value + ' ' + mat.material_id.material_unit;
            }
        }
    }
}

function deleteProduct(){
    var objvalue = getRowObject();
    let userConfirm = window.confirm(`Are you sure to delete ${objvalue.product_code} - ${objvalue.product_name}`);
    if(userConfirm){
        $.ajax("/customerorder/delete", {
            async : false,
            type : "DELETE",
            data : JSON.stringify(ordObject),
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
            out += `<tr value=${JSON.stringify(product)} onclick='showContextMenu(` + JSON.stringify(product) + `, event)'>
                <td>${product.producttype_id.producttype_name.replaceAll("_", " ") + " " +product.productsize_id.productsize_name.replaceAll("_", " ")}</td>
                <td>${product.product_code}</td>
                <td>${product.product_extra} packets</td>    
                <td>${product.product_need} packets</td> 
                <td>${product.product_has} packets</td>
                <td>${product.product_progress} packets</td>
                <td class="important">${(product.product_has + product.product_progress) - (product.product_extra + product.product_need)} packets</td>         
            </tr>`
        }
        document.getElementById("data-output").innerHTML = out;

        for(var ele of document.getElementById("data-output").querySelectorAll(".important")){
            var val = parseInt(ele.innerHTML);
            
            if(val<0){
                ele.classList.add('red');
            }else if(val == 0){
                ele.classList.add('orange');
            }else{
                ele.classList.add('green');
            }
        }
    });
}

function loadProduction(){
    showForm();

    // fetch("/batchcode")
    // .then(function(response){
    //     return response.json();
    // }).then(function(code){
    //     console.log(code);
    // });

    var materialForm = document.getElementById("material_val");

    loadInnerTable();

    fetch("/material/findall/exist")
    .then(function(response){
        return response.json();
    })
    .then(function(materials){
         var out ="";
        for(var material of materials){

            out += `<div class="input-onethird">
                <label for="${material.material_name}">${material.material_name}</label>
                <input  type= "text" class="materialSumary" id= "${material.material_id}" value="${material.material_has-(material.material_want+material.material_extra)}   ${material.material_unit}" disabled="disabled" >
             </div>`
            
        }
        materialForm.innerHTML = out;
    });
}

function showForm(){

    document.getElementById('modal').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
    

}

function createBatch(){
    var batch = {};

    var batchProducts = new Array();
    var batchTr = document.getElementsByClassName('batchProd');

    for(var trEle of batchTr){
        if(trEle.getElementsByClassName('planQty')[0].value != 0){
            var batchObj = {};
            batchObj.product_id = JSON.parse(trEle.id);
            batchObj.qty = parseInt(trEle.getElementsByClassName('planQty')[0].value);
            batchProducts.push(batchObj);
        }   
    }
    batch.batchHasProductList = batchProducts;

    var loadAfter = "/"+ window.location.href.split("/").slice(-1);

    $.ajax("/batch/save", {
        async : false,
        type : "POST",
        data : JSON.stringify(batch),
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

    //window.location.href = loadAfter;

}