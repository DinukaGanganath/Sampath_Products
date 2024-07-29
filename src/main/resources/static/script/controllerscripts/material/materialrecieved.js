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
        "url" : "/materialordered"
    },
    {
        "str" : "Recieved",
        "url" : "/materialreceived",
        "status" : "active"
    }
]
createThinLongDiv(jsonList);

let currentPage = 1;

loadTable();

function loadTable(){
    fetch("/material/findall/exist")
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
    for(let material of pagDataList){
        out += `
            <tr id=`+ material.material_id +`>
                <td id="material_name">${material.material_name.replaceAll('_', ' ')}</td>
                <td id="material_code">${material.material_code}</td>
                <td id="material_extra">${material.material_extra +" " + material.material_unit}</td>
                <td>
                    <div id="basicBtn" style="display:flex">
                        <button class="btnEdit" onclick='editMaterial(` + JSON.stringify(material) + `, this)'>Edit</button>
                        <button class="btnDelete" onclick='deleteMaterial(` + JSON.stringify(material) + `)'>Delete</button>
                    </div>
                </td>
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

    $("#material_tab tbody").prepend("<tr><td></td><td><select onChange=loadQuotation() id='material_id'></select></td><td><select id='supplier_id'><option selected disabled>Select Supplier</option></select></td><td id='qty'><div style='display:flex'><input id='lineQty' type='number' min=0 onchange='calculatePayment(this);' style='width:50px' /><div id='lineUnit' style='padding-top:5px; padding-left:10px;'></div></div></td><td><input type='date' id='recievedDate' /></td><td id='total'></td><td id='newAddBtn'><button class='btnEdit' onclick='saveRecieved()'>Record</button></td></tr>");

    loadOptionVal("/materials/findall", "material_id", "material_name", "Material");
}

function calculatePayment(ele){
    let qtyInput = document.getElementById('qty');
    let previousSiblingVal = qtyInput.previousElementSibling.firstChild.value;
    var obj = JSON.parse(previousSiblingVal);

    document.getElementById('lineUnit').innerHTML = obj.supplier_id.supplier_material_id.material_unit;
    var date = new Date();
    var month = (date.getMonth()+1)>9 ? date.getMonth()+1 : "0"+(date.getMonth()+1);
    document.getElementById("recievedDate").max = date.getFullYear() + "-" + month + "-" + date.getDate();
    document.getElementById("recievedDate").min = obj.request_created_date.split('T')[0];

    var unit_price = obj.request_price / obj.request_units;
    console.log(unit_price);

    document.getElementById('total').innerHTML = "Rs.  " + unit_price*ele.value;
         
}

function saveRecieved(){
    var object= {};

    let qtyInput = document.getElementById('qty');
    let previousSiblingVal = qtyInput.previousElementSibling.firstChild.value;
    var obj = JSON.parse(previousSiblingVal);
    var material = JSON.parse(document.getElementById('material_id').value);
    material.material_has += parseInt(document.getElementById('lineQty').value);
    object['request_id'] = obj;

    object['recieve_note_qty'] = parseInt(document.getElementById('lineQty').value);
    object['recieve_note_date'] = document.getElementById("recievedDate").value + "T00:00:00";
    object['recieve_note_total'] = parseInt(document.getElementById("total").innerHTML.split(" ")[2]);

    $.ajax("/material/edit", {
        async : false,
        type : "PUT",
        data : JSON.stringify(material),
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

    restFunction('/recievenote/save', object, "POST", "/materialreceived", "Receive Note");


}

function loadQuotation(){
    document.getElementById('supplier_id').innerHTML = '<option disabled selected>Select Supplier</option>';
    fetch("/request/findall/created")
    .then(function(response){
        return response.json();
    })
    .then(function(quotations){
        let dropdown = document.querySelector("#supplier_id");
        for(var val of quotations){
            if(val.supplier_id.supplier_material_id.material_name == JSON.parse(document.getElementById('material_id').value).material_name){
                var opt = document.createElement('option');
                opt.innerHTML = val.supplier_id.supplier_business_name;
                opt.value = JSON.stringify(val);
                opt.class = "reqSupplier";
                dropdown.appendChild(opt);
            }
        }
    });
}

function saveMaterial(){
    var materialNew = document.getElementById('materialName').value.replaceAll(' ', '_');
    var extraQty = document.getElementById('materialExtra').value;

    var material = {};
    material['material_name'] = materialNew;
    material['material_extra'] = extraQty.split(" ")[0]
    material['material_unit'] = extraQty.split(" ")[1];

    restFunction('/material/save', material, "POST", "/material", "Material");
}

function deleteMaterial(material){

    console.log(material);
    restFunction('/material/delete', material, "DELETE", "/material", "Material");

}

function editMaterial(material, ele){
    var trObj = ele.parentNode.parentNode.parentNode;
    trObj.querySelector("#material_name").innerHTML = `<input id="materialInput" placeholder = '${material.material_name}'/>`;
    trObj.querySelector("#material_extra").innerHTML = `<input id="materialExtra" placeholder = '${material.material_extra +" " + material.material_unit}'/>`;
    trObj.querySelector("#basicBtn").innerHTML = `<button class='btnEdit' onclick='editRowMaterial(${JSON.stringify(material)})'>save</button>`; 
}

function editRowMaterial(material){
    var materialName = document.getElementById('materialInput').value.replaceAll(" ", "_");
    var extraQty = document.getElementById('materialExtra').value;
    material.material_name = materialName;
    material.material_extra = extraQty.split(" ")[0];
    material.material_unit = extraQty.split(" ")[1];
    console.log(material);
    restFunction('/material/edit', material, "PUT", "/material", "Material");
}