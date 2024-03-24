initLayout("Material", "Material Details");
sidebarLoader("/material");

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
                <td id="material_name">${material.material_name}</td>
                <td id="material_code">${material.material_code}</td>
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

    $("#material_tab tbody").prepend("<tr><td id= newItem></td><td></td><td id= newAddBtn onclick=saveMaterial()></td></tr>");

    var inputFieldData = document.getElementById("newItem");
    var inputField = document.createElement("input");
    inputField.type = "text";
    inputField.id = "materialName";
    inputFieldData.appendChild(inputField);

    var buttonFieldData = document.getElementById("newAddBtn");
    var buttonField = document.createElement('button');
    buttonField.innerHTML = 'Submit';
    buttonField.className = 'btnEdit';
    buttonField.id = 'buttonAdd';
    buttonFieldData.appendChild(buttonField);
}

function saveMaterial(){
    var materialNew = document.getElementById('materialName').value;
    var area = {};
    area['material_name'] = materialNew;
    restFunction('/material/save', area, "POST", "/material", "Material");
}

function deleteMaterial(material){

    console.log(material);
    restFunction('/material/delete', material, "DELETE", "/material", "Material");

}

function editMaterial(material, ele){
    var trObj = ele.parentNode.parentNode.parentNode;
    trObj.querySelector("#material_name").innerHTML = `<input id="materialInput" placeholder = '${material.material_name}'/>`;
    trObj.querySelector("#basicBtn").innerHTML = `<button class='btnEdit' onclick='editRowMaterial(${JSON.stringify(material)})'>save</button>`; 
}

function editRowMaterial(material){
    var materialName = document.getElementById('materialInput').value;
    material.material_name = materialName;
    console.log(material);
    restFunction('/material/edit', material, "PUT", "/material", "Material");
}