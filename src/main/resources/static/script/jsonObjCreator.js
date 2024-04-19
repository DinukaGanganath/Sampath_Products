//form filled values are turned to JSON object
function createJson(eleid, parentEle, idVal){
    
    var jsonObj = {};
    var formProperties = [];

    if(idVal !== undefined){
        for(val of idVal)
            formProperties.push(val);
    }
    
    for(pEle of parentEle){
        var jsonAttr = document.getElementById(eleid).querySelectorAll(pEle);
        for(var attr of jsonAttr){
            console.log(typeof(attr.value));
            if(attr.id != "" && !attr.classList.contains("avoid")){
                var oneProperty=[];
                switch(attr.localName){
                    case "td":
                        oneProperty.push(attr.id, attr.innerHTML);
                        break;
                    case "input": 
                        if(attr.type == 'date'){
                            oneProperty.push(attr.id, attr.value+'T00:00');
                        }else if(attr.classList.contains("optVal")){
                            var attrVal = attr.getAttribute('value');
                            oneProperty.push(attr.id, JSON.parse(attrVal));
                        }else
                            oneProperty.push(attr.id, attr.value);
                        break;
                    case "select":
                        if(attr.value == '')
                            oneProperty.push(attr.id, attr.value);
                        else if(attr.classList.contains("genderSel")){
                            oneProperty.push(attr.id, attr.value);
                        }else{
                            console.log(attr);
                            console.log(attr.id);
                            console.log(attr.value);
                            console.log(JSON.parse(attr.value));
                            oneProperty.push(attr.id, JSON.parse(attr.value));
                        }
                }
                formProperties.push(oneProperty);
            }
        }
    }

    console.log(formProperties);
    for(prop of formProperties){
        jsonObj[prop[0]] = prop[1];
    }
    return jsonObj;
    
}