const form = 'Form';



$(function(){
    $('#inputGroupSelect01').change(function() {
        // console.log(event.targetElement);
        // console.log(event.targetElement);
        let selectedText = $(this).find("option:selected").text();
        console.log(selectedText);

        if (selectedText === form) {
            changeSendReportElementsVisibility(true);
        } else {
            changeSendReportElementsVisibility(false);
        }
    })
});

function changeSendReportElementsVisibility(formChosenValue) {
    let formWrapper = document.getElementById("formWrapper");
    let inputFileWrapper = document.getElementById("inputFileWrapper");

    if (formChosenValue) {
        formWrapper.style.display = "block";
        inputFileWrapper.style.display = "none";
    } else {
        formWrapper.style.display = "none";
        inputFileWrapper.style.display = "block";
    }
}

function openFile(evt) {
    var fileReader = new FileReader();
    var input = evt.target;
    let chosenFile = input.files[0];

    fileReader.onload = function() {
        var text = fileReader.result;
        console.log(text.substring(0, 200));
        document.getElementById("inputFileLabel").innerText  = chosenFile.name;
    };

    fileReader.readAsText(chosenFile, "UTF-8");
    console.log("changed");
}


