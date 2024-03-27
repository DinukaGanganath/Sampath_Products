var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
//console.log(receivedData);

var idVal = [["vehicle_id", `${receivedData["vehicle_id"]}`], ["vehicle_code", `${receivedData["vehicle_code"]}`]];
//console.log(idVal);

var optionIdList = [
    ["/vehicletypes/findall", "vehicle_types_id", "vehicle_types_description", "Vehicle Type"]
];

initLayout("Vehicle Edit", `Vehicle Edit - ${receivedData.vehicle_code}`);
sidebarLoader("/vehicle");

optionInput(optionIdList, receivedData);

objectToForm('vehicleEditForm', receivedData, ["vehicle_types_description"]);

for(ele of document.querySelectorAll("input")){
    ele.classList.add("valid");
}

function editObj(formId, eleList, url, method, loadAfter, navigator){
    for(ele of document.querySelectorAll("select")){
        if(ele.childNodes.length === 0){
            ele.parentNode.removeChild(ele);
        }
    }
    validForm(formId, eleList, url, method, loadAfter, navigator, idVal);
}

