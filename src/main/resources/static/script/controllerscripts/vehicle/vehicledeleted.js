initLayout("Deleted vehicle", "Deleted Vehicles");
sidebarLoader("/vehicle");

dataLoadTable("/vehicle/findall/deleted", ["vehicle_no","vehicle_code", "vehicle_owner_name", "vehicle_owner_contact",["vehicle_types_id","vehicle_types_description"]],8);

////////////////////////// 
/// Vehicle Restore /////
//////////////////////////

function restoreVehicle(){
    var objvalue = getRowObject();
    console.log(objvalue);
    restFunction('/vehicle/restore', objvalue, "PUT", "/vehicle", "Vehicle");
    window.location.href = '/vehicle';
}