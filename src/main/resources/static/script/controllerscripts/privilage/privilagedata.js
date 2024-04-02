//configure the GUI and window parameters
initLayout("Privilage", "Privilage Details");
sidebarLoader("/supplier");

//Load the names options to the Select tag
loadOptionVal("/module/findall", "module_id", "module_name", "Module");
loadOptionVal("/role/findall", "role_id", "role_name", "Role");

var attrList = ["cre", "sel", "edit", "del" ];

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
                rowLine.classList.add('tbodyTr');
                rowLine.setAttribute('value', rows.privilage_id);

                var roleIdTd = document.createElement('td');
                roleIdTd.setAttribute("data-json", JSON.stringify(rows.role_id));
                roleIdTd.id = "role_id";
                roleIdTd.innerHTML = rows.role_id.role_name;
                rowLine.appendChild(roleIdTd);

                for(eles of attrList){
                    var checkBoxTd = document.createElement('td');
                    var checkBoxEles = document.createElement('input');
                    checkBoxEles.type = "checkbox";
                    checkBoxEles.id = eles;
                    checkBoxTd.appendChild(checkBoxEles);
                    rowLine.appendChild(checkBoxTd);
                }
                
                tbdy.appendChild(rowLine);
                rowLine.firstChild.nodeValue = JSON.stringify(rows.role_id);
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

function addPrivilage(){
    var rowObj = {};
    
    var tbdy = document.getElementById('data-output');
    var trs = document.getElementsByClassName('tbodyTr');
    
    
    for (var rowItem of trs){
        var tdEle = rowItem.querySelector('#role_id');
        var x = tdEle.attributes
        console.log(x);
        //rowObj[role_id] = JSON.parse(rowItem.querySelector('#role_id').value);
        rowObj[module_id] = JSON.parse(document.getElementById('module_id').value);
        
        for(attr of attrList){
            rowObj[attr] = chkBoxVal(rowItem, attr);
        }
        if(rowItem.classList.contains('newEdited')){
            rowObj['privilage_id'] = rowItem.value;
        }
        if(rowItem.classList.contains('newAdded')){
            //console.log(rowObj);
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

