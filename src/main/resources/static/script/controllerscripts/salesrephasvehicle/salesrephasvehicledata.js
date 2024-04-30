//configure the GUI and window parameters
initLayout("Salesrep", "Salesrep Vehicle Types");
sidebarLoader("/salesrep");

loadCheckboxVal("/vehicletypes/findall", "vehicleTypesCheckBox", "vehicleTypes", "vehicle_types_description", "vehicle_types_id");

setInnerForm("/lastsalesrep", [["salesrep_code","salesrep_code"], ["salesrep_name","salesrep_name"], ["salesrep_contact_no1","salesrep_contact_no1"], ["salesrep_nic","salesrep_nic"]]);

getMainFormObject("/lastsalesrep", "salesrep_id")

function submitVehicles(){
    // var vehicleObjs = [];
    // var checkboxList = document.getElementsByClassName("vehicleTypes");
    // var vehicleObj = {};
    // vehicleObj["salesrep_details_salesrep_id"] = JSON.parse(document.getElementById('salesrep_id').value);
    // //console.log(JSON.parse(document.getElementById('salesrep_id').value));
    // for(var i =0; i<checkboxList.length; i++){
    //     if(chkboxList[i].checked){
    //         //console.log(chkbox);
    //         vehicleObj['vehicle_types_vehicle_types_id'] = JSON.parse(chkbox.value);
    //         vehicleObj.push(vehicleObj);
    //     }
    //     alert('hi');
    //     console.log(vehicleObjs);
    // }

    // if (vehicleObjs.length > 0) {
    //     $.ajax({
    //         url: "/salesrephasvehicletypes/saveall",
    //         type: 'POST',
    //         data: JSON.stringify(vehicleObjs),
    //         contentType: 'application/json',
    //         success: function() {
    //             console.log("Vehicles submitted successfully.");
    //             window.location.href = "/salesrep";
    //         },
    //         error: function(xhr, status, error) {
    //             console.error("Error occurred while submitting vehicles:", error);
    //         }
    //     });
    // } else {
    //     alert("No vehicles to submit.");
    // } 
    document.location.href = "https://www.google.com";
}