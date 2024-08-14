initLayout("Area", "Area Details");
sidebarLoader("/areas");

let currentPage = 1;

loadTable();

function loadTable(){
    fetch("/areas/findall/exist")
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
    let out = "";
    let parentElements = '["td", "p"]';
    for(let area of pagDataList){
        out += `
            <tr id=`+ area.area_id +`>
                <td id="area_name">${area.area_name.replaceAll('_', ' ')}</td>
                <td id="area_code">${area.area_code}</td>
                <td id="area_div">${area.postal_division_id.postal_division_name}</td>
                <td>
                    <div id="basicBtn" style="display:flex">
                        <button class="btnEdit" onclick='editArea(` + JSON.stringify(area) +`, this)'>Edit</button>
                        <button class="btnDelete" onclick='deleteArea(` + JSON.stringify(area) + `)'>Delete</button>
                    </div>
                </td>
            </tr>
        `;
    }
    return out;
}

function divisionAddForm(){
    document.getElementById('modal').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
}

function createDivision(){
    var obj ={};
    obj.postal_division_code = document.getElementById('postal_division_code').value;
    obj.postal_division_name = document.getElementById('postal_division_name').value;

    $.ajax("/division/save", {
        async : false,
        type : "POST",
        data : JSON.stringify(obj),
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

    window.location.href = "/areas";

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

    $("#area_tab tbody").prepend("<tr><td id= newItem><td></td></td><td id='newdivision'></td><td id= newAddBtn onclick=saveArea()></td></tr>");

    var inputFieldData = document.getElementById("newItem");
    var inputField = document.createElement("input");
    inputField.type = "text";
    inputField.id = "areaName";
    inputFieldData.appendChild(inputField);

    var selectFieldData = document.getElementById("newdivision");
    var selectField = document.createElement("select");
    selectField.type = "text";
    selectField.id = "areaDiv";
    selectFieldData.appendChild(selectField);
    loadOptionVal("/division/findall", "areaDiv", "postal_division_name", "Division");
    //console.log(document.getElementById("areaDiv"));

    var buttonFieldData = document.getElementById("newAddBtn");
    var buttonField = document.createElement('button');
    buttonField.innerHTML = 'Submit';
    buttonField.className = 'btnEdit';
    buttonField.id = 'buttonAdd';
    buttonFieldData.appendChild(buttonField);
}

function saveArea(){
    var areaNew = document.getElementById('areaName').value.replaceAll(' ', '_');
    var divNew= JSON.parse(document.getElementById('areaDiv').value);
    var area = {};
    area['area_name'] = areaNew;
    area['postal_division_id'] = divNew;
    restFunction('/area/save', area, "POST", "/areas", "Area");
}

function editArea(area, ele){
    var trObj = ele.parentNode.parentNode.parentNode;
    trObj.querySelector("#area_div").innerHTML = `<select id="newAreaSelect"></select>`;
    loadOptionVal("/division/findall", "newAreaSelect", "postal_division_name", "Division");
    trObj.querySelector("#area_name").innerHTML = `<input id="areaInput" placeholder = '${area.area_name}'/>`;
    trObj.querySelector("#basicBtn").innerHTML = `<button class='btnEdit' onclick='editRowArea(${JSON.stringify(area)})'>save</button>`; 
}

function editRowArea(area){
    var areaName = document.getElementById('areaInput').value;
    area.area_name = areaName;
    area.postal_division_id = JSON.parse(document.getElementById('newAreaSelect').value);
    console.log(area);
    restFunction('/area/edit', area, "PUT", "/areas", "Area");
}

function deleteArea(area){

    console.log(area);
    restFunction('/area/delete', area, "DELETE", "/areas", "Area");

}