<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Suppression d'une Oeuvre Pret </H1>
<form method="post" action="supprimerOeuvrepret.htm?id=${op.idOeuvrepret}" onsubmit="return teste()">
    <div class="col-md-12 well well-md">
        <h1>Supprimer une oeuvre pret</h1>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Titre de l'oeuvre : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txttitreop" value="${op.titreOeuvrepret}"  id="titreop" class="form-control" min="0" disabled="disabled">
            </div>

        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Proprietaire de l'oeuvre : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txtproprietaireop" value="${op.proprietaire.nomProprietaire} ${op.proprietaire.prenomProprietaire}" id="proprietaire" class="form-control" min="0" disabled="disabled">
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