Feature: Gestion des utilisateurs

  Scenario: Ajouter un utilisateur
    Given un utilisateur avec le nom "Amal"
    When j’ajoute l’utilisateur
    Then l’utilisateur est enregistré
