<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Suppression d'une Oeuvre Vente </H1>
<form method="post" action="supprimerOeuvrevente.htm?id=${ov.idOeuvrevente}" onsubmit="return teste()">
<div class="col-md-12 well well-md">
    <h1>Supprimer une oeuvre vente</h1>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Titre de l'oeuvre : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txttitreov" value="${ov.titreOeuvrevente}"  id="titreov" class="form-control" min="0" disabled="disabled">
        </div>

    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Prix de l'oeuvre : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txtprixov" value="${ov.prixOeuvrevente}" id="prixov" class="form-control" min="0" disabled="disabled">
        </div>
    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Proprietaire de l'oeuvre : </label>
        <div class="col-md-3">
            <INPUT type="text" name="txtproprietaire" value="${ov.proprietaire.nomProprietaire} ${ov.proprietaire.prenomProprietaire}" id="proprietaire" class="form-control" min="0" disabled="disabled">
        </div>
    </div>
    <div class="row" >
        <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">Etat de l'oeuvre: </label>
        <div class="col-md-3">
            <c:choose>
                <c:when test="${ov.etatOeuvrevente == 'L'}">
                    <INPUT type="text" name="txtetatov" value="Libre" id="etatov" class="form-control" min="0" disabled>
                </c:when>
                <c:otherwise>
                    <INPUT type="text" name="txtetatov" value="Réservé" id="etatov" class="form-control" min="0" disabled>
                </c:otherwise>
            </c:choose>
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