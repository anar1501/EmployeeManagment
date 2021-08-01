const btn = document.querySelector("#toggle-btn");
const section = document.querySelectorAll(".section-name");
const dropdown = document.querySelector("#members-collapse");
const sidebar = document.querySelector("#sidebar");
const main = document.querySelector("main");
const heading = document.querySelector(".heading");

eventListeners();

function eventListeners() {
    btn.addEventListener("click", openCloseNavbar);
    section[3].parentElement.addEventListener("click", function() {
        for (var i = 0; i < 5; i++) {
            if (section[i].style.display == "none") {
                setTimeout(function(section,i) {
                    section[i].style.display = "inline-block";
                }, 400, section, i);
                sidebar.style.width = "300px";
                main.style.marginLeft = "300px";
                heading.style.marginLeft = "300px";
            }
        }
    });
}

function openCloseNavbar() {
    for (var i = 0; i < 5; i++) {
        if (section[i].style.display == "inline-block") {
            section[i].style.display = "none";
            dropdown.className = "collapse";
            sidebar.style.width = "90px";
            main.style.marginLeft = "90px";
            heading.style.marginLeft = "90px";
        } else {
            setTimeout(function(section,i) {
                section[i].style.display = "inline-block";
            }, 400, section, i);
            sidebar.style.width = "300px";
            main.style.marginLeft = "300px";
            heading.style.marginLeft = "300px";
        }
    }
}