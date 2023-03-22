<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Report abuse</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <style>
            .container{
                display: block;
                position: relative;
                /*margin: 40px auto;*/
                height: auto;
                width: 500px;
                /*padding: 20px;*/
            }

            .container ul{
                list-style: none;
                margin: 0;
                padding: 0;
                overflow: auto;
            }

            #option-ul li{
                color: black;
                display: block;
                position: relative;
                float: left;
                width: 100%;
                height: 160px;
                border-bottom: 1px solid #333;
            }

            ul li input[type=radio]{
                position: absolute;
                visibility: hidden;
            }

            ul li label{
                display: block;
                position: relative;
                font-weight: 300;
                font-size: 1.35em;
                padding: 25px 25px 25px 80px;
                margin: 10px auto;
                height: 30px;
                z-index: 9;
                cursor: pointer;
                -webkit-transition: all 0.25s linear;
            }

            ul li p{
                display: block;
                position: relative;
                font-weight: 300;
                font-size: small;
                padding: 0 25px 25px 80px;
                height: 30px;
                z-index: 9;
                cursor: pointer;
                -webkit-transition: all 0.25s linear;
            }

            ul li:hover label{
                color: #0d73ea;
            }

            ul li .check{
                display: block;
                position: absolute;
                border: 5px solid #AAAAAA;
                border-radius: 100%;
                height: 30px;
                width: 30px;
                top: 33px;
                left: 23px;
                z-index: 5;
                transition: border .25s linear;
                -webkit-transition: border .25s linear;
            }

            ul li:hover .check {
                border: 5px solid #0d73ea;
            }

            ul li .check::before {
                display: block;
                position: absolute;
                content: '';
                border-radius: 100%;
                height: 15px;
                width: 15px;
                top: 3px;
                left: 3px;
                /*margin: auto;*/
                transition: background 0.25s linear;
                -webkit-transition: background 0.25s linear;
            }

            input[type=radio]:checked ~ .check {
                border: 5px solid #0d73ea;
            }

            input[type=radio]:checked ~ .check::before{
                background: #0d73ea;
            }

            input[type=radio]:checked ~ label{
                color: black;
            }
        </style>
    </head>

    <body id="page-top">

        <c:set var="user" value="${sessionScope.LOGIN_USER}"/>
        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">DMA</div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <hr class="sidebar-divider">
                <!-- Nav Item - Pages Collapse Menu -->
                <div class="nav-item">
                    <a class="nav-link" href="MainController?action=UserHome">
                        <i class="fas fa-fw fa-hard-drive"></i>&emsp;
                        <span>My Document</span>
                    </a>
                </div>

                <div class="nav-item active">
                    <a class="nav-link" href="MainController?action=MyProjects" 
                       data-toggle="collapse" data-target="#collapseMyProjects" 
                       aria-expanded="true" aria-controls="collapseMyProjects">
                        <i class="fas fa-fw fa-user-group"></i>&emsp;
                        <span>Group sharing</span>
                    </a>
                    <div id="collapseMyProjects" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <h6 class="collapse-header">Projects</h6>
                            <c:set var="myProjects" value="${sessionScope.MY_PROJECTS}"/>
                            <c:if test="${not empty myProjects}">
                                <c:forEach var="project" varStatus="counter" items="${myProjects}">
                                    <a class="collapse-item" href="MainController?action=GroupShare&projectId=${project.projectID}&projectName=${project.projectName}">
                                        <i class="fas fa-solid fa-rectangle-list"></i>&emsp;
                                        <span>${project.projectName}</span>
                                    </a>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                </div>


                <!-- Divider -->
                <hr class="sidebar-divider">
                <div class="nav-item">
                    <a class="nav-link" href="MainController?action=Recent&projectId=${param.projectId}">
                        <i class="fas fa-fw fa-clock"></i>&emsp;
                        <span>Recent</span>
                    </a>
                </div>

                <div class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fas fa-fw fa-share-from-square"></i>&emsp;
                        <span>Shared with me</span>
                    </a>
                </div>

                <div class="nav-item">
                    <a class="nav-link" href="MainController?action=Starred&projectId=${param.projectId}">
                        <i class="fas fa-fw fa-star"></i>&emsp;
                        <span>Starred</span>
                    </a>

                </div><div class="nav-item">
                    <a class="nav-link" href="MainController?action=viewProject">
                        <i class="fas fa-fw fa-diagram-project"></i>&emsp;
                        <span>Project</span>
                    </a>

                </div>

                <div class="nav-item">
                    <a class="nav-link" href="MainController?action=Bin&projectId=${param.projectId}">
                        <i class="fas fa-fw fa-trash"></i>&emsp;
                        <span>Bin</span>
                    </a>
                </div>

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">
                <div class="nav-item">
                    <a class="nav-link" href="#">
                        <i class="fas fa-fw fa-comment-dots"></i>&emsp;
                        <span>Feedback</span>
                    </a>
                </div>

                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">

                            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                            <li class="nav-item dropdown no-arrow d-sm-none">
                                <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-search fa-fw"></i>
                                </a>
                                <!-- Dropdown - Messages -->
                                <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                                    <form class="form-inline mr-auto w-100 navbar-search">
                                        <div class="input-group">
                                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                                            <div class="input-group-append">
                                                <button class="btn btn-primary" type="button">
                                                    <i class="fas fa-search fa-sm"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>

                            <!-- Nav Item - Alerts -->
                            <li class="nav-item dropdown no-arrow mx-1">
                                <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-bell fa-fw"></i>
                                    <!-- Counter - Alerts -->
                                    <span class="badge badge-danger badge-counter">3+</span>
                                </a>
                                <!-- Dropdown - Alerts -->
                                <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                                    <h6 class="dropdown-header">
                                        Alerts Center
                                    </h6>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="mr-3">
                                            <div class="icon-circle bg-primary">
                                                <i class="fas fa-file-alt text-white"></i>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="small text-gray-500">December 12, 2019</div>
                                            <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="mr-3">
                                            <div class="icon-circle bg-success">
                                                <i class="fas fa-donate text-white"></i>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="small text-gray-500">December 7, 2019</div>
                                            $290.29 has been deposited into your account!
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="mr-3">
                                            <div class="icon-circle bg-warning">
                                                <i class="fas fa-exclamation-triangle text-white"></i>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="small text-gray-500">December 2, 2019</div>
                                            Spending Alert: We've noticed unusually high spending for your account.
                                        </div>
                                    </a>
                                    <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                                </div>
                            </li>

                            <!-- Nav Item - Messages -->
                            <li class="nav-item dropdown no-arrow mx-1">
                                <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <i class="fas fa-envelope fa-fw"></i>
                                    <!-- Counter - Messages -->
                                    <span class="badge badge-danger badge-counter">7</span>
                                </a>
                                <!-- Dropdown - Messages -->
                                <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
                                    <h6 class="dropdown-header">
                                        Message Center
                                    </h6>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="dropdown-list-image mr-3">
                                            <img class="rounded-circle" src="https://source.unsplash.com/fn_BT9fwg_E/60x60" alt="">
                                            <div class="status-indicator bg-success"></div>
                                        </div>
                                        <div class="font-weight-bold">
                                            <div class="text-truncate">Hi there! I am wondering if you can help me with a problem I've been having.</div>
                                            <div class="small text-gray-500">Emily Fowler · 58m</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="dropdown-list-image mr-3">
                                            <img class="rounded-circle" src="https://source.unsplash.com/AU4VPcFN4LE/60x60" alt="">
                                            <div class="status-indicator"></div>
                                        </div>
                                        <div>
                                            <div class="text-truncate">I have the photos that you ordered last month, how would you like them sent to you?</div>
                                            <div class="small text-gray-500">Jae Chun · 1d</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="dropdown-list-image mr-3">
                                            <img class="rounded-circle" src="https://source.unsplash.com/CS2uCrpNzJY/60x60" alt="">
                                            <div class="status-indicator bg-warning"></div>
                                        </div>
                                        <div>
                                            <div class="text-truncate">Last month's report looks great, I am very happy with the progress so far, keep up the good work!</div>
                                            <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item d-flex align-items-center" href="#">
                                        <div class="dropdown-list-image mr-3">
                                            <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60" alt="">
                                            <div class="status-indicator bg-success"></div>
                                        </div>
                                        <div>
                                            <div class="text-truncate">Am I a good boy? The reason I ask is because someone told me that people say this to all dogs, even if they aren't good...</div>
                                            <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                        </div>
                                    </a>
                                    <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                                </div>
                            </li>

                            <div class="topbar-divider d-none d-sm-block"></div>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.fullName}<br/>
                                        <span style="float: right; color: #4e73df">${user.roleID=="AD"?"Admin":"File Organizer"}</span></span>
                                    <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60">
                                </a>
                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                    <a class="dropdown-item" href="profile.jsp">
                                        <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Profile
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Settings
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Activity Log
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Logout
                                    </a>
                                </div>
                            </li>

                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <div class="col-lg-6" style="margin: auto">
                            <h1 class="h3 mb-4 text-gray-800">Report this item for violating Terms of Service</h1>
                        </div>
                        <div class="row">

                            <div class="col-lg-6" style="margin: auto">

                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">File to report</h6>
                                    </div>
                                    <form action="MainController" method="POST">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Owner</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${requestScope.OWNER}
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">File name</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                ${requestScope.FILE_NAME}
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="card-header py-3">
                                            <h6 class="m-0 font-weight-bold text-primary">Choose type of policy violated</h6>
                                        </div>
                                        <div class="container">
                                                <ul id="option-ul">
                                                    <li>
                                                        <input type="radio" id="f-option" name="selector" value="Spam">
                                                        <label for="f-option">Spam</label>

                                                        <div class="check"></div>
                                                        <p>Spam includes, but is not limited to, unwanted, irrelevant, promotional or commercial content; unwanted or mass solicitation; content that is created by an automated program; or repetitive content.</p>
                                                    </li>
                                                    <li>
                                                        <input type="radio" id="s-option" name="selector" value="Malware">
                                                        <label for="s-option">Malware</label>

                                                        <div class="check"><div class="inside"></div></div>
                                                        <p>The transmission of malware, viruses, destructive code or anything that may harm or interfere with the operation of the networks, servers or other infrastructure.</p>
                                                    </li>
                                                    <li>
                                                        <input type="radio" id="t-option" name="selector" value="Phishing">
                                                        <label for="t-option">Phishing</label>

                                                        <div class="check"><div class="inside"></div></div>
                                                        <p>Soliciting or collecting sensitive data, including but not limited to passwords, financial details and national insurance numbers.</p>
                                                    </li>
                                                    <li>
                                                        <input type="radio" id="a-option" name="selector" value="Violence">
                                                        <label for="a-option">Violence</label>

                                                        <div class="check"><div class="inside"></div></div>
                                                        <p>Posting violent or gory content that's primarily intended to be shocking, sensational or gratuitous.</p>
                                                    </li>
                                                    <li>
                                                        <input type="radio" id="b-option" name="selector" value="Hate speech">
                                                        <label for="b-option">Hate speech</label>

                                                        <div class="check"><div class="inside"></div></div>
                                                        <p>Hate speech is content that has the primary purpose of inciting hatred against an individual or group on the basis of their race, religion, disability, age or any other characteristic that is associated with systemic discrimination.</p>
                                                    </li>
                                                    <li>
                                                        <input type="radio" id="c-option" name="selector" value="Terrorist content">
                                                        <label for="c-option">Terrorist content</label>

                                                        <div class="check"><div class="inside"></div></div>
                                                        <p>Content that promotes terrorist acts, incites violence or celebrates terrorist attacks.</p>
                                                    </li>
                                                    <li>
                                                        <input type="radio" id="d-option" name="selector" value="Harassment, bullying and threats">
                                                        <label for="d-option">Harassment, bullying and threats</label>

                                                        <div class="check"><div class="inside"></div></div>
                                                        <p>Threatening someone with serious harm, sexualizing someone in an unwanted way, exposing private information of someone else that could be used to carry out threats, or harassing in other ways.</p>
                                                    </li>
                                                    <li>
                                                        <input type="radio" id="e-option" name="selector" value="Sexually explicit material">
                                                        <label for="e-option">Sexually explicit material</label>

                                                        <div class="check"><div class="inside"></div></div>
                                                        <p>Sexually explicit images or videos or any material that promotes or depicts unlawful or inappropriate sexual acts with children or animals.</p>
                                                    </li>
                                                    <li>
                                                        <input type="radio" id="g-option" name="selector" value="Confidential information">
                                                        <label for="g-option">Confidential information</label>

                                                        <div class="check"><div class="inside"></div></div>
                                                        <p>Unauthorized publishing of people's private and confidential information, such as credit card numbers, national insurance numbers, driver's license and other license numbers, or any other information that is not publicly accessible.</p>
                                                    </li>
                                                    <li>
                                                        <input type="radio" id="h-option" name="selector" value="Illegal activities">
                                                        <label for="h-option">Illegal activities</label>

                                                        <div class="check"><div class="inside"></div></div>
                                                        <p>For instance, do not facilitate the promotion or sale of regulated or illegal drugs.</p>
                                                    </li>
                                                </ul>
                                        </div>
                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <input type="hidden" name="projectId" value="${requestScope.PROJECT_ID}" />
                                                <input type="hidden" name="fileId" value="${requestScope.FILE_ID}" />
                                                <button class="btn btn-info" name="action" value="SubmitReport">Submit report</button>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>

                        </div>
                        </form>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2019</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="MainController?action=Logout">Logout</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

    </body>

</html>
