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
                                <li class="breadcrumb-item active" aria-current="page">Kullanıcı Bilgileri</li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>


            <div class="container-fluid mt-lg-3">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-header bg-megna" >
                                <h4 class="m-b-0 text-white">Kullanıcı Listesi</h4>
                            </div>

                            <div class="form-body">
                                <div class="card-body">
                                    <div class="row p-t-20">

                                        <table id="demo-foo-addrow"
                                               class="table m-t-30 no-wrap table-hover contact-list"
                                               data-page-size="10">
                                            <thead>
                                            <tr>
                                                <th>No</th>
                                                <th>Kullanıcı Adı</th>
                                                <th>Şifre</th>
                                                <th>Kullanıcı Rol</th>
                                                <th>Role Adı</th>
                                                <th>Update</th>
                                                <th>Delete</th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            <c:forEach var="tempUsers" items="${users}">

                                                <c:url var="updateLink" value="/admin/createUser">
                                                    <c:param name="userId" value="${tempUsers.id}"/>
                                                </c:url>

                                                <tr>
                                                    <td><span class="label label-danger">${tempUsers.id}</span></td>
                                                    <td><span class="label label-inverse">${tempUsers.username}</span>
                                                    </td>
                                                    <td>${tempUsers.password}</td>
                                                    <td><span class="label label-info">${tempUsers.userType}</span></td>
                                                    <td><span class="label label-info">${tempUsers.roleName}</span></td>
                                                    <td>
                                                        <a class="updateHref" data-id="${tempUsers.id}"
                                                           data-userid="${tempUsers.userId}"
                                                           data-username="${tempUsers.username}"
                                                           data-password="${tempUsers.password}"
                                                           data-usertype="${tempUsers.userType}"
                                                           data-rolename="${tempUsers.roleName}">
                                                            <i class="fas fa-edit" data-toggle="tooltip"
                                                               data-placement="bottom" title=""></i>
                                                        </a>
                                                    </td>

                                                    <td>
                                                        <a class="deleteHref" data-id="${tempUsers.id}"
                                                           data-userid="${tempUsers.userId}"
                                                           data-username="${tempUsers.username}"
                                                           data-password="${tempUsers.password}"
                                                           data-usertype="${tempUsers.userType}"
                                                           data-rolename="${tempUsers.roleName}">
                                                            <i class="fas fa-trash-alt" data-toggle="tooltip"
                                                               data-placement="bottom"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        >
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</div>
</div>
</div>


<div class="modal fade" id="delete" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">

                <h5 class="modal-title">Kaydı silmek üzeresiniz!</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                Silmek istediğinize emin misiniz ?
            </div>

            <div class="modal-footer">
                <button type="button" class="btn waves-effect waves-light btn-danger" id="btnDelete">Evet</button>
                <button type="button" class="btn waves-effect waves-light btn-inf" data-dismiss="modal">Hayır</button>
            </div>

        </div>
    </div>
</div>


<div class="modal fade modal-profile" id="updateUserModel" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 700px">
            <div class="modal-header">
                <h5>Kullanıcı Güncelle</h5>
            </div>
            <div class="modal-body">
                <form id="updateUserForm" method="post">
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Kullanıcı Adı</label>
                        <div class="col-sm-10">
                            <input id="username" type="text" class="form-control" name="username">
                            <input type="text" style="visibility: hidden" id="id" name="id">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Şifre</label>
                        <div class="col-sm-10">
                            <input id="password" type="text" class="form-control" name="password">
                            <input type="text" style="visibility: hidden" id="userId" name="userId">
                        </div>
                    </div>


                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Kullanıcı Tipi</label>
                        <div class="col-sm-10">
                            <select id="userType" name="userType" class="form-control custom-select">
                                <c:forEach items="${userRoles}" var="role">
                                    <option value="${role}">${role}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <input style="visibility: hidden">
                    </div>

                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label">Kullanıcı Rol</label>
                        <div class="col-sm-10">
                            <input id="roleName" type="text" class="form-control" name="roleName">

                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnUserGuncelle">Güncelle</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">İptal</button>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    $(document).ready(function () {

        $(".deleteHref").click(function () {
            $('#delete').data('id', $(this).data('id'));
            $('#delete').data('userid', $(this).data('userid'));
            $('#delete').data('username', $(this).data('username'));
            $('#delete').data('password', $(this).data('password'));
            $('#delete').data('userType', $(this).data('usertype'));
            $('#delete').data('rolename', $(this).data('rolename')).modal('show');

        })

        $(".updateHref").click(function () {

            $('#updateUserModel #id').val($(this).data('id'));
            $('#updateUserModel #userId').val($(this).data('userid'));
            $('#updateUserModel #username').val($(this).data('username'));
            $('#updateUserModel #password').val($(this).data('password'));
            $('#updateUserModel #userType').val($(this).data('usertype'));
            $('#updateUserModel #roleName').val($(this).data('rolename'));
            $('#updateUserModel').data('type', "update").modal('show');

        })


        $("#btnDelete").click(function () {

            var formData = {
                id: $('#delete').data('id'),
                userId: $('#delete').data('userid'),
                username: $('#delete').data('username'),
                password: $('#delete').data('password'),
                userType: $('#delete').data('userType'),
                roleName: $('#delete').data('rolename'),
            }

            console.log(formData)
            $.ajax({
                type: "DELETE",
                contentType: "application/json; charset=utf-8",
                url: "deleteUser",
                data: JSON.stringify(formData),
                timeout: 100000,
                success: function (response) {
                    if (response.result == 0) {
                        toastr.success(response.message)
                        setTimeout(function () {
                            location.reload()
                        }, 500)
                    } else {
                        toastr.error(response.message)
                    }

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    toastr.error("Bilinmeyen Bir Hata oluştu")
                }
            });
        })


        $("#btnUserGuncelle").click(function () {

            var postData = $('#updateUserForm').serializeObject();
            console.log(postData)
            $.ajax({
                type: "PUT",
                contentType: "application/json; charset=utf-8",
                url: "updateUser",
                data: JSON.stringify(postData),
                timeout: 100000,
                success: function (response) {
                    if (response.result == 0) {
                        toastr.success(response.message)
                        setTimeout(function () {
                            location.reload()
                        }, 500)
                    } else {
                        toastr.error(response.message)
                    }

                },
                error: function (jqXHR, textStatus, errorThrown) {
                    toastr.error("Bilinmeyen Bir Hata oluştu")
                }
            });
        })
    });

</script>


</body>

</html>
