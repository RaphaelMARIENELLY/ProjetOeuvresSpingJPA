package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.*;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.*;
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

//
//            @RequestMapping(value = "reserverOeuvrevente.htm")
//            public ModelAndView reserverOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {
//                String destinationPage = "";
//                try {
//                    /* Changement de l'état de l'oeuvre vente */
//                    OeuvreventeService unServiceOeuvrevente = new OeuvreventeService();
//                    int unid = Integer.parseInt(request.getParameter("id"));
//                    OeuvreventeEntity uneOeuvrevente = unServiceOeuvrevente.oeuvreventeById(unid);
//                    uneOeuvrevente.setEtatOeuvrevente("R");
//                    unServiceOeuvrevente.updateOeuvrevente(uneOeuvrevente);
//                    uneOeuvrevente = unServiceOeuvrevente.oeuvreventeById(unid);
//
//                    /* Récupération de l'adhérent */
//                    int unidAdh = Integer.parseInt(request.getParameter("txtadherent"));
//                    AdherentService unServiceAdherent = new AdherentService();
//                    AdherentEntity unAdherent = unServiceAdherent.adherentById(unidAdh);
//
//                    /* Date de réservation */
//                    Calendar dateactuelle = Calendar.getInstance();
//                    Date uneDate = new Date((dateactuelle.getTime()).getTime());
//
//                    System.out.println("pointdecontrole1" + "    adh:"+unAdherent.getIdAdherent()+"    vente:"+uneOeuvrevente+"    date:"+uneDate);
//
//                    /* Définition de la nouvelle réservation */
//                    ReservationEntity uneReservation = new ReservationEntity();
//                    uneReservation.setAdherentByIdAdherent(unAdherent);
//                    uneReservation.setOeuvreventeByIdOeuvrevente(uneOeuvrevente);
//                    uneReservation.setStatut("en attente");
//                    uneReservation.setDateReservation(uneDate);
//
//            /* Ajout de la nouvelle reservation */
//            ReservationService unServiceReservation = new ReservationService();
//            unServiceReservation.insertReservation(uneReservation);
//
//            /* Préparation liste oeuvreventes */
//            request.setAttribute("mesOeuvreventes", unServiceOeuvrevente.consulterListeOeuvrevente());
//            destinationPage = "vues/listerOeuvrevente";
//        } catch (Exception e) {
//            request.setAttribute("MesErreurs", e.getMessage());
//            destinationPage = "vues/Erreur";
//        }
//
//        return new ModelAndView(destinationPage);
//    }

    @RequestMapping(value = "reserverOeuvrevente.htm")
    public ModelAndView reserverOeuvrevente(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            /* Changement de l'état de l'oeuvre vente */
            OeuvreventeService unServiceOeuvrevente = new OeuvreventeService();
            int unid = Integer.parseInt(request.getParameter("id"));
            OeuvreventeEntity uneOeuvrevente = unServiceOeuvrevente.oeuvreventeById(unid);
            uneOeuvrevente.setEtatOeuvrevente("R");
            unServiceOeuvrevente.updateOeuvrevente(uneOeuvrevente);
            uneOeuvrevente = unServiceOeuvrevente.oeuvreventeById(unid);

            /* Récupération de l'adhérent */
            int unidAdh = Integer.parseInt(request.getParameter("txtadherent"));
            AdherentService unServiceAdherent = new AdherentService();
            AdherentEntity unAdherent = unServiceAdherent.adherentById(unidAdh);

            /* Date de réservation */
            Calendar dateactuelle = Calendar.getInstance();
            Date uneDate = new Date((dateactuelle.getTime()).getTime());

            System.out.println("pointdecontrole1" + "    adh:"+unAdherent.getIdAdherent()+"    vente:"+uneOeuvrevente+"    date:"+uneDate);

            /* Définition de la nouvelle réservation */
            ReservationOeuvreventeEntity uneReservation = new ReservationOeuvreventeEntity();
            //uneReservation.setIdReservationOeuvrevente(100000);
            uneReservation.setAdherent(unAdherent);
            uneReservation.setOeuvrevente(uneOeuvrevente);
            uneReservation.setStatut("en attente");
            uneReservation.setDateReservation(uneDate);

            /* Recherche du nouvel id */
            ReservationOeuvreventeService unServiceReservation = new ReservationOeuvreventeService();
            int nextId = unServiceReservation.nextIdReservation();
            uneReservation.setIdReservationOeuvrevente(nextId);

            /* Ajout de la nouvelle reservation */
            unServiceReservation.insertReservation(uneReservation);

            /* Préparation liste oeuvreventes */
            request.setAttribute("mesOeuvreventes", unServiceOeuvrevente.consulterListeOeuvrevente());
            destinationPage = "vues/listerOeuvrevente";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "gererReservation.htm")
    public ModelAndView gererReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage;
        try {
            // HttpSession session = request.getSession();
            ReservationOeuvreventeService uneReservationService = new ReservationOeuvreventeService();
            request.setAttribute("mesReservations", uneReservationService.consulterListeReservation());
            destinationPage = "vues/gererReservation";
        } catch (MonException e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";

        }
        return new ModelAndView(destinationPage);
    }


    @RequestMapping(value = "confirmerReservation.htm")
    public ModelAndView confirmerReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            ReservationOeuvreventeService uneReservationService = new ReservationOeuvreventeService();
            int unid = Integer.parseInt(request.getParameter("id"));
            ReservationOeuvreventeEntity uneReservation = uneReservationService.reservationById(unid);

            uneReservation.setStatut("vendu");
            uneReservationService.updateReservation(uneReservation);

            /* Préparation liste reservations */
            request.setAttribute("mesReservations", uneReservationService.consulterListeReservation());
            destinationPage = "vues/gererReservation";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }

    @RequestMapping(value = "supprimerReservation.htm")
    public ModelAndView supprimerReservation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String destinationPage = "";
        try {
            ReservationOeuvreventeService uneReservationService = new ReservationOeuvreventeService();
            int unid = Integer.parseInt(request.getParameter("id"));
            ReservationOeuvreventeEntity uneReservation = uneReservationService.reservationById(unid);
            int oeuvreventId = uneReservation.getOeuvrevente().getIdOeuvrevente();
            uneReservationService.supprimerReservation(uneReservation);

            OeuvreventeService uneOeuvreVenteService = new OeuvreventeService();
            OeuvreventeEntity uneOeuvrevente = uneOeuvreVenteService.oeuvreventeById(oeuvreventId);
            uneOeuvrevente.setEtatOeuvrevente("L");
            uneOeuvreVenteService.updateOeuvrevente(uneOeuvrevente);

            /* Préparation liste reservations */
            request.setAttribute("mesReservations", uneReservationService.consulterListeReservation());
            destinationPage = "vues/gererReservation";
        } catch (Exception e) {
            request.setAttribute("MesErreurs", e.getMessage());
            destinationPage = "vues/Erreur";
        }

        return new ModelAndView(destinationPage);
    }
}
