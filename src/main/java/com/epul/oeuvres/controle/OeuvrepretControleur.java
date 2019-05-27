package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.AdherentService;
import com.epul.oeuvres.dao.EmpruntService;
import com.epul.oeuvres.dao.OeuvrepretService;
import com.epul.oeuvres.dao.ProprietaireService;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.AdherentEntity;
import com.epul.oeuvres.metier.EmpruntEntity;
import com.epul.oeuvres.metier.OeuvrepretEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OeuvrepretControleur {

    @RequestMapping(value = "listerOeuvrepret.htm")
    public ModelAndView listerOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // HttpSession session = request.getSession();
            OeuvrepretService uneOeuvrepretService = new OeuvrepretService();
            request.setAttribute("mesOeuvreprets", uneOeuvrepretService.consulterListeOeuvrepret());
            destinationPage = "vues/listerOeuvrepret";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insererOeuvrepret.htm")
    public ModelAndView insererOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            OeuvrepretEntity uneOp = new OeuvrepretEntity();
            uneOp.setTitreOeuvrepret(request.getParameter("txttitre"));
            int propID = Integer.parseInt(request.getParameter("txtproprietaireop"));

            OeuvrepretService uneOeuvrepretService = new OeuvrepretService();
            uneOp.setProprietaire((new ProprietaireService()).proprietaireById(propID));
            uneOp.setEtatOeuvrepret("N");
            uneOeuvrepretService.insertOeuvrepret(uneOp);

            request.setAttribute("mesOeuvreprets", uneOeuvrepretService.consulterListeOeuvrepret());

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        destinationPage = "vues/listerOeuvrepret";
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "ajouterOeuvrepret.htm")
    public ModelAndView ajouterOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            ProprietaireService unProprietaireService = new ProprietaireService();
            request.setAttribute("mesProprietaires", unProprietaireService.consulterListeProprietaire());

            destinationPage = "vues/ajouterOeuvrepret";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "modifierOeuvrepret.htm")
    public ModelAndView modifierOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            OeuvrepretService unService = new OeuvrepretService();
            OeuvrepretEntity uneOp = unService.oeuvrepretById(unid);
            ProprietaireService unProprietaireService = new ProprietaireService();

            request.setAttribute("mesProprietaires", unProprietaireService.consulterListeProprietaire());
            request.setAttribute("op", uneOp);
            destinationPage = "vues/modifierOeuvrepret";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "updateOeuvrepret.htm")
    public ModelAndView updateOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            OeuvrepretService unService = new OeuvrepretService();
            OeuvrepretEntity uneOp = unService.oeuvrepretById(unid);

            uneOp.setTitreOeuvrepret(request.getParameter("txttitreop"));
            int propID = Integer.parseInt(request.getParameter("txtproprietaireop"));
            uneOp.setProprietaire((new ProprietaireService()).proprietaireById(propID));

            unService.updateOeuvrepret(uneOp);

            request.setAttribute("mesOeuvreprets", unService.consulterListeOeuvrepret());
            destinationPage = "vues/listerOeuvrepret";

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "preterOeuvrepret.htm")
    public ModelAndView preterOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            OeuvrepretService unService = new OeuvrepretService();
            List<OeuvrepretEntity> touteslesoeuvres = unService.consulterListeOeuvrepret();
            List<OeuvrepretEntity> oeuvreNonPreter = new ArrayList<>();
            for (OeuvrepretEntity o: touteslesoeuvres) {
                if(o.getEtatOeuvrepret().equals("N")) {
                    oeuvreNonPreter.add(o);
                }
            }
            request.setAttribute("mesOeuvres", oeuvreNonPreter);

            AdherentService unAdherentService = new AdherentService();
            request.setAttribute("mesAdherents", unAdherentService.consulterListeAdherents());

            destinationPage = "vues/preterOeuvrepret";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "lendOeuvrepret.htm")
    public ModelAndView lendOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            int id = Integer.parseInt(request.getParameter("txtoeuvrepret"));
            OeuvrepretService unService = new OeuvrepretService();
            OeuvrepretEntity uneOp = unService.oeuvrepretById(id);

            int idAdh = Integer.parseInt(request.getParameter("txtadherentpret"));
            AdherentEntity unAdh = (new AdherentService()).adherentById(idAdh);

            uneOp.setEtatOeuvrepret("P");
            uneOp.setAdherent(unAdh);
            unService.updateOeuvrepret(uneOp);


            EmpruntService empruntService = new EmpruntService();
            EmpruntEntity empruntEntity = new EmpruntEntity();
            int nextId = empruntService.nextIdEmprunt();
            empruntEntity.setIdEmprunt(nextId);
            empruntEntity.setAdherentPret(unAdh);
            empruntEntity.setOeuvrepret(uneOp);

            Date datePret = Date.valueOf(LocalDate.now());
            empruntEntity.setDatePret(datePret);
            Date dateRetour = Date.valueOf(LocalDate.now().plusDays(21));
            empruntEntity.setDateRetour(dateRetour);

            empruntService.insertEmprunt(empruntEntity);

            request.setAttribute("mesOeuvreprets", unService.consulterListeOeuvrepret());
            destinationPage = "vues/listerOeuvrepret";
            //destinationPage = "index";

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "supprimerOeuvrepret.htm")
    public ModelAndView supprimerOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            OeuvrepretService unService = new OeuvrepretService();
            int unid = Integer.parseInt(request.getParameter("id"));
            OeuvrepretEntity uneOeuvrepret = unService.oeuvrepretById(unid);
            unService.supprimerOeuvrepret(uneOeuvrepret);

            request.setAttribute("mesOeuvreprets", unService.consulterListeOeuvrepret());
            destinationPage = "vues/listerOeuvrepret";
            //destinationPage = "index";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "verifierSupprimerOeuvrepret.htm")
    public ModelAndView verifierSupprimerOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            OeuvrepretService unService = new OeuvrepretService();
            OeuvrepretEntity uneOeuvrepret = unService.oeuvrepretById(unid);
            request.setAttribute("op", uneOeuvrepret);

            destinationPage = "vues/verifierSupprimerOeuvrepret";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "gererOeuvrepret.htm")
    public ModelAndView gererOeuvrepret(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage;
        try {
            OeuvrepretService uneOeuvrepretService = new OeuvrepretService();
            request.setAttribute("mesOeuvreprets", uneOeuvrepretService.consulterListeOeuvrepret());

            EmpruntService prets = new EmpruntService();
            request.setAttribute("mesPrets", prets.consulterListeEmprunts());

            destinationPage = "vues/gererOeuvrepret";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "retourner.htm")
    public ModelAndView retourner(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            EmpruntService empService = new EmpruntService();
            EmpruntEntity emp = empService.empruntByPretId(unid);
            empService.supprimerEmprunt(emp);

            OeuvrepretService unService = new OeuvrepretService();
            OeuvrepretEntity uneOeuvrepret = unService.oeuvrepretById(unid);
            uneOeuvrepret.setEtatOeuvrepret("N");
            uneOeuvrepret.setAdherent(null);
            unService.updateOeuvrepret(uneOeuvrepret);

            request.setAttribute("mesOeuvreprets", unService.consulterListeOeuvrepret());
            destinationPage = "vues/listerOeuvrepret";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "prolonger.htm")
    public ModelAndView prolonger(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            EmpruntService unService = new EmpruntService();
            EmpruntEntity emp = unService.empruntByPretId(unid);
            LocalDate oldDate = (new Date(emp.getDateRetour().getTime())).toLocalDate();
            Date newDate = Date.valueOf(LocalDate.of(oldDate.getYear(), oldDate.getMonth(), oldDate.getDayOfMonth()).plusDays(7));
            emp.setDateRetour(newDate);

            unService.updateEmprunt(emp);


            request.setAttribute("mesOeuvreprets", (new OeuvrepretService()).consulterListeOeuvrepret());
            request.setAttribute("mesPrets", unService.consulterListeEmprunts());

            destinationPage = "vues/gererOeuvrepret";

        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }
}
