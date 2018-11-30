# API For the app

## ScalePoints

## /pointScale

## /create
```
POST : 

JSON{
   Add a new pointScale
   int : ApiKey
   String : Name
   String : Description
   int : Pourcentge 
}

return HTTP 200;

```

### /all

```
GET : 

JSON{
    int : APIkey
}

return 

JSON{
    pointScale[] : tout les pointScale
}
```
### /update/userPoints

```
POST : 

JSON{
   int : ApiKey
   String : Username
   String : ScalePointsName
   int : morePoints 
}

```

### /update/scalePoints

```
POST : 
JSON{
   int : ApiKey
   String : ScalePointsName
   String[] : UpadetToMake
   int[] : values 
}
```

### /user

```
GET : 
JSON{
   int : ApiKey
   String : username
}
return {
    User : user
}
```


## /user/all
```
GET : 

JSON{
   int : ApiKey
}

return 

return {
    User[] : user
    int[] : values
}
```

## Badges