var receivedData = JSON.parse(sessionStorage.getItem("dataToSend"));
//console.log(receivedData);

var idVal = [["product_id", `${receivedData["product_id"]}`], ["product_code", `${receivedData["product_code"]}`]];
//console.log(idVal);

var optionIdList = [
    ["/areas/findall", "product_area_id", "area_name", "Area"],
    ["/materials/findall", "product_material_id", "material_name", "Material"],
    ["/types/findall", "product_business_type", "type_name", "Business Type"],
];

initLayout("Product Edit", `Product Edit - ${receivedData.product_code}`);
sidebarLoader("/product");

optionInput(optionIdList, receivedData);

objectToForm('productEditForm', receivedData, ["type_name","area_name","material_name"]);

for(ele of document.querySelectorAll("input")){
    ele.classList.add("valid");
}

function editObj(formId, eleList, url, method, loadAfter, navigator){
    for(ele of document.querySelectorAll("select")){
        if(ele.childNodes.length === 0){
            ele.parentNode.removeChild(ele);
        }
    }
    validForm(formId, eleList, url, method, loadAfter, navigator, idVal);
}

function loadDivisionVal(ele){

    var city = document.getElementById("product_address_city");
    var code = document.getElementById("product_address_postal");

    console.log(JSON.parse(ele.value));
    city.value = JSON.parse(ele.value).postal_division_id.postal_division_name;
    code.value = JSON.parse(ele.value).postal_division_id.postal_division_code;
    
    city.setAttribute("disabled","disabled");
}

function productNeed(productList){
    for(var i of productList){
        i[0].product_need = i[0].product_need + i[1];
        var prodObj = i[0];
        console.log(prodObj);
        $.ajax("/product/edit", {
            async : false,
            type : "PUT",
            data : JSON.stringify(prodObj),
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
        /*
        for(var j of i[0].productHasMaterialList){
            var matObj = j.material_id;
            matObj.material_want = matObj.material_want + (j.quantity_needed * i[1]);
            console.log(matObj);

            $.ajax("/material/edit", {
                async : false,
                type : "PUT",
                data : JSON.stringify(matObj),
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

        }
            */
    }
}
