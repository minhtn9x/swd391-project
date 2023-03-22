<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <style>
            .half .contents .form-control, .half .bg .form-control {
                border: none;
                border-radius: 4px;
                height: 40px;
                margin-bottom: 20px;
                background: #efefef; 
            }

            .buttonRegis {
                border: none;
                border-radius: 4px;
                height: 40px;
                width: 100%;
                margin-bottom: 20px;
                color: #fff;
                background-color: #fb771a;
                border-color: #fb771a; 
            }
            .text-danger {
                color : red; 
                margin-bottom: 10px;
                font-size: 15px;
            }
        </style>

        <title>Sign Up</title>
    </head>
    <body>
        <input type="hidden" id="status" value="${requestScope.STATUS}">

        <div class="d-md-flex half">
            <div class="bg" style="background-image: url('images/bg_1.jpg');"></div>
            <div class="contents">

                <div class="container">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-md-12">
                            <div class="form-block mx-auto">
                                <div class="text-center mb-3">
                                    <h4 class="text-uppercase">Sign up</h4>
                                </div>

                                <c:set var="error" value="${requestScope.USER_ERROR}"/>
                                <c:set var="info" value="${requestScope.USER_INFO}"/>
                                <c:set var="duplicate" value="${requestScope.DUPLICATE}"/>
                                <c:set var="message" value="${requestScope.ERROR_MESSAGE}"/>
                                
                                <c:if test="${message != null}">
                                        <div class="text-danger ml-1 text-center">
                                            ${message}
                                        </div>
                                </c:if>

                                <form action="MainController" method="post">    
                                    <div>
                                        <input type="text" class="form-control" placeholder="Your User ID Ex: Nhan1402" name="userID" id=""
                                               <c:if test="${info != nul}">value="${info.getUserID()}"</c:if>
                                                   />
                                        </div>

                                    <c:if test="${not empty error.getUserID()}">
                                        <div class="text-danger ml-1 text-center">
                                            ${error.getUserID()}
                                        </div>
                                    </c:if>
                                                   
                                    <c:if test="${not empty duplicate}">
                                        <div class="text-danger ml-1 text-center">
                                            ${duplicate}
                                        </div>
                                    </c:if>

                                    <div>
                                        <input type="text" class="form-control" placeholder="Your Full Name" name="fullName" id=""
                                               <c:if test="${info != nul}">value="${info.getFullName()}"</c:if>
                                                   />
                                        </div>

                                    <c:if test="${not empty error.getFullName()}">
                                        <div class="text-danger ml-1 text-center" style="color :red">
                                            ${error.getFullName()}
                                        </div>
                                    </c:if>

                                    <div>
                                        <input type="text" class="form-control" placeholder="Your email@gmail.com/fpt.edu.vn/fe.edu.vn" name="email" id=""
                                               <c:if test="${info != nul}">value="${info.getEmail()}"</c:if>
                                                   />
                                        </div>

                                    <c:if test="${not empty error.getEmail()}">
                                        <div class="text-danger ml-1 text-center" style="color :red">
                                            ${error.getEmail()}
                                        </div>
                                    </c:if>    

                                    <div>

                                        <input type="password" class="form-control" placeholder="Your password" name="password" id=""/>

                                        <input type="password" class="form-control" placeholder="Your passwod" name="password" id=""/>

                                    </div>

                                    <c:if test="${not empty error.getPassword()}">
                                        <div class="text-danger ml-1 text-center">
                                            ${error.getPassword()}
                                        </div>
                                    </c:if>

                                    <div>
                                        <input type="password" class="form-control" placeholder="Confirm your password" name="confirmPassword" id=""/>
                                    </div>

                                    <c:if test="${not empty error.getConfirmPassword()}">
                                        <div class="text-danger ml-1 text-center">
                                            ${error.getConfirmPassword()}
                                        </div>
                                    </c:if>    

                                    <div>
                                        <input type="date" class="form-control" placeholder="Your Date of Birth" id="" name="birthday" max="2008-01-01"
                                               <c:if test="${info != nul}">value="${info.getBirthday()}"</c:if>
                                                   />
                                        </div>

                                    <c:if test="${not empty error.getBirthday()}">
                                        <div class="text-danger ml-1 text-center">
                                            ${error.getBirthday()}
                                        </div>
                                    </c:if>    

                                    <div>
                                        <input type="text" class="form-control" placeholder="Your phone number" name="phoneNumber" id=""
                                               <c:if test="${info != nul}">value="${info.getPhoneNumber()}"</c:if>
                                                   />
                                        </div>

                                    <c:if test="${not empty error.getPhoneNumber()}">
                                        <div class="text-danger ml-1 text-center">
                                            ${error.getPhoneNumber()}
                                        </div>
                                    </c:if>    

                                    <div style="padding-bottom: 20px;">
                                        <input type="text" class="form-control" placeholder="Your address (Optional)" name="address" id=""
                                               <c:if test="${info != nul}">value="${info.getAddress()}"</c:if>
                                               />
                                    </div>                                                                                                                       
                                    <div style="padding-bottom: 20px;">
                                        <input type="submit" name="action" value="Register" class="btn btn-block py-2 btn-primary"/>
                                    </div>
                                    <a href="login.jsp" class="signup-image-link">I am already a
                                        member</a>
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
            if (status === "success") {
//                swal("Congrats", "Account Created Successfully!", "success");
                swal({
                    title: "Congrats",
                    text: "Account Created Successfully!",
                    type: "success"
                }).then(function () {
                    window.location = "login.jsp";
                });
            }
        </script>
    </body>
</html>