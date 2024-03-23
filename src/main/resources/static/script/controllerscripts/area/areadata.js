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
                <td id="area_name">${area.area_name}</td>
                <td id="area_code">${area.area_code}</td>
                <td>
                    <div id="basicBtn" style="display:flex">
                        <button class="btnEdit" onclick='editArea(` + JSON.stringify(area) +`, this)'>Edit</button>
                        <button class="btnDelete" onclick='deleteArea(` + JSON.stringify(area) + `)'>Delete</button>
                    </div>
                    <div id="secondaryBtn"  style="display:none">
                        <button class="btnEdit" onclick='editRowArea(this)'>save</button>
                        <button class="btnDelete" onclick='discardRowArea()'>Discard</button>
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

    $("#area_tab tbody").prepend("<tr><td id= newItem></td><td></td><td id= newAddBtn onclick=saveArea()></td></tr>");

    var inputFieldData = document.getElementById("newItem");
    var inputField = document.createElement("input");
    inputField.type = "text";
    inputField.id = "areaName";
    inputFieldData.appendChild(inputField);

    var buttonFieldData = document.getElementById("newAddBtn");
    var buttonField = document.createElement('button');
    buttonField.innerHTML = 'Submit';
    buttonField.className = 'btnEdit';
    buttonField.id = 'buttonAdd';
    buttonFieldData.appendChild(buttonField);
}

function saveArea(){
    var areaNew = document.getElementById('areaName').value;
    var area = {};
    area['area_name'] = areaNew;
    
    restFunction('/area/save', area, "POST", "/areas", "Area");
}

function editArea(area, ele){
    var trObj = ele.parentNode.parentNode.parentNode;
    trObj.querySelector("#area_name").innerHTML = `<input id="areaInput" placeholder = '${area.area_name}'/>`;
    trObj.querySelector("#basicBtn").innerHTML = `<button class='btnEdit' onclick='editRowArea(${JSON.stringify(area)})'>save</button>`;
    console.log(trObj);
    console.log(ele);  
}

function editRowArea(area){
    var areaName = document.getElementById('areaInput').value;
    area.area_name = areaName;
    console.log(area);
    restFunction('/area/edit', area, "PUT", "/areas", "Area");
}

function deleteArea(area){

    console.log(area);
    restFunction('/area/delete', area, "DELETE", "/areas", "Area");

}