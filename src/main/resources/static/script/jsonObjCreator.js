function createJson(eleid, parentEle){
    var jsonObj = {};
    var formProperties = [];

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
                        oneProperty.push(attr.id, JSON.parse(attr.value));
                }
                formProperties.push(oneProperty);
            }
        }
    }

    for(prop of formProperties){
        jsonObj[prop[0]] = prop[1];
    }

    console.log(jsonObj);
    return jsonObj;
    
}