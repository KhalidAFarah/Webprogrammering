const personRegister = [];
let ut = "";

const person1 = {
    navn: "Line Jensen",
    adresse: "Askerveien 82",
    telefonnr: "123456789",
    fødselsnummer: "11111111111"
};
const person2 = {
    navn: "Ole Hansen",
    adresse: "Osloveien 82",
    telefonnr: "99887766",
    fødselsnummer: "22222222222"
};

const person3 = {
    navn: "Peter Pettersen",
    adresse: "Osloveien 42",
    telefonnr: "55443322",
    fødselsnummer: "33333333333"
};

const person4 = {
    navn: "Chris Lee",
    adresse: "Klokkevei 1000",
    telefonnr: "11223344",
    fødselsnummer: "44444444444"
};

personRegister.push(person1);
personRegister.push(person2);
personRegister.push(person3);
personRegister.push(person4);

function visPersonRegister () {
    ut = "<table><tr>" +
        "<th>Navn</th><th>Adresse</th><th>Telefonnr</th><th>Fødselsnr</th>" +
        "</tr>";
    for(let i in personRegister){
        if(i % 2 == 0){
            ut += "<tr>" +
                "<td><strong>"+personRegister[i].navn+"</strong></td><td><strong>"+personRegister[i].adresse+"</strong</td><td><strong>"+personRegister[i].telefonnr+"</strong></td><td><strong>"+personRegister[i].fødselsnummer+"</strong></td>" +
                "</tr>";
        }else{

            let kjønn = personRegister[i].fødselsnummer.split("")[10];
            kjønn = parseInt(kjønn);
            if(kjønn % 2 == 0){
                ut += "<tr>" +
                    "<td>"+personRegister[i].navn+"</td><td>"+personRegister[i].adresse+"</td><td>"+personRegister[i].telefonnr+"</td><td>"+personRegister[i].fødselsnummer+"</td>" +
                    "</tr>";
            }else{
                ut += "<tr>" +
                    "<td><strong>"+personRegister[i].navn+"</strong></td><td><strong>"+personRegister[i].adresse+"</strong</td><td><strong>"+personRegister[i].telefonnr+"</strong></td><td><strong>"+personRegister[i].fødselsnummer+"</strong></td>" +
                    "</tr>";
            }

        }
    }
    ut += "<table/>";
    document.getElementById("personRegister").innerHTML=ut;

    document.getElementById("personRegister").innerHTML+= "</br>"
    ut = "<table><tr>" +
        "<th>Navn</th><th>Adresse</th><th>Telefonnr</th><th>Fødselsnr</th>" +
        "</tr>";

    personRegister.sort((person1, person2) => {
        const navn1 = person1.navn.toLowerCase();
        const navn2 = person2.navn.toLowerCase();

        if(navn1 > navn2){
            return 1;
        }else if(navn1 < navn2){
            return -1;
        }else{
            return 0;
        }

    })
    for(let i in personRegister){
        if(i % 2 == 0){
            ut += "<tr>" +
                "<td><strong>"+personRegister[i].navn+"</strong></td><td><strong>"+personRegister[i].adresse+"</strong</td><td><strong>"+personRegister[i].telefonnr+"</strong></td><td><strong>"+personRegister[i].fødselsnummer+"</strong></td>" +
                "</tr>";
        }else{

            let kjønn = personRegister[i].fødselsnummer.split("")[10];
            kjønn = parseInt(kjønn);
            if(kjønn % 2 == 0){
                ut += "<tr>" +
                    "<td>"+personRegister[i].navn+"</td><td>"+personRegister[i].adresse+"</td><td>"+personRegister[i].telefonnr+"</td><td>"+personRegister[i].fødselsnummer+"</td>" +
                    "</tr>";
            }else{
                ut += "<tr>" +
                    "<td><strong>"+personRegister[i].navn+"</strong></td><td><strong>"+personRegister[i].adresse+"</strong</td><td><strong>"+personRegister[i].telefonnr+"</strong></td><td><strong>"+personRegister[i].fødselsnummer+"</strong></td>" +
                    "</tr>";
            }

        }
    }
    ut +="<table/>";
    document.getElementById("personRegister").innerHTML+=ut;
}
function registrer(){
    const navn = document.getElementById("navn").value;
    const adresse = document.getElementById("adresse").value;
    const tlf = document.getElementById("tlf").value;
    const fødselsnummer = document.getElementById("fødselsnummer").value;

    const person = {
        navn: navn,
        adresse: adresse,
        telefonnr: tlf,
        fødselsnummer: fødselsnummer,
    };

    personRegister.push(person);
    visPersonRegister();


}