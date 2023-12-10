function swapNext(){
    const form = document.querySelector("form");
    form.classList.add('secActive');
    /*const allInput = form.querySelector(".first input");
    alert("working");

    allInput.forEach(input => {
        if(input.value != ""){
            form.classList.add('secActive');
        }else{
            form.classList.remove('secActive');
        }
    })*/
}

function swapBack(){
    const form = document.querySelector("form");
    form.classList.remove('secActive');
}