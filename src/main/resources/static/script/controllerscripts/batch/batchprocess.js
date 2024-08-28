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
        "url" : "/productstores"
    },
    {
        "str" : "Batch process",
        "url" : "/batchprocess",
        "status" : "active"
    },
    {
        "str" : "Batch finished",
        "url" : "/batch"
    }
]
createThinLongDiv(jsonList);

let currentPage = 1;

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


// function loadTable(){
//     var tbdy = document.getElementById('data-output');
//     var out="";
//     fetch("/batch/findall/process")
//     .then(function(response){
//         return response.json();
//     })
//     .then(function(batches){
//         for(var batch of batches){

//             var batchProd = batch.batchHasProductList.length;
//             out += `
//             <tr value=${JSON.stringify(batch)}>
//                 <td rowspan=${batchProd}>${batch.batch_code}</td>
//                 <td rowspan=${batchProd}>${batch.batch_created_date}</td>
//                 <td>${batch.batchHasProductList[0].product_id.producttype_id.producttype_name} ${batch.batchHasProductList[0].product_id.productsize_id.productsize_name}</td>
//                 <td>${batch.batchHasProductList[0].qty}</td>
//                 <td rowspan=${batchProd}>aaa</td>
//             </tr>`
            
            

//             for(var i =1; i<batchProd; i++){
//                 out += `
//             <tr value=${JSON.stringify(batch)}>
//                 <td>${batch.batchHasProductList[i].product_id.producttype_id.producttype_name} ${batch.batchHasProductList[i].product_id.productsize_id.productsize_name}</td>
//                 <td>${batch.batchHasProductList[i].qty}</td>
//             </tr>`
//             }
//         }
//         tbdy.innerHTML = out;
//     });
// }

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

// table value load
function loadTable(){
    fetch("/batch/findall/process")
    .then(function(response){
        return response.json();
    })
    .then(function(areas){
        let placeholder = document.querySelector("#data-output");
        let rowNo = areas.length;
        let maxRow = 3;
        let pageNo = Math.ceil(rowNo/maxRow);
        let pagDataList = [];
        
        var start = (currentPage-1)*maxRow;
        var stop = currentPage*maxRow;

        if(rowNo<stop)
            stop = rowNo;

        for(var i = start; i<stop; i++){
            pagDataList.push(areas[i]);
        }

        console.log(`pag :\n ${pagDataList} \n start : ${start} \n end : ${stop}`);
        document.getElementById("pagMiddle").innerHTML = `<b>${currentPage}</b> of ${pageNo}`;

        visualizePag(pageNo);
        placeholder.innerHTML = setDataSet(pagDataList);

        
    });

    
}


// show paginations
function visualizePag(pageNo){
    if(currentPage == 1 && pageNo != 1){
        document.getElementById("pagLeftBtn").style.display = "none";
        document.getElementById("pagRightBtn").style.display = "block";
    }
    if(currentPage == pageNo){
        document.getElementById("pagRightBtn").style.display = "none";
        document.getElementById("pagLeftBtn").style.display = "block";
    }
    if(currentPage != pageNo && currentPage != 1){
        document.getElementById("pagRightBtn").style.display = "block";
        document.getElementById("pagLeftBtn").style.display = "block";
    }
    if(pageNo==1){
        document.getElementById("pagRightBtn").style.display = "none";
        document.getElementById("pagLeftBtn").style.display = "none";
    }
    if(pageNo==0){
        document.getElementById("pagRightBtn").style.display = "none";
        document.getElementById("pagLeftBtn").style.display = "none";
        document.getElementById("pagMiddle").style.display = "none";
    }
}


// load pagination pages
function loadPrevious(){
    currentPage--;
    loadTable();
}

function loadNext(){
    currentPage++;
    loadTable();
}

// data set creaing
function setDataSet(pagDataList){
    


    var quotClass = "";

    let currentDate = new Date().toJSON().slice(0, 10)
    var out ="";
    for(var batch of pagDataList){

        var batchProd = batch.batchHasProductList.length;
        out += `
        <tr value=${JSON.stringify(batch)}>
            <td rowspan=${batchProd}>${batch.batch_code}</td>
            <td rowspan=${batchProd}>${batch.batch_created_date}</td>
            <td>${batch.batchHasProductList[0].product_id.producttype_id.producttype_name} ${batch.batchHasProductList[0].product_id.productsize_id.productsize_name}</td>
            <td>${batch.batchHasProductList[0].qty}</td>
            <td rowspan=${batchProd}>
                <button id=${batch} class="btnDelete" onclick='processBatch(` + JSON.stringify(batch) + `)'>Process</button>
            </td>
        </tr>`
        
        

        for(var i =1; i<batchProd; i++){
            out += `
        <tr value=${JSON.stringify(batch)}>
            <td>${batch.batchHasProductList[i].product_id.producttype_id.producttype_name} ${batch.batchHasProductList[i].product_id.productsize_id.productsize_name}</td>
            <td>${batch.batchHasProductList[i].qty}</td>
        </tr>`
        }
    }
    return out;
}

function processBatch(obj){

    console.log(obj);

    $.ajax("/batch/edit", {
        async : false,
        type : "PUT",
        data : JSON.stringify(obj),
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