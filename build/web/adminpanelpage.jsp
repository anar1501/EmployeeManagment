<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard</title>
        <link rel="stylesheet" href="/employee/admnp/css/bootstrap.min.css">
        <link rel="stylesheet" href="/employee/admnp/css/style.css">
    </head>

    <body>
        <header>
            <div id="sidebar" style="width: 300px;">
                <div class="d-flex flex-column px-3">
                    <div id="toggle-btn" class="p-2 mx-1 my-3" role="button">
                        <i class="fas fa-bars"></i>
                    </div>
                    <ul class="nav flex-column">
                        <li class="nav-item mb-1">
                            <a href="/employee/home" class="nav-link">
                                <i class="fas fa-home"></i>
                                <span class="section-name" style="display: inline-block;">&nbsp;Home</span>
                            </a>
                        </li>
                        <li class="nav-item mb-1">
                            <a href="index.html" class="nav-link active">
                                <i class="fas fa-chart-line"></i>
                                <span class="section-name" style="display: inline-block;">&nbsp;Dashboard</span>
                            </a>
                        </li>
                        <li class="nav-item mb-1">
                            <a href="#" class="nav-link">
                                <i class="fas fa-users"></i>
                                <span class="section-name" style="display: inline-block;">&nbsp;Employee Managment</span>
                            </a>
                        </li>
                        <li class="nav-item mb-1">
                            <a href="#" class="nav-link" data-bs-toggle="collapse" data-bs-target="#members-collapse">
                                <i class="fas fa-cog"></i>
                                <span class="section-name" style="display: inline-block;">
                                    <span class="dropdown-toggle">&nbsp;System Managment&nbsp;&nbsp;</span>
                                </span>
                            </a>
                            <div class="collapse" id="members-collapse">
                                <ul class="dropdown p-0 mb-3">
                                    <li><a href="/employee/admin/userlist">Users</a></li>
                                    <li><a href="/employee/admin/rolelist">Roles</a></li>
                                    <li><a href="#">Permission</a></li>
                                    <li><a href="#">City</a></li>
                                    <li><a href="#">Report</a></li>
                                </ul>
                            </div>
                        </li>
                        <li class="nav-item mb-1">
                            <a href="#" class="nav-link">
                                <i class="fas fa-user-circle"></i>
                                <span class="section-name" style="display: inline-block;">&nbsp;Admin Managment</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div id="navbar" class="navbar">
                <div class="d-flex justify-content-between w-100 pe-4">
                    <div class="heading" style="margin-left: 300px;">
                        <a class="navbar-brand" href="index.html">
                            <h1 class="ms-2">Dashboard</h1>
                        </a>
                    </div>
                    <div class="user">
                        <a class=" dropdown-toggle" href="#" type="button" data-bs-toggle="dropdown">
                            <img src="../assets/img/avatar-profile-pic.png" alt="Profile picture" class="user-img">
                            <span>username</span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end me-4">
                            <li><a href="#" class="dropdown-item" type="button">Profile</a></li>
                            <li><a href="/employee/private/logout" class="dropdown-item" type="button">Log Out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>

        <main class="my-4" style="margin-left: 300px;">
            <!-- <div class="container">
                <div class="row">
                    <div class="col-3">
                        <div class="box"></div>
                    </div>
                    <div class="col-3">
                        <div class="box"></div>
                    </div>
                    <div class="col-3">
                        <div class="box"></div>
                    </div>
                    <div class="col-3">
                        <div class="box"></div>
                    </div>
                </div>
            </div> -->
        </main>

        <script src="/employee/admnp/js/bootstrap.bundle.min.js"></script>
        <script src="/employee/admnp/js/script.js"></script>
        <script src="https://kit.fontawesome.com/f9a01ea1da.js" crossorigin="anonymous"></script>

    </body>

</html>
