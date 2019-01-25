#Cucumber Test
  
Pour lancer les tests, lancer le serveur avec `docker-compose up` à partir du dossier spring-server/docker, puis dans le dossier fruits-spec, lancez la commande :  
`mvn clean test`
  
##Déroulement des tests
  - Badges
    - un badge est correctement crée
    - l'API nous retourne le badge correctement crée
    - il est impossible de recréer le même badge
    - l'API nous retourne tout les badges avec l'appKey
    - Il est possible de modifier un badge
  
  - Pointscale
    - une pointscale est correctement crée
    - l'API nous retourne la pointscale crée
    - il est impossible de recréer la même pointscale
    - il est possible de modifier une pointscale
    - l'API nous retourne les points des utilisateurs
  
  - Rules
    - une rule est correctement crée
    - l'API nous retourne la rule correctement crée
    - il est impossible de recréer la même rule
    - l'API nous retourne toutes les rule avec l'appKey
    - Il est possible de modifier une rule
    - Il est possible de supprimer une rule
    - Il est possible d'obtenir toutes les règles d'une application
    
  - Events
    - un event est correctement envoyé
    - lorsqu'un event a lieu, une règle est déclenché et un utilisateur gagne un badge et des points