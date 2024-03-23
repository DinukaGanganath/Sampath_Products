initLayout("Area", "Area Details");
sidebarLoader("/areas");

let currentPage = 1;

loadTable();

function loadTable(){
    fetch("/areas/findall/deleted")
    .then(function(response){
        return response.json();
    })
    .then(function(areas){
        let placeholder = document.querySelector("#data-output");
        let rowNo = areas.length;
        let maxRow = 8;
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
                        <button class="btnEdit" onclick='restoreArea(` + JSON.stringify(area) + `)'>Restore</button>
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

function saveArea(){
    var areaNew = document.getElementById('areaName').value;
    addAreaPost(areaNew);
}

function restoreArea(area){
    console.log(area);
    restFunction('/area/restore', area, "PUT", "/areas", "Area");
}
