<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Ajout d'une Oeuvre </H1>
<form method="post" action="insererOeuvrepret.htm" onsubmit="return teste()">
    <div class="col-md-12 well well-md">

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3 control-label">Titre de l'oeuvre : </label>
            <div class="col-md-3">
                <INPUT type="text" name="txttitre" value="" id="titrep" class="form-control" min="0">
            </div>

        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3" for="selectprop">Choisir un propriétaire:</label>
            <div class="col-md-3">
            <select id="selectprop" name="txtproprietaireop" required>
                <option value="">--Please choose an option--</option>

                <c:forEach items="${mesProprietaires}" var="itemProp">

                    <option value="${itemProp.idProprietaire}">${itemProp.nomProprietaire} ${itemProp.prenomProprietaire}</option>
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
                Ajouter
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