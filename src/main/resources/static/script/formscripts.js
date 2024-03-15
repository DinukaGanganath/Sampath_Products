function setOptions(url, ele, eleVal){
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(areas){
        let options = document.querySelector(ele);
        let out = "";
        for(let area of areas){
            out += `
                <option>
                    ${area[eleVal]}
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
        var inputField = form.elements[key];
        if (inputField) {
            if(typeof(jsonObj[key]) != 'string' && jsonObj[key] != null){
                var cmnval = commonValue(Object.keys(jsonObj[key]),objValList);
                console.log(cmnval);
                inputField.value = jsonObj[key][cmnval];
            }else{
                inputField.value = jsonObj[key];
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