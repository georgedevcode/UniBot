import java.io.IOException;
import java.util.Scanner;
import org.json.JSONException;


public class App {
    public static void main(String[] args) throws Exception {
        
        //Variables globales
        String result;
        String question;
        String answer;

        Scanner userInputScanner = new Scanner(System.in);

        ChatBot ChatBot = new ChatBot("UniBot");

        ChatBot.ChatBotInit(true);//Incializamos el chatbot

        while (ChatBot.IsChatBotRunning()) {
            
            String userInput = userInputScanner.nextLine();

            switch (userInput) {

                case "Mostrar todas las preguntas":

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Mostrando todas las preguntas:");

                    ChatBot.ShowAllQuestionsFromJsonQnAData();

                    break;
                
                case "Modificar respuesta a la pregunta":

                    System.out.println(ChatBot.ChatBotName + " dice: " + "A cual pregunta deseas modificar la respuesta:");

                    question = userInputScanner.nextLine();

                    ChatBot.SelectAnswerOnJsonQnAData(question);

                    break;
                
                case "Eliminar pregunta y su respuesta(s)":

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Cual pregunta deseas eliminar:");

                    question = userInputScanner.nextLine();

                    ChatBot.DeleteQuestionAnswersOnJsonQnAData(question);

                    break;
                
                case "Agregar respuesta a una pregunta existente":

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Ingrese la pregunta que desea agregar una respuesta nueva");

                    question = userInputScanner.nextLine();
                   
                    System.out.print(ChatBot.ChatBotName + " dice: " + "Ingrese la respuesta nueva");

                    answer = userInputScanner.nextLine();

                    ChatBot.AddNewAnswerOnJsonQnAData(question, answer);

                    break;
                
                case "Agregar una pregunta nueva y su respuesta":

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Ingrese la nueva pregunta:");

                    question = userInputScanner.nextLine();

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Ingrese la nueva respuesta");

                    answer = userInputScanner.nextLine();

                    ChatBot.AddNewQuestionAnswersOnJsonQnAData(question, answer);

                    break;
                
                case "Hacer una pregunta":

                    
                    break;
                
                case "Ayuda":

                    ChatBot.ChatBotHelp();

                    break;
                
                case "Salir":

                    System.out.println(ChatBot.ChatBotName + " dice " + "Deteniendo Chatbot!");

                    userInputScanner.close();

                    ChatBot.ChatBotStop();

                    break;
            
                default:

                    System.out.println(ChatBot.ChatBotName + " dice: " + "Perdon, no te comprendo!");

                    break;
            }

        }
    }
}
