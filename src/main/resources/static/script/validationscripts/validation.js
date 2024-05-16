// Validation is going to be done when the form is loadedx.
document.addEventListener('DOMContentLoaded', function() {
    const fields = document.querySelectorAll('input, select'); // Get 
    var newObject = {};

    fields.forEach(field => {
        
        if(field.hasAttribute('required')){
            const fieldLabel =  field.previousElementSibling;
            fieldLabel.innerHTML += '<span class="reqMark"> *</span>';

        }

        field.addEventListener('keyup', function(){
            // class names selects the method and regex expressions to use evaluate.
            if(field.classList.contains('valTelephone')){
                const regexStr = new RegExp('^[0][1234568][1-8][0-9]{7}$');
                const messageStr = "Enter Telephone No 01xxxxxxxx format."
                checkValidate(field, regexStr, messageStr);
            }
            if(field.classList.contains('valMobphone')){
                const regexStr = new RegExp('^[0][7][01245678][0-9]{7}$');
                const messageStr = "Enter Mobile No 07xxxxxxxx format."
                checkValidate(field, regexStr, messageStr);
            }
            if(field.classList.contains('valIdentity')){
                const regexStr = new RegExp('^([0-9]{12})|([0-9]{9}[xXvV])$');
                const messageStr = "NIC should contain 12 digits or 9 digits ends with vVxV."
                checkValidate(field, regexStr, messageStr);
            }
            if(field.classList.contains('valFullName')){
                const regexStr = new RegExp('^([A-Z][a-z]*\. ?\s?)+[A-Z][a-z]*$');
                const messageStr = "Enter name with capitalized each words and keep space between two names."
                checkValidate(field, regexStr, messageStr);
            }
            if(field.classList.contains('valEmail')){
                const regexStr = new RegExp('^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$');
                const messageStr = "Enter a valid email address. ex: someone@outlook.com."
                checkValidate(field, regexStr, messageStr);
            }
            if(field.classList.contains('valReg')){
                const regexStr = new RegExp('^[A-Za-z]{1,2}[0-9]{5,6}$');
                const messageStr = "Enter a business registration no with correct format."
                checkValidate(field, regexStr, messageStr);
            }
            if(field.classList.contains('valVehicleNo')){
                const regexStr = new RegExp('^(?:CP|EP|NC|NE|NW|SB|SP|UP|WP)[" "][A-Z]{2,3}[-][0-9]{4}$');
                const messageStr = "Enter Letters Capital and Valid Province Identifier."
                checkValidate(field, regexStr, messageStr);
            }
            if(field.classList.contains('valEngine')){
                const regexStr = new RegExp('^[A-Z0-9]{6,12}$');
                const messageStr = "Check the format again."
                checkValidate(field, regexStr, messageStr);
            }
            if(field.classList.contains('valChasis')){
                const regexStr = new RegExp('^[A-HJ-NPR-Z0-9]{17}$');
                const messageStr = "Check the format again."
                checkValidate(field, regexStr, messageStr);
            }
            if(field.classList.contains('valPassword')){
                const regexStr = new RegExp('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$'); // at least one simple letter (?=.*[a-z]) 
                const messageStr = "Your password must be at least 8 characters long, and include at least one uppercase letter, one lowercase letter, one digit, and one special character (e.g., @$!%*?&).";
                checkValidate(field, regexStr, messageStr);
            }
        });
        
    });

});	

// check weather validation happening with given regex expression
function checkValidate(field, regexStr, messageStr){
    console.log(regexStr.test(field.value));
    if (regexStr.test(field.value)) {
        field.classList.remove('invalid');
        field.classList.add('valid');
        field.nextElementSibling.style.display="none";
    } else {
        field.classList.remove('valid');
        field.classList.add('invalid');
        field.nextElementSibling.innerHTML=messageStr;
        field.nextElementSibling.style.display="block";
    }
}

// check weather the given field is required or not
function checkRquired(field){
    if(field.hasAttribute("required")){
        if(field.value.trim() == ''){
            field.classList.add('invalid');
            field.nextElementSibling.innerHTML="This field is required";
        }
        else{
            field.nextElementSibling.innerHTML="";
        }
    }
}

//validate the form & making the alert msg
//validForm("employeeAddForm",["input", "select"], "/employee/save", "POST", "/employee", "Employee")
function validForm(formId, eleList, url, method, loadAfter, navigator, idVal){
    var formObj = createJson(formId, eleList, idVal);
    var errorStr = "";
    errorStr += checkForRequired();
    errorStr += checkForErrors()
    console.log(errorStr);
    if(errorStr.trim().length>0)
        alert(errorStr.trim());
    else
        getUserConfirmation(formObj,url, formObj, method, loadAfter, navigator);
        //finishConfirmation();
}

// dialog box opened and ge confirmations to do 
function getUserConfirmation(formObj,url, formObj, method, loadAfter, navigator ){
    let userConfirm;
    if(method=="POST" || method=="PUT")
        userConfirm = window.confirm(userConfirmation(formObj));
    if(method=="DELETE")
        userConfirm = window.confirm(`Are you sure to delete ${navigator}`);
    if(userConfirm)
        //console.log(formObj);
        restFunction(url, formObj, method, loadAfter, navigator);
        //restFunction('/supplier/save', supplier, "POST", "/supplier", "Supplier");
}

//checking the required values
function checkForRequired(){
    var fields = document.querySelectorAll('input, select');
    var errorMsg ='Enter values for Required values : \n'; // final error msg
    var initLen = errorMsg.length;
    fields.forEach(field => {
        if(field.hasAttribute('required') && field.value.trim() === ''){

            var indexOfLessThan = field.previousElementSibling.innerHTML.indexOf('<');
            var resultString = field.previousElementSibling.innerHTML.substring(0, indexOfLessThan);
            errorMsg += "Enter value for " + resultString + "\n"

        }
    })
    if(errorMsg.length>initLen){
        return errorMsg;
    }else{
        return '';
    }
}

//checking for errors
function checkForErrors(){
    var fields = document.querySelectorAll('input, select');
    var errorMsg ='\n\nEnter values in correct form for following details : \n';
    var initLen = errorMsg.length;
    fields.forEach(field => {
        if(field.classList.contains('invalid')){

            var indexOfLessThan; 
            var resultString = field.previousElementSibling.innerHTML;
            if(resultString.includes("<")){
                indexOfLessThan = resultString.indexOf('<');
                resultString = resultString.substring(0, indexOfLessThan);
            }
            errorMsg += resultString + "\n"

        }
    })
    if(errorMsg.length>initLen){
        return errorMsg;
    }else{
        return '';
    }
}

//getting user confirmation
function userConfirmation(obj){
    var rule;
    var valueJson;
    var confirmString = 'Are you sure to submit following details: \n';
    console.log(Object.keys(obj));
    for(key of Object.keys(obj)){
        if(obj[key] != ""){
            valueJson = obj[key];
            
            if(typeof(obj[key]) == "object"){
                for(nameKey in obj[key]){
                    if(nameKey.includes('name')){
                        valueJson = obj[key][nameKey];
                    }
                }
            }
            
            //console.log(document.querySelector(`#${key}`));
            var keyString = document.querySelector(`#${key}`).previousElementSibling.innerHTML;
            if(!document.querySelector(`#${key}`).classList.contains("avoid")){
                if(keyString.includes("<")){
                    indexOfLessThan = keyString.indexOf('<');
                    keyString = keyString.substring(0, indexOfLessThan);
                }
                rule = (keyString + ' : ' + valueJson +'\n');
                confirmString += rule;
            }
        }
    }
    return confirmString;
}

//check the two values are equals
function checkEquality(firstEle, secondEle, messageStr){
    var firstStr = document.getElementById(firstEle).value;
    var secondStr = document.getElementById(secondEle).value;
    var field = document.getElementById(secondEle);

    if(firstStr == secondStr){
        field.classList.remove('invalid'); 
        field.classList.add('valid');
        field.nextElementSibling.style.display="none"; // msg string hide
    } else {
        field.classList.remove('valid');
        field.classList.add('invalid');
        field.nextElementSibling.innerHTML=messageStr;
        field.nextElementSibling.style.display="block"; // msg string show
    }
}

