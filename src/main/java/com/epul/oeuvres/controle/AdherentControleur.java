package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.*;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AdherentControleur {

    @RequestMapping(value = "listerAdherent.htm")
    public ModelAndView afficherLesStages(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // HttpSession session = request.getSession();
            AdherentService unService = new AdherentService();
            request.setAttribute("mesAdherents", unService.consulterListeAdherents());
            destinationPage = "vues/listerAdherent";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "Erreur";

        }
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "insererAdherent.htm")
    public ModelAndView insererAdherent(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            AdherentEntity unAdherent = new AdherentEntity();
            unAdherent.setNomAdherent(request.getParameter("txtnom"));
            unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
            unAdherent.setVilleAdherent(request.getParameter("txtville"));
            AdherentService unService = new AdherentService();
            unService.insertAdherent(unAdherent);
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        destinationPage = "index";
        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "ajouterAdherent.htm")
    public ModelAndView ajouterAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            destinationPage = "vues/ajouterAdherent";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "modifierAdherent.htm")
    public ModelAndView modifierAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            AdherentService unService = new AdherentService();
            AdherentEntity unAdherent = unService.adherentById(unid);
            request.setAttribute("adh", unAdherent);
            destinationPage = "vues/modifierAdherent";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "updateAdherent.htm")
    public ModelAndView updateAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            AdherentService unService = new AdherentService();
            AdherentEntity unAdherent = unService.adherentById(unid);
            unAdherent.setNomAdherent(request.getParameter("txtnom"));
            unAdherent.setPrenomAdherent(request.getParameter("txtprenom"));
            unAdherent.setVilleAdherent(request.getParameter("txtville"));


            unService.updateAdherent(unAdherent);
            request.setAttribute("mesAdherents", unService.consulterListeAdherents());
            destinationPage = "vues/listerAdherent";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "verifierSupprimerAdherent.htm")
    public ModelAndView verifierSupprimerAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            int unid = Integer.parseInt(request.getParameter("id"));
            AdherentService unService = new AdherentService();
            AdherentEntity unAdherent = unService.adherentById(unid);
            request.setAttribute("adh", unAdherent);

            destinationPage = "vues/verifierSupprimerAdherent";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "supprimerAdherent.htm")
    public ModelAndView supprimerAdherent(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destinationPage = "";
        try {
            AdherentService unService = new AdherentService();
            int unid = Integer.parseInt(request.getParameter("id"));
            AdherentEntity unAdherent = unService.adherentById(unid);

            //suppression des prets
            OeuvrepretService pretService = new OeuvrepretService();
            EmpruntService empService = new EmpruntService();
            List<EmpruntEntity> empruntEntities = empService.empruntsByAdherentId(unAdherent.getIdAdherent());

            for(EmpruntEntity empruntEntity : empruntEntities) {

                OeuvrepretEntity opEnt = empruntEntity.getOeuvrepret();
                opEnt.setEtatOeuvrepret("N");
                //opEnt.setAdherent(null);

                empService.supprimerEmprunt(empruntEntity);
                pretService.updateOeuvrepret(opEnt);
            }

            //suppression des ventes
            ReservationOeuvreventeService unereservationService = new ReservationOeuvreventeService();
            List<ReservationOeuvreventeEntity> reservations = unereservationService.reservationByAdherentId(unid);
            for (ReservationOeuvreventeEntity reservation: reservations) {
                OeuvreventeEntity oeuvreventeEntity = reservation.getOeuvrevente();

                OeuvreventeService oeuvreventeService = new OeuvreventeService();
                oeuvreventeEntity.setEtatOeuvrevente("L");
                oeuvreventeService.updateOeuvrevente(oeuvreventeEntity);

                unereservationService.supprimerReservation(reservation);
            }

            unService.supprimerAdherent(unAdherent);

            destinationPage = "index";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

}
