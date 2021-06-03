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

    <meta name="viewport" content="width=device-width, initial-scale=1" >
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="../../resources/css/style.min.css" rel="stylesheet">
    <link href="../../resources/css/toastr.css" rel="stylesheet">

    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <link rel="stylesheet"  href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">

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
                                    <a href="/admin/listUser">Kullanıcı Bilgileri</a>
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

                            <div class="card-header bg-megna">
                                <h4 class="m-b-0 text-white">Kullanıcı Tanımlama Ve Yetkilendirme</h4>
                            </div>

                            <form id="createUser">
                                <div class="form-body">
                                    <div class="card-body">

                                        <div class="row p-t-20">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label">Username</label>
                                                    <input type="text" id="username" name="username" class="form-control">
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group has-danger">
                                                    <label class="control-label">password</label>
                                                    <input type="password" id="password" name="password"
                                                           class="form-control form-control-danger">
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row p-t-20">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label">Kullanıcı Rolu</label>
                                                    <select class="form-control custom-select" name="userType">
                                                         <c:forEach items="${userRoles}" var="role">
                                                             <option value="${role}">${role}</option>
                                                         </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group has-danger">
                                                    <label class="control-label">Admin/Acenta Adı</label>
                                                    <input type="text" id="roleName" name="roleName"
                                                           class="form-control">
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
            <jsp:include page="footer.jsp"></jsp:include>
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
            $("#createUser").submit(function(event) {
                event.preventDefault();
                var postData = $('#createUser').serializeObject();
                $.ajax({
                    type: "post",
                    url: "createUser",
                    data: JSON.stringify(postData),
                    contentType: "application/json",
                    success: function(response) {
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
