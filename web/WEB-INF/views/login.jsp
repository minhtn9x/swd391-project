<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="fonts/icomoon/style.css">

        <link rel="stylesheet" href="css-l/owl.carousel.min.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css-l/bootstrap.min.css">

        <!-- Style -->
        <link rel="stylesheet" href="css-l/style.css">

        <title>Sign in</title>

        <style>
            .google {
                width: 20px;
                height: 20px;
            }
            .btn.btn-google{
                background: white;
                border: 2px groove black;
            }  
            h6 {
                color: black;
                display: inline-block;
            }
        </style>
    </head>
    <body>

        <input type="hidden" id="status" value="${requestScope.STATUS}">

        <div class="d-md-flex half">
            <div class="bg" style="background-image: url('images/bg_1.png');"></div>
            <div class="contents">
                <div class="container">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-md-12">
                            <div class="form-block mx-auto">
                                <div class="text-center mb-5">
                                    <h3 class="text-uppercase">Login</h3>
                                </div>
                                <form action="MainController" method="post">
                                    <div class="form-group first">
                                        <label for="username">Username</label>
                                        <input type="text" class="form-control" placeholder="Your User ID" id="username" name="userID">
                                    </div>
                                    <div class="form-group last mb-3">
                                        <label for="password">Password</label>
                                        <input type="password" class="form-control" placeholder="Your Password" id="password" name="password">
                                    </div>

                                    <div class="d-sm-flex mb-5 align-items-center">
                                        <label class="control control--checkbox mb-3 mb-sm-0"><span class="caption">Remember me</span>
                                            <input type="checkbox"/>
                                            <div class="control__indicator"></div>
                                        </label>
                                        <span class="ml-auto"><a href="MainController?action=ForgotPassword" class="forgot-pass">Forgot Password</a></span> 
                                    </div>

                                    <input type="submit" name="action" value="Sign in" class="btn btn-block py-2 btn-primary">

                                    <div class="row" style="padding: 20px;">
                                        <div class="col-lg-7">
                                            <h6>Don't have an account?</h6>
                                        </div>
                                        <div class="col-lg-5">
                                            <a href="registration.jsp">
                                                Create an account
                                            </a>
                                        </div>
                                    </div>
                                    <span class="text-center my-3 d-block">or</span>
                                    <div>
                                        <a href="https://accounts.google.com/o/oauth2/auth?scope=profile email
                                           &redirect_uri=http://localhost:8084/SE1622_GROUP6_DMA/LoginGoogleController
                                           &response_type=code
                                           &client_id=131624469088-nlrbae77mt7l609nsl8398uh9f7ebldq.apps.googleusercontent.com
                                           &approval_prompt=force" class="btn btn-block py-2 btn-google">
                                            <img class="google" src="images/google-icon.svg"> <h6>Sign in with email @fpt.edu.vn</h6></a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="https://unpkg.com//sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">
        <script type="text/javascript">
            var status = document.getElementById("status").value;
            if (status === "Incorrect UserID or Password!") {
                swal("Sorry!", "Incorrect Username or Password", "error");
            } else if (status === "Invalid account!") {
                swal("Sorry!", "Your account is not allowed to log into the system!", "error");
            } else if (status === "invalidID") {
                swal("Sorry!", "Please enter your User ID", "error");
            } else if (status === "invalidPassword") {
                swal("Sorry!", "Please enter your password", "error");
            } else if (status === "resetSuccess") {
                swal("Congrats!", "Password Reset Successfully", "success");
            } else if (status === "resetFailed") {
                swal("Sorry!", "Password Reset Unsuccessfully", "error");
            } else if (status === " Account deleted ") {
                swal("Sorry!", "Your account has been disabled. Contact us for more information!","error")
            }
        </script>
    </body>
</html>