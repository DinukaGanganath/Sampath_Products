initLayout("Dashboard", "Dashboard");
sidebarLoader("/home");

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

var dashBoard =[
	{
		"title":"Supply Chain Management",
		"dashboardCards" : [
			{"title":"Suppliers",
			"attributes" : [
				{
					"name" : "Bread",
				},
				{
					"name" : "Covers",
				},
				{
					"name" : "Stickers",
				}
			],
			},
			{"title":"Materials",
				"attributes" : [
					{
						"name" : "Bread",
					},
					{
						"name" : "Covers",
					},
					{
						"name" : "Stickers",
					}
				],},
			{"title":"Quotations",
				"attributes" : [
					{
						"name" : "Valid",
					},
					{
						"name" : "Ending",
					},
					{
						"name" : "Invalid",
					}
				],},
			{"title":"Stock",
				"attributes" : [
					{
						"name" : "Bread",
					},
					{
						"name" : "Covers",
					},
					{
						"name" : "Stickers",
					}
				],}
		],
	},
	{
		"title":"Production Management",
		"dashboardCards" : [
			{"title":"Products",
				"attributes" : [
					{
						"name" : "Enough",
					},
					{
						"name" : "Covers",
					},
					{
						"name" : "Stickers",
					}
				],
			},
			{"title":"Batches"},
			{"title":"Available"},
			{"title":"Lack"}
		],
	},
	{
		"title":"Customer and Order Management",
		"dashboardCards" : [
			{"title":"Customers"},
			{"title":"Created"},
			{"title":"Processed"},
			{"title":"Shipped"}
		],
	}
    
];

createDashboard();

function createDashboard(){

	var container = document.getElementById('dashboard-container');

    //dashboard partition
	for(var partition of dashBoard){
		var dashboard_partition = document.createElement('div');
		dashboard_partition.id = "dashboard_partition";
		var partTitleDiv = document.createElement('div');
		partTitleDiv.classList.add('dashboard-title');
		partTitleDiv.style.fontSize = "20px";
		partTitleDiv.style.fontWeight = "bold";
		partTitleDiv.style.color = "green";
		partTitleDiv.innerHTML = partition.title;
		var flexdiv = document.createElement('div');
		flexdiv.style.display = "flex";

		//cardviews
		for(var dashBoardCard of partition.dashboardCards){
			var dashboardcard = document.createElement('div');
			dashboardcard.classList.add('dashboardcard');
			var cardTitle = document.createElement('div');
			cardTitle.id ="titleDiv";
			cardTitle.innerHTML = dashBoardCard.title;
			var attributeDiv = document.createElement('div');
			attributeDiv.id ="attributeDiv";
			attributeDiv.style.display = "flex";



			dashboardcard.appendChild(cardTitle);
			dashboardcard.appendChild(attributeDiv);
			flexdiv.appendChild(dashboardcard);
		}
		
		dashboard_partition.appendChild(partTitleDiv);
		dashboard_partition.appendChild(flexdiv);
		container.appendChild(dashboard_partition);
	}



}


/*
                        <div class="dashboard-partition">
							<div class="dashboard-title" style="font-size:20px; font-weight:bold; color:green">Supplier Management Sub System</div>
							<div style="display:flex">
								<div class="dashboardcard">
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
								</div>
								<div class="dashboardcard">
									<div id="titleDiv"></div>
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
								</div>
							</div>
						</div>

*/