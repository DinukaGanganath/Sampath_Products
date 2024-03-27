var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
console.log(receivedData);

var idVal = [["vehicle_id", `${receivedData["vehicle_id"]}`], ["vehicle_code", `${receivedData["vehicle_code"]}`]];

//configure the GUI and window parameters
initLayout("Vehicle", `Vehicle View - ${receivedData.vehicle_code}`);
sidebarLoader("/vehicle");

objectToForm('vehicleViewForm', receivedData, ["vehicle_types_description"]);
disableForm('vehicleViewForm', ['input', 'select']);

function deleteFormVehicle(){
    restFunction('/vehicle/delete', receivedData, "DELETE", "/vehicle", "Vehicle");
    window.location.href = '/vehicle';
}

function editFormVehicle(){
    sessionStorage.setItem("dataToSend", JSON.stringify(receivedData));
    window.location.href = '/vehicleedit';
}

function editObj(formId, eleList, url, method, loadAfter, navigator){
    for(ele of document.querySelectorAll("select")){
        if(ele.childNodes.length === 0){
            ele.parentNode.removeChild(ele);
        }
    }

    validForm(formId, eleList, url, method, loadAfter, navigator, idVal);
}