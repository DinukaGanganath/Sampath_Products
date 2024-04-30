//configure the GUI and window parameters
initLayout("Salesrep", "Salesrep Details");
sidebarLoader("/salesrep");

//Load the names options to the Select tag
loadOptionVal("/areas/findall", "salesrep_address_area_id", "area_name", "Area");

//load identity card details
function loadIdDetails(ele){
    var id = ele.value;
    var year;
    var dateMonth;

    if(id.length == 10){
        year = parseInt('19' + id.substring(0, 2));
        dateMonth = parseInt(id.substring(2,5));
          
    }else{
        year = parseInt(id.substring(0,4));
        dateMonth = parseInt(id.substring(4,7));

    }
    var gender = findGender(dateMonth)["gender"];
    var date = findMonthAndDate(findGender(dateMonth)["value"])["date"];
    var month = findMonthAndDate(findGender(dateMonth)["value"])["month"];

    setGender(gender);

    document.getElementById("salesrep_birthdate").value = year + "-" + month + "-" + date;
    setAge();

}

function findGender(value){
    if(value>500){
        return {
            "gender" : "female",
            "value" : `${value - 500}` 
        }
    }else{
        return {
            "gender" : "male",
            "value" : `${value}` 
        }
    }
}

function findMonthAndDate(dayOfYear) {
    
    dayOfYear = parseInt(dayOfYear);

    // Array to store the number of days in each month
    const daysInMonth = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    // Initialize month and date
    let month = 0;
    let date = dayOfYear;

    // Find the month
    for (let i = 0; i < daysInMonth.length; i++) {
        if (date <= daysInMonth[i]) {
            month = i + 1;
            break;
        } else {
            date -= daysInMonth[i];
        }
    }

    if(month<10)
        month = '0' + month;
    if(date<10)
        date = '0' + date;

    // Return the result
    return {
        "month" : month,
        "date" : date
    };
}

function setGender(val){
    var selectElement = document.getElementById("salesrep_gender");
    for(var inputEle of selectElement.querySelectorAll('option')){
        if(inputEle.value == val){
            inputEle.setAttribute('selected', 'selected');
        }
    }
}

function setAge(){
    var birthdayInput = document.getElementById("salesrep_birthdate").value;
    var today = new Date();
    var birthDate = new Date(birthdayInput);
    var age = today.getFullYear() - birthDate.getFullYear();
    console.log(birthdayInput);
    document.getElementById('salesrep_age').value = age;
}

