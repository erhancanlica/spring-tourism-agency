<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="../../resources/css/style.min.css" rel="stylesheet">
    <link href="../../resources/css/toastr.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">

</head>

<body>

<div id="main-wrapper">

    <jsp:include page="topHeader.jsp"></jsp:include>

    <jsp:include page="leftSideBar.jsp"></jsp:include>

    <div class="page-wrapper">

        <div class="page-breadcrumb">
            <div class="row">
                <div class="col-5 align-self-center">
                    <h4 class="page-title">ADMİN PANELİ</h4>
                </div>
                <div class="col-7 align-self-center">
                    <div class="d-flex align-items-center justify-content-end">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">
                                    <a href="">Admin</a>
                                </li>
                                <li class="breadcrumb-item active" aria-current="page">Backup</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container-fluid mt-lg-3">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-header bg-info" >
                                <h4 class="m-b-0 text-white">Yedekleme İşlemleri</h4>
                            </div>
                        </div>

                            <div class="form-body">
                                <div class="card-body">
                                    <c:if test="${!empty backupMsg}">
                                        <p>${backupMsg}</p>
                                    </c:if>
                                    <div class="row p-t-20">
                                        <a href="/admin/create-backup">Backup Oluştur</a>
                                    </div>
                                    <br/><br/>
                                <form:form action="/admin/restore" method="post" enctype="multipart/form-data">
                                    <h2>Yedekten Geri Dön</h2>
                                    <div class="row p-t-20">
                                        <div class="col-12">
                                            <div class="form-group">
                                                <label class="control-label col-form-label">Database Yedeği Seçin</label>
                                                <input id="backup" type="file" accept=".dmp"  name="backup">
                                            </div>
                                        </div>
                                    </div>
                                    <input type="submit" class="btn btn-success">
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</div>
</div>


</body>

</html>
