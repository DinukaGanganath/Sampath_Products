initLayout("Product Types", "Product Types Details");
sidebarLoader("/product");

var jsonList = [
    {
        "str" : "Product size",
        "url" : "/productsizes"
    },
    {
        "str" : "Product type",
        "url" : "/producttypes",
        "status" : "active",
    },
    {
        "str" : "Product",
        "url" : "/product"
    },
]
createThinLongDiv(jsonList);

let currentPage = 1;

loadTable();

function loadTable(){
    fetch("/producttypes/findall/exist")
    .then(function(response){
        return response.json();
    })
    .then(function(producttypes){
        let placeholder = document.querySelector("#data-output");
        let rowNo = producttypes.length;
        let maxRow = 7;
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
                        <button class="btnEdit" onclick='editProductType(` + JSON.stringify(producttype) +`, this)'>Edit</button>
                        <button class="btnDelete" onclick='deleteProductType(` + JSON.stringify(producttype) + `)'>Delete</button>
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

    $("#producttype_tab tbody").prepend("<tr><td id= newItem><td></td></td><td id='newdivision'></td><td id= newAddBtn onclick=saveProductType()></td></tr>");

    var inputFieldData = document.getElementById("newItem");
    var inputField = document.createElement("input");
    inputField.type = "text";
    inputField.id = "producttypeName";
    inputFieldData.appendChild(inputField);


    var buttonFieldData = document.getElementById("newAddBtn");
    var buttonField = document.createElement('button');
    buttonField.innerHTML = 'Submit';
    buttonField.className = 'btnEdit';
    buttonField.id = 'buttonAdd';
    buttonFieldData.appendChild(buttonField);
}

function saveProductType(){
    var producttypeNew = document.getElementById('producttypeName').value.replaceAll(' ', '_');
    var producttype = {};
    producttype['producttype_name'] = producttypeNew;
    restFunction('/producttype/save', producttype, "POST", "/producttypes", "ProductType");
}

function editProductType(producttype, ele){
    var trObj = ele.parentNode.parentNode.parentNode;
    
    trObj.querySelector("#producttype_name").innerHTML = `<input id="producttypeInput" placeholder = '${producttype.producttype_name}'/>`;
    trObj.querySelector("#basicBtn").innerHTML = `<button class='btnEdit' onclick='editRowProductType(${JSON.stringify(producttype)})'>save</button>`; 
}

function editRowProductType(producttype){
    var producttypeName = document.getElementById('producttypeInput').value;
    producttype.producttype_name = producttypeName;
    restFunction('/producttype/edit', producttype, "PUT", "/producttypes", "ProductType");
}

function deleteProductType(producttype){

    console.log(producttype);
    restFunction('/producttype/delete', producttype, "DELETE", "/producttypes", "ProductType");

}