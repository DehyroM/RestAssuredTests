Feature: Obtener información de un usuario
  Se necesita obtener la informacion de un usuario de la empresa
  Para poder personalizar su perfil de usuario

  Scenario: Obtención de información de un usuario
    Given se requiere solicitar la obtencion de informacion de un usuario especifico
    When cuando se realiza la peticion de informacion del usuario
    Then se debera obtener un codigo de respuesta exitoso y la informacion del usuario

  Scenario: Login erróneo un usuario
    Given el usuario ingresa en la pagina para loguearse con su informacion de nombre de usuario
    When cuando se realiza la peticion para loguearse por parte del usuario
    Then se debera obtener un codigo de respuesta de error y un mensaje indicando que hace falta la contrasena