package com.example.projetmpisi.cucumber.steps;

import io.cucumber.java.en.*;

public class UserSteps {

    @Given("un utilisateur avec le nom {string}")
    public void un_utilisateur_avec_le_nom(String nom) {
        System.out.println("Utilisateur : " + nom);
    }

    @When("j’ajoute l’utilisateur")
    public void j_ajoute_l_utilisateur() {
        System.out.println("Ajout utilisateur");
    }

    @Then("l’utilisateur est enregistré")
    public void l_utilisateur_est_enregistre() {
        System.out.println("Utilisateur enregistré");
    }
}
