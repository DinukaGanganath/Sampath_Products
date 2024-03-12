initLayout("Supplier", "Supplier Details");
sidebarLoader("/supplier");

fetch("/supplier/findall")
.then(function(response){
    return response.json();
})
.then(function(suppliers){
    var placeholder = document.querySelector("#data-output");
    var out = "";
    for(let supplier of suppliers){
        if(supplier.supplier_deleted == 0){
            out += `
                <tr class="sup_raw" ondblclick='directEditform(` + JSON.stringify(supplier) + `)'>
                    <td>${supplier.supplier_name}</td>
                    <td>${supplier.supplier_code}</td>
                    <td>${supplier.supplier_contact_no1}</td>
                    <td>${supplier.supplier_email}</td>
                    <td>${supplier.supplier_business_name}</td>
                    <td>${supplier.supplier_area_id.area_name}</td>
                    <td>${supplier.supplier_material_id.material_name}</td>
                </tr>
            `;
        }
    }
    placeholder.innerHTML = out;
})

fetch("/areas/findall")
.then(function(response){
    return response.json();
})
.then(function(areas){
    let options = document.querySelector("#supplier_area_id");
    let out = "<option selected disabled>Select Area</option>";
    for(let area of areas){
        
        out += `
            <option value=` + JSON.stringify(area) +`>
                ${area.area_name}
            </option>
        `;
    }
    options.innerHTML = out;
    console.log(out);
})

fetch("/materials/findall")
.then(function(response){
    return response.json();
})
.then(function(materials){
    let options = document.querySelector("#supplier_material_id");
    let out = "<option selected disabled>Select Material</option>";
    for(let material of materials){
        out += `
            <option value=` + JSON.stringify(material) +` >
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
    let options = document.querySelector("#supplier_business_type");
    let out = "<option selected disabled>Select Business Type</option>";
    for(let type of types){
        out += `
            <option value=` + JSON.stringify(type) + `>
                ${type.type_name}
            </option>
        `;
    }
    options.innerHTML = out;
})

function directEditform(supplier){
    console.log(supplier);
    window.location.href = `/supplieredit?id=${supplier.supplierid}`;
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
        var supplier = createJson("supplierAddForm", ["input", "select"]);
        console.log(supplier);

        $.ajax('/supplier/save', {
            async : false,
            type : "POST",
            data : JSON.stringify(supplier),
            contentType: 'application/json',

            success : function (data, status, xhr){
                console.log("success " + status + " " + xhr);
                responseStatus = data;
                console.log(responseStatus);
            },

            error : function (xhr, status, errormsg){
                console.log("fail " + errormsg + " " + status +" " + xhr);
                responseStatus = errormsg;
            },
        });

        if (responseStatus=='Ok'){
            alert('Supplier Saved Succesfully...');
            window.location.href = "/supplier";
        }else{
            alert('Some Errors has Occured...');
        }
    }
}

function createObject(){

    /*object = {
        "supplier_name" : "",
        "supplier_nic" : "", 
        "supplier_address_line1" : "", 
        "supplier_address_city" : "",
        "supplier_address_postal" : "", 
        "supplier_business_name" : "", 
        "supplier_business_reg" : "",
        "supplier_land_phone" : "", 
        "supplier_contact_no1" : "", 
        "supplier_contact_no2" : "", 
        "supplier_email" : "",
        "supplier_area_id" : 0, 
        "supplier_business_type" : 0,
        "supplier_material_id" : "",
        "created_date_time" : null, 
        "updated_date_time" : null, 
        "deleted_date_time" : null
    };

    object.supplier_name = document.getElementById("supplierName").value; 
    object.supplier_nic = document.getElementById("supplierNIC").value;
    object.supplier_address_line1 = document.getElementById("supplierAddressOne").value; 
    object.supplier_address_city = document.getElementById("supplierCity").value;
    object.supplier_address_postal = document.getElementById("supplierPostalCode").value; 
    object.supplier_business_name = document.getElementById("supplierBusinessName").value; 
    object.supplier_business_reg = document.getElementById("supplierRegistration").value;
    object.supplier_land_phone = document.getElementById("supplierLandPhone").value;
    object.supplier_contact_no1 = document.getElementById("supplierMobileOne").value; 
    object.supplier_contact_no2 = document.getElementById("supplierMobileTwo").value; 
    object.supplier_email = document.getElementById("supplierEmail").value;
    object.supplier_area_id = JSON.parse(document.getElementById("supplierAddressTwo").value); 
    object.supplier_business_type = JSON.parse(document.getElementById("supplierBusinessType").value);
    object.supplier_material_id = JSON.parse(document.getElementById("supplierMaterial").value);
    //object.created_date_time = document.getElementById("supplierId").value; 
    //object.updated_date_time = document.getElementById("supplierId").value; 
    //object.deleted_date_time = document.getElementById("supplierId").value;

    return object; */

    var object = createJson("supplierAddForm", ["input", "select"]);
    return object;

}

 