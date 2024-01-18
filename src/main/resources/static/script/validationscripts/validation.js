document.addEventListener('DOMContentLoaded', function() {
    const fields = document.querySelectorAll('input');
    console.log(fields);

    fields.forEach(field => {
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
