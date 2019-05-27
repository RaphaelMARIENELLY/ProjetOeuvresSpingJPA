<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Modification d'une Oeuvre </H1>
<form method="post" action="updateOeuvrepret.htm?id=${op.idOeuvrepret}" onsubmit="return teste()">
    <div class="col-md-12 well well-md">

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Titre de l'oeuvre : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txttitreop" value="${op.titreOeuvrepret}" id="titreop" class="form-control" min="0">
            </div>
        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3" for="selectprop">Choisir un propriétaire:</label>
            <div class="col-md-3">
            <select class="form-control" id="selectprop" name="txtproprietaireop" required>
                <option value="${op.proprietaire.idProprietaire}">${op.proprietaire.nomProprietaire} ${op.proprietaire.prenomProprietaire}</option>

                <c:forEach items="${mesProprietaires}" var="itemProp">
                    <c:if test="${itemProp.idProprietaire != op.proprietaire.idProprietaire}">
                    <option value="${itemProp.idProprietaire}">${itemProp.nomProprietaire} ${itemProp.prenomProprietaire}</option>
                    </c:if>
                </c:forEach>
            </select>
            </div>
        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                Valider
            </button>

            <button type="button" class="btn btn-default btn-primary"
                    onclick="{
                            window.location = 'index.htm';
                        }">
                <span class="glyphicon glyphicon-remove"></span> Annuler

            </button>
        </div>
    </div>
</form>
<%@include file="footer.jsp"%>
</body>

</html>