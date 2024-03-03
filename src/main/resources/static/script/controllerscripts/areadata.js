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
                        <button class="btnEdit" onclick='editArea(` + JSON.stringify(area) + `)'>Edit</button>
                        <button class="btnDelete" onclick='deleteArea(` + JSON.stringify(area) + `)'>Delete</button>
                    </div>
                </td>
            </tr>
        `;
    }
    placeholder.innerHTML = out;

    console.log(out);
})


function showForm(viewId){
    if(document.getElementById(viewId).classList.contains('hidden')){
        document.getElementById(viewId).classList.remove('hidden');
        document.getElementById(viewId).classList.add('horizontal');
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


function editArea(area){

    showForm('areaEditForm');

    if(document.getElementById('areaEditForm').classList.contains('horizontal')){
        document.getElementById('areaEditForm').classList.remove('horizontal');
        document.getElementById('areaEditForm').classList.add('hidden');
        //document.getElementById('addBtn').disabled = false;
    }

    document.getElementById('areaCode').value = area.area_code;
    document.getElementById('areaName').value = area.area_name;
}

function addAreaPost(){
    // create new area object
    var area = {
        "area_name" : ""
    };

    area.area_name = document.getElementById("areaName").value;
    console.log(area);

    //call POST function
    restFunction('/area/save', area, "POST", "/areas", "Area");

}

function deleteArea(area){

    console.log(area);

    $.ajax('/area/delete', {
        async : false,
        type : "DELETE",
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

    if (responseStatus=='ok'){
        alert('Area Deleted Succesfully...');
        window.location.href = "/areas";
    }else{
        alert('Some Errors has Occured...');
    }
}

function editAreaPut(){

    var area = {
        "area_name" : ""
    };

    area.area_name = document.getElementById("areaName").value;

    console.log(area);

    $.ajax('/area/delete', {
        async : false,
        type : "PUT",
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
        alert('Area Saved Succesfully...');
        window.location.href = "/areas";
    }else{
        alert('Some Errors has Occured...');
    }
}