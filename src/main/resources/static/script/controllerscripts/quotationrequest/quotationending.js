//configure the GUI and window parameters
initLayout("Quotation", "Quotation Details");
sidebarLoader("/quotation");

//submenu created
var jsonList = [
    {
        "str" : "Requested",
        "url" : "/requestedquot"
    },
    {
        "str" : "All",
        "url" : "/quotation"
    },
    {
        "str" : "Valid",
        "url" : "/validquot"
    },
    {
        "str" : "Ending",
        "url" : "/endingquot",
        "status" : "active"
    },
    {
        "str" : "Expired",
        "url" : "/expiredquot"
    },
]
createThinLongDiv(jsonList);

let currentPage = 1;

loadTable();

function loadTable(){
    fetch("/request/findall/ending")
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
    let currentDate = new Date().toJSON().slice(0, 10)
    var out ="";
    for(let request of pagDataList){
        out += `
            <tr id=`+ request.request_id +`>
                <td id="request_code">${request.request_code}</td>
                <td id="supplier_id">${request.supplier_id.supplier_business_name.replaceAll('_', ' ')}</td>
                <td id="material_name" class='avoid'>${request.supplier_id.supplier_material_id.material_name.replaceAll('_',' ')}</td>
                <td id="request_date">${request.request_created_date.split('T')[0]}</td>
                <td id="request_validity">${request.request_validity}</td>
                <td id="material_units">${request.request_units + " " + request.supplier_id.supplier_material_id.material_unit}</td>
                <td id="request_price">${request.request_price}</td>
            </tr>
        `;
    }
    return out;
}

function loadPrevious(){
    currentPage--;
    loadTable();
}

function loadNext(){
    currentPage++;
    loadTable();
}

function showForm(){

    $("#material_tab tbody").prepend("<tr><td></td><td id='supplier'></td><td id='supplier_code'></td><td id= 'material'></td><td id='date'></td><td id= 'newAddBtn' onclick=requestQuotation()></td></tr>");

    var inputFieldData = document.getElementById("supplier");
    var inputField = document.createElement("select");
    inputField.id = "supplier_id";
    inputFieldData.appendChild(inputField);
    loadOptionVal("/supplier/findall/exist", "supplier_id", "supplier_business_name", "Supplier");
    
    document.getElementById('supplier_id').onchange = function (){
        var sup = JSON.parse(document.getElementById('supplier_id').value);
        console.log(document.getElementById('material'));
        document.getElementById('supplier_code').innerHTML = sup.supplier_code;
        document.getElementById('material').innerHTML = sup.supplier_material_id.material_name.replaceAll('_',' ');
    }

    var dateVal = document.getElementById('date');
    dateVal.innerHTML = new Date().toJSON().slice(0, 10);

    var buttonFieldData = document.getElementById("newAddBtn");
    var buttonField = document.createElement('button');
    buttonField.innerHTML = 'Submit';
    buttonField.className = 'btnEdit';
    buttonField.id = 'buttonAdd';
    buttonFieldData.appendChild(buttonField);
}

function requestQuotation(){
     
    var supplier = JSON.parse(document.getElementById('supplier_id').value);
    var requestDate = document.getElementById('date').innerHTML;

    var request = {};
    request.supplier_id = supplier;
    request.request_date = requestDate + 'T00:00:00';

    restFunction('/request/save', request, "POST", "/requestedquot", "Quotation Request");
}

function addQuotation(object){

    console.log(object);

    document.getElementById('modal').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';

    document.getElementById("request_id").value = object.request_code;
    document.getElementById("request_date").value = object.request_date.split('T')[0];
    document.getElementById("business_name").value = object.supplier_id.supplier_business_name.replaceAll('_', ' ');
    document.getElementById("material_name_form").value = object.supplier_id.supplier_material_id.material_name.replaceAll('_',' ');
    document.getElementById("unit").innerHTML = object.supplier_id.supplier_material_id.material_unit.replaceAll('_',' ');

    document.getElementById("btnQuotaionSubmit").addEventListener("click", function(){
        object.request_created = 1;
        object.request_units = document.getElementById("request_units").value;
        object.request_price = document.getElementById("price").value;
        object.request_validity = document.getElementById("valid_days").value;
        object.request_created_date = document.getElementById("quotation_date").value + 'T00:00:00';
        object.request_deleted = 0;


        restFunction('/request/edit', object, "PUT", "/requestedquot", "Quotation Request");
    })

}
