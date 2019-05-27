<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Modification d'une Oeuvre </H1>
<form method="post" action="updateOeuvrepret.htm" onsubmit="return teste()">
    <div class="col-md-12 well well-md">
        <h2>Informations</h2>>
        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Titre de l'oeuvre : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txttitreop" value="" id="titreop" class="form-control" min="0">
            </div>
        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Date de Pret : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txtdatepret" value="" id="datepret" class="form-control" min="0">
            </div>
        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Adherent : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txtadh" value="" id="adh" class="form-control" min="0">
            </div>
        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Adresse de l'adherent : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txtadradh" value="" id="adradh" class="form-control" min="0">
            </div>
        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Statut : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txtstatut" value="" id="statut" class="form-control" min="0">
            </div>
        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default btn-primary"><span class="glyphicon glyphicon-ok"></span>
                Prolonger
            </button>
<!-- disable prolonger if penalty -->
            <button type="button" class="btn btn-default btn-primary"
                    onclick="{
                            window.location = './retournerPret.jsp';
                        }">
                <span class="glyphicon glyphicon-ok"></span> Retourner

            </button>
        </div>
    </div>
</form>
<%@include file="footer.jsp"%>
</body>

</html>