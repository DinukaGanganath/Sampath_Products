initLayout("Product Type", "Deleted Product Type");
sidebarLoader("/producttypes");

let currentPage = 1;

loadTable();

function loadTable(){
    fetch("/producttypes/findall/deleted")
    .then(function(response){
        return response.json();
    })
    .then(function(producttypes){
        let placeholder = document.querySelector("#data-output");
        let rowNo = producttypes.length;
        let maxRow = 8;
        let pageNo = Math.ceil(rowNo/maxRow);
        let pagDataList = [];
        
        var start = (currentPage-1)*maxRow;
        var stop = currentPage*maxRow;

        if(rowNo<stop)
            stop = rowNo;

        for(var i = start; i<stop; i++){
            pagDataList.push(producttypes[i]);
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
    for(let producttype of pagDataList){
        out += `
            <tr id=`+ producttype.producttype_id +`>
                <td id="producttype_name">${producttype.producttype_name.replaceAll('_', ' ')}</td>
                <td id="producttype_code">${producttype.producttype_code}</td>
                <td>
                    <div id="basicBtn" style="display:flex">
                        <button class="btnEdit" onclick='restoreProductType(` + JSON.stringify(producttype) + `)'>Restore</button>
                    </div>
                    <div id="secondaryBtn"  style="display:none">
                        <button class="btnEdit" onclick='editRowProductType(this)'>save</button>
                        <button class="btnDelete" onclick='discardRowProductType()'>Discard</button>
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

function restoreProductType(producttype){
    console.log(producttype);
    restFunction('/producttype/restore', producttype, "PUT", "/producttypes", "ProductType");
}
