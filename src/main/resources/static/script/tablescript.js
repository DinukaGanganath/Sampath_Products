function showContextMenu(str, event){
    //console.log(str);
    //console.log(event);
    var contextElement = document.getElementById("context-menu");
    contextElement.style.left = event.clientX + "px";
    contextElement.style.top = event.clientY + "px";
    contextElement.classList.add("active");

    document.getElementById('context_edit').onclick = function (){
        console.log(str);
        directEditform(str);
    };
    

    function handleEditClick() {
        
    }
      
}

function selectOneItem(){
    window.addEventListener("click", function(){
        document.getElementById("context-menu").classList.remove("active");
    });
}