//configure the GUI and window parameters
initLayout("Privilage", "Privilage Details");
sidebarLoader("/privilage");

//Load the names options to the Select tag
loadOptionVal("/module/findall", "module_id", "module_name", "Module");

var attrList = ["cre", "sel", "upd", "del" ];
var roleObjs = [];

function searchPrivilage(ele){
    

    loadCheckBox("/module/findall");
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
            tabData.innerHTML = roleObj.role_name.replaceAll('_', ' ');
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
    var tBody = document.getElementById('data-output');
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(modules){
        for(var mod of modules){
            // if(moduleId == priv.module_id.module_id){
            //     var privRole = document.getElementById(priv.role_id.role_id);
            //     if(moduleId == priv.module_id.module_id && privRole.id == priv.role_id.role_id){
            //         var privId = document.createElement('td');
            //         privId.innerHTML = priv.privilage_id;
            //         privId.style.display='none';
            //         privId.id = "privId";
            //         privRole.appendChild(privId);
            //     }
            //     for(var attr of attrList){
            //         if(priv[attr] == 1){
            //             var chkAttr = privRole.querySelector("."+attr);
            //             chkAttr.setAttribute('checked', 'checked');
            //         }       
            //     }
            // }

            if(mod.module_id == moduleId){
                for(var trEle  of tBody.getElementsByTagName('tr')){
                    for(var privEle of mod.privilages){
                        if(trEle.id == privEle.role_id.role_id){
                            trEle.setAttribute('value', privEle['module_role']);
                            for(var cls of attrList){
                                console.log(privEle[cls]);
                                if(privEle[cls]==1){
                                    trEle.getElementsByClassName(cls)[0].setAttribute("checked","checked");
                                }else{
                                    trEle.getElementsByClassName(cls)[0].removeAttribute("checked");
                                }
                            }
                        }
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
        console.log(row);
        var objRow = {};
        objRow.module_id = module_id;
        objRow.role_id = JSON.parse(row.querySelector("#hidden").innerHTML);
        // var str = row.querySelector("#hidden").innerHTML;
        // var obj = JSON.parse(str)
        // objRow.role_id = obj;
        objRow.module_role = parseInt(row.getAttribute('value'));

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

function roleAddForm(){
    console.log(window.location.href);
    document.getElementById('modal').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
}

function moduleAddForm(){
    console.log(window.location.href);
    document.getElementById('modal_notification').style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
}

function createRole(){
    var loadAfter = "/"+ window.location.href.split("/").slice(-1);
    var obj ={};
    obj.role_name = document.getElementById('type_name').value.replaceAll(" ", "_");

    $.ajax("/role/save", {
        async : false,
        type : "POST",
        data : JSON.stringify(obj),
        contentType: 'application/json',

        success : function (data, status, xhr){
            console.log("success " + status + " " + xhr);
            responseStatus = data;
            console.log(responseStatus);
        },

        error : function (xhr, status, errormsg){
            console.log("fail " + errormsg + " " + status +" " + xhr);
            console.log(xhr);
            responseStatus = errormsg;
        },
    });

    //window.location.href = loadAfter;

}

function createModule(){
    var loadAfter = "/"+ window.location.href.split("/").slice(-1);
    
    var modobj ={};
    modobj.privilages = new Array();
    
    modobj.module_name = document.getElementById('module_name').value.replaceAll(" ", "_");
    fetch("/role/findall")
    .then(function(response){
        return response.json();
    })
    .then(function(roles){
        for(var i of roles){
            var privobj = {};
            privobj.role_id = i;
            privobj.sel = 0;
            privobj.del = 0;
            privobj.edit = 0;
            privobj.cre =0;
            modobj.privilages.push(privobj);
        }
    });

    console.log(modobj);

    $.ajax("/module/save", {
        async : false,
        type : "POST",
        data : JSON.stringify(modobj),
        contentType: 'application/json',

        success : function (data, status, xhr){
            console.log("success " + status + " " + xhr);
            responseStatus = data;
            console.log(responseStatus);
        },

        error : function (xhr, status, errormsg){
            console.log("fail " + errormsg + " " + status +" " + xhr);
            console.log(xhr);
            responseStatus = errormsg;
        },
    });

    //window.location.href = loadAfter;

}