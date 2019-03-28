package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.AdherentService;
import com.epul.oeuvres.dao.OeuvreventeService;
import com.epul.oeuvres.dao.ProprietaireService;
import com.epul.oeuvres.dao.ReservationService;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.AdherentEntity;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import com.epul.oeuvres.metier.ProprietaireEntity;
import com.epul.oeuvres.metier.ReservationEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Calendar;

@Controller
public class OeuvreventeControleur {

    @RequestMapping(value = "listerOeuvrevente.htm")
    public ModelAndView listerOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // HttpSession session = request.getSession();
            OeuvreventeService uneOeuvreventeService = new OeuvreventeService();
            request.setAttribute("mesOeuvreventes", uneOeuvreventeService.consulterListeOeuvrevente());
            destinationPage = "vues/listerOeuvrevente";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insererOeuvrevente.htm")
    public ModelAndView insererOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            OeuvreventeEntity uneOv = new OeuvreventeEntity();
            uneOv.setTitreOeuvrevente(request.getParameter("txttitreov"));
            uneOv.setPrixOeuvrevente(Integer.parseInt(request.getParameter("txtprixov")));
            ProprietaireService unProprietaireService = new ProprietaireService();
            uneOv.setProprietaire(unProprietaireService.proprietaireById(Integer.parseInt(request.getParameter("txtproprietaireov"))));
            uneOv.setEtatOeuvrevente("L");

            System.out.println("\n\n iiiiccciiiiiiii     "+uneOv.getTitreOeuvrevente() +  "    "+uneOv.getPrixOeuvrevente());
            OeuvreventeService uneOeuvreventeService = new OeuvreventeService();
            uneOeuvreventeService.insertOeuvrevente(uneOv);
            request.setAttribute("mesOeuvreventes", uneOeuvreventeService.consulterListeOeuvrevente());
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        destinationPage = "vues/listerOeuvrevente";
        return new ModelAndView(destinationPage);
    }
    @RequestMapping(value = "ajouterOeuvrevente.htm")
    public ModelAndView ajouterOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            ProprietaireService unProprietaireService = new ProprietaireService();
            request.setAttribute("mesProprietaires", unProprietaireService.consulterListeProprietaire());

            destinationPage = "vues/ajouterOeuvrevente";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "modifierOeuvrevente.htm")
    public ModelAndView modifierOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            OeuvreventeService unService = new OeuvreventeService();
            OeuvreventeEntity uneOv = unService.oeuvreventeById(unid);
            request.setAttribute("ov", uneOv);

            ProprietaireService unProprietaireService = new ProprietaireService();
            request.setAttribute("mesProprietaires", unProprietaireService.consulterListeProprietaire());
            System.out.print(request.getAttribute("mesProprietaires"));

            destinationPage = "vues/modifierOeuvrevente";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "updateOeuvrevente.htm")
    public ModelAndView updateOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            OeuvreventeService unService = new OeuvreventeService();
            OeuvreventeEntity uneOv = unService.oeuvreventeById(unid);
            uneOv.setTitreOeuvrevente(request.getParameter("txttitreov"));
            uneOv.setPrixOeuvrevente(Double.parseDouble(request.getParameter("txtprixov")));
            ProprietaireService unProprietaireService = new ProprietaireService();

            System.out.println("\n\niiiiiiiiiccccccccccccciiiiiiiii" + (Integer.parseInt(request.getParameter("txtproprietaireov")))  );
            uneOv.setProprietaire(unProprietaireService.proprietaireById(Integer.parseInt(request.getParameter("txtproprietaireov"))));
            System.out.println("\n\niiiiiiiiiccccccccccccciiiiiiiii" +uneOv.getProprietaire().getIdProprietaire());
            unService.updateOeuvrevente(uneOv);
            request.setAttribute("mesOeuvreventes", unService.consulterListeOeuvrevente());
            destinationPage = "vues/listerOeuvrevente";
            //destinationPage = "index";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "verifierSupprimerOeuvrevente.htm")
    public ModelAndView verifierSupprimerOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            OeuvreventeService unService = new OeuvreventeService();
            OeuvreventeEntity uneOeuvrevente = unService.oeuvreventeById(unid);
            request.setAttribute("ov", uneOeuvrevente);

            destinationPage = "vues/verifierSupprimerOeuvrevente";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }
    @RequestMapping(value = "supprimerOeuvrevente.htm")
    public ModelAndView supprimerOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            OeuvreventeService unService = new OeuvreventeService();
            int unid = Integer.parseInt(request.getParameter("id"));
            OeuvreventeEntity uneOeuvrevente = unService.oeuvreventeById(unid);
            unService.supprimerOeuvrevente(uneOeuvrevente);
            request.setAttribute("mesOeuvreventes", unService.consulterListeOeuvrevente());
            destinationPage = "vues/listerOeuvrevente";
            //destinationPage = "index";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "prereserverOeuvrevente.htm")
    public ModelAndView prereserverOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            OeuvreventeService unService = new OeuvreventeService();
            OeuvreventeEntity uneOeuvrevente = unService.oeuvreventeById(unid);
            request.setAttribute("ov", uneOeuvrevente);


            AdherentService unAdherentService = new AdherentService();
            request.setAttribute("mesAdherents", unAdherentService.consulterListeAdherents());


            destinationPage = "vues/prereserverOeuvrevente";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "reserverOeuvrevente.htm")
    public ModelAndView reserverOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            OeuvreventeService unServiceOeuvrevente = new OeuvreventeService();
            int unid = Integer.parseInt(request.getParameter("id"));
            OeuvreventeEntity uneOeuvrevente = unServiceOeuvrevente.oeuvreventeById(unid);
            uneOeuvrevente.setEtatOeuvrevente("R");
            unServiceOeuvrevente.updateOeuvrevente(uneOeuvrevente);

            unid = Integer.parseInt(request.getParameter("txtadherent"));
            AdherentService unServiceAdherent = new AdherentService();
            AdherentEntity unAdherent = unServiceAdherent.adherentById(unid);

            ReservationEntity uneReservation = new ReservationEntity();
            uneReservation.setAdherentByIdAdherent(unAdherent);
            uneReservation.setOeuvreventeByIdOeuvrevente(uneOeuvrevente);
            uneReservation.setStatut("en attente");

            Calendar currenttime = Calendar.getInstance();
            Date uneDate = new Date((currenttime.getTime()).getTime());

            uneReservation.setDateReservation(uneDate);
            ReservationService unServiceReservation = new ReservationService();
            unServiceReservation.insertReservation(uneReservation);

            request.setAttribute("mesOeuvreventes", unServiceOeuvrevente.consulterListeOeuvrevente());
            destinationPage = "vues/listerOeuvrevente";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }
}
