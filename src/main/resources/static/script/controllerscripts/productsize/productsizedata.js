initLayout("Product Sizes", "Product Sizes Details");
sidebarLoader("/product");

var jsonList = [
    {
        "str" : "Product size",
        "url" : "/productsizes",
        "status" : "active"
    },
    {
        "str" : "Product type",
        "url" : "/producttypes"
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
    fetch("/productsizes/findall/exist")
    .then(function(response){
        return response.json();
    })
    .then(function(productsizes){
        let placeholder = document.querySelector("#data-output");
        let rowNo = productsizes.length;
        let maxRow = 7;
        let pageNo = Math.ceil(rowNo/maxRow);
        let pagDataList = [];
        
        var start = (currentPage-1)*maxRow;
        var stop = currentPage*maxRow;

        if(rowNo<stop)
            stop = rowNo;

        for(var i = start; i<stop; i++){
            pagDataList.push(productsizes[i]);
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
    for(let productsize of pagDataList){
        out += `
            <tr id=`+ productsize.productsize_id +`>
                <td id="productsize_name">${productsize.productsize_name.replaceAll('_', ' ')}</td>
                <td id="productsize_code">${productsize.productsize_code}</td>
                <td>
                    <div id="basicBtn" style="display:flex">
                        <button class="btnEdit" onclick='editProductType(` + JSON.stringify(productsize) +`, this)'>Edit</button>
                        <button class="btnDelete" onclick='deleteProductType(` + JSON.stringify(productsize) + `)'>Delete</button>
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

    $("#productsize_tab tbody").prepend("<tr><td id= newItem><td></td></td><td id='newdivision'></td><td id= newAddBtn onclick=saveProductType()></td></tr>");

    var inputFieldData = document.getElementById("newItem");
    var inputField = document.createElement("input");
    inputField.type = "text";
    inputField.id = "productsizeName";
    inputFieldData.appendChild(inputField);


    var buttonFieldData = document.getElementById("newAddBtn");
    var buttonField = document.createElement('button');
    buttonField.innerHTML = 'Submit';
    buttonField.className = 'btnEdit';
    buttonField.id = 'buttonAdd';
    buttonFieldData.appendChild(buttonField);
}

function saveProductType(){
    var productsizeNew = document.getElementById('productsizeName').value.replaceAll(' ', '_');
    var productsize = {};
    productsize['productsize_name'] = productsizeNew;
    restFunction('/productsize/save', productsize, "POST", "/productsizes", "ProductType");
}

function editProductType(productsize, ele){
    var trObj = ele.parentNode.parentNode.parentNode;
    
    trObj.querySelector("#productsize_name").innerHTML = `<input id="productsizeInput" placeholder = '${productsize.productsize_name}'/>`;
    trObj.querySelector("#basicBtn").innerHTML = `<button class='btnEdit' onclick='editRowProductType(${JSON.stringify(productsize)})'>save</button>`; 
}

function editRowProductType(productsize){
    var productsizeName = document.getElementById('productsizeInput').value;
    productsize.productsize_name = productsizeName;
    restFunction('/productsize/edit', productsize, "PUT", "/productsizes", "ProductType");
}

function deleteProductType(productsize){

    console.log(productsize);
    restFunction('/productsize/delete', productsize, "DELETE", "/productsizes", "ProductType");

}