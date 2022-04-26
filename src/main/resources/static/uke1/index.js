let i = 0;

function heiVerden(){
    //document.write("Hei verden");
    alert("Hei verden");
}
function visText(txt){
    document.write("<p style='color: blue;'>"+txt+"</p>");
    //document.write(txt);
    //document.body.style="blue"
    alert(txt.toUpperCase());
    console.log(txt);
}
function log1(){
    i++;
    console.log(1);
    //console.log(i);
    console.log("Du har trykket på knappen " + i + " ganger");
}