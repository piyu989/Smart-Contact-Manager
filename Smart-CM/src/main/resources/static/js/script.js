console.log("ram ram ji");

let currentTheme=getTheme();

changeTheme(currentTheme);

function changeTheme(){
    document.querySelector('html').classList.add(currentTheme);

    const changeThemeButton = document.querySelector("#theme_change_button");

    changeThemeButton.addEventListener("click",() => {
        const oldTheme=currentTheme;
        if(currentTheme=="dark"){
            currentTheme="light";
        }else{
            currentTheme="dark";
        }
        setTheme(currentTheme);
        document.querySelector('html').classList.remove(oldTheme);
        document.querySelector('html').classList.add(currentTheme);
    });
}

function setTheme(theme){
    localStorage.setItem("theme",theme);
}

function getTheme(){
    let theme=localStorage.getItem("theme");
    if(theme) return theme;
    else return "light";
}