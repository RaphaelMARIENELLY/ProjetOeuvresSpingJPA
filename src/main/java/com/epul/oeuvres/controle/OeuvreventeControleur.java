package com.epul.oeuvres.controle;

import com.epul.oeuvres.dao.OeuvreventeService;
import com.epul.oeuvres.dao.ProprietaireService;
import com.epul.oeuvres.meserreurs.MonException;
import com.epul.oeuvres.metier.OeuvreventeEntity;
import com.epul.oeuvres.metier.ProprietaireEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


}
