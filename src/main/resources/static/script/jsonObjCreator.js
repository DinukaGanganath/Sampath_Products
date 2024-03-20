function createJson(eleid, parentEle, idVal){
    
    var jsonObj = {};
    var formProperties = [];
    if(idVal != null){
        jsonObj[idVal[0]] = idVal[1];
    }
    for(pEle of parentEle){
        var jsonAttr = document.getElementById(eleid).querySelectorAll(parentEle);
        for(var attr of jsonAttr){
            if(attr.id != ""){

                var oneProperty=[];
                switch(attr.localName){
                    case "td":
                        oneProperty.push(attr.id, attr.innerHTML);
                        break;
                    case "input":
                        oneProperty.push(attr.id, attr.value);
                        break;
                    case "select":
                        if(attr.value == '')
                            oneProperty.push(attr.id, attr.value);
                        else
                            oneProperty.push(attr.id, JSON.parse(attr.value));
                }
                formProperties.push(oneProperty);
            }
        }
    }

    for(prop of formProperties){
        jsonObj[prop[0]] = prop[1];
    }

    return jsonObj;
    
}