document.addEventListener('DOMContentLoaded', function() {
    const fields = document.querySelectorAll('input, select');
    var newObject = {};

    fields.forEach(field => {
        
        if(field.hasAttribute('required')){
            const fieldLabel =  field.previousElementSibling;
            fieldLabel.innerHTML += '<span class="reqMark"> *</span>';

        }

        field.addEventListener('keyup', function(){
            
            if(field.classList.contains('valTelephone')){
                const regexStr = new RegExp('^[0][7][01245678][0-9]{7}$');
                const messageStr = "Enter Telephone No 07xxxxxxxx format."
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
        if(field.value.trim() === ''){
            field.classList.add('invalid');
            field.nextElementSibling.innerHTML="This field is required";
        }
        else{
            field.nextElementSibling.innerHTML="";
        }
    }
}

function validForm(){
    var errorStr = "";
    errorStr += checkForRequired();
    if(errorStr.trim().length>0)
        alert(errorStr.trim());
    else
        alert(userConfirmation());
}

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

function userConfirmation(){
    var fields = document.querySelectorAll('input, select');
    var confirmString = 'Are you sure to submit following details: \n';
    fields.forEach(field => {

        if(field.previousElementSibling.innerHTML.includes("<")){
            var indexOfLessThan = field.previousElementSibling.innerHTML.indexOf('<');
            var resultString = field.previousElementSibling.innerHTML.substring(0, indexOfLessThan);
            confirmString += resultString + " : " + field.value +"\n";
        }else{
            confirmString += field.previousElementSibling.innerHTML + " : " + field.value +"\n";
        }


    })

    return confirmString;
}
