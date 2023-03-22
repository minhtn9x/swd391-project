<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Home</title>

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Right click Menu-->
        <link rel="stylesheet" href="css/rmenu.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/rmenu.js"></script>

        <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous">
        </script>

        <!-- Tables -->
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            #filepicker, #filepicker2 {
                opacity: 0;
            }

            a:hover {
                text-decoration: none;
            }
        </style>
        <style>
            .dropbtn {
                background-color: #4e73df;
                color: white;
                height: 30px;
                width: 30px;
                border: none;
                cursor: pointer;
                border-radius: 10px;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
                z-index: 1;
                width: 200px;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {
                background-color: #f1f1f1
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .dropdown:hover .dropbtn {
                background-color: #2e59d9;
            }

            #contextMenu {
                position: fixed;
                background: teal;
                color: white;
                cursor: pointer;
                border: 1px black solid
            }

            #contextMenu>p {
                padding: 0 1rem;
                margin: 0
            }

            #contextMenu>p:hover {
                background: black;
                color: white;
            }
        </style>
        <style>
            .form-popup {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                border: 3px solid #f1f1f1;
                z-index: 9;
                box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            }
            .form-container {
                max-width: 300px;
                padding: 10px;
                background-color: white;
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

                <!-- Nav Item - Pages Collapse Menu -->
                <div class="nav-item">
                    <a class="nav-link" href="MainController?action=UserHome">
                        <i class="fas fa-fw fa-hard-drive"></i>&emsp;
                        <span>My Document</span>
                    </a>
                </div>

                <div class="nav-item">
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
                                    <a class="collapse-item" href="MainController?action=GroupShare&projectId=${project.projectID}">
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
                        <span>Recent </span>
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

                <div class="nav-item active">
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
                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>
                <input type="file" id="filepicker" name="fileList" webkitdirectory multiple />
                <form id="uploadFileForm" action="MainController" method="POST" enctype="multipart/form-data">
                    <input type="file" id="filepicker2" name="files" multiple="multiple" />
                    <input type="hidden" id="currentFolderId" name="currentFolderId" value="${param.folderId}"/>
                    <input type="hidden" id="currentProjectId" name="currentProjectId" value="${param.projectId}"/>
                </form>
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

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">                                        
                            <div class="topbar-divider d-none d-sm-block"></div>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">
                                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${user.fullName}<br/>
                                        <span style="float: right; color: #4e73df">${user.roleID=="AD"?"Admin":"File Organizer"}</span></span>
                                    <img class="img-profile rounded-circle" src=${user.roleID=="AD"?"images/avatar_admin.jpg":"images/avatar.jpg"}>
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
                        <form id="folderClickForm" action="MainController" method="POST">
                            <input type="hidden" id="folderName" name="folderName"/>
                        </form>
                        <button id="folderClick" form="folderClickForm" name="action" value="UserHome" type="submit" style="opacity: 0"></button>
                        <h2 class="h2 mb-4 text-gray-900">Bin</h2>
                        <hr class="d-none d-md-block"/>
                        <c:set var="projects" value="${requestScope.PROJECTS}"/>
                        <c:if test="${empty projects}">
                            <h2 class="h3 mb-4 text-gray-800">Folder</h2>
                            <c:set var="foldersDB" value="${requestScope.FOLDERS_DB}"/>
                            <c:if test="${not empty foldersDB}">
                                <div class="card mb-4">
                                    <div class="card-body">
                                        <c:forEach var="folder" varStatus="counter" items="${foldersDB}">
                                            <c:if test="${not counter.last}">
                                                <c:if test="${counter.count %5 == 1}">
                                                    <div class="row">
                                                        <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                                            <div class="row">
                                                                <div class="col-lg-9">
                                                                    <a>
                                                                        <span>${folder.name}</span>
                                                                    </a>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <div class="dropdown">
                                                                        <button class="dropbtn">
                                                                            <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                        </button>
                                                                        <div class="dropdown-content">
                                                                            <a href="MainController?action=RestoreFolderUser&folderId=${folder.id}">
                                                                                <i class="fas fa-solid fa-rotate-left"></i>&ensp;
                                                                                Restore
                                                                            </a>
                                                                            <a onclick="document.getElementById('deleteFolderName').innerHTML = '${folder.name}';
                    document.getElementById('deleteFolderId').value = '${folder.id}'" 
                                                                               style="color: black; cursor: pointer"
                                                                               data-bs-toggle="modal" data-bs-target="#staticBackdropFolderDelete">
                                                                                <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                                Delete forever
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${counter.count %5 ne 1 && counter.count %5 ne 0}">
                                                        <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                                            <div class="row">
                                                                <div class="col-lg-9">
                                                                    <a>
                                                                        <span>${folder.name}</span>
                                                                    </a>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <div class="dropdown">
                                                                        <button class="dropbtn">
                                                                            <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                        </button>
                                                                        <div class="dropdown-content">
                                                                            <a href="MainController?action=RestoreFolderUser&folderId=${folder.id}">
                                                                                <i class="fas fa-solid fa-rotate-left"></i>&ensp;
                                                                                Restore
                                                                            </a>
                                                                            <a onclick="document.getElementById('deleteFolderName').innerHTML = '${folder.name}';
                                                                                    document.getElementById('deleteFolderId').value = '${folder.id}'" 
                                                                               style="color: black; cursor: pointer"
                                                                               data-bs-toggle="modal" data-bs-target="#staticBackdropFolderDelete">
                                                                                <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                                Delete forever
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${counter.count %5 == 0}">
                                                        <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                                            <div class="row">
                                                                <div class="col-lg-9">
                                                                    <a>
                                                                        <span>${folder.name}</span>
                                                                    </a>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <div class="dropdown">
                                                                        <button class="dropbtn">
                                                                            <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                        </button>
                                                                        <div class="dropdown-content">
                                                                            <a href="MainController?action=RestoreFolderUser&folderId=${folder.id}">
                                                                                <i class="fas fa-solid fa-rotate-left"></i>&ensp;
                                                                                Restore
                                                                            </a>
                                                                            <a onclick="document.getElementById('deleteFolderName').innerHTML = '${folder.name}';
                                                                                    document.getElementById('deleteFolderId').value = '${folder.id}'" 
                                                                               style="color: black; cursor: pointer"
                                                                               data-bs-toggle="modal" data-bs-target="#staticBackdropFolderDelete">
                                                                                <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                                Delete forever
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${counter.last}">
                                                <c:if test="${counter.count %5 == 1}">
                                                    <div class="row">
                                                        <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                                            <div class="row">
                                                                <div class="col-lg-9">
                                                                    <a>
                                                                        <span>${folder.name}</span>
                                                                    </a>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <div class="dropdown">
                                                                        <button class="dropbtn">
                                                                            <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                        </button>
                                                                        <div class="dropdown-content">
                                                                            <a href="MainController?action=RestoreFolderUser&folderId=${folder.id}">
                                                                                <i class="fas fa-solid fa-rotate-left"></i>&ensp;
                                                                                Restore
                                                                            </a>
                                                                            <a onclick="document.getElementById('deleteFolderName').innerHTML = '${folder.name}';
                                                                                    document.getElementById('deleteFolderId').value = '${folder.id}'" 
                                                                               style="color: black; cursor: pointer"
                                                                               data-bs-toggle="modal" data-bs-target="#staticBackdropFolderDelete">
                                                                                <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                                Delete forever
                                                                            </a>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${counter.count %5 ne 1}">
                                                    <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                                        <div class="row">
                                                            <div class="col-lg-9">
                                                                <a>
                                                                    <span>${folder.name}</span>
                                                                </a>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <div class="dropdown">
                                                                    <button class="dropbtn">
                                                                        <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                    </button>
                                                                    <div class="dropdown-content">
                                                                        <a href="MainController?action=RestoreFolderUser&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-rotate-left"></i>&ensp;
                                                                            Restore
                                                                        </a>
                                                                        <a onclick="document.getElementById('deleteFolderName').innerHTML = '${folder.name}';
                                                                                document.getElementById('deleteFolderId').value = '${folder.id}'" 
                                                                           style="color: black; cursor: pointer"
                                                                           data-bs-toggle="modal" data-bs-target="#staticBackdropFolderDelete">
                                                                            <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                            Delete forever
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${not empty requestScope.ERROR_FOLDERS_DB}">
                            <div class="row">
                                <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                    <span>No items</span>
                                </div>
                            </div>
                        </c:if>
                        <c:set var="folders" value="${requestScope.FOLDERS}"/>
                        <c:if test="${not empty folders}">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <c:forEach var="folder" varStatus="counter" items="${folders}">
                                        <c:if test="${not counter.last}">
                                            <c:if test="${counter.count %5 == 1}">
                                                <div class="row">
                                                    <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                                        <div class="row">
                                                            <div class="col-lg-9">
                                                                <a>
                                                                    <span>${folder.name}</span>
                                                                </a>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <div class="dropdown">
                                                                    <button class="dropbtn">
                                                                        <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                    </button>
                                                                    <div class="dropdown-content">
                                                                        <a href="MainController?action=RestoreFolderUser&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-rotate-left"></i>&ensp;
                                                                            Restore
                                                                        </a>
                                                                        <a onclick="document.getElementById('deleteFolderName').innerHTML = '${folder.name}';
                                                                                document.getElementById('deleteFolderId').value = '${folder.id}'" 
                                                                           style="color: black; cursor: pointer"
                                                                           data-bs-toggle="modal" data-bs-target="#staticBackdropFolderDelete">
                                                                            <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                            Delete forever
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${counter.count %5 ne 1 && counter.count %5 ne 0}">
                                                    <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                                        <div class="row">
                                                            <div class="col-lg-9">
                                                                <a>
                                                                    <span>${folder.name}</span>
                                                                </a>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <div class="dropdown">
                                                                    <button class="dropbtn">
                                                                        <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                    </button>
                                                                    <div class="dropdown-content">
                                                                        <a href="MainController?action=RestoreFolderUser&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-rotate-left"></i>&ensp;
                                                                            Restore
                                                                        </a>
                                                                        <a onclick="document.getElementById('deleteFolderName').innerHTML = '${folder.name}';
                                                                                document.getElementById('deleteFolderId').value = '${folder.id}'" 
                                                                           style="color: black; cursor: pointer"
                                                                           data-bs-toggle="modal" data-bs-target="#staticBackdropFolderDelete">
                                                                            <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                            Delete forever
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${counter.count %5 == 0}">
                                                    <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                                        <div class="row">
                                                            <div class="col-lg-9">
                                                                <a>
                                                                    <span>${folder.name}</span>
                                                                </a>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <div class="dropdown">
                                                                    <button class="dropbtn">
                                                                        <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                    </button>
                                                                    <div class="dropdown-content">
                                                                        <a href="MainController?action=RestoreFolderUser&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-rotate-left"></i>&ensp;
                                                                            Restore
                                                                        </a>
                                                                        <a onclick="document.getElementById('deleteFolderName').innerHTML = '${folder.name}';
                                                                                document.getElementById('deleteFolderId').value = '${folder.id}'" 
                                                                           style="color: black; cursor: pointer"
                                                                           data-bs-toggle="modal" data-bs-target="#staticBackdropFolderDelete">
                                                                            <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                            Delete forever
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${counter.last}">
                                            <c:if test="${counter.count %5 == 1}">
                                                <div class="row">
                                                    <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                                        <div class="row">
                                                            <div class="col-lg-9">
                                                                <a>
                                                                    <span>${folder.name}</span>
                                                                </a>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <div class="dropdown">
                                                                    <button class="dropbtn">
                                                                        <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                    </button>
                                                                    <div class="dropdown-content">
                                                                        <a href="MainController?action=RestoreFolderUser&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-rotate-left"></i>&ensp;
                                                                            Restore
                                                                        </a>
                                                                        <a onclick="document.getElementById('deleteFolderName').innerHTML = '${folder.name}';
                                                                                document.getElementById('deleteFolderId').value = '${folder.id}'" 
                                                                           style="color: black; cursor: pointer"
                                                                           data-bs-toggle="modal" data-bs-target="#staticBackdropFolderDelete">
                                                                            <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                            Delete forever
                                                                        </a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <c:if test="${counter.count %5 ne 1}">
                                                <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                                    <div class="row">
                                                        <div class="col-lg-9">
                                                            <a>
                                                                <span>${folder.name}</span>
                                                            </a>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <div class="dropdown">
                                                                <button class="dropbtn">
                                                                    <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                </button>
                                                                <div class="dropdown-content">
                                                                    <a href="MainController?action=RestoreFolderUser&folderId=${folder.id}">
                                                                        <i class="fas fa-solid fa-rotate-left"></i>&ensp;
                                                                        Restore
                                                                    </a>
                                                                    <a onclick="document.getElementById('deleteFolderName').innerHTML = '${folder.name}';
                                                                            document.getElementById('deleteFolderId').value = '${folder.id}'" 
                                                                       style="color: black; cursor: pointer"
                                                                       data-bs-toggle="modal" data-bs-target="#staticBackdropFolderDelete">
                                                                        <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                        Delete forever
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${not empty requestScope.ERROR_FOLDERS}">
                        <div class="row">
                            <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                <span>No items</span>
                            </div>
                        </div>
                    </c:if>
                    <c:set var="files" value="${requestScope.FILES}"/>
                    <h2 class="h3 mb-4 text-gray-800">File</h2>
                    <!-- Table -->
                    <div>
                        <c:if test="${not empty files}">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <table class="table" id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Last modified</th>
                                                <th>File size</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="file" varStatus="counter" items="${files}">
                                                <tr>
                                                    <td>
                                                        <a href="${file.webViewLink}">${file.name}</a>
                                                        <div class="dropdown" style="float: right">
                                                            <button class="dropbtn">
                                                                <i class="fa-solid fa-ellipsis-vertical"></i>
                                                            </button>
                                                            <div class="dropdown-content">
                                                                <a href="MainController?action=RestoreFileUser&fileId=${file.id}">
                                                                    <i class="fas fa-solid fa-rotate-left"></i>&ensp;
                                                                    Restore
                                                                </a>
                                                                <a onclick="document.getElementById('deleteFileName').innerHTML = '${file.name}';
                                                                        document.getElementById('deleteFileId').value = '${file.id}'" 
                                                                   style="color: black; cursor: pointer"
                                                                   data-bs-toggle="modal" data-bs-target="#staticBackdropFileDelete">
                                                                    <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                    Delete forever
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td><fmt:formatDate value="${file.modifiedTime}" pattern="dd-MM-yyyy HH:mm" /></td>
                                                    <td>${file.size}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${empty files}">
                            <div class="row">
                                <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                    <span>No items</span>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </c:if>

                <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">Rename</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="border: none; background-color: white">X</button>
                            </div>
                            <form action="MainController">
                                <div class="modal-body">
                                    <input id="renameInput" type="text" placeholder="Enter Name" name="fileName" required />
                                    <input id="renameId" type="hidden" name="folderId" value="" />
                                    <input type="hidden" name="projectId" value="${param.projectId}" />
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary" name="action" value="RenameFolderInProject">OK</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="staticBackdropFile" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">Rename</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="border: none; background-color: white">X</button>
                            </div>
                            <form action="MainController">
                                <div class="modal-body">
                                    <input id="renameInput" type="text" placeholder="Enter Name" name="fileName" required />
                                    <input id="renameId" type="hidden" name="folderId" value="" />
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary" name="action" value="Rename File">OK</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="staticBackdropFolderDelete" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">Delete forever?</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="border: none; background-color: white">X</button>
                            </div>
                            <form action="MainController">
                                <div class="modal-body">
                                    '<span id="deleteFolderName"></span>' will be deleted forever and you won't be able to restore it.
                                    <input id="deleteFolderId" type="hidden" name="folderId" value="" />
                                    <input id="deleteFileId" type="hidden" name="projectId" value="${param.projectId}" />
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary" name="action" value="Delete FolderUser">Delete forever</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="staticBackdropFileDelete" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">Delete forever?</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="border: none; background-color: white">X</button>
                            </div>
                            <form action="MainController">
                                <div class="modal-body">
                                    '<span id="deleteFileName"></span>' will be deleted forever and you won't be able to restore it.
                                    <input id="deleteFileId" type="hidden" name="fileId" value="" />
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary" name="action" value="Delete FileUser">Delete forever</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="newFolder" data-bs-backdrop="static" data-bs-keyboard="true" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="staticBackdropLabel">New folder</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="border: none; background-color: white">X</button>
                            </div>
                            <form action="MainController">
                                <div class="modal-body">
                                    <input id="renameInput" type="text" placeholder="Enter folder name" name="folderName" required />
                                    <input type="hidden" name="projectId" value="${param.projectId}" />
                                    <input type="hidden" name="parentFolderId" value="${param.folderId}" />
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                    <button type="submit" class="btn btn-primary" name="action" value="Create Folder">Create</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <!-- Footer -->

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
                    <span aria-hidden="true">X</span>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
                                                                    function fileUploaderClicked() {
                                                                        document.getElementById("filepicker2").click();
                                                                    }
                                                                    document.getElementById("filepicker2").addEventListener("change", function () {
                                                                        document.getElementById("uploadFileBtn").click();
                                                                    }, false);

                                                                    function uploaderClicked() {
                                                                        document.getElementById("filepicker").click();
                                                                    }

                                                                    document.getElementById("filepicker").addEventListener("change", function (event) {
                                                                        let outputFolders = document.getElementById("folderList");
                                                                        let files = event.target.files;
                                                                        var folderList = "";
                                                                        for (var i = 0; i < files.length; i++) {
                                                                            folderList += files[i].webkitRelativePath + ";";
                                                                        }
                                                                        outputFolders.value = folderList;
                                                                        document.getElementById("uploadFolder").click();
                                                                    }, false);

                                                                    function folderClicked(folderId, folderName) {
                                                                        let outputFolderName = document.getElementById("folderName");
                                                                        let currentFolderId = document.getElementById("currentFolderId");
                                                                        currentFolderId.value = folderId;
                                                                        outputFolderName.value = folderName;
                                                                        document.getElementById("folderClick").click();
                                                                    }
</script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>

<script src="https://unpkg.com//sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="alert/dist/sweetalert.css">
<input type="hidden" id="error" value="${requestScope.ERROR}">
<script type="text/javascript">
                                                                    var error = document.getElementById("error").value;
                                                                    if (error === "ProjectID NOT FOUND") {
                                                                        swal("Oops!", "Please select the project you want to add files to it.", "error");
                                                                    } else if (error === "Create folder - ProjectID NOT FOUND") {
                                                                        swal("Oops!", "Please select the project where you want to create a folder.", "error");
                                                                    }
</script>
</body>
</html>