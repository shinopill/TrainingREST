# TrainingREST

# Utilisation du Gamification engine partie 2

Vous pouvez utiliser les lignes suivantes pour lancer l’application dans docker.

```
cd swagger/spring-server/docker
docker-compose up
```

De là, vous pouvez aller sur le endpoint /api de votre docker (sur toolbox 192.168.99.100/api) et regarder la spécification de notre engine.

Vous pourrez remarquer que nous n’implémentons que partiellement les opérations CRUD sur les badges et les pointScale.

Ce choix est dû principalement à une cohérence vis-à-vis de l’utilisateur. En effet si nous enlevons un badge d’une application et que l’on le recréer directement, tous les utilisateurs auront ‘perdu’ leur badge. Cela peut semer la confusion sur les règles du jeu de notre gamification engine et c’est pour cela que nous ne supprimons pas de badges ni de pointScale.

Par contre ces derniers peuvent être impossibles à donner en cas de suppression de la règle qui offre soit des points soit un badge. Il reste donc encore une option pour les propriétaires d’applications pour ‘supprimer’ un badge ou un pointScale.

D’un point de vue performance, le fait de ne pas supprimer de pointScale ou de Bages permet d’améliorer la vitesse de notre service en effet, la suppression en cascade d’un badge dans une application peut prendre énormément de temps si nous avons beaucoup de règles et utilisateurs utilisant cedit badge.

Ce choix permet aussi de ne pas avoir de problème de cohérence dans le cas ou badge est supprimé, mais peut par exemple toujours être attribué.


# Test the Fruit microservice by running the executable specification

You can use the Cucumber project to validate the API implementation. Do this when the server is running.

```
cd cd swagger/fruits-specs/
mvn clean test
```

You will see the test results in the console, but you can also open the file located in ’./target/cucumber/index.html’


