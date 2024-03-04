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
                <td>${material.material_name}</td>
                <td>${material.material_code}</td>
                <td>
                    <div style="display:flex">
                        <button class="btnEdit" onclick='editMaterial(` + JSON.stringify(material) + `)'>Edit</button>
                        <button class="btnDelete" onclick='deleteMaterial(` + JSON.stringify(material) + `)'>Delete</button>
                    </div>
                </td>
            </tr>
        `;
    }
    placeholder.innerHTML = out;
})


function showAddForm(){
    if(document.getElementById('materialAddForm').classList.contains('hidden')){
        document.getElementById('materialAddForm').classList.remove('hidden');
        document.getElementById('materialAddForm').classList.add('horizontal');
        //document.getElementById('addBtn').disabled = true;
    }
}

function saveMaterial(){
    if(document.getElementById('materialAddForm').classList.contains('horizontal')){
        document.getElementById('materialAddForm').classList.remove('horizontal');
        document.getElementById('materialAddForm').classList.add('hidden');
        //document.getElementById('addBtn').disabled = false;
        addMaterialPost();
    }
}

function addMaterialPost(){

    //create Material Object
    if(document.getElementById("materialName").value != ""){
        var material = {
            "material_name" : ""
        };

        material.material_name = document.getElementById("materialName").value;

        //save the material
        restFunction('/material/save', material, "POST", '/materials', "Material");
    }else{
        alert("Enter a Material Name");
        window.location.href = "/materials";
    }
  
}

function deleteMaterial(material){

    console.log(material);

}

function editMaterial(material){

    console.log(material);

    $.ajax('/material/delete', {
        async : false,
        type : "PUT",
        data : JSON.stringify(material),
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
        alert('Material Saved Succesfully...');
        window.location.href = "/materials";
    }else{
        alert('Some Errors has Occured...');
    }
}