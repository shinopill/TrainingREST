# TrainingREST

# Utilisation du Gamification engin partie 2

Vous pouvez utiliser les lignes suivantes pour lancer l'application dans docker.

```
cd swagger/spring-server/docker
docker-compose up
```

De la , vous pouvez aller sur le endpoit /api de votre docker (Sur toolbox 192.168.99.100/api) et regarder la spécification de notre engin.

Vous pourrez remarquer que nous n'implémentons que partiellement les opératios CRUD
sur les badges et les pointScale.

Ce choix est dû principalement à une cohérence vis-à-vis de l'utilisateur. En effet si nous enleveons un badges d'une aplication et que l'on le recréer direcement, tous les utilisateurs auront "perdu" leur badge. Cela peut semer la confusion sur la règle du jeu de notre gamification engin et c'est pour cela que nous ne suprimons pas de badges ni de pointScale.

Par contre ces derniers peuvent être impossible à donner en cas de supression de la règle qui offre soit des points soit un bage. Il reste donc encore une option pour les propriétaires d'applications pour "supprimer" un bage ou un pointScale.

D'un point de vue performance, le fait de ne pas supprimer de pointScale ou de Bages permet d'améliorer la vitesse de notre service en effet, la supression en cascade d'un bagde dans une application peut prendre énormemenet de temps si nous avons beacoup de règles et utilisateurs utilisant ce dit badge.

Ce choix permet aussi de ne pas avoir de problème de cohérence dans le cas ou badge est supprimer mais peut par exemple toujours être attribué.



# Test the Fruit microservice by running the executable specification

You can use the Cucumber project to validate the API implementation. Do this when the server is running.

```
cd cd swagger/fruits-specs/
mvn clean test
```
You will see the test results in the console, but you can also open the file located in `./target/cucumber/index.html`
