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

function mobMenuToggle(){
    const menu = document.querySelector('.sidebar');

    if(menu.classList.contains('mobmenuhide')){
        menu.classList.remove('mobmenuhide');
        menu.classList.add('mobmenushow');
    }else{
        menu.classList.remove('mobmenushow');
        menu.classList.add('mobmenuhide');
    }
}

