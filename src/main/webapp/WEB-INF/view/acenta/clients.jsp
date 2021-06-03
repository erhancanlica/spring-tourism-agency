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
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="../../assets/images/favicon.png">
    <title>Nice admin Template - The Ultimate Multipurpose admin template</title>
    <!-- Custom CSS -->
    <!-- Custom CSS -->
    <link href="../../../resources/css/style.min.css" rel="stylesheet">
    <link href="../../../resources/css/toastr.css" rel="stylesheet">
    <link href="../../../resources/css/default.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
    <![endif]-->
</head>

<body>

<div id="main-wrapper">

    <jsp:include page="../topHeader.jsp"></jsp:include>

    <jsp:include page="../leftSideBar.jsp"></jsp:include>

    <div class="page-wrapper">
        <div class="page-breadcrumb">
            <div class="row">
                <div class="col-5 align-self-center">
                    <h4 class="page-title">Müşteri Listesi</h4>
                </div>
                <div class="col-7 align-self-center">
                    <div class="d-flex align-items-center justify-content-end">
                        <nav aria-label="breadcrumb">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item">
                                    Müşteri
                                </li>
                                <li class="breadcrumb-item active" aria-current="page"><a href="javascript:void(0)">Müşteri
                                    Listesi</a></li>
                            </ol>
                        </nav>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Filtreleme Alanı</h4>
                        </div>
                        <form id="clientFilter" method="get">
                            <div class="form-body">
                                <div class="card-body">
                                    <div class="row p-t-20">
                                        <div class="col-md-4">
                                            <select class="form-control custom-select" name="property">
                                                <option value="name">İsim ile</option>
                                                <option value="surname">Soyisim ile</option>
                                                <option value="email">Mail ile</option>
                                                <option value="phone">Telefon ile</option>
                                                <option value="address">Adres ile</option>
                                            </select>
                                        </div>
                                        <div class="col-md-6">
                                            <input class="form-control" name="value" type="text">
                                        </div>
                                        <div class="col-md-2">
                                            <button type="submit" class="btn btn-info">Filtrele</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Müşteriler</h4>
                            <br>
                            <div class="table-responsive">
                                <h3>Excel Download</h3>
                                <div>
                                    <a href="/acenta/download/customers.xlsx">Customers .xlsx</a>
                                </div>
                                <br>
                                <br>
                                <div>
                                    <label for="file">Excel İle İmport</label>
                                    <input id="excel" name="file" type="file" accept=".xls,.xlsx">
                                </div>
                                <div class="float-right mb-2">
                                    <a class="addUser" data-toggle="modal" data-target="#createClientModal">
                                        <i class="fa fa-plus">Müşteri Ekle</i>
                                    </a>
                                </div>
                                <table id="demo-foo-addrow" class="table m-t-30 no-wrap table-hover contact-list"
                                       data-page-size="10">
                                    <thead>
                                    <tr>

                                        <th>İsim</th>
                                        <th>Soyisim</th>
                                        <th>Mail</th>
                                        <th>Telefon</th>
                                        <th>Adres</th>
                                        <th>Güncelle</th>
                                        <th>Sil</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach var="client" items="${clients}">
                                        <tr>
                                            <td><span class="label label-inverse">${client.name}</span></td>
                                            <td><span class="label label-info">${client.surname}</span></td>
                                            <td><span class="label label-info">${client.email}</span></td>
                                            <td><span class="label label-info">${client.phone}</span></td>
                                            <td><span class="label label-info">${client.address}</span></td>


                                            <td>
                                                <a class="updateHref"
                                                   data-id="${client.id}"
                                                   data-name="${client.name}"
                                                   data-surname="${client.surname}"
                                                   data-email="${client.email}"
                                                   data-phone="${client.phone}"
                                                   data-address="${client.address}">
                                                    <i class="fas fa-edit" data-toggle="tooltip"
                                                       data-placement="bottom" title=""></i>
                                                </a>
                                            </td>

                                            <td>
                                                <a class="deleteHref" data-id="${client.id}" data-tourid="${tourId}">
                                                    <i class="fas fa-trash-alt" data-toggle="tooltip"
                                                       data-placement="bottom" title="Sil"></i>
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
        <jsp:include page="../footer.jsp"></jsp:include>
    </div>
</div>

<div class="modal fade modal-profile" id="createClientModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5>Müşteri Ekle</h5>
            </div>
            <div class="modal-body">
                <form id="createClientForm" method="post">
                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">İsim</label>
                        <div class="col-sm-10">
                            <input id="name" type="text" class="form-control" name="name" placeholder="İsim">
                            <input type="text" style="visibility: hidden" id="id" name="id">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">Soy İsim</label>
                        <div class="col-sm-10">
                            <input id="surname" type="text" class="form-control" name="surname" placeholder="Soy İsim">
                            <input type="text" style="visibility: hidden" id="tourId" name="tourId">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">Mail</label>
                        <div class="col-sm-10">
                            <input id="email" type="email" class="form-control" name="email" placeholder="Mail">
                            <input type="text" style="visibility: hidden">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">Telefon</label>
                        <div class="col-sm-10">
                            <input id="phone" type="text" class="form-control" name="phone" placeholder="Telefon">
                            <input type="text" style="visibility: hidden">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label  class="col-sm-2 col-form-label">Adres</label>
                        <div class="col-sm-10">
                            <input id="address" type="text" class="form-control" name="address" placeholder="Adres">
                            <input type="text" style="visibility: hidden">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="clientCreateBtn">Kaydet</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">İptal</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteClientModal" tabindex="-1" role="dialog">
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
                <button type="submit" class="btn waves-effect waves-light btn-danger" id="btnDeleteYes">Evet</button>
                <button type="reset" class="btn waves-effect waves-light btn-inf" data-dismiss="modal">Hayır</button>
            </div>

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

    $(document).ready(function (){

        $("input:file#excel").change(function(e){
            var formData = new FormData();
            formData.append("file", $('input[type=file]')[0].files[0])
            formData.append("tourId","${tourId}");
            console.log("${tourId}");
            $.ajax({
                type: "post",
                url: "importClient",
                processData: false,
                contentType: false,
                enctype: 'multipart/form-data',
                data: formData,

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
                error: function (jqXHR, textStatus, errorThrown){
                    toastr.error("Bilinmeyen Bir Hata Oluştu")
                }
            });
        });

        $(".deleteHref").click(function (){
            $('#deleteClientModal').data('id', $(this).data('id'));
            $('#deleteClientModal').data('tourId', $(this).data('tourid')).modal('show');
        })

        $('#btnDeleteYes').click(function (){
            var id = $('#deleteClientModal').data('id')
            var tourId = $('#deleteClientModal').data('tourId')
            console.log(tourId)
            $.ajax({
                type:'DELETE',
                contentType: 'application/json; charset=utf-8',
                url: "client?id="+id+"&tourId="+tourId,

                timeout: 100000,
                success: function (response){
                    if (response.result == 0){
                        toastr.success(response.message)
                        setTimeout(function (){
                            location.reload()
                        },500)
                    }else {
                        toastr.error(response.message)
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    toastr.error("Bilinmeyen Bir Hata oluştu")
                }
            })
        })

        $('.updateHref').click(function (){
            $('#createClientModal #id').val($(this).data('id'));
            $('#createClientModal #name').val($(this).data('name'));
            $('#createClientModal #surname').val($(this).data('surname'));
            $('#createClientModal #email').val($(this).data('email'));
            $('#createClientModal #phone').val($(this).data('phone'));
            $('#createClientModal #address').val($(this).data('address'));
            $('#createClientModal').data('type',"update").modal('show');
        })

        $('#createClientModal').on('hidden.bs.modal', function (e) {
            $('#createClientForm').trigger("reset");
            $('#createClientModal').removeData('type');
        })

        $('#clientCreateBtn').click(function (e){
            e.preventDefault();
            $('#createClientModal #tourId').val(${tourId});
            var postData = $('#createClientForm').serializeObject();
            <%--var tourId = ${tourId}--%>
            var type =  $('#createClientModal').data('type') == 'update' ? 'put' : 'post';
            $.ajax({
                type: type,
                url: "client",
                data: JSON.stringify(postData),

                contentType: "application/json",
                success: function (response){
                    if (response.result == 0){
                        toastr.success(response.message)
                        setTimeout(function (){
                            location.reload();
                        },500)
                    }else{
                        toastr.error(response.message)
                    }
                },
                error: function (jqXHR, textStatus, errorThrown){
                    toastr.error("Bilinmeyen Bir Hta Oluştu")
                }
            })

        })
    })
</script>

</body>

</html>
