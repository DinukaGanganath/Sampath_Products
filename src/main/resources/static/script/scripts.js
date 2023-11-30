// Get the image element
var imgElement = document.querySelector('.user-img');

// Default image path
var defaultImagePath = 'images/customers.png';

// Function to load default image
function loadDefaultImage() {
    imgElement.src = defaultImagePath;
}

// Check if the image path exists or load default image
imgElement.onerror = loadDefaultImage;

// Check if the default image path is invalid or fails to load
var defaultImage = new Image();
defaultImage.onerror = loadDefaultImage;
defaultImage.src = defaultImagePath;




// Menu toggle with chevro button
function menuToggle(element){
    const menu = document.querySelector('.sidebar');

    if(menu.classList.contains('show')){
        menu.classList.remove('show');
        menu.classList.add('hide');
        element.classList.remove('fa-circle-chevron-left');
        element.classList.add('fa-circle-chevron-right');
    }else{
        menu.classList.remove('hide');
        menu.classList.add('show');
        element.classList.add('fa-circle-chevron-left');
        element.classList.remove('fa-circle-chevron-right');
    }
}

// Mobile view of side bar toggle
function mobileToggle(element){
    const mobile_menu = document.querySelector('.sidebar');
    console.log(mobile_menu.classList);

    if(mobile_menu.classList.contains('mob_show')){
        mobile_menu.classList.remove('mob_show');
    }else{
        mobile_menu.classList.add('mob_show');
    }
}