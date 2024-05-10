function createThinLongDiv(jsonList) {
    
    var parentDiv = document.getElementById("parentDiv");

    var childDiv = document.createElement("div");

    childDiv.style.width = "80%";
    childDiv.style.margin = "auto"; 
    //childDiv.style.border = "1px solid black"; 
    //childDiv.style.height = "50px"; 
    childDiv.style.display = "flex";  
    childDiv.style.justifyContent = "space-around"; 
    childDiv.style.alignItems = "center"; 
    //childDiv.style.backgroundColor = "white"; 


    for (var obj of jsonList) {

        var partitionDiv = document.createElement("div");
        partitionDiv.className = "partitionDiv";
        var link = document.createElement("a");
        link.text = obj.str;
        link.href = obj.url;
        partitionDiv.appendChild(link);
 
        partitionDiv.style.flex = "1";  

        childDiv.appendChild(partitionDiv);
    }

    parentDiv.appendChild(childDiv);
}

