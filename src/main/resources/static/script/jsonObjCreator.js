function createJson(eleid, parentEle){
    var jsonObj = '';
    for(pEle of parentEle){
        var jsonAttr = document.getElementById(eleid).querySelectorAll(parentEle);
        for(attr of jsonAttr){
            if(attr.id != ""){
                /*switch(attr.localName){
                    case "td":
                        jsonObj += `"${attr.id}":"${attr.innerHTML}",`;
                        break;
                    case "input":
                        jsonObj += `"${attr.id}":"${attr.value}",`;
                        break;
                    case "select":
                        jsonObj += `"${attr.id}": ${JSON.parse(attr.value)} ,`;
                }*/

                jsonObj += `"${attr.id}":"",`;
                
            }
        }
    }
    
    jsonObj = jsonObj.slice(0, -1);
    console.log(jsonObj);
    var obj = JSON.parse('{'+jsonObj+'}');

    console.log(Object.keys(obj));
}