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
        "url" : "/materialneed",
        "status" : "active"
    },
    {
        "str" : "Ordered",
        "url" : "/materialordered"
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
                <td id="material_want">${material.material_want +" " + material.material_unit}</td>
                <td id="material_has">${material.material_has +" " + material.material_unit}</td>
                <td id="material_extra">${material.material_extra +" " + material.material_unit}</td>
                <td id="material_ordered">${material.material_extra + material.material_want - material.material_has +" " + material.material_unit}</td>
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