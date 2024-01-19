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
