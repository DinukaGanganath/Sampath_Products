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


    jsonList.forEach(function (item) {
        var partitionDiv = document.createElement("div");

        partitionDiv.textContent = item.string; 
        partitionDiv.style.flex = "1"; 

        var link = document.createElement("a");
        link.href = item.url;
        link.textContent = item.string; 
        //partitionDiv.appendChild(link);

        childDiv.appendChild(partitionDiv);
    });

    parentDiv.appendChild(childDiv);
}

