function motorvognValidering(eierspersonnummer, eiersnavn, eiersadresse, kjennetegn, bilmerke, biltype){
    const regexp_personnummer = /^[0-9]{11}$/
    const regexp_navn = /^[A-ZØÆÅa-zøæå\s]{3,30}$/
    const regexp_adresse = /^[A-ZØÆÅa-zøæå0-9\-,\s]{3,50}$/
    const regexp_kjennetegn = /^[A-Z0-9]{3,10}$/

    let valid = true;
    if(!regexp_personnummer.test(eierspersonnummer)){
        $("#eierspersonnummer-error").html("Vennligst fyll ut feltet med 11 gyldige tall");
        valid = false;
    }if(!regexp_navn.test(eiersnavn)){
        $("#eiersnavn-error").html("Vennligst fyll ut feltet med 3 til 30 bokstaver");
        valid = false;
    }if(!regexp_adresse.test(eiersadresse)){
        $("#eiersadresse-error").html("Vennligst fyll ut feltet med en gyldig adresse");
        valid = false;
    }if(!regexp_kjennetegn.test(kjennetegn)){
        $("#kjennetegn-error").html("Vennligst fyll ut feltet med en gyldig kjennetegn");
        valid = false;
    }if(bilmerke == ""){
        $("#bilmerke-error").html("Vennligst velg et bilmerke");
        valid = false;
    }if(biltype == ""){
        $("#biltype-error").html("Vennligst velg en biltype");
        valid = false;
    }
    return valid;
}
function motorvognTommmeErrorFelter(){
    $("#eierspersonnummer-error").html("");
    $("#eiersnavn-error").html("");
    $("#eiersadresse-error").html("");
    $("#kjennetegn-error").html("");
    $("#bilmerke-error").html("");
    $("#biltype-error").html("");
}
function motorvognTommeFelter(){
    $("#eierspersonnummer").val("");
    $("#eiersnavn").val("");
    $("#eiersadresse").val("");
    $("#kjennetegn").val("");
    $("#bilmerke").val("");
    $("#biltype").val("");
}