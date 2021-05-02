<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="resources/css/main.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script>
        // Variables passed through template engine's rendering phase
        let disableLoadButton = ("${disable_load_button}" === 'true');
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col text-center display-2"> Game of Life</div>
    </div>
    <div class="row mb-4">
        <div class="col text-center display-4">by Sileno (Vincenzo Canfora)</div>
    </div>
    <div class="row mb-2">
        <div class="col-2"></div>
        <button class="btn btn-primary m-2 shadow col" data-toggle="modal" data-target="#infoModal">Info</button>

        <div class="dropdown m-2 shadow">
            <button class="btn btn-primary dropdown-toggle col" id="dropdownMenuButton" data-toggle="dropdown">
                Randomize strategies
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                <a onclick="generatePopulation(0)" class="dropdown-item">Noise</a>
                <a onclick="generatePopulation(1)" class="dropdown-item">Villages</a>
                <a onclick="generatePopulation(2)" class="dropdown-item">Roads</a>
            </div>
        </div>
        <button id="load-btn" class="btn btn-primary m-2 shadow col" onclick="loadState()">Load</button>
        <button id="save-btn" class="btn btn-primary m-2 shadow col" onclick="saveState()" disabled>Save</button>
        <div class="col-2"></div>
    </div>

    <div class="row mb-4">
        <div class="col-3"></div>
        <div class="row col mb-4 btn-group btn-group-toggle" data-toggle="buttons">
            <label class="btn btn-dark active">
                <input type="radio" id="pause" autocomplete="off" onclick="stopUpdaterTimer()" checked>Pause
            </label>
            <label class="btn btn-dark">
                <input type="radio" id="play" autocomplete="off" onclick="setUpdaterTimer(500)">x1
            </label>
            <label class="btn btn-dark">
                <input type="radio" id="play2" autocomplete="off" onclick="setUpdaterTimer(250)">x2
            </label>
        </div>
        <div class="col-3"></div>
    </div>

    <div class="row">
        <p class="col text-right text-muted m-2">Session = ${session_id}</p>
    </div>

    <div class="row">
        <div class="col-sm"></div>
        <div id="image-container" class="col-sm text-centered" onclick="forwardGeneration()">
            <img id="image-gol" class="shadow-lg" data-tilt>
        </div>
        <div class="col-sm"></div>
    </div>

    <div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Info</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Informazioni varie ed eventuali
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script type="text/javascript" src="resources/js/main.js"></script>
<script type="text/javascript" src="resources/js/vanilla-tilt.min.js"></script>
</body>
</html>