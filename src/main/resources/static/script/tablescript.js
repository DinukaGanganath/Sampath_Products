var object;

function showContextMenu(str, event){
    //console.log(str);
    //console.log(event);
    object = str;
    var contextElement = document.getElementById("context-menu");
    contextElement.style.left = event.clientX + "px";
    contextElement.style.top = event.clientY + "px";
    contextElement.classList.add("active");

    // document.getElementById('context_edit').onclick = function (){
    //     console.log(str);
    //     directEditform(str);
    // };
    
    //contextElement.classList.remove("active");
      
}

function onClickItem(ele){
    directEditform()
    
    console.log(object);
    document.getElementById("context-menu").classList.remove('active');
}

//direct to the edit window.
function directEditform(object, url){
    alert(object);
    window.location.href = url;
}

// return Row Object
function getRowObject(){
    return object;
}