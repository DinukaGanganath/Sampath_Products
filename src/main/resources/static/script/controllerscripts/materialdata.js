fetch("/materials/findall")
.then(function(response){
    return response.json();
})
.then(function(materials){
    let placeholder = document.querySelector("#data-output");
    let out = "";
    for(let material of materials){
        out += `
            <tr>
                <td>${material.material_id}</td>
                <td>${material.material_name}</td>
                <td>${material.material_code}</td>
            </tr>
        `;
    }
    placeholder.innerHTML = out;
})