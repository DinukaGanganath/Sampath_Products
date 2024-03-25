//this is to load the option list from the data base and load it
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

//create a div with different buttons
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

//when the object is given to the function it will be filled
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

//get the common values in two arrays
function commonValue(arr1, arr2) {
    for (var i = 0; i < arr1.length; i++) {
        if (arr2.includes(arr1[i])) {
            return arr1[i] ;
        }
    }
    return "-1";
}

//create a button which can be used to direct the path
function btnConfig(btnId, btnUrl, btnClass){
    document.getElementById(btnId).addEventListener("click", function() {
        window.location.href = btnUrl;
    });

    document.getElementById("redirectButton").classList.add(btnClass);
}

//used when loading the form. can be used to create input text element before the select element
function optionInput(optionIdList, recievedObj){
    for(items of optionIdList){
        var sib = document.getElementById(items[1]);

        var optInput = document.createElement("input");
        optInput.setAttribute("id", items[1]);
        optInput.setAttribute("type", "text");
        optInput.onclick = function() {
            viewOptions(this,optionIdList);
        };
        optInput.classList.add("optVal");
        optInput.setAttribute("value",JSON.stringify(recievedObj[items[1]]));
        sib.parentNode.insertBefore(optInput, sib);
        optInput.nextElementSibling.classList.add("avoid");
        optInput.setAttribute('required', 'required');
        optInput.nextElementSibling.removeAttribute('required', 'required');
    }
}

//used for whole option lists
function viewOptions(ele, optionIdList){
    ele.nextElementSibling.style.display = "block";
    ele.nextElementSibling.classList.remove("avoid");
    ele.style.display = "none";
    ele.nextElementSibling.setAttribute('required', 'required');
    ele.removeAttribute('required', 'required');

    for(optionId of optionIdList){
        if(ele.nextElementSibling.id==optionId[1]){
            ele.remove();
            loadOptionVal(optionId[0], optionId[1], optionId[2], optionId[3]);
        }
    }
}

function disableForm(formId, attrList){
    for(ele of document.getElementById(formId).querySelectorAll(attrList))
        ele.setAttribute('disabled', true);
}

function loadDivisionVal(ele){

    var city = document.getElementById("supplier_address_city");
    var code = document.getElementById("supplier_address_postal");

    city.value = JSON.parse(ele.value).postal_division_id.postal_division_name;
    code.value = JSON.parse(ele.value).postal_division_id.postal_division_code;
    
    city.setAttribute("disabled","disabled");
}

