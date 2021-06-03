<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionInfo.user.userType eq 'ADMIN'}">
<aside class="left-sidebar">
    <!-- Sidebar scroll-->
    <div class="scroll-sidebar">
        <!-- Sidebar navigation-->
        <nav class="sidebar-nav">
            <ul id="sidebarnav">
                <li class="nav-small-cap">
                    <i class="mdi mdi-dots-horizontal"></i>
                    <span class="hide-menu">Dashboard</span>
                </li>

                <li class="sidebar-item">
                    <a class="sidebar-link has-arrow waves-effect waves-dark" href="javascript:void(0)" aria-expanded="false">
                        <i class="mdi mdi-tune"></i>
                        <span class="hide-menu">Yönetim Paneli </span>
                    </a>
                    <ul aria-expanded="false" class="collapse  first-level">
                        <li class="sidebar-item">
                            <a href="<c:url value="/admin/createUser"/>" class="sidebar-link">
                                <i class="mdi mdi-view-quilt"></i>
                                <span class="hide-menu">Kullanıcı Oluştur</span>
                            </a>
                        </li>

                        <li class="sidebar-item">
                            <a href="<c:url value="/admin/listUser"/>" class="sidebar-link">
                                <i class="mdi mdi-view-quilt"></i>
                                <span class="hide-menu">Kullanıcı Listele</span>
                            </a>
                        </li>
                        <li class="sidebar-item">
                            <a href="<c:url value="/admin/backup"/>" class="sidebar-link">
                                <i class="mdi mdi-view-quilt"></i>
                                <span class="hide-menu">Database Yedek İşlemleri</span>
                            </a>
                        </li>
                    </ul>


            </ul>
        </nav>
        <!-- End Sidebar navigation -->
    </div>
    <!-- End Sidebar scroll-->
</aside>
</c:if>

<c:if test="${sessionInfo.user.userType eq 'ACENTA'}">
    <aside class="left-sidebar">
        <!-- Sidebar scroll-->
        <div class="scroll-sidebar">
            <!-- Sidebar navigation-->
            <nav class="sidebar-nav">
                <ul id="sidebarnav">
                    <li class="nav-small-cap">
                        <i class="mdi mdi-dots-horizontal"></i>
                        <span class="hide-menu">Kullanıcılar</span>
                    </li>
                    <li class="sidebar-item">
                    <li class="sidebar-item">
                        <a href="<c:url value="/acenta"/>" class="sidebar-link">
                            <i class="mdi mdi-view-module"></i>
                            <span class="hide-menu">Personel Sayfası</span>
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a href="<c:url value="/acenta/client"/>" class="sidebar-link">
                            <i class="mdi mdi-view-module"></i>
                            <span class="hide-menu">Müşteri Sayfası</span>
                        </a>
                    </li>

                    <li class="sidebar-item">
                        <a class="sidebar-link has-arrow waves-effect waves-dark" href="javascript:void(0)" aria-expanded="false">
                            <i class="mdi mdi-tune"></i>
                            <span class="hide-menu">Yönetim Paneli </span>
                        </a>
                        <ul aria-expanded="false" class="collapse  first-level">

                            <li class="sidebar-item">
                                <a href="<c:url value="/acenta/getTour"/>" class="sidebar-link">
                                    <i class="mdi mdi-view-module"></i>
                                    <span class="hide-menu"> Tur ekle </span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a href="<c:url value="/acenta/tours"/>" class="sidebar-link">
                                    <i class="mdi mdi-view-quilt"></i>
                                    <span class="hide-menu">Turlar</span>
                                </a>
                            </li>
                        </ul>


                </ul>
            </nav>
            <!-- End Sidebar navigation -->
        </div>
        <!-- End Sidebar scroll-->
    </aside>
</c:if>