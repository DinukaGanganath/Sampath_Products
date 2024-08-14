initLayout("Material", "Material Details");
sidebarLoader("/material");

//submenu created
var jsonList = [
    {
        "str" : "Types",
        "url" : "/material"
    },
    {
        "str" : "Needed",
        "url" : "/materialneed"
    },
    {
        "str" : "Ordered",
        "url" : "/materialordered",
        "status" : "active"
    },
    {
        "str" : "Recieved",
        "url" : "/materialreceived"
    }
]
createThinLongDiv(jsonList);

let currentPage = 1;

loadTable();

function loadTable(){
    fetch("/purchaseorderLine/findall")
    .then(function(response){
        return response.json();
    })
    .then(function(areas){
        let placeholder = document.querySelector("#data-output");
        let rowNo = areas.length;
        let maxRow = 7;
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
    })
}

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


function setDataSet(pagDataList){
    var out ="";
    for(let order of pagDataList){
        out += `
            <tr id=`+ JSON.stringify(order) +`>
                <td id="supplier">${order.request_id.supplier_id.supplier_business_name.replaceAll('_', ' ')}</td>
                <td id="material">${order.request_id.supplier_id.supplier_material_id.material_name.replaceAll('_', ' ')}</td>
                <td id="qty">${order.requested_qty + " " + order.request_id.supplier_id.supplier_material_id.material_unit}</td>
                <td id="price">${"Rs. "+order.line_price}</td>
                <td id="date">${order.request_id.request_created_date.split('T')[0]}</td>
                <td>
                    <div id="basicBtn" style="display:flex">
                        <button class="btnEdit" onclick='recieveForm(${JSON.stringify(order)})';>${order.status}</button>
                    </div>
                </td>
            </tr>
        `;
    }
    return out;
}

function calcDiscount(){
    var price = parseFloat(document.getElementById('price').value);
    var paid = parseFloat(document.getElementById('paid').value);

    var discount = (price-paid)*100/paid;
    document.getElementById('discount').value = discount;
}

function recieveForm(order){

    console.log(order);
    document.getElementById('modal').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';

    document.getElementById('supplier').value = order.request_id.supplier_id.supplier_business_name.replaceAll('_', ' ');
    document.getElementById('material').value = order.request_id.supplier_id.supplier_material_id.material_name.replaceAll('_', ' ');
    document.getElementById('quantity').value = order.requested_qty + " " + order.request_id.supplier_id.supplier_material_id.material_unit;
    document.getElementById('price').value =  order.line_price;
    document.getElementById('created').value = order.request_id.request_date.split('T')[0];

    document.getElementById('submitNote').addEventListener('click', function(){
        order.paid_date = document.getElementById('paying').value + 'T00:00:00';
        order.status = "recieved";
        order.payment_paid = document.getElementById('paid').value;

        console.log(order);


        $.ajax("/purchaseorderLine/edit", {
            async : false,
            type : "PUT",
            data : JSON.stringify(order),
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

        var materialObj = order.request_id.supplier_id.supplier_material_id;
        materialObj.material_has = materialObj.material_has + order.requested_qty;
        console.log(materialObj);

        $.ajax("/material/edit", {
            async : false,
            type : "PUT",
            data : JSON.stringify(materialObj),
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

        window.location.href ="/materialneed";
    });

    
    
}

function submitReceiveForm(orderEle){
    orderEle.paid_date = document.getElementById('paying').value + 'T00:00:00';
    orderEle.status = "recieved";
    orderEle.payment_paid = document.getElementById('paid').value;

    console.log(orderEle);

    $.ajax("/purchaseorderLine/edit", {
        async : false,
        type : "PUT",
        data : JSON.stringify(orderEle),
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

function loadPrevious(){
    currentPage--;
    loadTable();
}

function loadNext(){
    currentPage++;
    loadTable();
}