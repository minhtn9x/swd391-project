

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>


        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">




        <link href="vendor/fontawesome-free/css/fontawesome.min.css" rel="stylesheet" type="text/css">

        <title>Home</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">



        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />


        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />


    </head>

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <c:set var="user" value="${sessionScope.LOGIN_USER}"/>
            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">DMA</div>
                </a>
                <form id="uploadFolderForm" action="MainController" method="POST">
                    <input type="hidden" id="folderList" name="folderList"/>
                    <input type="hidden" id="folderId" name="folderId" value="${param.folderId}"/>
                    <input type="hidden" id="projectId" name="projectId" value="${param.projectId}"/>
                </form>
                <button id="uploadFolder" form="uploadFolderForm" name="action" value="UploadFolder" type="submit" style="opacity: 0"></button>

                <li class="nav-item">
                    <a class="nav-link" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
                        <i class="fas fa-solid fa-plus"></i>&emsp;
                        <span>New</span>
                    </a>
                    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">
                            <a class="collapse-item" 
                               data-bs-toggle="modal" data-bs-target="#newFolder">
                                <i class="fas fa-solid fa-folder-plus"></i>&emsp;
                                <span>Folder</span>
                            </a>
                            <hr style="margin-top: 0; margin-bottom: 0">
                            <a class="collapse-item" onclick="fileUploaderClicked()">
                                <i class="fas fa-solid fa-file-upload"></i>&emsp;&nbsp;
                                <span>File upload</span>
                            </a>
                            <a class="collapse-item" onclick="uploaderClicked()">
                                <i class="fas fa-solid fa-folder-open"></i>&emsp;
                                <span>Folder upload</span>
                            </a>
                        </div>
                    </div>
                </li>
                <!-- Divider -->
                <hr class="sidebar-divider my-0">
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

                <!--                    <div class="nav-item">
                                        <a class="nav-link" href="#">
                                            <i class="fas fa-fw fa-share-from-square"></i>&emsp;
                                            <span>Shared with me</span>
                                        </a>
                                    </div>-->

                <div class="nav-item">
                    <a class="nav-link" href="MainController?action=Starred&projectId=${param.projectId}">
                        <i class="fas fa-fw fa-star"></i>&emsp;
                        <span>Starred</span>
                    </a>
                </div>
                <div class="nav-item">
                    <a class="nav-link" href="MainController?action=viewProject">
                        <i class="fas fa-fw fa-diagram-project"></i>&emsp;
                        <span>Projects</span>
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
                    <a class="nav-link" href="MainController?action=feedBack">
                        <i class="fas fa-fw fa-comment-dots"></i>&emsp;
                        <span>Feedback</span>
                    </a>
                </div>
                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

                <button form="uploadFileForm" id="uploadFileBtn" type="submit" name="action" value="UploadFile" style="opacity: 0"></button>

                <button form="uploadFileForm" id="uploadFileBtn" type="submit" name="action" value="UploadFileToProject" style="opacity: 0"></button>

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

                        <!-- Topbar Search -->

                        

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">                                        
                            <div class="topbar-divider d-none d-sm-block"></div>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">



                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                        ${user.fullName}<br/>
                                        <span style="float: right; color: #4e73df">${user.roleID=="AD"?"Admin":"File Organizer"}</span>
                                    </span>

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


                        <h1 class="h3 mb-4 text-gray-800"><a style="text-decoration: none" href="MainController?action=viewProject"><i class="fas fa-arrow-left"></i>&emsp;</a>Edit Project</h1>

                        <div class="col-lg-9">

                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Project Info</h6>
                                </div>
                                <div class="card-body">
                                    <form action="MainController" method="POST">
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0" style="padding-top: 10px">Project Name</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">

                                                <input type="text" class="form-control" name="name" required="" value="${sessionScope.pr.projectName==null?requestScope.P.projectName:sessionScope.pr.projectName}">

                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0"  style="padding-top: 10px">Project Description</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">

                                                <input type="text" class="form-control" name="des" required=""value="${sessionScope.pr.projectDesc==null?requestScope.P.projectDesc:sessionScope.pr.projectDesc}">

                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0"  style="padding-top: 10px">Manager</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input type="text" class="form-control" name="manager" required=" "value="${sessionScope.pr.managerID==null?sessionScope.P.managerID:sessionScope.pr.projectDesc}">

                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0"  style="padding-top: 10px">Start Date</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input type="date" class="form-control" name="startpdate" required="" value="${sessionScope.pr.startDate==null?sessionScope.P.startDate:sessionScope.pr.startDate}">
                                            </div>
                                        </div>


                                        <div class="row mb-3">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0"  style="padding-top: 10px">End Date</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <input type="date" class="form-control" name="endpdate" required="" value="${sessionScope.pr.endDate==null?requestScope.P.endDate:sessionScope.pr.endDate}">

                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-3"></div>
                                            <div class="col-sm-9 text-secondary">

                                                <input type="hidden" name="id" value="${sessionScope.pr.projectID==null?requestScope.P.projectID:sessionScope.pr.projectID}">


                                                <input type="submit" class="btn btn-primary px-4" name="action" value="Save Project Profile"/>
                                            </div>
                                        </div>
                                    </form>

                                    <div class="card-header">
                                        <i class="fas fa-table me-1"></i>
                                        User in project: ${sessionScope.pr.projectName==null?requestScope.P.projectName:sessionScope.pr.projectName}

                                    </div>
                                    <div class="card-body">

                                        <form action="MainController" method="POST">


                                            <table id="datatablesSimple">
                                                <thead>
                                                    <tr>
                                                        <th>User ID</th>
                                                        <th>Start Date</th>
                                                        <th>End Date</th>
                                                        <th>Action</th>


                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>User ID</th>
                                                        <th>Start Date</th>
                                                        <th>End Date</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                    <c:forEach items="${requestScope.list==null?requestScope.L:requestScope.list}" var="x">
                                                        <tr>
                                                            <td>${x.userID}</td>
                                                            <td><input id="startDate" type="date" pattern="dd-mm-yyy" name="startdate" required="" min="" value="${x.startDate}"</td>
                                                            <td><input id="endDate" type="date" pattern="dd-mm-yyy" name="enddate" required="" min="" value="${x.endDate}"</td>                                          
                                                            <td>
                                                                <a class="btn btn-primary px-4" href="MainController?action=deleteMember&uid=${x.userID}&pid=${sessionScope.pr.projectID==null?requestScope.P.projectID:sessionScope.pr.projectID}">Delete</a> 
                                                                <input type="hidden" class="form-control" name="endpdate" required="" value="${sessionScope.pr.endDate==null?requestScope.P.endDate:sessionScope.pr.endDate}"/>
                                                                <input type="hidden" name="startpdate" required="" value="${sessionScope.pr.startDate==null?sessionScope.P.startDate:sessionScope.pr.startDate}"/>
                                                                <input type="hidden" name="pid" value="${sessionScope.pr.projectID==null?requestScope.P.projectID:sessionScope.pr.projectID}"/>
                                                                 <input type="hidden" name="uid" value="${x.userID}"/>
                                                                <input type="submit" class="btn btn-primary px-4" name="action" value="Edit User"/>
                                                            </td>
                                                          
                                                        </tr>
                                                    </c:forEach>
                                                <td class="text-danger ml-1">${requestScope.ERROR}</td>
                                                <a class="btn btn-primary px-4" href="MainController?action=addMember&pid=${sessionScope.pr.projectID==null?requestScope.P.projectID:sessionScope.pr.projectID}">Add member</a>
                                                </tbody>
                                            </table>
                                        </form>
                                    </div>


                                </div>
                            </div>

                        </div>

                    </div>

                </div>
                <!-- /.container-fluid -->

            </div>
        </div>

        <!-- Brand Buttons -->
        <!--                                <div class="card shadow mb-4">
                                            <div class="card-header py-3">
                                                <h6 class="m-0 font-weight-bold text-primary">Brand Buttons</h6>
                                            </div>
                                            <div class="card-body">
                                                <p>Google and Facebook buttons are available featuring each company's respective brand color. They are used on the user login and registration pages.</p>
                                                <p>You can create more custom buttons by adding a new color variable in the <code>_variables.scss</code> file and then using the Bootstrap button variant mixin to create a new style, as demonstrated in the <code>_buttons.scss</code> file.</p>
                                                <a href="#" class="btn btn-google btn-block"><i class="fab fa-google fa-fw"></i> .btn-google</a>
                                                <a href="#" class="btn btn-facebook btn-block"><i class="fab fa-facebook-f fa-fw"></i> .btn-facebook</a>
        
                                            </div>
                                        </div>-->

    </div>



</div>

<!-- Brand Buttons -->
<!--                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Brand Buttons</h6>
                                    </div>
                                    <div class="card-body">
                                        <p>Google and Facebook buttons are available featuring each company's respective brand color. They are used on the user login and registration pages.</p>
                                        <p>You can create more custom buttons by adding a new color variable in the <code>_variables.scss</code> file and then using the Bootstrap button variant mixin to create a new style, as demonstrated in the <code>_buttons.scss</code> file.</p>
                                        <a href="#" class="btn btn-google btn-block"><i class="fab fa-google fa-fw"></i> .btn-google</a>
                                        <a href="#" class="btn btn-facebook btn-block"><i class="fab fa-facebook-f fa-fw"></i> .btn-facebook</a>

                                    </div>
                                </div>-->

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
                    <span aria-hidden="true">�</span>
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
<script>
                                function togglePassword() {
                                    var x = document.getElementById("password");
                                    const type = x.getAttribute('type') === 'password' ? 'text' : 'password';
                                    x.type = type;
                                    var today = new Date();
                                    var dd = String(today.getDate()).padStart(2, '0');
                                    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
                                    var yyyy = today.getFullYear();

                                    today = yyyy + '-' + mm + '-' + dd;

                                    document.getElementById("startDate").setAttribute("min", today);
                                    document.getElementById("endDate").setAttribute("min", today);


                                }
</script>

<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
<script src="https://unpkg.com//sweetalert/dist/sweetalert.min.js"></script>
<script src="js/datatables-simple-demo.js"></script>
</body>


</html>
