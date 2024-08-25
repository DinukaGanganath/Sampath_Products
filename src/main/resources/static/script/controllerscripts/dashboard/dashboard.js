/* <div class="dashboardcard">
    <div id="titleDiv">Material</div>
    <div id="dataDiv"></div>
    <div id="attributeDiv" style="display:flex">
        <div>
            <div style="font-size:10px">Title 1</div>
            abc
        </div>
        <div>
            <div style="font-size:10px">Title 1</div>
            abc
        </div>
        <div>
            <div style="font-size:10px">Title 1</div>
            abc
        </div>
    </div>
</div> */

var dashcards = 
    {
        "title" : "Material",
        "data" : "5",
        "subs" : [
            {
                "title" : "Material",
                "data" : "5",
                "subs" : "red"
            },
            {
                "title" : "Material",
                "data" : "5",
                "subs" : "yellow"
            },
            {
                "title" : "Material",
                "data" : "5",
                "subs" : "green"
            }
        ]
        
    };

function createDashBoard(){
    var mainDiv = document.createElement('div');
    mainDiv.classList.add("dashboardcard");

    var titleDiv = document.createElement('div');
    titleDiv.id = 'titleDiv';
    titleDiv.innerHTML = dashcards.title;

    var dataDiv = document.createElement('div');
    dataDiv.id = 'dataDiv';
    titleDiv.innerHTML = dashcards.data;

    var attributeDiv = document.createElement('div');

}