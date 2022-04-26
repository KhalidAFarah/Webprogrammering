function main(){
    const navn = "Per Pettersen";
    const alder = 21;
    const myndig = true;
    const hoyde = 1.83;

    let ut = navn + " er " + alder + " år";
    if(myndig){
        ut += ", er myndig";
    }else{
        ut += " og er ikke myndig";
    }

    ut += " og er " + hoyde + " høy."

    document.write(ut);
    console.log(ut);
    alert(ut);
}


function innText(value){
    let ut = "Du har skrevet inn følgende data: " + value;
    alert(ut);
}
function innKlikk(){
    alert("Knappen er klikket!");
}


main();