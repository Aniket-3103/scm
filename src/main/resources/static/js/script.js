let themeBtn=document.getElementById("theme-btn")

let currentTheme=getTheme();

//call changeTheme on page loading
document.addEventListener('DOMContentLoaded', ()=>{
    changeTheme();
})


function changeTheme(){
    changeCurrentTheme(currentTheme, currentTheme);
    document.querySelector('html').classList.add(currentTheme);

    themeBtn.addEventListener('click', ()=>{

        const oldTheme=currentTheme;

        if(currentTheme==="dark"){
            currentTheme="light";
        }
        else{
            currentTheme="dark";
        }

        changeCurrentTheme(currentTheme, oldTheme);
    })

}


//set theme to local storage
function setTheme(theme){
    localStorage.setItem("theme", theme)
}

//get theme from local storage
function getTheme(){
    let theme=localStorage.getItem("theme");

    return theme ? theme : "light";
}

function changeCurrentTheme(theme, oldTheme){
    //set the current theme in local storage
    setTheme(theme);

    //remove the current theme from the html
    document.querySelector('html').classList.remove(oldTheme);

     //add the current theme from the html
     document.querySelector('html').classList.add(theme);

     //change the btn text
     themeBtn.querySelector('span').textContent = theme==="light"?"Dark":"Light";
}