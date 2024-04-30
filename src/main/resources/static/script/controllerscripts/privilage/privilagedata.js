//configure the GUI and window parameters
initLayout("Privilage", "Privilage Details");
sidebarLoader("/privilage");

//Load the names options to the Select tag
loadOptionVal("/module/findall", "module_id", "module_name", "Module");

var attrList = ["cre", "sel", "edit", "del" ];
var roleObjs = [];

function searchPrivilage(ele){
    

    loadCheckBox("/privilage/findall");
    loadTable("/role/findall");
    
    var privilageTable = document.getElementById('data-output');
    var trs = privilageTable.querySelectorAll('tr');
     
}

function loadTable(url){
    var privilageTable = document.getElementById('data-output');
    privilageTable.innerHTML = "";

    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(roleObjs){
        for(var roleObj of roleObjs){
            var tabRow = document.createElement('tr');
            tabRow.id = roleObj.role_id;
            var tabData = document.createElement('td');
            tabData.innerHTML = roleObj.role_name;
            tabRow.appendChild(tabData);
            
            for(var attr of attrList){
                var checkTd = document.createElement('td');
                var chk = document.createElement('input');
                chk.type = 'checkbox';
                chk.classList.add(attr);
                checkTd.appendChild(chk);
                tabRow.appendChild(checkTd);
            }

            var hidden = document.createElement('td');
            hidden.innerHTML = JSON.stringify(roleObj);
            hidden.style.display = 'none';
            hidden.id = "hidden";
            tabRow.appendChild(hidden);

            privilageTable.appendChild(tabRow);
        }
    });
}

function loadCheckBox(url){
    var module = JSON.parse(document.getElementById('module_id').value);
    var moduleId = module.module_id;
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(privilages){
        for(var priv of privilages){
            if(moduleId == priv.module_id.module_id){
                var privRole = document.getElementById(priv.role_id.role_id);
                if(moduleId == priv.module_id.module_id && privRole.id == priv.role_id.role_id){
                    var privId = document.createElement('td');
                    privId.innerHTML = priv.privilage_id;
                    privId.style.display='none';
                    privId.id = "privId";
                    privRole.appendChild(privId);
                }
                for(var attr of attrList){
                    if(priv[attr] == 1){
                        var chkAttr = privRole.querySelector("."+attr);
                        chkAttr.setAttribute('checked', 'checked');
                    }       
                }
            }
        }
    });
}

function savePrivilage(){
    var module_id = JSON.parse(document.getElementById('module_id').value);
    var tbdy = document.getElementById('data-output');
    var trs = tbdy.getElementsByTagName('tr');
    for(var row of trs){
        var objRow = {};
        objRow.module_id = module_id;
        var str = row.querySelector("#hidden").innerHTML;
        var obj = JSON.parse(str)
        objRow.role_id = obj;
        objRow.privilage_id = parseInt(row.querySelector("#privId").innerHTML);

        for(var attr of attrList){
            if(row.querySelector('.'+attr).checked){
                objRow[attr] = 1;
            }
            else{
                objRow[attr] = 0;
            }
        }

        console.log(objRow);

        $.ajax("/privilage/edit", {
            async : false,
            type : "PUT",
            data : JSON.stringify(objRow),
            contentType: 'application/json',
        });
        
    }
    
}