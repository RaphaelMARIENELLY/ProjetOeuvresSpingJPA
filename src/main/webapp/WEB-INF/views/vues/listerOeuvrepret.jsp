<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="jumbotron text-center">
    <h1>Listing des oeuvres</h1>
</div>

<div class="container">
    <a class="btn btn-secondary" href="index.htm" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
    <h2>Tableau des Oeuvres</h2>
    <div class="container">
        <h3>Liste des Oeuvres</h3>
        <table class="table table-hover">
            <tr>
                <th class="col-md-2">Titre</th>
                <th class="col-md-2">Propriétaire</th>
                <!--<th class="col-md-2">Adhérant</th>-->
            </tr>

            <c:forEach items="${mesOeuvreprets}" var="item">
                <tr>
                    <td>${item.titreOeuvrepret}</td>
                    <td>${item.proprietaire.nomProprietaire} ${item.proprietaire.prenomProprietaire}</td>

                    <%--<c:choose>
                        <c:when test="${empty item.adherent}">
                            <td>Aucun adhérent</td>
                        </c:when>
                        <c:otherwise>
                            <td>${item.adherent.nomAdherent} ${item.adherent.prenomAdherent}</td>
                        </c:otherwise>
                    </c:choose>--%>

                    <td>

                        <c:choose>
                            <c:when test="${item.etatOeuvrepret == 'N'}">
                                <a class="btn btn-info" href="modifierOeuvrepret.htm?id=${item.idOeuvrepret}" role="button"><span
                                        class="glyphicon glyphicon-pencil"></span> Modifier</a>
                                <a class="btn btn-danger" href="verifierSupprimerOeuvrepret.htm?id=${item.idOeuvrepret}" role="button"><span
                                        class="glyphicon glyphicon-remove-circle"></span> Supprimer</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-info" role="button" disabled="disabled"><span
                                        class="glyphicon glyphicon-pencil"></span> Modifier</a>
                                <a class="btn btn-danger" role="button" disabled="disabled" ><span
                                        class="glyphicon glyphicon-remove-circle"></span> Supprimer</a>
                            </c:otherwise>
                        </c:choose>


                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>

</html>