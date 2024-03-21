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
        });
        
    });

});	

function checkValidate(field, regexStr, messageStr){
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

function getUserConfirmation(formObj,url, formObj, method, loadAfter, navigator ){
    let userConfirm;
    if(method=="POST" || method=="PUT")
        userConfirm = window.confirm(userConfirmation(formObj));
    if(method=="DELETE")
        userConfirm = window.confirm(`Are you sure to delete ${navigator}`);
    if(userConfirm)
        restFunction(url, formObj, method, loadAfter, navigator);
        //restFunction('/supplier/save', supplier, "POST", "/supplier", "Supplier");
}

//checking the required values
function checkForRequired(){
    var fields = document.querySelectorAll('input, select');
    var errorMsg ='Enter values for Required values : \n';
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


