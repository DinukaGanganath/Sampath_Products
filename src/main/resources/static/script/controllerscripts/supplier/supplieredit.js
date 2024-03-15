var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

initLayout("Supplier Edit", "Supplier Edit");
sidebarLoader("/supplier");

/*var queryString = window.location.search;
var urlParams = new URLSearchParams(queryString);
var encodedData = urlParams.get('data');
var myObject = JSON.parse(decodeURIComponent(encodedData));
console.log(myObject);*/

fetch("/areas/findall")
.then(function(response){
    return response.json();
})
.then(function(areas){
    let optionList = document.querySelector("#supplierAddressTwo");
    let out = "<option selected disabled>Select Area</option>";
    for(let area of areas){
        
        out += `
            <option value=` + JSON.stringify(area) +`>
                ${area.area_name}
            </option>
        `;
    }
    optionList.innerHTML = out;
})

fetch("/materials/findall")
.then(function(response){
    return response.json();
})
.then(function(materials){
    let optionList = document.querySelector("#supplierMaterial");
    let out = "<option selected disabled>Select Material</option>";
    for(let material of materials){
        out += `
            <option value=` + JSON.stringify(material) +` >
                ${material.material_name}
            </option>
        `;
    }
    optionList.innerHTML = out;
})

fetch("/types/findall")
.then(function(response){
    return response.json();
})
.then(function(types){
    let optionList = document.querySelector("#supplierBusinessType");
    let out = "<option selected disabled>Select Business Type</option>";
    for(let type of types){
        out += `
            <option value=` + JSON.stringify(type) + `>
                ${type.type_name}
            </option>
        `;
    }
    optionList.innerHTML = out;
})

const parameters = new URLSearchParams(window.location.search);
const supplier_id = parameters.get('id');
console.log(supplier_id);

fetch("/supplier/findall")
.then(function(response){
    return response.json();
})
.then(function(suppliers){
    for(let supplier of suppliers){
        if(supplier.supplierid == supplier_id){
            console.log(supplier);
            document.getElementById('supplierName').value = supplier.supplier_name;
            document.getElementById('supplierNIC').value = supplier.supplier_nic;
            document.getElementById('supplierAddressOne').value = supplier.supplier_address_line1;
            document.getElementById('supplierAddressTwo').value = supplier.supplier_area_id.area_name;
            document.getElementById('supplierCity').value = supplier.supplier_address_city;
            document.getElementById('supplierPostalCode').value = supplier.supplier_address_postal;
            document.getElementById('supplierLandPhone').value = supplier.supplier_land_phone;
            document.getElementById('supplierMobileOne').value = supplier.supplier_contact_no1;
            document.getElementById('supplierMobileTwo').value = supplier.supplier_contact_no2;
            document.getElementById('supplierEmail').value = supplier.supplier_email;
            document.getElementById('supplierBusinessName').value = supplier.supplier_business_name;
            document.getElementById('supplierRegistration').value = supplier.supplier_business_reg;
            document.getElementById('supplierBusinessType').value = supplier.supplier_business_type.type_name;
            document.getElementById('supplierMaterial').value = supplier.supplier_material_id.material_name; 
            
            console.log(document.querySelector('#supplierAddressTwo').value);
        }
    }
})