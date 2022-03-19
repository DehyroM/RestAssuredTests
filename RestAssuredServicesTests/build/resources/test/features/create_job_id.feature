Feature: Crear identificador de trabajador
  Como un trabajador activo de la empresa
  Necesito poder crear un identificador de mi trabajo en el sitio web para el trabajo que desempeño
  Para poder utilizar un perfil de trabajo en el sitio web de la empresa

  Scenario: Creacion de identificador de trabajo exitoso
    Given el trabajador ingresa en la pagina de creacion de identificador de trabajo con un nombre de trabajador y nombre de trabajo que realiza
    When el usuario hace una peticion de creacion de identificador de trabajo
    Then el usuario debera ver un codigo de respuesta exitoso y la informacion de identificacion de su trabajo