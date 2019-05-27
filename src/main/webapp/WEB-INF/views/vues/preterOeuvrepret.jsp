<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>
<body>
<%@include file="navigation.jsp"%>
<H1> Pret d'une Oeuvre </H1>
<form method="post" action="lendOeuvrepret.htm" onsubmit="return teste()">
    <div class="col-md-12 well well-md">

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3" for="selectoeuvre">Choisir une oeuvre :</label>
            <div class="col-md-3">
                <select id="selectoeuvre" name="txtoeuvrepret" required>
                    <option value="">--Please choose an option--</option>

                    <c:forEach items="${mesOeuvres}" var="itemOeuvre">

                        <option value="${itemOeuvre.idOeuvrepret}">${itemOeuvre.titreOeuvrepret}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row" >
            <div class="col-md-12" style ="border:none; background-color:transparent; height :20px;">
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-3" for="selectadherent">Choisir un adherent :</label>
            <div class="col-md-3">
                <select id="selectadherent" name="txtadherentpret" required>
                    <option value="">--Please choose an option--</option>

                    <c:forEach items="${mesAdherents}" var="itemAdherent">

                        <option value="${itemAdherent.idAdherent}">${itemAdherent.nomAdherent} ${itemAdherent.prenomAdherent}</option>
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