<html>
<head>
    <title>Kar-Utils</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width ,initial-scale=1">
    <link href="/css/base.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav class="navbar navbar-inverse ">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" target="_blank"
               href="https://github.com/karthikeyan-thiyagarajan"><kbd>Kar-Utils</kbd></a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/"><span class="glyphicon glyphicon-home"></span> Home</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-home"></span> Queue</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-home"></span> Users</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-home"></span> Login</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-home"></span> about</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="row">
        <!--<div class="col-md-12 well">
            <h2 class="text-center text-primary">Kar-Utils</h2>
        </div>-->
        <h3 class="sub-heading text-center" >QR Code Generator</h3>
    </div>
    
    <div class="row">
        <div class="col-md-2">
            <ul class="nav nav-pills nav-stacked">
                <li class="active" id="1qr1"><a id="qr1" class="tiles" href="#"><span
                        class="glyphicon glyphicon-qrcode"></span> &nbsp; QR code</a></li>
                <li id="2qr2"><a id="qr2" class="tiles" href="#"><span class="glyphicon glyphicon-transfer"></span>
                    &nbsp; Text compare</a></li>
                <li id="3qr3"><a id="qr3" class="tiles" href="#"><span class="glyphicon glyphicon-home"></span>
                    &nbsp; Users</a></li>
                <li id="4qr4"><a id="qr4" class="tiles" href="#"><span class="glyphicon glyphicon-home"></span>
                    &nbsp; Login</a></li>
                <li id="5qr5"><a id="qr5" class="tiles" href="#"><span class="glyphicon glyphicon-home"></span>
                    &nbsp; about</a></li>
            </ul>
        </div>
        <!--QR -FRAME-->
        <div class="col-md-10" id="qr-main">
            <p>You can generate QR code graphics with our QR code generator</p>
            <div class="col-md-5 ">
                <div class="input-group mb-3">
                    <input id="qr-data" type="text" class="form-control" placeholder="Content">
                    <span class="input-group-btn">
                    <button class="btn btn-warning" type="button" id="button-addon2" data-loading-text="Generating..."
                    >Generate</button>
                </span>
                </div>
            </div>
            <div class="col-md-2 ">
            
            </div>
            <div class="col-md-3 ">
                <img id="qr-image" src="">
            </div>
        </div>
        <!--2nd -FRAME-->
        <div class="col-md-10 hide" id="qr-main1">
            <p>Text Difference Checker lets you compare text files, documents, binary files, and archives</p>
            <div class="col-md-6 ">
                <textarea id="left" class="form-control" rows="5" placeholder="Enter or paste text here."></textarea>
            </div>
            <div class="col-md-6 ">
                <textarea id="right" class="form-control" rows="5" placeholder="Enter or paste text here."></textarea>
            </div>
            <div class="row">
                <div class="col-md-12 text-center" id="text-compare">
                    <button type="button" class="btn btn-success" id="compare-text" >Compare </button>
                    <button type="button" class="btn btn-danger hide" id="compare-text-reset" >Reset </button>
                </div>
            </div>
    
            <div class="row well hide" id="compare-res">
                <div class="col-md-12 text-center" >
                    <h3 id="compare-result"></h3>
                </div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/base.js"></script>
</body>
</html>