# projet-jee
Projet JEE - ESGI 2018-2019 4AL2

## Objets
- Event
- Interest

## Routes
- /events
- /interests

# DB
- MySQL
- host: localhost
- user: spring
- password: spring
- database: projetjee

# Authentification
- Pour l'exemple, "GET /events" requiert une authentification (Access denied sinon)
- Créer user admin, sur la console MySQL : `INSERT INTO USER(username, password) VALUES('ADMIN', 'ADMIN');`
- Pour s'authentifier, "POST /login", avec {"username":"ADMIN", "password":"ADMIN"} en body.
- "GET /events" fonctionne

# Avant exécution
mvn clean install

# Domaines

Pour l'exemple, la BDD est "prendsplace" en dev, et "prendsplaceprod" en prod.

##Développement
Exécuter `mvn -Pdev clean install` avant de runner le projet

##Production
Exécuter `mvn -Pprod clean install` avant de runner le projet