//configure the GUI and window parameters
initLayout("Salesrep", "Salesrep Vehicle Types");
sidebarLoader("/salesrep");

loadCheckboxVal("/vehicletypes/findall", "vehicleTypesCheckBox", "vehicleTypes", "vehicle_types_description", "vehicle_types_id");

setInnerForm("/lastsalesrep", [["salesrep_code","salesrep_code"], ["salesrep_name","salesrep_name"], ["salesrep_contact_no1","salesrep_contact_no1"], ["salesrep_nic","salesrep_nic"]]);

getMainFormObject("/lastsalesrep", "salesrep_id")

function submitVehicles(){
    alert('works');
    console.log(JSON.parse(document.getElementById('salesrep_id').value));
}