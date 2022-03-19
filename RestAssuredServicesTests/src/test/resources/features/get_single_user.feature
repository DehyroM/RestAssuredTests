Feature: Obtener información de un usuario
  Se necesita obtener la informacion de un sólo usuario de la empresa
  Para poder personalizar su perfil de usuario

  Scenario: Obtención de información de un usuario
    Given se requiere solicitar la obtencion de informacion de un usuario especifico
    When cuando se realiza la peticion de informacion del usuario
    Then se debera obtener un codigo de respuesta exitoso y la informacion del usuario