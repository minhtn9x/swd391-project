<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                display: none;
            }

            a:hover {
                text-decoration: none;
            }

            .folder {
                padding: 1.5em 4em 1.5em 0;
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
                <div class="nav-item active">
                    <a class="nav-link active" href="MainController?action=UserHome">
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
                    <a class="nav-link" href="MainController?action=RecentUser">
                        <i class="fas fa-fw fa-clock"></i>&emsp;
                        <span>Recent</span>
                    </a>
                </div>

                <div class="nav-item">
                    <a class="nav-link" href="MainController?action=SharedWithMe">
                        <i class="fas fa-fw fa-share-from-square"></i>&emsp;
                        <span>Shared with me</span>
                    </a>
                </div>

                <div class="nav-item">
                    <a class="nav-link" href="MainController?action=StarredUser">
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
                    <a class="nav-link" href="MainController?action=BinUser">
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
                <input type="file" id="filepicker" name="fileList" webkitdirectory multiple />
                <form id="uploadFileForm" action="MainController" method="POST" enctype="multipart/form-data">
                    <input type="file" id="filepicker2" name="files" multiple="multiple" />
                    <input type="hidden" id="currentFolderId" name="currentFolderId" value="${param.folderId}"/>
                </form>
                <button form="uploadFileForm" id="uploadFileBtn" type="submit" name="action" value="UploadFile" style="opacity: 0"></button>
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
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">
                                        ${user.fullName}<br/>
                                        <span style="float: right; color: #4e73df">${user.roleID=="AD"?"Admin":"File Organizer"}</span>
                                    </span>

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
                        <c:set var="breadcrumbs" value="${requestScope.BREADCRUMBS}"/>
                        <c:if test="${not empty breadcrumbs}">
                            <ol class="breadcrumb h5 mb-4 text-gray-800">
                                <c:forEach var="breadcrumb" varStatus="counter" items="${breadcrumbs}">
                                    <c:if test="${not counter.last}">
                                        <li class="breadcrumb-item"><a href="${breadcrumb.key}">${breadcrumb.value}</a></li>
                                        </c:if>
                                        <c:if test="${counter.last}">
                                        <li class="breadcrumb-item active">${breadcrumb.value}</li>
                                        </c:if>
                                    </c:forEach>
                            </ol>
                        </c:if>
                        <c:if test="${empty breadcrumbs}">
                            <ol class="breadcrumb h5 mb-4 text-gray-800">
                                <li class="breadcrumb-item">My Document</li>
                            </ol>
                        </c:if>

                        <form id="folderClickForm" action="MainController" method="POST">
                            <input type="hidden" id="folderName" name="folderName"/>
                        </form>
                        <button id="folderClick" form="folderClickForm" name="action" value="UserHome" type="submit" style="opacity: 0"></button>
                        <h2 class="h4 mb-4 text-gray-800">Folder</h2>
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
                                                                <a class="folder" href="MainController?action=UserHome&folderId=${folder.id}">
                                                                    <span>${folder.name}</span>
                                                                </a>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <div class="dropdown">
                                                                    <button class="dropbtn">
                                                                        <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                    </button>
                                                                    <div class="dropdown-content">
                                                                        <a href="MainController?action=GoToShareFolder&folderId=${folder.id}&folderName=${folder.name}">
                                                                            <i class="fas fa-solid fa-user-plus"></i>&ensp;
                                                                            Share
                                                                        </a>
                                                                        <a href="MainController?action=StarFolder&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-star"></i>&ensp;
                                                                            Add to Starred
                                                                        </a>
                                                                        <a onclick="document.getElementById('renameInput').value = '${folder.name}';
                                                                                document.getElementById('renameId').value = '${folder.id}'" 
                                                                           style="color: black; cursor: pointer"
                                                                           data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                                                            <i class="fas fa-solid fa-pen"></i>&ensp;
                                                                            Rename
                                                                        </a>
                                                                        <hr style="margin: 0">
                                                                        <a href="MainController?action=RemoveFolder&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                            Remove
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
                                                                <a class="folder" href="MainController?action=UserHome&folderId=${folder.id}">
                                                                    <span>${folder.name}</span>
                                                                </a>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <div class="dropdown">
                                                                    <button class="dropbtn">
                                                                        <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                    </button>
                                                                    <div class="dropdown-content">
                                                                        <a href="MainController?action=GoToShareFolder&folderId=${folder.id}&folderName=${folder.name}">
                                                                            <i class="fas fa-solid fa-user-plus"></i>&ensp;
                                                                            Share
                                                                        </a>
                                                                        <a href="MainController?action=StarFolder&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-star"></i>&ensp;
                                                                            Add to Starred
                                                                        </a>
                                                                        <a onclick="document.getElementById('renameInput').value = '${folder.name}';
                                                                                document.getElementById('renameId').value = '${folder.id}'" 
                                                                           style="color: black; cursor: pointer"
                                                                           data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                                                            <i class="fas fa-solid fa-pen"></i>&ensp;
                                                                            Rename
                                                                        </a>
                                                                        <hr style="margin: 0">
                                                                        <a href="MainController?action=RemoveFolder&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                            Remove
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
                                                                <a class="folder" href="MainController?action=UserHome&folderId=${folder.id}">
                                                                    <span>${folder.name}</span>
                                                                </a>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <div class="dropdown">
                                                                    <button class="dropbtn">
                                                                        <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                    </button>
                                                                    <div class="dropdown-content">
                                                                        <a href="MainController?action=GoToShareFolder&folderId=${folder.id}&folderName=${folder.name}">
                                                                            <i class="fas fa-solid fa-user-plus"></i>&ensp;
                                                                            Share
                                                                        </a>
                                                                        <a href="MainController?action=StarFolder&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-star"></i>&ensp;
                                                                            Add to Starred
                                                                        </a>
                                                                        <a onclick="document.getElementById('renameInput').value = '${folder.name}';
                                                                                document.getElementById('renameId').value = '${folder.id}'" 
                                                                           style="color: black; cursor: pointer"
                                                                           data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                                                            <i class="fas fa-solid fa-pen"></i>&ensp;
                                                                            Rename
                                                                        </a>
                                                                        <hr style="margin: 0">
                                                                        <a href="MainController?action=RemoveFolder&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                            Remove
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
                                                                <a class="folder" href="MainController?action=UserHome&folderId=${folder.id}">
                                                                    <span>${folder.name}</span>
                                                                </a>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <div class="dropdown">
                                                                    <button class="dropbtn">
                                                                        <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                    </button>
                                                                    <div class="dropdown-content">
                                                                        <a href="MainController?action=GoToShareFolder&folderId=${folder.id}&folderName=${folder.name}">
                                                                            <i class="fas fa-solid fa-user-plus"></i>&ensp;
                                                                            Share
                                                                        </a>
                                                                        <a href="MainController?action=StarFolder&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-star"></i>&ensp;
                                                                            Add to Starred
                                                                        </a>
                                                                        <a onclick="document.getElementById('renameInput').value = '${folder.name}';
                                                                                document.getElementById('renameId').value = '${folder.id}'" 
                                                                           style="color: black; cursor: pointer"
                                                                           data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                                                            <i class="fas fa-solid fa-pen"></i>&ensp;
                                                                            Rename
                                                                        </a>
                                                                        <hr style="margin: 0">
                                                                        <a href="MainController?action=RemoveFolder&folderId=${folder.id}">
                                                                            <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                            Remove
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
                                                            <a class="folder" href="MainController?action=UserHome&folderId=${folder.id}">
                                                                <span>${folder.name}</span>
                                                            </a>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <div class="dropdown">
                                                                <button class="dropbtn">
                                                                    <i class="fa-solid fa-ellipsis-vertical"></i>
                                                                </button>
                                                                <div class="dropdown-content">
                                                                    <a href="MainController?action=GoToShareFolder&folderId=${folder.id}&folderName=${folder.name}">
                                                                        <i class="fas fa-solid fa-user-plus"></i>&ensp;
                                                                        Share
                                                                    </a>
                                                                    <a href="MainController?action=StarFolder&folderId=${folder.id}">
                                                                        <i class="fas fa-solid fa-star"></i>&ensp;
                                                                        Add to Starred
                                                                    </a>
                                                                    <a onclick="document.getElementById('renameInput').value = '${folder.name}';
                                                                            document.getElementById('renameId').value = '${folder.id}'" 
                                                                       style="color: black; cursor: pointer"
                                                                       data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                                                        <i class="fas fa-solid fa-pen"></i>&ensp;
                                                                        Rename
                                                                    </a>
                                                                    <hr style="margin: 0">
                                                                    <a href="MainController?action=RemoveFolder&folderId=${folder.id}">
                                                                        <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                        Remove
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
                    <c:if test="${empty folders}">
                        <div class="row">
                            <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                <span>No items</span>
                            </div>
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
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="submit" class="btn btn-primary" name="action" value="Rename Folder">OK</button>
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
                                        <input id="renameFileInput" type="text" placeholder="Enter Name" name="fileName" required />
                                        <input id="renameFileId" type="hidden" name="folderId" value="" />
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="submit" class="btn btn-primary" name="action" value="Rename Folder">OK</button>
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
                                <form action="MainController" id="newFolderForm">
                                    <div class="modal-body">
                                        <input id="renameInput" class="form-group" type="text" placeholder="Enter folder name" name="folderName" />
                                        <input type="hidden" name="userId" value="${user.userID}" />
                                        <input type="hidden" name="parentFolderId" value="${param.folderId}" />
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                        <button type="submit" class="btn btn-primary" name="action" value="Create Folder User">Create</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <c:set var="files" value="${requestScope.FILES}"/>
                    <h2 class="h4 mb-4 text-gray-800">File</h2>
                    <!-- Table -->

                    <c:if test="${not empty files}">
                        <div class="card mb-4">
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Last modified</th>
                                            <th>File size</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Name</th>
                                            <th>Last modified</th>
                                            <th>File size</th>
                                        </tr>
                                    </tfoot>
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
                                                            <a href="${file.webViewLink}">
                                                                <i class="fas fa-regular fa-eye"></i>&ensp;
                                                                Preview
                                                            </a>
                                                            <hr style="margin: 0">
                                                            <a href="MainController?action=GoToShareFile&fileId=${file.id}&fileName=${file.name}">
                                                                <i class="fas fa-solid fa-user-plus"></i>&ensp;
                                                                Share
                                                            </a>
                                                            <a href="MainController?action=StarFile&fileId=${file.id}">
                                                                <i class="fas fa-solid fa-star"></i>&ensp;
                                                                Add to Starred
                                                            </a>
                                                            <a onclick="document.getElementById('renameFileInput').value = '${file.name}';
                                                                    document.getElementById('renameFileId').value = '${file.id}'" 
                                                               style="color: black; cursor: pointer"
                                                               data-bs-toggle="modal" data-bs-target="#staticBackdropFile">
                                                                <i class="fas fa-solid fa-pen"></i>&ensp;
                                                                Rename
                                                            </a>
                                                            <hr style="margin: 0">
                                                            <a href="MainController?action=CopyFile&fileId=${file.id}&fileName=${file.name}">
                                                                <i class="fas fa-solid fa-copy"></i>&ensp;
                                                                Make a copy
                                                            </a>
                                                            <a href="MainController?action=DownloadFile&fileId=${file.id}&fileName=${file.name}">
                                                                <i class="fas fa-solid fa-download"></i>&ensp;
                                                                Download
                                                            </a>
                                                            <hr style="margin: 0">
                                                            <a href="MainController?action=RemoveFile&folderId=${param.folderId}&fileId=${file.id}">
                                                                <i class="fas fa-solid fa-trash"></i>&ensp;
                                                                Remove
                                                            </a>
                                                        </div>
                                                    </div>
                                                </td>

                                                </td>
                                                <td><fmt:formatDate value="${file.modifiedTime}" pattern="dd-MM-yyyy HH:mm" /></td>
                                                <td>${file.size}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <br/><br/><br/><br/><br/><br/><br/><br/>
                    </c:if>
                    <c:if test="${empty files}">
                        <div class="row">
                            <div class="col-lg-2" style="border-style: solid;padding: 20px; margin: 20px;">
                                <span>No items</span>
                            </div>
                        </div>
                    </c:if>

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
    <script>
        (function () {

            "use strict";

            //////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////
            //
            // H E L P E R    F U N C T I O N S
            //
            //////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////

            /**
             * Function to check if we clicked inside an element with a particular class
             * name.
             * 
             * @param {Object} e The event
             * @param {String} className The class name to check against
             * @return {Boolean}
             */
            function clickInsideElement(e, className) {
                var el = e.srcElement || e.target;

                if (el.classList.contains(className)) {
                    return el;
                } else {
                    while (el = el.parentNode) {
                        if (el.classList && el.classList.contains(className)) {
                            return el;
                        }
                    }
                }

                return false;
            }

            /**
             * Get's exact position of event.
             * 
             * @param {Object} e The event passed in
             * @return {Object} Returns the x and y position
             */
            function getPosition(e) {
                var posx = 0;
                var posy = 0;

                if (!e)
                    var e = window.event;

                if (e.pageX || e.pageY) {
                    posx = e.pageX;
                    posy = e.pageY;
                } else if (e.clientX || e.clientY) {
                    posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
                    posy = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
                }

                return {
                    x: posx,
                    y: posy
                }
            }

            //////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////
            //
            // C O R E    F U N C T I O N S
            //
            //////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////

            /**
             * Variables.
             */
            var contextMenuClassName = "context-menu";
            var contextMenuItemClassName = "context-menu__item";
            var contextMenuLinkClassName = "context-menu__link";
            var contextMenuActive = "context-menu--active";

            var taskItemClassName = "task";
            var taskItemInContext;

            var clickCoords;
            var clickCoordsX;
            var clickCoordsY;

            var menu = document.querySelector("#context-menu");
            var menuItems = menu.querySelectorAll(".context-menu__item");
            var menuState = 0;
            var menuWidth;
            var menuHeight;
            var menuPosition;
            var menuPositionX;
            var menuPositionY;

            var windowWidth;
            var windowHeight;

            /**
             * Initialise our application's code.
             */
            function init() {
                contextListener();
                clickListener();
                keyupListener();
                resizeListener();
            }

            /**
             * Listens for contextmenu events.
             */
            function contextListener() {
                document.addEventListener("contextmenu", function (e) {
                    taskItemInContext = clickInsideElement(e, taskItemClassName);

                    if (taskItemInContext) {
                        e.preventDefault();
                        toggleMenuOn();
                        positionMenu(e);
                    } else {
                        taskItemInContext = null;
                        toggleMenuOff();
                    }
                });
            }

            /**
             * Listens for click events.
             */
            function clickListener() {
                document.addEventListener("click", function (e) {
                    var clickeElIsLink = clickInsideElement(e, contextMenuLinkClassName);

                    if (clickeElIsLink) {
                        e.preventDefault();
                        menuItemListener(clickeElIsLink);
                    } else {
                        var button = e.which || e.button;
                        if (button === 1) {
                            toggleMenuOff();
                        }
                    }
                });
            }

            /**
             * Listens for keyup events.
             */
            function keyupListener() {
                window.onkeyup = function (e) {
                    if (e.keyCode === 27) {
                        toggleMenuOff();
                    }
                }
            }

            /**
             * Window resize event listener
             */
            function resizeListener() {
                window.onresize = function (e) {
                    toggleMenuOff();
                };
            }

            /**
             * Turns the custom context menu on.
             */
            function toggleMenuOn() {
                if (menuState !== 1) {
                    menuState = 1;
                    menu.classList.add(contextMenuActive);
                }
            }

            /**
             * Turns the custom context menu off.
             */
            function toggleMenuOff() {
                if (menuState !== 0) {
                    menuState = 0;
                    menu.classList.remove(contextMenuActive);
                }
            }

            /**
             * Positions the menu properly.
             * 
             * @param {Object} e The event
             */
            function positionMenu(e) {
                clickCoords = getPosition(e);
                clickCoordsX = clickCoords.x;
                clickCoordsY = clickCoords.y;

                menuWidth = menu.offsetWidth + 4;
                menuHeight = menu.offsetHeight + 4;

                windowWidth = window.innerWidth;
                windowHeight = window.innerHeight;

                if ((windowWidth - clickCoordsX) < menuWidth) {
                    menu.style.left = windowWidth - menuWidth + "px";
                } else {
                    menu.style.left = clickCoordsX + "px";
                }

                if ((windowHeight - clickCoordsY) < menuHeight) {
                    menu.style.top = windowHeight - menuHeight + "px";
                } else {
                    menu.style.top = clickCoordsY + "px";
                }
            }

            /**
             * Dummy action function that logs an action when a menu item link is clicked
             * 
             * @param {HTMLElement} link The link that was clicked
             */
            function menuItemListener(link) {
                console.log("Task ID - " + taskItemInContext.getAttribute("data-id") + ", Task action - " + link.getAttribute("data-action"));
                toggleMenuOff();
            }

            /**
             * Run the app.
             */
            init();

        })();



    </script>
    <script>
        oncontextmenu = (e) => {
            e.preventDefault();
            var menu = document.createElement("div");
            menu.id = "contextMenu";
            menu.style = `top:${e.pageY - 10}px;left:${e.pageX - 40}px`;
            menu.onmouseleave = () => contextMenu.outerHTML = '';
            menu.innerHTML = "<p onclick='alert(`1 It is!`)'>Choose 1</p><p onclick='alert(`2 It is!`)'>Choose 2</p><p onclick='alert(`3 It is!`)'>Choose 3</p><p onclick='alert(`Thank you!`)'>No thanks</p>";
            document.body.appendChild(menu);
        };
    </script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</body>
</html>