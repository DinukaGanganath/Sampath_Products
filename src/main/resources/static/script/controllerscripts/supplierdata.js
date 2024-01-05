fetch("/supplier/findall")
.then(function(response){
    return response.json();
})
.then(function(suppliers){
    let placeholder = document.querySelector("#data-output");
    let out = "";
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

 