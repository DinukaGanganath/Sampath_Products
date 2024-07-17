//configure the GUI and window parameters
initLayout("Purchase Order", "Purchase Order Details");
sidebarLoader("/purchaseorder");

//Load Material select tag values
loadOptionVal("/materials/findall", "material", "material_name", "Material");

//load data to the table
function loadQuotationList(){

    var selectedObj = JSON.parse(document.getElementById("material").value)
    var material = selectedObj.material_name;

    document.getElementById("needed").value = selectedObj.material_extra + selectedObj.material_want - selectedObj.material_has;
    for (var ele of document.querySelectorAll(".unit")){
        ele.innerHTML = JSON.parse(document.getElementById("material").value).material_unit;
    }

    fetch("/request/findall/created")
    .then(function(response){
        return response.json();
    })
    .then(function(quotations){
        let tableBody = document.querySelector(".tableValid");
        tableBody.innerHTML = loadTableData(quotations, material);
    });

    fetch("/request/findall/created")
    .then(function(response){
        return response.json();
    })
    .then(function(quotations){
        let tableBody = document.querySelector(".tableEnding");
        tableBody.innerHTML = loadTableData(quotations, material);
    });
}


function totalCalc(ele){
    ele.parentElement.nextElementSibling.innerHTML = 'Rs. ' + ele.value * ele.parentElement.previousElementSibling.innerHTML.split(" ")[1];
    
    var total = 0;
    for(var element of document.querySelectorAll(".price")){
        if(element.innerHTML != ""){
            total += parseInt(element.innerHTML.split(" ")[1]);
        }
    }
    document.getElementById("astement").value = total;

    var ordWeight = 0;
    for(var ord of document.querySelectorAll(".ordQty")){
        console.log(ord.value);
        if(ord.value != ""){
            console.log(ord.value);
            ordWeight += parseInt(ord.value);
        }
    }
    document.getElementById("ordered").value = ordWeight;

    var difference = document.getElementById("needed").value - document.getElementById("ordered").value;
    document.getElementById("difference").value = difference;

    if(difference>0){
        document.getElementById("difference").style.backgroundColor = "rgba(255,0,0,0.3)";
    }else if(difference==0){
        document.getElementById("difference").style.backgroundColor = "rgba(255,255,0,0.3)";
    }else{
        document.getElementById("difference").style.backgroundColor = "rgba(0,255,0,0.3)";
    }
}

function createOrder(){
    var obj = {};
    var quotationList = [];

    for(var val in document.getElementsByClassName('orderLine')){
        console.log(val.getElementById('ordering_qty'));
    }

    
}

function loadTableData(dataList, dataVal){
    console.log(dataList);
    var out = '';
    for(var quotation of dataList){
        let tableObj = quotation;
        tableObj.supplier_id.supplier_name = tableObj.supplier_id.supplier_name.replaceAll(" ", "_");
        tableObj.supplier_id.supplier_address_line1 = tableObj.supplier_id.supplier_address_line1.replaceAll(" ", "_");
        tableObj.supplier_id.supplier_business_name = tableObj.supplier_id.supplier_business_name.replaceAll(" ", "_");
        if(quotation.supplier_id.supplier_material_id.material_name == dataVal){
            out += `
                <tr class="orderLine">
                    <td id="supplier">${quotation.supplier_id.supplier_business_name.replaceAll('_', ' ')}</td>
                    <td id="ready_qty">${quotation.request_units + " " + quotation.supplier_id.supplier_material_id.material_unit}</td>
                    <td id="unit_price">Rs. ${quotation.request_price / quotation.request_units}</td>
                    <td id="ordering_qty">
                        <input class="ordQty" type=number min=0 max=${quotation.request_units} onchange="totalCalc(this)"/> `+" "+ `${quotation.supplier_id.supplier_material_id.material_unit}
                    </td>
                    <td id="total_price" class="price"></td>
                    <td></td>
                </tr>
            `;
        }
    }
    return out;
}
