fetch("/areas/findall")
.then(function(response){
    return response.json();
})
.then(function(areas){
    let placeholder = document.querySelector("#data-output");
    let out = "";
    for(let area of areas){
        out += `
            <tr>
                <td>${area.area_name}</td>
                <td>${area.area_code}</td>
                <td>
                    <div style="display:flex">
                        <button class="btnEdit" onclick='editMaterial(` + JSON.stringify(area) + `)'>Edit</button>
                        <button class="btnDelete" onclick='deleteMaterial(` + JSON.stringify(area) + `)'>Delete</button>
                    </div>
                </td>
            </tr>
        `;
    }
    placeholder.innerHTML = out;

    console.log(out);
})


function showAddForm(){
    if(document.getElementById('areaAddForm').classList.contains('hidden')){
        document.getElementById('areaAddForm').classList.remove('hidden');
        document.getElementById('areaAddForm').classList.add('horizontal');
        //document.getElementById('addBtn').disabled = true;
    }
}

function saveArea(){
    if(document.getElementById('areaAddForm').classList.contains('horizontal')){
        document.getElementById('areaAddForm').classList.remove('horizontal');
        document.getElementById('areaAddForm').classList.add('hidden');
        //document.getElementById('addBtn').disabled = false;
        addAreaPost();
    }
}

function addAreaPost(){

    var area = {
        "area_name" : ""
    };

    area.area_name = document.getElementById("areaName").value;

    let responseStatus;
    console.log(area);

    $.ajax('/area/save', {
        async : false,
        type : "POST",
        data : JSON.stringify(area),
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
        window.location.href = "/areas";
    }else{
        alert('Some Errors has Occured...');
    }
}

function deleteMaterial(material){

    console.log(material);

    $.ajax('/material/delete', {
        async : false,
        type : "DELETE",
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

    if (responseStatus=='ok'){
        alert('Material Deleted Succesfully...');
        window.location.href = "/materials";
    }else{
        alert('Some Errors has Occured...');
    }
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