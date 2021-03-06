<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">

            <ul class="nav navbar-nav">
                <li><a href="index.htm"> <span class="glyphicon glyphicon-home"></span> Accueil</a></li>
                <c:if test="${sessionScope.id == null }">
                <li class="dropdown">
                    <a class="nav navbar-nav navbar-right"  href="login.htm">
                        <span class="glyphicon glyphicon-user"></span>
                        Se Connecter
                        <span class="caret"></span>
                    </a>
                    </c:if>
                    <c:if test="${sessionScope.id > 0  }">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="glyphicon glyphicon-user"></span>
                        Adhérents
                        <span class="caret"></span>
                    </a>

                    <ul class="dropdown-menu">
                        <li><a href="ajouterAdherent.htm"> <span class="glyphicon glyphicon-plus"></span> Ajout Adhérent</a></li>
                        <li><a href="listerAdherent.htm"><span class="glyphicon glyphicon-th-list"></span> Lister les adhérents</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="glyphicon glyphicon-shopping-cart"></span>
                        Ventes
                        <span class="caret"></span>
                    </a>

                    <ul class="dropdown-menu">
                        <li><a href="ajouterOeuvrevente.htm"> <span class="glyphicon glyphicon-plus"></span> Ajout Oeuvre Vente</a></li>
                        <li><a href="listerOeuvrevente.htm"><span class="glyphicon glyphicon-th-list"></span> Lister les oeuvres ventes</a></li>
                        <li><a href="gererReservation.htm"><span class="glyphicon glyphicon-refresh"></span> Gerer les réservations</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="glyphicon glyphicon-briefcase"></span>
                        Emprunts
                        <span class="caret"></span>
                    </a>

                    <ul class="dropdown-menu">
                        <li><a href="ajouterOeuvrepret.htm"> <span class="glyphicon glyphicon-plus"></span> Ajout Oeuvre Pret</a></li>
                        <li><a href="listerOeuvrepret.htm"><span class="glyphicon glyphicon-th-list"></span> Lister les oeuvres prets</a></li>
                        <li><a href="preterOeuvrepret.htm"> <span class="glyphicon glyphicon-book"></span> Faire un Pret</a></li>
                        <li><a href="gererOeuvrepret.htm"><span class="glyphicon glyphicon-cog"></span> Gerer les prets</a></li>
                    </ul>
                </li>
                <li><a href="javascript:fermer()"><span class="glyphicon glyphicon-log-out"></span> Quitter</a></li>
                </c:if>

            </ul>
        </div>
    </nav>
</div>