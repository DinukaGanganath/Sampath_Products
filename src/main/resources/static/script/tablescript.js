var object;
var currPage = 1;
var thisUrl, thisTabVal;

function showContextMenu(str, event){
    object = str;
    var contextElement = document.getElementById("context-menu");
    contextElement.style.left = event.clientX + "px";
    contextElement.style.top = event.clientY + "px";
    contextElement.classList.add("active"); 
}

function dataLoadTable(url, tableValList, maxRow){
    thisUrl = url;
    thisTabVal = tableValList;
    thisMaxRow = maxRow;

    console.log(url);
    console.log(tableValList)

    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(objs){
        var placeholder = document.querySelector("#data-output");
        let rowNo = objs.length;
        let pageNo = Math.ceil(rowNo/thisMaxRow);
        let pagDataList = [];
        
        var start = (currPage-1)*thisMaxRow;
        var stop = currPage*thisMaxRow;

        if(rowNo<stop)
            stop = rowNo;

        for(var i = start; i<stop; i++){
            pagDataList.push(objs[i]);
        }

        console.log(`pag :\n ${pagDataList} \n start : ${start} \n end : ${stop} \n curr : ${currPage} \n page : ${pageNo} \n max no : ${thisMaxRow}`);
        document.getElementById("pagMiddle").innerHTML = `<b>${currPage}</b> of ${pageNo}`;

        visualizePag(currPage, pageNo);
        placeholder.innerHTML = setDataSet(pagDataList, tableValList);
    });
    
}

function getDbData(url){
    var listDb = [];
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(objs){
        for(var obj of objs){
            listDb.push(obj)
        }
    });
    return listDb;
}

function loadPrevious(){
    currPage--;
    dataLoadTable(thisUrl, thisTabVal, thisMaxRow);
}

function loadNext(){
    currPage++;
    dataLoadTable(thisUrl, thisTabVal, thisMaxRow);
}

function setDataSet(pagDataList, tableValList){
    var tdList = "";
    var out = "";
    for(let obj of pagDataList){
        for(val of tableValList){
            var str = obj[val];
            if(typeof(val)=="string"){
                tdList += `<td>${str.replaceAll('_', ' ')}</td>`
            }
            else{
                var ele = obj[val[0]];
                for(var i=1; i<val.length; i++){
                    ele = ele[val[i]];
                }
                tdList += `<td>${ele.replaceAll('_', ' ')}</td>`
            }
        }
        out += `
            <tr class="sup_raw"  onclick='showContextMenu(` + JSON.stringify(obj) + `, event)'>
                ${tdList}
            </tr>
        `;
        tdList="";
    }
    return out;
}

function onClickItem(ele){
    directEditform()
    
    console.log(object);
    document.getElementById("context-menu").classList.remove('active');
}

//direct to the edit window.
function directEditform(object, url){
    alert(object);
    window.location.href = url;
}

// return Row Object
function getRowObject(){
    return object;
}

function tableInnerContentLoader(objs, tableValList){
    var tdList = "";
    var out = "";
    for(let obj of objs){
        for(val of tableValList){
            var str = obj[val];
            if(typeof(val)=="string"){
                tdList += `<td>${str.replaceAll('_', ' ')}</td>`
            }
            else{
                var ele = obj[val[0]];
                for(var i=1; i<val.length; i++){
                    ele = ele[val[i]];
                }
                tdList += `<td>${ele}</td>`
            }

        }
        out += `
            <tr class="sup_raw"  onclick='showContextMenu(` + JSON.stringify(obj) + `, event)'>
                ${tdList}
            </tr>
        `;
        tdList="";
    }
    return out;
}

function visualizePag(currentPage, pageNo){
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

