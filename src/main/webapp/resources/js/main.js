const loadBtn = document.getElementById("load-btn");
const saveBtn = document.getElementById("save-btn");
const imageGol = document.getElementById("image-gol");
let updaterInterval;

function setUpdaterTimer(milliseconds) {
    clearInterval(updaterInterval);
    updaterInterval = window.setInterval(function () {
        forwardGeneration();
    }, milliseconds);
}

function stopUpdaterTimer() {
    clearInterval(updaterInterval);
}

function updateImageWithBase64Data(data) {
    imageGol.src = "data:image/png;base64," + data;
}

$(function() {
    if(disableLoadButton) {
        loadBtn.disabled = true;
    }
});

function saveState() {
    $.ajax({
        url : '/save',
        type : 'GET',
        success : function(data) {
            if(loadBtn.disabled === true) {
                loadBtn.disabled = false;
            }
        },
        error : function(request,error) {
            console.log("Error on saveState: ", error)
        }
    });
}

function loadState() {
    $.ajax({
        url : '/load',
        type : 'GET',
        success : function(data) {
            updateImageWithBase64Data(data)
        },
        error : function(request,error) {
            console.log("Error on loadState: ", error)
        }
    });
}

function generatePopulation(strategyType) {
    $.ajax({
        url : '/generate/' + strategyType,
        type : 'GET',
        dataType: "text",
        success : function(data) {
            if(saveBtn.disabled  === true) {
                saveBtn.disabled = false;
            }
            updateImageWithBase64Data(data)
        },
        error : function(request,error) {
            console.log("Error on generatePopulation: ", error)
        }
    });
}

function forwardGeneration() {
    $.ajax({
        url : '/forward',
        type : 'GET',
        success : function(data) {
            updateImageWithBase64Data(data);
        },
        error : function(request,error) {
            console.log("Error on saveState: ", error)
        }
    });
}
