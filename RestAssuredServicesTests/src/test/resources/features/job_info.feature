Feature: Información del trabajador
  Como un trabajador activo de la empresa
  Necesito poder manipular mi información de usuario en el sitio web para el trabajo que desempeño
  Para poder utilizar un perfil de trabajo en el sitio web de la empresa

  Background:
    Given el trabajador ingresa en la pagina para crear o modificar su informacion de trabajo con un nombre de trabajador y nombre de trabajo que realiza

  Scenario: Creacion de identificador de trabajo exitoso
    When el usuario hace una peticion de creacion de identificador de trabajo
    Then el usuario debera ver un codigo de respuesta exitoso y la informacion de identificacion de su trabajo

  Scenario: Actualización exitosa de la información del trabajador
    When el usuario hace una peticion de actualizacion de la informacion del trabajador
    Then el usuario debera ver un codigo de respuesta exitoso y la actualizacion de su informacion de trabajo