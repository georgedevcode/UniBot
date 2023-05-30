import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChatBot {

    // Instanciamos los objetos ha utilizar
    // --- Scanner: Obtenemos datos de entrada del usuario
    // --- JSOBject: Manipulacion del archivo QnA.json y de la estructura JSON de
    // las preguntas y respuestas
    // --- Random: Utilizado para acceder a las llaves del archivo JSON de manera
    // aleatoria, cuando se da el caso que una pregunta tiene varias respuestas.

    Scanner sc = new Scanner(System.in);

    JSONObject JsonObjData = new JSONObject();

    Random RandObj = new Random();

    // Path donde se almacena el archivo QnA.json
    // El path puede cambiar en base al directorio de la maquina donde al aplicacion
    // esta siendo ejecutada
    String path = "C:\\Users\\jocerdas\\Documents\\UIA\\2ndo_cuatrimestre\\programacion_1\\Proyecto\\ChatBot\\json\\QnA.json";

    // Variables de control del Chatbot
    boolean b_result = false;

    public String ChatBotName;

    private boolean ChatBotRunning = false;

    // Funcion constructora donde se almacena el nombre del ChatBot.
    public ChatBot(String ChatBotName) {

        this.ChatBotName = ChatBotName;

    }

    // Funcion que inicializa el Chatbot
    public void ChatBotInit(boolean b_init) {

        this.ChatBotRunning = b_init;

        this.ChatBotHelp();

    }

    // Funcion que detiene el Chatbot
    public void ChatBotStop(boolean b_stop) {

        this.ChatBotRunning = b_stop;

    }

    // Funcion que evalua si el Chatbot esta corriendo
    public boolean IsChatBotRunning() {

        return this.ChatBotRunning;

    }

    // Funcion que calcula un numbero aleatoriamente en base al tama_o que tenga la
    // lista que contiene las respuestas
    public int GetRandom(JSONArray answer) {

        int index = new Random().nextInt(answer.length());

        return index;
    }

    // Funcion de ayuda, muestra los comandos para utilizar el Chatbot
    public void ChatBotHelp() {
        System.out.println("\nHola mi nombre es " + this.ChatBotName
                + "! Para comunicarte conmigo debes usar los siguientes comandos\n");
        System.out.println(
                "\n-----------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println(
                "Mostrar todas las preguntas                          - Muestra todas las preguntas que el Chatbot puede responder");
        System.out.println(
                "Modificar respuesta a la pregunta                    - Modifica la respuesta a la pregunta especificada");
        System.out.println(
                "Eliminar pregunta y su respuesta(s)                  - Elimina la respuesta a la pregunta especificada");
        System.out.println(
                "Agregar respuesta a una pregunta existente           - Agrega una respuesta a la pregunta especificada");
        System.out.println(
                "Agregar una pregunta nueva y su respuesta            - Agrega una pregunta nueva y su respuesta(s)");
        System.out.println(
                "Hacer una pregunta                                   - Realiza una pregunta al chatbot en base a la entrada del usuario");
        System.out.println(
                "Ayuda                                                - Muestra los comandos");
        System.out.println(
                "Salir                                                - Termina la ejecucion del Chatbot!");
    }

    // Funcion escribe los datos del objeto JSON al archivo QnA.json
    public boolean WriteJsonQnAData(JSONObject JsonObjData) {

        try {

            FileWriter FileWriter = new FileWriter(path);

            PrintWriter PrinterWriter = new PrintWriter(FileWriter);

            PrinterWriter.write(JsonObjData.toString());

            PrinterWriter.close();

            b_result = true;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return b_result;
    }

    // Lee el archivo QnA.json y retorna un objeto JSON manipulable
    public JSONObject GetJsonQnAData(String path) throws JSONException, IOException {

        String jsonContent = new String(Files.readAllBytes(Paths.get(path)));

        return new JSONObject(jsonContent);

    }

    // Funcion para modificar las respuestas de las preguntas
    public void ModAnswersOnJsonData(String question, String AnswerSelected, String newAnswer, JSONObject JsonObjdata) {

        JSONArray Answers = JsonObjdata.getJSONArray(question);

        for (int i = 0; i < Answers.length(); i++) {

            String data = Answers.getString(i);

            if (AnswerSelected.equals(data)) {

                Answers.remove(i);

                Answers.put(newAnswer);
            }
        }

        b_result = this.WriteJsonQnAData(JsonObjdata);

        if (b_result) {

            System.out.println(this.ChatBotName + " dice: " + "La respuesta ha sido modificada");

        } else {

            System.out.println(this.ChatBotName + " dice: " + "No se pudo modificar respuesta");

        }
    }

    // Funcion para agregar respuestas a preguntas exitentes
    public void AddNewAnswersOnJsonQnAData(String question, String answer) throws JSONException, IOException {

        JSONObject jsonObjData = GetJsonQnAData(path);

        jsonObjData.append(question, answer);

        boolean b_result = this.WriteJsonQnAData(jsonObjData);

        if (b_result) {

            System.out.println(this.ChatBotName + " dice: " + "Nueva respuesta agregada");

        } else {

            System.out.println(
                    this.ChatBotName + " dice: " + "No se pudo agregar la nueva respuesta");

        }
    }

    // Funcion para elminar preguntas y sus respuestas
    public void DeleteQuestionAnswersOnJsonQnAData(String question) throws JSONException, IOException {

        JSONObject jsonObjdata = GetJsonQnAData(path);

        jsonObjdata.remove(question);

        b_result = this.WriteJsonQnAData(jsonObjdata);

        if (b_result) {

            System.out.println(this.ChatBotName + " dice: " + "Pregunta eliminada");

        } else {

            System.out.println(this.ChatBotName + " dice: " + "No se pudo eliminar la pregunta");

        }
    }

    // Funcion para agregar Preguntas y respuestas nuevas
    public void AddNewQuestionAnswersOnJsonQnAData(String question, String answer) throws JSONException, IOException {

        JSONObject JsonObjData = GetJsonQnAData(path);

        JsonObjData.append(question, answer);

        boolean b_result = this.WriteJsonQnAData(JsonObjData);

        if (b_result) {

            System.out.println(this.ChatBotName + " dice: " + "Nueva pregunta y su respuesta han sido agregadas");

        } else {

            System.out.println(this.ChatBotName + " dice: " + "No se pudo agregar la pregunta y su respuesta");

        }
    }

    // Funcion seleccionar respuesta de preguntas existentes
    // Seleccionamos la respuesta en base a la pregunta que el usuario desea
    // modificar.
    public void SelectAnswersOnJsonQnAData(String question) throws JSONException, IOException {

        JSONObject JsonObjData = GetJsonQnAData(path);

        JSONArray answers = JsonObjData.getJSONArray(question);

        for (Object object : answers) {

            System.out.println(
                    this.ChatBotName + " dice: " + "estas son las respuestas encontradas:" + object.toString());

        }

        System.out.println(this.ChatBotName + " dice: " + "Cual desea modificar?");

        String AnswerSelected = sc.nextLine();

        System.out.println(this.ChatBotName + " dice: " + "Escriba la nueva respuesta");

        String newAnswer = sc.nextLine();

        this.ModAnswersOnJsonData(question, AnswerSelected, newAnswer, JsonObjData);

    }

    // Funcion seleccionar pregunta y mostrar respuesta(s)
    // GetRandom() va a calcular un numero random que sera usado para obtener la
    // respuesta del array de respuestas
    // siendo en el caso que haya mas de una respuesta
    public String SelectQuestionFromJsonQnAData(String question) throws IOException {

        JSONObject JsonObjData = GetJsonQnAData(path);

        JSONArray answer = null;

        int index = 0;

        String b_result = null;

        try {

            answer = JsonObjData.getJSONArray(question);

            index = this.GetRandom(answer);

            b_result = answer.getString(index);

        } catch (JSONException e) {

            System.out.println(this.ChatBotName + " dice: " + "mmmm no encontre respuesta a tu pregunta!");

        }

        return b_result;
    }

    // Funcion que itera sobre las preguntas que se encuentran dentro del archivo
    // QnA.json y muestra cada una de ellas.
    public Iterator<String> ShowAllQuestionsFromJsonQnAData() throws JSONException, IOException {

        JSONObject JsonObjData = GetJsonQnAData(path);

        Iterator<String> questions = JsonObjData.keys();

        while (questions.hasNext()) {

            System.out.println(this.ChatBotName + " dice: " + questions.next());

        }

        return questions;

    }
}
