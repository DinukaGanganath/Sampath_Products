initLayout("Material", "Material Details");
sidebarLoader("/material");

let currentPage = 1;

loadTable();

function loadTable(){
    fetch("/material/findall/exist")
    .then(function(response){
        return response.json();
    })
    .then(function(materials){
        let placeholder = document.querySelector("#data-output");
        let rowNo = materials.length;
        let maxRow = 2;
        let pageNo = Math.ceil(rowNo/maxRow);
        let pagDataList = [];
        
        var start = (currentPage-1)*maxRow;
        var stop = currentPage*maxRow;

        if(rowNo<stop)
            stop = rowNo;

        for(var i = start; i<stop; i++){
            pagDataList.push(materials[i]);
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
            <tr>
                <td>${material.material_name}</td>
                <td>${material.material_code}</td>
                <td>
                    <div style="display:flex">
                        <button class="btnEdit" onclick='editMaterial(` + JSON.stringify(material) + `)'>Edit</button>
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

function showAddForm(){
    if(document.getElementById('materialAddForm').classList.contains('hidden')){
        document.getElementById('materialAddForm').classList.remove('hidden');
        document.getElementById('materialAddForm').classList.add('horizontal');
        //document.getElementById('addBtn').disabled = true;
    }
}

function saveMaterial(){
    if(document.getElementById('materialAddForm').classList.contains('horizontal')){
        document.getElementById('materialAddForm').classList.remove('horizontal');
        document.getElementById('materialAddForm').classList.add('hidden');
        //document.getElementById('addBtn').disabled = false;
        addMaterialPost();
    }
}

function addMaterialPost(){

    //create Material Object
    if(document.getElementById("materialName").value != ""){
        var material = {
            "material_name" : ""
        };

        material.material_name = document.getElementById("materialName").value;

        //save the material
        restFunction('/material/save', material, "POST", '/materials', "Material");
    }else{
        alert("Enter a Material Name");
        window.location.href = "/materials";
    }
  
}

function deleteMaterial(material){

    console.log(material);
    restFunction('/material/delete', material, "DELETE", "/material", "Material");

}

function editMaterial(material){

    console.log(material);
    restFunction('/material/edit', material, "PUT", "/materials", "Material");

}