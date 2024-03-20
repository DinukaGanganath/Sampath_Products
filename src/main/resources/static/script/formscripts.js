function loadOptionVal(url, eleId, eleVal, type){
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(objs){
        let options = document.querySelector('#'+eleId);
        let out = `<option value='' selected disabled>Select ${type}</option>`;
        for(let obj of objs){
            out += `
                <option value=` + JSON.stringify(obj) +` >
                    ${obj[eleVal]}
                </option>
            `;
        }
        options.innerHTML = out;
    })
}

function createButtonDiv(divDetails, btnList){
    var btnDiv = document.createElement("div");
    btnDiv.id = divDetails[0];
    btnDiv.classList.add(divDetails[1]);
    btnDiv.style.display = divDetails[2];
    
    for(var i = 0 ; i<btnList.length; i++){
    
        var newBtn = document.createElement("button");
        newBtn.id = btnList[i][0];
        newBtn.classList.add(btnList[i][1]);
        newBtn.innerHTML = btnList[i][2];
        newBtn.onclick = btnList[i][3]; 
        btnDiv.appendChild(newBtn);
    }

    return btnDiv;
}

function objectToForm(formId, jsonObj, objValList) {
    var form = document.getElementById(formId);
    var keys = Object.keys(jsonObj);

    keys.forEach(function(key) {
        var inputField = document.getElementById(key);
        if (inputField) {
            if(inputField.tagName != "SELECT"){
                if(typeof(jsonObj[key]) != 'string' && jsonObj[key] != null){
                    var cmnval = commonValue(Object.keys(jsonObj[key]),objValList);
                    inputField.value = jsonObj[key][cmnval];
                }else{
                    inputField.value = jsonObj[key];
                }
            }else{
                for(attr of Object.keys(jsonObj[key])){
                    for(objVal of objValList){
                        if(attr == objVal){
                           
                        }
                    }
                }

            }
        }
    });
}

function commonValue(arr1, arr2) {
    for (var i = 0; i < arr1.length; i++) {
        if (arr2.includes(arr1[i])) {
            return arr1[i] ;
        }
    }
    return "-1";
}

function btnConfig(btnId, btnUrl, btnClass){
    document.getElementById(btnId).addEventListener("click", function() {
        window.location.href = btnUrl;
    });

    document.getElementById("redirectButton").classList.add(btnClass);
}

function optionInput(optionIdList, recievedObj){
    for(items of optionIdList){
        var sib = document.getElementById(items[1]);

        var optInput = document.createElement("input");
        optInput.setAttribute("id", items[1]);
        optInput.setAttribute("type", "text");
        optInput.onclick = function() {
            viewOptions(this,optionIdList);
        };
        optInput.setAttribute("value",JSON.stringify(recievedObj[items[1]]));
        //console.log(recievedObj[items[1]]);
        sib.parentNode.insertBefore(optInput, sib);
    }
}

function viewOptions(ele, optionIdList){
    ele.nextElementSibling.style.display = "block";
    ele.style.display = "none";

    for(optionId of optionIdList){
        if(ele.nextElementSibling.id==optionId[1]){
            ele.remove();
            loadOptionVal(optionId[0], optionId[1], optionId[2], optionId[3]);
        }
    }
}

