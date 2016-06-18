package br.unaerp.compras.br.unaerp.compras.connection;

import android.util.Log;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import br.unaerp.compras.br.unaerp.compras.model.LoginModel;

/**
 * Created by raul on 7/6/2016.
 */
public class WebClient {

    public String post(String json) {
        try {
            URL url = new URL("http://10.0.2.2:80/sv/adiciona-cliente.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Content-type","application/json");
            con.setRequestProperty("Accept","text");

            con.setDoOutput(true); //faz o POST

            PrintStream output = new PrintStream(con.getOutputStream());
            output.println(json); //insere no corpo

            con.connect();

            Scanner input = new Scanner(con.getInputStream());
            String resposta = input.next();
            return resposta;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String logarSistema(String usuario, String urlRec){
        URL url = null;
        try {
            System.setProperty("http.keepAlive","false");
            url = new URL(urlRec);
            //Tenta Fazer a Conexão
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //con.setConnectTimeout(2000);
            con.setRequestProperty("Content-type","application/json");
            con.setRequestProperty("Accept","text");

            con.setDoOutput(true); //faz o POST

            PrintStream output = new PrintStream(con.getOutputStream());
            output.println(usuario); //insere no corpo

            con.connect();

            Scanner input = new Scanner(con.getInputStream());
            String resposta = input.next();
            return resposta;
        } catch (MalformedURLException e) {
            Log.d("CONEXÃO: ", String.valueOf(e));
            return "Erro ao acessar o servidor";
        } catch (IOException e) {
            Log.d("CONEXÃO: ", String.valueOf(e));
            return "Erro ao acessar o servidor";
        }



    };
}
