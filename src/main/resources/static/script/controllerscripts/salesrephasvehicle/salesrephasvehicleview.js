//configure the GUI and window parameters
initLayout("Salesrep", "Salesrep Vehicle Types");
sidebarLoader("/salesrep");

loadCheckboxVal("/vehicletypes/findall", "vehicleTypesCheckBox", "vehicleTypes", "vehicle_types_description", "vehicle_types_id");

setInnerForm("/lastsalesrep", [["salesrep_code","salesrep_code"], ["salesrep_name","salesrep_name"], ["salesrep_contact_no1","salesrep_contact_no1"], ["salesrep_nic","salesrep_nic"]]);

getMainFormObject("/lastsalesrep", "salesrep_id")

function submitVehicles(){
    // var checkboxList = document.getElementsByClassName("vehicleTypes");
    // var vehicleObj = {};
    // vehicleObj["salesrep_details_salesrep_id"] = JSON.parse(document.getElementById('salesrep_id').value);
    // //console.log(JSON.parse(document.getElementById('salesrep_id').value));
    // for(var chkbox of checkboxList){
    //     if(chkbox.checked){
    //         //console.log(chkbox);
    //         vehicleObj['vehicle_types_vehicle_types_id'] = JSON.parse(chkbox.value);
    //         //console.log(vehicleObj);

    //         $.ajax("/salesrephasvehicletypes/save", {
    //             async : false,
    //             type : "POST",
    //             data : JSON.stringify(vehicleObj),
    //             contentType: 'application/json',
    //         });
    //     }
    // }
    document.location.href = "https://www.google.com";
}