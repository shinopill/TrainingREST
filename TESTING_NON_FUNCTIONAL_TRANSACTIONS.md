Pour les tests de charges et conccurence à l'aide de JMeter en suivant le séncario suivant:

J envoie une règle
Je récupère le nombre de point de l'utilisateur testé

Je fais X nombre d'évenement pour mon utilisateur

Je récupère le nombre de point de l'utilisateur à la sortie des tests.

Dans le premier cas simple, j ai testé avec 1 utilisateur qui fait 10 requête et j ai fait obtenu 10 bon événement et un bon nombre de points. On remarque aussi le traitement de 8.4 requête par seconde.
![not found](./img/post_1_10.png)


![not found](./img/reponce_1_10.png)

Puis j ai fait un test avec 1 utilisateur qui fait 1000 requête afin de voir la perte de performance du serveur.
![not found](./img/post_1_1000.png)

![not found](./img/reponce_1_1000.png)

On remarque un très grande diminution de la vitesse de traitement avec 1.4 req/s mais la cohérence des données est toujours présente avec + 1000 au pointScale voulu (1020).

Dans un deuième temps j ai créer une nouvelle règle et  j ai fait 10 utilisateurs qui font 10 évements
le résultat est le suivant :

![test 10 utilisateurs](./img/post_event_10_10.png)

Et le résultat en DB est le suivant:
```
[
  {
    "username": "test5",
    "appkey": 1,
    "pointScalesWithPoints": [
      {
        "pointScale": {
          "name": "test5",
          "description": "test5",
          "appKey": 1
        },
        "points": 11
      }
    ],
    "badgesArray": [
      {
        "name": "test5",
        "description": "test5",
        "appKey": 1
      }
    ]
  },
  {
    "username": "test5",
    "appkey": 1,
    "pointScalesWithPoints": [
      {
        "pointScale": {
          "name": "test5",
          "description": "test5",
          "appKey": 1
        },
        "points": 1
      }
    ],
    "badgesArray": [
      {
        "name": "test5",
        "description": "test5",
        "appKey": 1
      }
    ]
  },
  {
    "username": "test5",
    "appkey": 1,
    "pointScalesWithPoints": [
      {
        "pointScale": {
          "name": "test5",
          "description": "test5",
          "appKey": 1
        },
        "points": 1
      }
    ],
    "badgesArray": [
      {
        "name": "test5",
        "description": "test5",
        "appKey": 1
      }
    ]
  },
  {
    "username": "test5",
    "appkey": 1,
    "pointScalesWithPoints": [
      {
        "pointScale": {
          "name": "test5",
          "description": "test5",
          "appKey": 1
        },
        "points": 1
      }
    ],
    "badgesArray": [
      {
        "name": "test5",
        "description": "test5",
        "appKey": 1
      }
    ]
  },
  {
    "username": "test5",
    "appkey": 1,
    "pointScalesWithPoints": [
      {
        "pointScale": {
          "name": "test5",
          "description": "test5",
          "appKey": 1
        },
        "points": 1
      }
    ],
    "badgesArray": [
      {
        "name": "test5",
        "description": "test5",
        "appKey": 1
      }
    ]
  }
]
````

Nous constatons donc qu'il y a une non gestion de la concurrence assez flagrante dans notre projet.
Nous avons pris le pari de l'utilisation du @Version est cela c'est avérer totalement non payant.
Il faudrait donc passer à une aproche plus pessimiste et utiliser des lock mais malheurusement nous n'avons pas le temps de faire cela.
