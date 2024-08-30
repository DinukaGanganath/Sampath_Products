// handle the data sets with different URL used
function restFunction(url, object, method, loadAfter, objectType){
    
    let responseStatus;
    let activity;

    switch(method){
        case "POST":
            activity = "Saved";
            break;
        case "PUT":
            activity = "Updated";
            break;
        case "DELETE":
            activity = "Deleted";
            break;   
    }

    $.ajax(url, {
        async : false,
        type : method,
        data : JSON.stringify(object),
        contentType: 'application/json',

        success : function (data, status, xhr){
            console.log("success " + status + " " + xhr);
            responseStatus = data;
            console.log(responseStatus);
        },

        error : function (xhr, status, errormsg){
            console.log("fail " + errormsg + " " + status +" " + xhr);
            responseStatus = errormsg;
        },
    });

    if (responseStatus=='Ok'){
        alert(objectType + ' ' + activity + ' Succesfully...');
        window.location.href = loadAfter;
    }else{
        console.log(responseStatus);
        alert(responseStatus);
    }
}

// get the array of records for given url
function getRecords(url, attr){

    var array=[];
    fetch(url)
    .then(function(response){
        return response.json();
    }).then(function(objs){
        for(var obj of objs){
            array.push(obj[attr]);
        }
    });
    console.log(array);
    console.log(array.length);
    return array;

    
}