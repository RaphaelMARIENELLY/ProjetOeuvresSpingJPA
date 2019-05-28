<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="jumbotron text-center">
    <h1>Listing des reservations</h1>
</div>

<div class="container">
    <a class="btn btn-secondary" href="index.htm" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
    <h2>Tableau des reservations</h2>
    <div class="container">
        <h3>Liste des Reservations</h3>
        <table class="table table-hover">
            <tr>
                <th class="col-md-1">Numero de réservation</th>
                <th class="col-md-2">Oeuvre</th>
                <th class="col-md-1">Adherent</th>
                <th class="col-md-2">Date</th>
                <th class="col-md-2">Statut</th>
            </tr>

            <c:forEach items="${mesReservations}" var="item">
                <tr>
                    <td>${item.idReservationOeuvrevente}</td>
                    <td>${item.oeuvrevente.titreOeuvrevente}</td>
                    <td>${item.adherent.idAdherent}</td>
                    <td>${item.dateReservation}</td>
                    <td>${item.statut}</td>

                    <td>
                        <c:choose>
                            <c:when test="${item.statut == 'en attente'}">
                                <a class="btn btn-warning" href="confirmerReservation.htm?id=${item.idReservationOeuvrevente}" role="button" ><span
                                        class="glyphicon glyphicon-ok-refresh"></span> Confirmer</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-warning" role="button" disabled="disabled"><span
                                        class="glyphicon glyphicon-ok-refresh"></span> Confirmer</a>
                            </c:otherwise>
                        </c:choose>
                        <a class="btn btn-danger" href="supprimerReservation.htm?id=${item.idReservationOeuvrevente}" role="button"><span
                                class="glyphicon glyphicon-remove-circle"></span> Supprimer</a>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>

</html>