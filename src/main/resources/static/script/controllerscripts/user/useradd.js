initLayout("User", "User Add");
sidebarLoader("/privilage");

//Load the names options to the Select tag
loadOptionVal("/employee/findall/nouser", "employee_id", "employee_code", "Employee");

loadCheckboxVal("/role/findall", "roleCheckBox", "userRole", "role_name", "role_id");

document.getElementById("user_photopath").addEventListener('change', function(event) {
    const imgDisplay = document.getElementById('userImg');
    const file = event.target.files[0];
    userImg.value = file.name;
    imgDisplay.src = `/images/users/${file.name}`;
});

document.getElementById("employee_id").addEventListener('change', function() {
    var obj = JSON.parse(document.getElementById("employee_id").value);
    document.getElementById("user_email").value = obj.employee_email;
});

//function for on change the status checkbox
function changeText(){
    var ele = document.getElementById("user_status");
    var txt = document.getElementById("checkText");
    
    if(ele.checked)
        txt.textContent = "This user is active";
    else
        txt.textContent = "Not Active User"
}

function createUserObject(){
    var user = {};
    var roles = [];

    for(var roleObj of document.getElementsByClassName('userRole')){
        if(roleObj.checked)
            roles.push(JSON.parse(roleObj.getAttribute('value')));
    }
    
    user.roles = roles;
    
    var inputFields = document.querySelectorAll('input');

    var selectField = document.querySelector('select');
    user[selectField.id] = JSON.parse(selectField.value);

    for(var inputField of inputFields){
        if(!inputField.classList.contains('avoid')){
            switch (inputField.type) {
                case 'text':
                    user[inputField.id] = inputField.value;
                    break;
                case 'password':
                    user[inputField.id] = inputField.value;
                    break;
                case 'checkbox':
                    if(!inputField.classList.contains('userRole')){
                        if(inputField.checked)
                            user[inputField.id] = 1;
                        else
                            user[inputField.id] = 0;
                    }
                    break;
                case 'file':
                    user[inputField.id] = inputField.value;
                    break;
                default:
                    break;
            }
        }
    }

    console.log(user);
    restFunction("/user/save", user, "POST", "/employee", "User")
}

function loadImage(ele){
    var userImage = document.getElementById('userImg');

}