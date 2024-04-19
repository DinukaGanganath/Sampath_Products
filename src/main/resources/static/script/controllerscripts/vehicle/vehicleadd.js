//configure the GUI and window parameters
initLayout("Vehicle", "Vehicle Add");
sidebarLoader("/vehicle");

//Load the names options to the Select tag
loadOptionVal("/vehicletypes/findall", "vehicle_types_id", "vehicle_types_description", "Vehicle Type");

function test(ele){
    console.log(ele.value);
    console.log(ele.value + 'T00:00');
}
