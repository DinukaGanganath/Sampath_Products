function setOptions(url, ele, eleVal){
    fetch(url)
    .then(function(response){
        return response.json();
    })
    .then(function(areas){
        let options = document.querySelector(ele);
        let out = "";
        for(let area of areas){
            out += `
                <option>
                    ${area[eleVal]}
                </option>
            `;
        }
        options.innerHTML = out;
    })
}

function createButtonDiv(divDetails, btnList){
    var btnDiv = document.createElement("div");
    btnDiv.id = divDetails[0];
    btnDiv.classList.add(divDetails[1]);
    btnDiv.style.display = divDetails[2];
    
    for(var i = 0 ; i<btnList.length; i++){
        
        console.log(btnList[i]);
        var newBtn = document.createElement("button");
        newBtn.id = btnList[i][0];
        newBtn.classList.add(btnList[i][1]);
        newBtn.innerHTML = btnList[i][2];
        newBtn.onclick = btnList[i][3]; 
        btnDiv.appendChild(newBtn);
    }

    return btnDiv;
}
