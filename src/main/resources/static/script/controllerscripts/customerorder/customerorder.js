//configure the GUI and window parameters
initLayout("Customer Order", "Customer Order Details");
sidebarLoader("/customer");

loadOptionVal("/customer/findall/exist", "customer_id", "customer_code", "Customer");
loadOptionVal("/customer/findall/exist", "customer_name", "customer_business_name", "Customer");

//submenu created
var jsonList = [
    {
        "str" : "Customer",
        "url" : "/customer"
    },
    {
        "str" : "Order",
        "url" : "/customerorder",
        "status" : "active",
    },
    {
        "str" : "Valid",
        "url" : "/validcustomerorder"
    },
    {
        "str" : "Ending",
        "url" : "/endingcustomerorder",
    },
    {
        "str" : "Expired",
        "url" : "/expiredcustomerorder"
    },
]
createThinLongDiv(jsonList);

let currentPage = 1;

loadTable();

// table value load
function loadTable(){
    fetch("/customerorder/findall/ordered")
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

        
    });

    
}


function alertCreate(){
    for(var i of pagDataList){
        var order = i.getAttribute('value');

        console.log(order.customer_order_code);
        var readyVal = 1;
        var productList = [];
        for(var i of order.customerOrderHasProductList){
            console.log(i.product_id.product_has + "----" + i.quantity);
            if(i.product_id.product_has<i.quantity){
                productList.push([i.product_id.productsize_id.productsize_name + " " + i.product_id.producttype_id.producttype_name, i.quantity, i.product_id.product_has]);
            }
        }
        if(productList.length != 0){
            console.log(order.customer_order_code);
            document.getElementById(order.customer_order_code).style.backgroundColor = "white";
            document.getElementById(order.customer_order_code).addEventListener("click", function(){
                document.getElementById('modal-notification').style.display = 'block';
                document.getElementById('overlay').style.display = 'block';
                var x = "";

            for(var y of productList){
                x += `<tr>
                <td>${y[0]}</td>
                <td>${y[1]}</td>
                <td>${y[2]}</td>
            </tr>`;
            }
            
            });
        }
        console.log("--------");
    }
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

// data set creaing
function setDataSet(pagDataList){
    


    var quotClass = "";

    let currentDate = new Date().toJSON().slice(0, 10)
    var out ="";
    for(let order of pagDataList){

        var color="";
        var today = new Date();
        var needed = new Date(order.customer_order_needed.split('T')[0]);
        var noDays = Math.ceil((needed - today)/(60*60*24*1000));

        if(noDays<0){
            color = "#ff0000aa";
        }else if(noDays<3 && 0<noDays){
            color ="#ffff00aa";
        }
        else{
            color = "#00ff00aa";
        }

        // <th>Business name</th>
        // <th>Area</th>
        // <th>Order Need date</th>
        // <th>Order Ordered date</th>
        // <th>Order value</th>
        // <th>Price paid</th>
        // <th>Status</th>	
                                        
        out += `
            <tr id=`+ JSON.stringify(order) +`" style="background-color:${color}">
                <td id="bussiness_name">${order.customer_id.customer_business_name.replaceAll('_', ' ')}</td>
                <td id="area_id">${order.customer_id.customer_address_city.replaceAll('_', ' ')}</td>
                <td id="need_date">${order.customer_order_needed.split('T')[0]}</td>
                <td id="ordered_date">${order.customer_order_created.split('T')[0]}</td>
                <td id="order_value">Rs. ${order.payment_amount}.00</td>
                <td id="price_paid">Rs. ${order.payment_paid}</td>
                <td id="status">${order.customer_order_status}</td>
                <td>
                    <div id="basicBtn" style="display:flex">
                        <button class="btnEdit" onclick='viewOrder(` + JSON.stringify(order) +`)'>View</button>
                        <button id=${order.customer_order_code} class="btnDelete" onclick='processOrder(` + JSON.stringify(order) + `)'>Process</button>
                    </div>
                </td>
            </tr>
        `;

        
    }

    

    return out;
}

function calibrateVal(){
    var total = parseFloat(document.getElementById('payment_amount').value.split(" ")[1]);
    var discount = parseFloat(document.getElementById('payment_discount').value);

    document.getElementById('payment_need').value = total - discount;
    
    var amount = parseFloat(document.getElementById('payment_need').value);
    var initial = parseFloat(document.getElementById('payment_paid').value);

    document.getElementById('payment_balance').value = amount - initial;
    
    
}

function viewOrder(order){
    var out = "";
    showForm();
    document.getElementById('addRowBtn').style.display='none';

    document.getElementById('customer_id').style.display = 'none';
    document.getElementById('customer_name').style.display = 'none';
    document.getElementById('customer_id_text').style.display = 'block';
    document.getElementById('customer_name_text').style.display = 'block';
    document.getElementById('customer_id_text').setAttribute('disabled',true);
    document.getElementById('customer_name_text').setAttribute('disabled',true);
    document.getElementById('customer_order_created').setAttribute('disabled',true);
    document.getElementById('customer_order_needed').setAttribute('disabled',true);

    document.getElementById('customer_id_text').value = order.customer_id.customer_code;
    document.getElementById('customer_name_text').value = order.customer_id.customer_business_name.replaceAll("_", " ");
    document.getElementById('customer_order_needed').value = order.customer_order_needed.split('T')[0];
    document.getElementById('customer_order_created').value = order.customer_order_created.split('T')[0];

    for(var obj of order.customerOrderHasProductList){
        out += `<tr>
                    <td>${obj.product_id.producttype_id.producttype_name + " "+obj.product_id.productsize_id.productsize_name}</td>
                    <td>${obj.quantity}</td>
                    <td>Rs. ${obj.price}.00</td>
                </tr>`

    }
    document.getElementById('tabBody').innerHTML = out;

}

function processOrder(order){
    
    order.customer_order_status = 'progress';
    console.log(order);

    $.ajax("/customerorder/edit", {
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

//add a row
function addRow(){
    var tBody = document.getElementById("tabBody");
    var trEle = document.createElement('tr');
    var tdProd = document.createElement('td');
    var tdQty = document.createElement('td');
    var tdprice = document.createElement('td');
    var tdRem = document.createElement('td');

    trEle.style.backgroundColor = 'rgba(0, 0, 0, 0)';
    var selectProd = document.createElement('select');
    trEle.classList.add('ordLine')
    selectProd.id = "productOpt_id";
    selectProd.style.backgroundColor = 'rgba(0, 0, 0, 0)'; 
    selectProd.style.border = "none";
    tdProd.appendChild(selectProd);

    var numInput = document.createElement('input');
    numInput.type = 'number';
    numInput.style.border = "none";
    numInput.id = "qty";
    numInput.min = 0;
    numInput.style.backgroundColor = 'rgba(0, 0, 0, 0)';
    numInput.onchange = function(){
        var lineVal = JSON.parse(this.parentElement.parentElement.firstChild.firstChild.value)['product_unit_price']*parseInt(this.value);
        tdprice.innerHTML = "Rs. " + lineVal + ".00";
        document.getElementById('payment_amount').value = 'Rs. '+calculateTotal()+'.00';
    }
    tdprice.id = "trPrice";
    tdQty.appendChild(numInput);

    tdRem.innerHTML = '<i class="fa-solid fa-minus"></i>';
    tdRem.onclick = function(){
        this.parentElement.remove();
        document.getElementById('total_price').value = 'Rs. '+calculateTotal()+'.00';
    };

    trEle.appendChild(tdProd);
    trEle.appendChild(tdQty);
    trEle.appendChild(tdprice);
    trEle.appendChild(tdRem);

    tBody.appendChild(trEle);

    // var selectedProd = [];
    // for(var obj of document.getElementById('tabBody').getElementsByTagName('tr')){
    //     selectedProd.push(obj.value);
    // }

    // console.log(selectedProd);

    fetch("/product/findall")
    .then(function(response){
        return response.json();
    })
    .then(function(products){
        var table = document.getElementById('tabBody');
        var rows = table.getElementsByTagName('tr');
        var lastRow = rows[rows.length - 1];
        var lastSelect = lastRow.getElementsByTagName('select')[0];
        for(var prod of products){
            //if(!selectedProd.includes(JSON.stringifyprod)){
                var opt = document.createElement('option');
                opt.value = JSON.stringify(prod);
                opt.innerHTML = prod.producttype_id.producttype_name.replaceAll("_"," ") + " " + prod.productsize_id.productsize_name.replaceAll("_"," ");
                lastSelect.appendChild(opt);
            //}
        }
        
        
    }
    )
}

function calculateTotal(){
    var total = 0;
    var table = document.getElementById('tabBody');
    var rows = table.getElementsByTagName('tr');
    for(var ele of rows){
        console.log(ele.childNodes[2].innerHTML);
        total += parseFloat(ele.childNodes[2].innerHTML.split(" ")[1]);
        console.log('total : '+total);
    }
    return(total);
}


// add form appear
function showForm(){

    document.getElementById('modal').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
    

}

function createBalance(){

    if(document.getElementById('payment_type').value=='card'){
        document.getElementById('payment_balance').value = 0;
        document.getElementById('payment_paid').value = document.getElementById('payment_need').value;
        document.getElementById('payment_paid').setAttribute("disabled", true);
    }

    if(document.getElementById('payment_type').value=='cash'){
        document.getElementById('payment_balance').value = 0;
        document.getElementById('payment_paid').value = 0;
        document.getElementById('payment_paid').removeAttribute("disabled");
    }
    
}

//add a customer order
function createCustOrder(){

    var ordObject = {};
    var productList = new Array();
    //ordObject.customer_order_id = {};
    //ordObject.customer_order_code ="";
    ordObject.customer_order_created = document.getElementById('customer_order_created').value + "T00:00";
    ordObject.customer_order_needed = document.getElementById('customer_order_needed').value + "T00:00";
    ordObject.customer_order_status = 'created';
    ordObject.customer_order_total = parseFloat(document.getElementById('payment_amount').value.split(" ")[1]);
    ordObject.customer_order_paid = parseFloat(document.getElementById('payment_paid').value);
    ordObject.customer_id = JSON.parse(document.getElementById('customer_id').value);

    productList = new Array();
    var trList = document.querySelectorAll('.ordLine');
    for(var trEle of trList){

        var trObj = {};
        trObj.product_id = JSON.parse(trEle.querySelector('select').value);
        trObj.quantity = parseFloat(trEle.querySelector('#qty').value);
        trObj.price = parseFloat(trEle.querySelector('#trPrice').innerHTML.split(" ")[1]);
        productList.push(trObj);

    }

    ordObject.customerOrderHasProductList = productList;
    ordObject.payment_amount = parseFloat(document.getElementById("payment_amount").value.split(" ")[1]);
    ordObject.payment_discount = parseFloat(document.getElementById("payment_discount").value);
    ordObject.payment_paid = parseFloat(document.getElementById("payment_need").value);
    ordObject.payment_balance = parseFloat(document.getElementById("payment_balance").value); 
    ordObject.payment_method= document.getElementById("payment_type").value;

    console.log(ordObject);

    $.ajax("/customerorder/save", {
        async : false,
        type : "POST",
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
