// Proyecto Grupo 3
// Asistente virtual para una universidad, información de carreras cursos, inicio de clases,
// precios, procesos
// de admisión

import java.io.IOException;
import java.util.Scanner;
import org.json.JSONException;

public class App {
    public static void main(String[] args) throws JSONException, IOException {

        // Variables globales
        String result;

        String question;

        String answer;

        // Instanciamos los objetos Scanner y ChatBot
        // El Objeto Scanner sera utilizado para obtener datos de entrada del usuario
        // El Objeto ChatBot poseee todos los metodos y logica que controla el Chatbot
        Scanner userInputScanner = new Scanner(System.in);

        ChatBot ChatBot = new ChatBot("UniBot");

        // Inicializamos el Chatbot
        ChatBot.ChatBotInit(true);

        // WHILE LOOP: Siempre y cuando el estado del Chatbot sea "running" el programa
        // ejecutara.
        while (ChatBot.IsChatBotRunning()) {

            // userInput es usado para guardar los comandos de entrada por parte del usuario
            String userInput = userInputScanner.nextLine();

            // SWITCH-CASE: Cada caso es un comando del Chatbot
            switch (userInput) {

                //Muestra todas las preguntas que esten guardadas en QnA.json
                case "Mostrar todas las preguntas":

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Mostrando todas las preguntas:");

                    ChatBot.ShowAllQuestionsFromJsonQnAData();

                    break;

                //Modifica la respuesta de la pregunta seleccionada por el usuario
                case "Modificar respuesta a la pregunta":

                    System.out.println(
                            ChatBot.ChatBotName + " dice: " + "A cual pregunta deseas modificar la respuesta:");

                    question = userInputScanner.nextLine();

                    ChatBot.SelectAnswersOnJsonQnAData(question);

                    break;

                //Elimina la pregunta con su respuesta(s)
                case "Eliminar pregunta y su respuesta(s)":

                    System.out.println(ChatBot.ChatBotName + " dice: " + "A cual pregunta deseas eliminar:");

                    question = userInputScanner.nextLine();

                    ChatBot.DeleteQuestionAnswersOnJsonQnAData(question);

                    break;

                //Agrega una respuesta a una pregunta existente
                //Puede existir una pregunta con varias respuestas
                case "Agregar respuesta a una pregunta existente":

                    System.out.println(ChatBot.ChatBotName + " dice: "
                            + "Ingrese la pregunta que desea agregar una respuesta nueva");

                    question = userInputScanner.nextLine();

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Ingrese la respuesta nueva:");

                    answer = userInputScanner.nextLine();

                    ChatBot.AddNewAnswersOnJsonQnAData(question, answer);

                    break;

                //Agrega una pregunta nueva y su respuesta/
                //Los datos son guardados en el archivo QnA.json
                case "Agregar una pregunta nueva y su respuesta":

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Ingrese la nueva pregunta:");

                    question = userInputScanner.nextLine();

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Ingrese la nueva respuesta:");

                    answer = userInputScanner.nextLine();

                    ChatBot.AddNewQuestionAnswersOnJsonQnAData(question, answer);

                    break;

                //Entramos en modo de conversacion y podemos hacer preguntas al Chatbot
                case "Hacer una pregunta":

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Escriba la pregunta que desea hacer:");

                    boolean b_loop = false;

                    while (!b_loop) {

                        question = userInputScanner.nextLine();

                        answer = ChatBot.SelectQuestionFromJsonQnAData(question);

                        if (answer != null) {

                            System.out.println(ChatBot.ChatBotName + " dice: " + answer);

                            System.out
                                    .println(ChatBot.ChatBotName + " dice: " + "Deseas hacer otra pregunta? Si o No?");

                            result = userInputScanner.nextLine();

                            if (result.equals("Si")) {

                                System.out.println(ChatBot.ChatBotName + " dice: " + "Ingrese su pregunta");

                                question = userInputScanner.nextLine();

                                answer = ChatBot.SelectQuestionFromJsonQnAData(question);

                            } else {

                                b_loop = true;// Saliendo del ciclo while interno

                                System.out
                                        .println(ChatBot.ChatBotName + " dice: " + "Para continuar ingrese un comando");

                                ChatBot.ChatBotHelp();

                            }

                        } else {

                            System.out.println(ChatBot.ChatBotName + " dice: "
                                    + "No tengo respuesta para tu pregunta! Ingrese su pregunta nuevamente");

                        }

                    }

                    break;
                
                //Ayuda invoca el metodo ChatBotHelp() e imprime los comandos de ayuda.
                case "Ayuda":

                    ChatBot.ChatBotHelp();

                    break;
                
                //Salida del programa y terminacion de la app.
                case "Salir":

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Deteniendo Chatbot!");

                    userInputScanner.close();

                    ChatBot.ChatBotStop(false);

                    break;

                default:
                    
                    //Respuesta por defecto
                    System.out.println(ChatBot.ChatBotName + " dice: " + "Perdon, no te comprendo");

                    break;
            }
        }
    }
}
