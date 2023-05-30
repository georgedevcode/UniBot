
# ChatBot Java Application

This Java application implements a simple chatbot that interacts with users through the command line interface. The chatbot allows users to perform various operations such as managing questions and answers stored in a JSON file.

## Usage   

To run the chatbot application, execute the App class in your Java IDE or compile and run the Java source files. The application provides the following commands for interacting with the chatbot:

* Mostrar todas las preguntas : Displays all the questions that the chatbot can respond to.

* Modificar respuesta a la pregunta: Modifies the answer to a specific question.

* Eliminar pregunta y su respuesta(s): Deletes a question and its corresponding answer(s).

* Agregar respuesta a una pregunta existente: Adds a new answer to an existing question.

* Agregar una pregunta nueva y su respuesta: Adds a new question and its corresponding answer(s).

* Hacer una pregunta: Allows the user to ask a question to the chatbot.

* Ayuda: Displays the available commands and their descriptions.

* Salir: Terminates the execution of the chatbot.

Please note that the application reads and writes data to a JSON file (QnA.json) located at the specified path. You may need to update the file path in the ChatBot class (path variable) to match your environment.

## Dependencies

The chatbot application relies on the following external libraries:

'org.json': A Java library for working with JSON data.
Make sure to include the necessary dependencies in your Java project.

## JSON Data Structure

{
  "question1": ["answer1", "answer2", ...],
  "question2": ["answer1", "answer2", ...],
  ...
}

You can manually modify the JSON file or use the provided commands to add, modify, or delete questions and answers.
## Authors

- [@Jorge Cerdas Valverde](https://www.linkedin.com/in/jorgecerdas/)

