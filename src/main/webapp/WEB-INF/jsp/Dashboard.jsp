<!DOCTYPE html>
<html lang="en">
<head>
    <title>Kar-Utils</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width ,initial-scale=1">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <link href="/css/base.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav class="navbar navbar-inverse ">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" title="Karthikeyan Thiyagarajan"
               href="#"><kbd>Kar-Utils</kbd></a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/dash"><span class="glyphicon glyphicon-home"></span> Home</a></li>
            <li><a target="_blank" href="https://kar-corona-dashboard.herokuapp.com/"><span class="glyphicon glyphicon-refresh"></span> Corona Dashboard</a></li>
            <li><a target="_blank" href="https://www.hackerrank.com/karthikeyan10031"><i class='glyphicon glyphicon-console'></i> HackerRank</a></li>
            <!--<li><a href="#"><span class="glyphicon glyphicon-home"></span> Login</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-home"></span> About</a></li>-->
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="navbar-right"><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Log Out &nbsp</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <div class="row">
        <h5 class="navbar-text navbar-right"><span class="glyphicon glyphicon-heart text-danger"></span> Karthikeyan Thiyagarajan
        </h5>
        <h3 class="sub-heading text-center">QR Code Generator</h3>
    </div>
    
    <div class="row">
        <div class="col-md-2">
            <ul class="nav nav-pills nav-stacked">
                <li class="active" id="1qr1"><a id="qr1" class="tiles" href="#"><span
                        class="glyphicon glyphicon-qrcode"></span> &nbsp; QR code</a></li>
                <li id="2qr2"><a id="qr2" class="tiles" href="#"><span class="glyphicon glyphicon-transfer"></span>
                    &nbsp; Text compare</a></li>
                <li id="3qr3"><a id="qr3" class="tiles" href="#"><span class="glyphicon glyphicon-th"></span>
                    &nbsp; PDF Parser</a></li>
                <li id="4qr4"><a id="qr4" class="tiles" href="#"><span class="glyphicon glyphicon-tasks"></span>
                    &nbsp; Text Matcher</a></li>
                <li id="5qr5"><a target="_blank" id="qr5" class="tiles"
                                 href="https://github.com/karthikeyan-thiyagarajan"><span
                        class="glyphicon glyphicon-user"></span>
                    &nbsp; About</a></li>
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
        <!--Text compare -FRAME-->
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
                    <button type="button" class="btn btn-success" id="compare-text">Compare</button>
                    <button type="button" class="btn btn-danger hide" id="compare-text-reset">Reset</button>
                </div>
            </div>
            
            <div class="row well hide" id="compare-res">
                <div class="col-md-12 text-center">
                    <h3 id="compare-result"></h3>
                </div>
            </div>
        </div>
        
        <!--PDF TO TEXT -FRAME-->
        <div class="col-md-10 hide" id="qr-main2">
            <p>PDF to text converter to extract text data from PDF/Docs/Txt files</p>
            <div class="col-md-6 well">
                <form>
                    <div class="custom-file">
                        <h4>Please Select file to Upload</h4>
                        <label class="custom-file-label btn btn-outline" for="customFile">Upload file</label>
                        <input type="file" class="custom-file-input hidden" id="customFile">
                    </div>
                </form>
                <div>
                    <button type="button" class="btn btn-warning hide" id="pdf-parse" data-loading-text="Parsing...">
                        Parse
                    </button>
                </div>
            </div>
            <div class="col-md-6 ">
                <textarea id="result-pdf" class="form-control hide" rows="6"></textarea>
            </div>
            <div class="row">
                <a id="download-content" class="hide btn btn-success" href=""><span
                        class="glyphicon glyphicon-download-alt"></span>&nbsp; Download</a>
            </div>
        </div>
        
        <!--Text matcher-->
        <div class="col-md-10 hide" id="qr-main3">
            <p>Text Matcher lets you know the how much percentage of matching source and target texts</p>
            <form>
                <div class="col-md-6 ">
                    <textarea id="match-left" class="form-control" rows="5" placeholder="Enter or paste text here."
                              required></textarea>
                </div>
                <div class="col-md-6 ">
                    <textarea id="match-right" class="form-control" rows="5" placeholder="Enter or paste text here."
                              required></textarea>
                </div>
                <div class="row">
                    <div class="col-md-12 text-center" id="text-match">
                        <button type="submit" class="btn btn-success" id="match-text">Match</button>
                        <button type="button" class="btn btn-danger hide" id="match-text-reset">Reset</button>
                    </div>
                </div>
            </form>
            
            <div class="row hide" id="match-res">
                <div class="col-md-4"></div>
                <div class="progress col-md-4">
                    <div class="progress-bar progress-bar-success progress-bar-striped active" role="progressbar"
                         id="match-result"
                         aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width:40%">
                        40%
                    </div>
                </div>
                <div class="col-md-4 "></div>
            </div>
        </div>
    
    </div>

</div>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/base.js"></script>
</body>
</html>