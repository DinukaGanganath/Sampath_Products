function initLayout(title, heading){
    document.title = title;
    document.getElementById("heading").innerHTML = heading;
}

function sidebarLoader(selected){

    var menus = "";
    fetch("script/menuLinks.json")
    .then(response => response.json())
    .then(data => {
        for (var item of data){
            if(selected == item.link){
                menus += `
                        <li class="nav-link active">
                            <a href="${item.link}">
                                <i class="${item.icon}"></i>
                                <span class="text nav-text">${item.text}</span>
                            </a>
                        </li>
            `; 
            }else{
                menus += `
                        <li class="nav-link">
                            <a href="${item.link}">
                                <i class="${item.icon}"></i>
                                <span class="text nav-text">${item.text}</span>
                            </a>
                        </li>
            `;
            }
        }

        document.getElementById("sidebarContainer").innerHTML = `
            <div class="sidebar show mobmenuhide">
                <div class="logo-details">
                    <header>
                        <!--Side bar Logo-->
                        <div class="image-text">
                            <!--Image-->
                            <span class="image">
                                <img src="images/logo_white.png" alt="logo" />
                            </span>
                            <!--image text-->
                            <div class="text header-text">
                                <h5 class="name">Sampath Products</h5>
                            </div>
                            <!--Chevoren mark-->
                            <div class="toggle">
                                <i class="icon fa-solid fa-circle-chevron-left" onclick="menuToggle(this)"></i>
                            </div>
                        </div>
                    </header>
                    <!--Menu items-->
                    <div class="menu-bar">
                        <div class="menu">
                            <ul class="menu-links" id="menuLists">` + menus + `  
                            </ul>
                        </div>
                        <div class="bottom-content">
                            <li class="nav-link">
                                <a href="#">
                                    <img class="user-image" src="images/myphoto.jpeg" alt="User Image">
                                    <span class="text nav-text">Dinuka</span>
                                </a>
                            </li>
                            <li class="nav-link">
                                <a href="#">
                                    <i class="fa-solid fa-sign-out icon"></i>
                                    <span class="text nav-text">Signout</span>
                                </a>
                            </li>
                        </div>
                    </div>
                </div>
            </div>
    `;
    });

}

