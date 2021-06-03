<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html dir="ltr" lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1" >
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="../../assets/images/favicon.png">
    <title>Nice admin Template - The Ultimate Multipurpose admin template</title>
    <!-- Custom CSS -->
    <!-- Custom CSS -->
    <link href="../../../resources/css/style.min.css" rel="stylesheet">
    <link href="../../../resources/css/toastr.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">

    <![endif]-->
</head>
<body>
<div id="main-wrapper">
    <!-- ============================================================== -->
    <!-- Topbar header - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <jsp:include page="../topHeader.jsp"></jsp:include>
    <!-- ============================================================== -->
    <!-- End Topbar header -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <jsp:include page="../leftSideBar.jsp"></jsp:include>
    <div class="page-wrapper">
        <!-- ============================================================== -->
        <!-- Bread crumb and right sidebar toggle -->
        <!-- ============================================================== -->
        <div class="page-breadcrumb">
            <div class="row">
                <div class="col-5 align-self-center">
                    <h4 class="page-title">Dashboard</h4>
                </div>
                <div class="col-7 align-self-center">
                    <div class="d-flex align-items-center justify-content-end">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">
                                    <a href="#">Home</a>
                                </li>
                                <li class="breadcrumb-item active" aria-current="page">Kullanıcı Oluştur</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="container-fluid mt-3">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-header bg-info">
                                <h4 class="m-b-0 text-white">Tur Oluşturma</h4>
                            </div>
                            <div class="card-body">
                                <h4 class="card-title">Tur bilgileri</h4>
                            </div>
                            <hr>
                            <form id="createForm">
                            <div class="form-body">
                                <div class="card-body">
                                    <div class="row p-t-20">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Başlangıç Tarihi</label>
                                                <input type="date" id="startDate" name="startDate" class="form-control">
                                                <%--                                                    <small class="form-control-feedback"> This is inline help </small> --%>
                                            </div>
                                        </div>
                                        <!--/span-->
                                        <div class="col-md-6">
                                            <div class="form-group has-danger">
                                                <label class="control-label">Bitiş Tarihi</label>
                                                <input type="date" id="endDate" name="endDate" class="form-control form-control-danger">
                                                <%--                                                    <small class="form-control-feedback"> This field has error. </small> --%>
                                            </div>
                                        </div>
                                        <!--/span-->
                                    </div>
                                    <!--/row-->
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group has-success">
                                                <label class="control-label">Personel Listesi</label>
                                                <select class="js-example-basic-multiple" name="personels[]" style="width: 100%;color: black" multiple="multiple" required>
                                                    <c:forEach items="${staffs}" var="staff">
                                                        <option value="${staff.id}">${staff.firstName}-${staff.lastName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Tur Tipi</label>
                                                <select class="form-control custom-select" name="tourType" >
                                                    <c:forEach items="${tourTypes}" var="type">
                                                        <option value="${type}">${type}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="firma">

                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label">Tur Açıklaması</label>
                                                    <input type="text" id="details" name="details" class="form-control" required>
                                                    <%--<small class="form-control-feedback"> This is inline help </small> --%>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label">Tur konumu </label>
                                                    <input type="text" id="location" class="form-control" name="location" required>
                                                    <%--<small class="form-control-feedback"> This is inline help </small> --%>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label">Tur Kapasitesi</label>
                                                    <input type="text" id="capasity" name="capasity" class="form-control number-mask" required>
                                                    <%--<small class="form-control-feedback"> This is inline help </small> --%>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label">Tur Ücreti </label>
                                                    <input type="text" id="price" class="form-control number-mask" name="price" required>
                                                    <%--<small class="form-control-feedback"> This is inline help </small> --%>
                                                </div>
                                            </div>
                                            <div class="col-md-6">


                                            </div>

                                        </div>
                                    </div>

                                </div>
                                <hr>
                                <div class="form-actions">
                                    <div class="card-body">
                                        <button type="submit" class="btn btn-success"> <i class="fa fa-check"></i>Kaydet</button>
                                        <button type="button" class="btn btn-dark">İptal</button>
                                    </div>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="../footer.jsp"></jsp:include>
        </div>
    </div>
</div>

<script>
    $(function () {

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });
    $(document).ready(function() {

        $('.js-example-basic-multiple').select2({
            placeholder: "Personel Seç",
            allowClear: true
        });

        $(document).on("focus", ".number-mask", function() {
            $(this).inputmask({
                "mask": "9",
                "repeat": 7,
                "greedy": false
            });
        });

        $("#createForm").submit(function(event) {
            event.preventDefault();
            var postData = $('#createForm').serializeObject();
            
            $.ajax({

                type: "post",
                url: "createTour",
                data: JSON.stringify(postData),
                contentType: "application/json",
                success: function(response) {
                    console.log(response)
                    if(response.result == 0){
                        toastr.success(response.message)
                        setTimeout(function(){
                            location.reload()
                        },500)
                    }
                    else {
                        console.log(response.message)
                        toastr.error(response.message)
                    }

                },
                error: function(jqXHR, textStatus, errorThrown) {

                    toastr.error("Bilinmeyen Bir Hata oluştu")
                }
            })

        });

    });

</script>
</body>
</html>
