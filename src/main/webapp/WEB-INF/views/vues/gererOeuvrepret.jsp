<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<div class="jumbotron text-center">
    <h1>Gestion des Prets</h1>
</div>

<div class="container">
    <a class="btn btn-secondary" href="index.htm" role="button"><span class="glyphicon glyphicon-menu-left"></span> Retour accueil</a>
    <h2>Tableau des Prets</h2>
    <div class="container">
        <h3>Liste des Prets</h3>

        <div class="panel-group" id="accordion">
    <c:forEach items="${mesPrets}" var="item">

        <c:forEach items="${mesOeuvreprets}" var="itemOv">
            <c:if test="${itemOv.idOeuvrepret == item.oeuvrepret.idOeuvrepret}">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#${itemOv.idOeuvrepret}" >${itemOv.titreOeuvrepret}</a>
                        </h4>
                    </div>

                    <div id="${itemOv.idOeuvrepret}" class="panel-collapse collapse">
                        <div class="panel-body">

                            <div>
                                <label>Adherent : ${itemOv.adherent.nomAdherent} ${itemOv.adherent.prenomAdherent}</label>
                            </div>
                            <div>
                                <label>Date de Pret : ${item.datePret}</label>
                            </div>
                            <div>
                                <label>Date de Retour : ${item.dateRetour}</label>
                            </div>

                            <%--<jsp:useBean id="now" class="java.util.Date"/>--%>
                            <!-- (item.dateRetour gt now)-->

                            <c:choose>
                                <c:when test="${((item.dateRetour.time - item.datePret.time) lt 3024000000)}">
                                    <!-- 3024000000 ms -> 35 jours ->
                                    une fois qu'on fait un pret on a 3 semaines pour le retourner -> 7*3 jours
                                    et on peut prolonger la date de retour que 2 fois -> 7*2 jours
                                    donc 35 jours = 7*3 + 7*2 jours -->
                            <div><a class="btn btn-info" href="retourner.htm?id=${itemOv.idOeuvrepret}" role="button"><span
                                    class="glyphicon glyphicon-pencil"></span> Retourner</a>
                                    <a class="btn btn-info" href="prolonger.htm?id=${itemOv.idOeuvrepret}" role="button"><span
                                            class="glyphicon glyphicon-pencil"></span> Prolonger</a>
                            </div>
                                </c:when>
                                <c:otherwise>
                                    <div>
                                        <label>Retard de : ${((item.dateRetour.time - item.datePret.time)/86400000)} jours</label>
                                    </div>
                                    <div>
                                        <label>Montant de Penalite : ${((item.dateRetour.time - item.datePret.time)/86400000)*0.25} euros</label>
                                    </div>
                            <div><a class="btn btn-info" href="retourner.htm?id=${itemOv.idOeuvrepret}" role="button"><span
                                    class="glyphicon glyphicon-pencil"></span> Retourner</a>
                                    <a class="btn btn-info" role="button" disabled="disabled"><span
                                            class="glyphicon glyphicon-pencil"></span> Prolonger</a>
                            </div>
                                </c:otherwise>
                            </c:choose>


                        </div>
                    </div>
                </div>

            </c:if>
        </c:forEach>

    </c:forEach>

        </div>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>

</html>