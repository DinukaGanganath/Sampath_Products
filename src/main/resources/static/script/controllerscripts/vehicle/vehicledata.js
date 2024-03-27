//configure the GUI and window parameters
initLayout("Vehicle", "Vehicle Details");
sidebarLoader("/vehicle");

//Create the table initial values
dataLoadTable("/vehicle/findall/exist", ["vehicle_no","vehicle_code", "vehicle_owner_name", "vehicle_owner_contact",["vehicle_types_id","vehicle_types_description"]],7);

////////////////////////// 
///// Vehicle View //////
//////////////////////////

function viewVehicle(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/vehicleview';
}

////////////////////////// 
///// Vehicle Edit //////
//////////////////////////

function editVehicle(){
    var objvalue = getRowObject();
    sessionStorage.setItem("dataToSend", JSON.stringify(objvalue));
    window.location.href = '/vehicleedit';
}

////////////////////////// 
//// Vehicle Delete /////
//////////////////////////

function deleteVehicle(){
    var objvalue = getRowObject();
    let userConfirm = window.confirm(`Are you sure to delete ${objvalue.vehicle_code} - ${objvalue.vehicle_no}`);
    if(userConfirm){
        restFunction('/vehicle/delete', objvalue, "DELETE", "/vehicle", "Vehicle");
    }
    window.location.href = '/vehicle';
}