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
                        <button class="btnEdit" onclick='editArea(` + JSON.stringify(area) + `,this)'>Edit</button>
                        <button class="btnDelete" onclick='deleteArea(` + JSON.stringify(area) + `)'>Delete</button>
                    </div>
                </td>
            </tr>
        `;
    }
    placeholder.innerHTML = out;

    console.log(out);
})


function showForm(){

    $("#area_tab tbody").prepend("<tr><td id= newItem></td><td></td><td id= newAddBtn onclick=saveArea()></td></tr>");

    var inputFieldData = document.getElementById("newItem");
    var inputField = document.createElement("input");
    inputField.type = "text";
    inputField.id = "areaName";
    inputFieldData.appendChild(inputField);

    var buttonFieldData = document.getElementById("newAddBtn");
    var buttonField = document.createElement('button');
    buttonField.innerHTML = 'Submit';
    buttonField.className = 'btnEdit';
    buttonField.id = 'buttonAdd';
    buttonFieldData.appendChild(buttonField);
}

function editRowShow(){

}

function saveArea(){
    var areaNew = document.getElementById('areaName').value;
    addAreaPost(areaNew);
}


function editArea(area, ele){

    var editValInput = document.createElement("input");
    editValInput.type = "text";
    editValInput.id = "editValue";
    ele.parentElement.parentElement.parentElement.firstElementChild.innerHTML = "";
    ele.parentElement.parentElement.parentElement.firstElementChild.appendChild(editValInput);
    document.getElementById('editValue').value = area.area_name;

    editAreaPut(document.getElementById('editValue').value);
}

function addAreaPost(areaNew){
    // create new area object
    var area;
    if(areaNew != ""){
        area = {
            "area_name" : ""
        };

        area.area_name = areaNew;
        console.log(area);

        //call POST function
        restFunction('/area/save', area, "POST", "/areas", "Area");
    }else{
        alert("Enter a Area Name !!!");
        window.location.href = "/areas";
    }

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

function editAreaPut(editedValue){

    var area = {
        "area_name" : ""
    };

    area.area_name = editedValue;

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