<html>
<head>
    <meta charset="utf-8">
    <title>success indication</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    Succeed!
                </h4> <strong>${msg!""}</strong><a href="${url}" class="alert-link">   Auto redirect in 1s!</a>
            </div>
        </div>
    </div>
</div>

</body>

<script>
    setTimeout('location.href="${url}"', 1000);
</script>

</html>