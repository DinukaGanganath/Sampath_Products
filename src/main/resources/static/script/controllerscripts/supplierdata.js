fetch("/supplier/findall")
.then(function(response){
    return response.json();
})
.then(function(suppliers){
    var placeholder = document.querySelector("#data-output");
    var out = "";
    for(let supplier of suppliers){
        out += `
            <tr class="sup_raw" ondblclick="directEditDelete(${supplier.supplier_id})">
                <td>${supplier.supplier_id}</td>
                <td>${supplier.supplier_name}</td>
                <td>${supplier.supplier_code}</td>
                <td>${supplier.supplier_contact_no1}</td>
                <td>${supplier.supplier_email}</td>
                <td>${supplier.supplier_business_name}</td>
                <td>${supplier.supplier_area_id.area_name}</td>
            </tr>
        `;
    }
    placeholder.innerHTML = out;
})

fetch("/areas/findall")
.then(function(response){
    return response.json();
})
.then(function(areas){
    let options = document.querySelector("#supplierAddressTwo");
    let out = "<option selected disabled>Select Area</option>";
    for(let area of areas){
        out += `
            <option value="${area.area_id}">
                ${area.area_name}
            </option>
        `;
    }
    options.innerHTML = out;
})

fetch("/materials/findall")
.then(function(response){
    return response.json();
})
.then(function(materials){
    let options = document.querySelector("#supplierMaterial");
    let out = "<option selected disabled>Select Material</option>";
    for(let material of materials){
        out += `
            <option value="${material.material_id}">
                ${material.material_name}
            </option>
        `;
    }
    options.innerHTML = out;
})

fetch("/types/findall")
.then(function(response){
    return response.json();
})
.then(function(types){
    let options = document.querySelector("#supplierBusinessType");
    let out = "<option selected disabled>Select Business Type</option>";
    for(let type of types){
        out += `
            <option value="${type.type_id}">
                ${type.type_name}
            </option>
        `;
    }
    options.innerHTML = out;
})

function directEditDelete(id){
    console.log(id);
}


function validForm(){
    var errorStr = "";
    errorStr += checkForRequired();
    errorStr += checkForErrors()
    if(errorStr.trim().length>0)
        alert(errorStr.trim());
    else
        finishConfirmation();
}

function checkForRequired(){
    var fields = document.querySelectorAll('input, select');
    var errorMsg ='Enter values for Required values : \n';
    var initLen = errorMsg.length;
    fields.forEach(field => {
        if(field.hasAttribute('required') && field.value.trim() === ''){

            var indexOfLessThan = field.previousElementSibling.innerHTML.indexOf('<');
            var resultString = field.previousElementSibling.innerHTML.substring(0, indexOfLessThan);
            errorMsg += "Enter value for " + resultString + "\n"

        }
    })
    if(errorMsg.length>initLen){
        return errorMsg;
    }else{
        return '';
    }
}

function checkForErrors(){
    var fields = document.querySelectorAll('input, select');
    var errorMsg ='\n\nEnter values in correct form for following details : \n';
    var initLen = errorMsg.length;
    fields.forEach(field => {
        if(field.classList.contains('invalid')){

            var indexOfLessThan = field.previousElementSibling.innerHTML.indexOf('<');
            var resultString = field.previousElementSibling.innerHTML.substring(0, indexOfLessThan);
            errorMsg += resultString + "\n"

        }
    })
    if(errorMsg.length>initLen){
        return errorMsg;
    }else{
        return '';
    }
}

function userConfirmation(){
    var fields = document.querySelectorAll('input, select');
    var confirmString = 'Are you sure to submit following details: \n';
    fields.forEach(field => {

        if(field.previousElementSibling.innerHTML.includes("<")){
            var indexOfLessThan = field.previousElementSibling.innerHTML.indexOf('<');
            var resultString = field.previousElementSibling.innerHTML.substring(0, indexOfLessThan);
            confirmString += resultString + " : " + field.value +"\n";
        }else{
            confirmString += field.previousElementSibling.innerHTML + " : " + field.value +"\n";
        }


    })

    return confirmString;
}

function finishConfirmation(){
    let userConfirm = window.confirm(userConfirmation());

    if(userConfirm){
        let responseStatus;
        var object = createObject();
        console.log(object);

        $.ajax('/supplier/save', {
            async : false,
            method : "POST",
            data : object,
            dataType : 'json',
            contentType: 'application/json',

            
            success : function (data, status, xhr){
                console.log("success " + status + " " + xhr);
                responseStatus = data;
            },

            error : function (xhr, status, errormsg){
                console.log("fail " + errormsg + " " + status +" " + xhr);
                responseStatus = errormsg;
            },
        });

        if (responseStatus=='OK'){
            

            
            //alert('Supplier Saved Succesfully...');
            //window.location.href = "/supplier";
        }else{
            alert('Some Errors has Occured...');
        }
    }
}

function createObject(){

    object = {
        "supplier_id" : "", 
        "supplier_name" : "", 
        "supplier_code"  : "",
        "supplier_address_line1" : "", 
        "supplier_address_city" : "",
        "supplier_address_postal" : "", 
        "supplier_business_name" : "", 
        "supplier_business_reg" : "", 
        "supplier_contact_no1" : "", 
        "supplier_contact_no2" : "", 
        "supplier_email" : "",
        "supplier_area_id" : 0, 
        "supplier_business_type" : 0,
        "created_date_time" : null, 
        "updated_date_time" : null, 
        "deleted_date_time" : null
    };

    object.supplier_id = parseInt(document.getElementById("supplierId").value, 10); 
    object.supplier_name = document.getElementById("supplierName").value; 
    object.supplier_code  = document.getElementById("supplierCode").value;
    object.supplier_address_line1 = document.getElementById("supplierAddressOne").value; 
    object.supplier_address_city = document.getElementById("supplierCity").value;
    object.supplier_address_postal = document.getElementById("supplierPostalCode").value; 
    object.supplier_business_name = document.getElementById("supplierBusinessName").value; 
    object.supplier_business_reg = document.getElementById("supplierRegistration").value; 
    object.supplier_contact_no1 = document.getElementById("supplierMobileOne").value; 
    object.supplier_contact_no2 = document.getElementById("supplierMobileTwo").value; 
    object.supplier_email = document.getElementById("supplierEmail").value;
    object.supplier_area_id = parseInt(document.getElementById("supplierAddressTwo").value, 10); 
    object.supplier_business_type = parseInt(document.getElementById("supplierBusinessType").value, 10);
    //object.created_date_time = document.getElementById("supplierId").value; 
    //object.updated_date_time = document.getElementById("supplierId").value; 
    //object.deleted_date_time = document.getElementById("supplierId").value;

    return object;

}

 