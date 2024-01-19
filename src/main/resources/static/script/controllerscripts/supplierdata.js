fetch("/supplier/findall")
.then(function(response){
    return response.json();
})
.then(function(suppliers){
    var placeholder = document.querySelector("#data-output");
    var out = "";
    for(let supplier of suppliers){
        out += `
            <tr>
                <td>${supplier.supplier_id}</td>
                <td>${supplier.supplier_name}</td>
                <td>${supplier.supplier_code}</td>
                <td>${supplier.supplier_contact_no1}</td>
                <td>${supplier.supplier_email}</td>
                <td>${supplier.supplier_business_name}</td>
                <td>${supplier.supplier_address_city}</td>
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
            <option>
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
            <option>
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
            <option>
                ${type.type_name}
            </option>
        `;
    }
    options.innerHTML = out;
})


 