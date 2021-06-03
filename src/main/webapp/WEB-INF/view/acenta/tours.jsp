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
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/jszip-2.5.0/dt-1.10.24/af-2.3.5/b-1.7.0/b-colvis-1.7.0/b-html5-1.7.0/b-print-1.7.0/cr-1.5.3/date-1.0.3/fc-3.3.2/fh-3.1.8/kt-2.6.1/r-2.2.7/rg-1.1.2/rr-1.2.7/sc-2.0.3/sb-1.0.1/sp-1.2.2/sl-1.3.3/datatables.min.css"/>
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
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">TURLAR</h4>
                                    <div class="table-responsive">
                                        <table id="file_export" class="table table-striped table-bordered display text-inputs-searching">
                                            <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>Başlangic Tarihi</th>
                                                <th>Bitiş Tarihi</th>
                                                <th>Tur tipi</th>
                                                <th>Tur Açıklaması</th>
                                                <th>Lokasyon</th>
                                                <th>Kalan Bilet</th>
                                                <th>Ücret</th>
                                                <th>Biriken Ücret </th>
                                                <th>Müşteriler </th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <th data-name="id">id</th>
                                                    <th data-name="startDate">Başlangic Tarihi</th>
                                                    <th data-name="endDate">Bitiş Tarihi</th>
                                                    <th data-name="tourType">Tur tipi</th>
                                                    <th data-name="details">Tur Açıklaması</th>
                                                    <th data-name="location">Lokasyon</th>
                                                    <th data-name="capasity">Kalan Bilet</th>
                                                    <th data-name="price">Ücret</th>
                                                    <th data-name="totalPrice">Biriken Ücret</th>
                                                </tr>
                                            </tfoot>
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
</div>





<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/bs4/jszip-2.5.0/dt-1.10.24/af-2.3.5/b-1.7.0/b-colvis-1.7.0/b-html5-1.7.0/b-print-1.7.0/cr-1.5.3/date-1.0.3/fc-3.3.2/fh-3.1.8/kt-2.6.1/r-2.2.7/rg-1.1.2/rr-1.2.7/sc-2.0.3/sb-1.0.1/sp-1.2.2/sl-1.3.3/datatables.min.js"></script>
<script>
    $(function () {

        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });
    // $('#file_export thead tr').append('<th/>');
    $(document).ready(function() {

        var tableSearching = $('#file_export').DataTable({
            serverSide: true,
            processing: false,
            ordering: false,
            searching:false,
            bPaginate: false,
            bInfo: false,
            dom: 'Bfrtip',
            buttons: [
                'copyHtml5',
                'excelHtml5',
                'csvHtml5',
                'pdfHtml5'
            ],
            ajax: {
                url: "/acenta/getTours",
                type: "get",
                dataType: 'json',
                dataSrc: "",
                data: function (data) {
                    delete data.columns;
                    if($.trim($('input[name=tourType]').val()).length){
                        data.tourType = $('input[name=tourType]').val();
                    }
                    if($.trim($('input[name=details]').val()).length){
                        data.details = $('input[name=details]').val();
                    }
                    if($.trim($('input[name=location]').val()).length){
                        data.location = $('input[name=location]').val();
                    }
                    if($.trim($('input[name=capasity]').val()).length){
                        data.capasity = $('input[name=capasity]').val();
                    }
                    if($.trim($('input[name=price]').val()).length){
                        data.price = $('input[name=price]').val();
                    }
                },
            },

            columns: [
                { data: "id", "visible": false , orderable: false},
                { data: "startDate", name: 'startDate', searchable: true, orderable: false},
                { data: "endDate", name: 'endDate', searchable: true, orderable: false},
                { data: "tourType", name: 'tourType', orderable: false},
                { data: "details", name: 'details', orderable: false },
                { data: "location", name: 'location', searchable: true, orderable: false},
                { data: "capasity", name: 'capasity', searchable: true, orderable: false},
                { data: "price", name: 'price', searchable: true, orderable: false},
                { data: "totalPrice", name: 'totalPrice', searchable: true, orderable: false},
                { data: "id", render:
                        function (data, type, row) {
                            var id = row.id
                            return "<div class='btn-group'>" + "<a href='client?id="+id+"' data-id='" + row.id + "' " +
                                "class='btnConfirmShow btn btn-outline-info btn-sm btn-icon-split float-right'>" +
                                "<span class='icon text-danger-50'>" + "<i class='fa fa-info fa-fw'></i>" + "</span>" +
                                "<span class='text delete-btn'>&nbsp;Göster</span>" + "</a>" + "</div>"
                        }}
            ],

            columnDefs: [
                { orderable: false, targets: [ 1, 2, 3, 4, 5, 6, 7 ] } //This part is ok now
            ]

            //     { "data": "personelNames"},
            //     {data: "durum", render : function(data, type, row) {
            //             var durums;
            //             if(data==0){
            //                 durums= "<span class=\"badge badge-danger shadow-danger m-1\">" +
            //                     'Pasif'
            //                 "</span>"
            //             }
            //             else if(data==1){
            //                 durums= "<span class=\"badge badge-success shadow-success m-1\"> " +
            //                     'Aktif'
            //                 "</span>"
            //             }
            //             return durums;
            //         }},
            // ]
        });


        $('.text-inputs-searching tfoot th').each(function() {
            var title = $(this).text();
            var name = $(this).data('name');
            if(!['startDate','endDate', "totalPrice"].includes(name)){
                $(this).html('<input type="text" name="'+name+'" placeholder="Search ' + title + '" />');
            }

        });

        $('.buttons-copy, .buttons-csv, .buttons-print, .buttons-pdf, .buttons-excel').addClass('btn btn-primary mr-1');
        tableSearching.columns().every(function () {
            var that = this;

            $('input', this.footer()).on('keyup', function () {
                    that
                        .search(this.value)
                        .draw();

            });
        });
    });

</script>
</body>
</html>

