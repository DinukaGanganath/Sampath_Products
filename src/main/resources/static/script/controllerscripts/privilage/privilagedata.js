//configure the GUI and window parameters
initLayout("Privilage", "Privilage Details");
sidebarLoader("/supplier");

//Load the names options to the Select tag
loadOptionVal("/module/findall", "module_id", "module_name", "Module");
loadOptionVal("/role/findall", "role_id", "role_name", "Role");

function searchPrivilage(ele){
    var module = JSON.parse(document.getElementById('module_id').value);
    var role = JSON.parse(document.getElementById('role_id').value);
    var moduleId = module.module_id;
    var roleId = role.role_id;

    var selectedPrivilages = [];
    var rolePrivilages = [];

    fetch("/privilage/findall")
    .then(function(response){
        return response.json();
    })
    .then(function(privilages){
        for(var privilage of privilages){
            if(privilage.module_id.module_id == moduleId){
                selectedPrivilages.push(privilage);
                rolePrivilages.push(privilage.role_id.role_id);
            }
        }
        if(rolePrivilages.includes(roleId)){
            
        }else{
            newRowAdd();
        }
        if(selectedPrivilages.length != 0){
            var tbdy = document.getElementById("data-output");
            for(rows of selectedPrivilages){
                var rowLine = document.createElement("tr");
                rowLine.classList.add('newEdited');
                rowLine.setAttribute('value', rows.privilage_id);
                rowLine.innerHTML =`
                    <td id="role_id" value=${JSON.stringify(rows.role_id)}>
                        ${rows.role_id.role_name}
                    </td>
                    <td>
                        <input type="checkbox" id="cre">
                    </td>
                    <td>
                        <input type="checkbox" id="sel">
                    </td>
                    <td>
                        <input type="checkbox" id="edit">
                    </td>
                    <td>
                        <input type="checkbox" id="del">
                    </td>
                `;
                tbdy.appendChild(rowLine);
                for(var oneIn of rowLine.getElementsByTagName("input")){
                    if(rows[oneIn.id] == 1){
                        oneIn.setAttribute("checked", "checked");
                    }
                }
            }
        }else
            newRowAdd();
        
    })
}

function addPrivilage(){
    var rowObj = {};
    var attrList = ["sel", "del", "edit", "cre"];
    
    for (var rowItem of (document.getElementById('data-output')).querySelectorAll('tr')){

        console.log(rowItem.querySelectorAll('#role_id'));
        console.log(JSON.parse(document.getElementById('module_id').value));

        rowObj[role_id] = JSON.parse(rowItem.querySelector('#role_id').value).role_id;
        rowObj[module_id] = JSON.parse(document.getElementById('module_id').value).module_id;
        for(attr of attrList){
            rowObj[attr] = chkBoxVal(rowItem, attr);
        }
        if(rowItem.classList.contains('newEdited')){
            rowObj['privilage_id'] = rowItem.value;
            //console.log(rowObj);
        }
        if(rowItem.classList.contains('newAdded')){
            console.log(rowObj);
        }
    }
}

function chkBoxVal(ele, eleId){
    if(ele.querySelector(`#${eleId}`).checked){
        return 1;
    }else{
        return 0;
    }
}

function newRowAdd(){
    var tbdy = document.getElementById("data-output");
    var rowLine = document.createElement("tr");
    rowLine.classList.add('newAdded');
    rowLine.innerHTML =`
        <td id="role_id" value='${document.getElementById('role_id').value}'>
            ${JSON.parse(document.getElementById('role_id').value).role_name}
        </td>
        <td>
            <input type="checkbox" id="cre">
        </td>
        <td>
            <input type="checkbox" id="sel">
        </td>
        <td>
            <input type="checkbox" id="edit">
        </td>
        <td>
            <input type="checkbox" id="del">
        </td>
    `;
    tbdy.appendChild(rowLine);
}