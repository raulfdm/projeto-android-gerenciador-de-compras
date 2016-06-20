package br.unaerp.compras.br.unaerp.compras.connection;

import android.util.Log;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import br.unaerp.compras.br.unaerp.compras.model.LoginModel;

/**
 * Created by raul on 7/6/2016.
 */
public class WebClient {

    public String post(String json) {
        //String uri = "http://10.0.2.2/sv/adiciona-cliente.php";
        String uri = "http://192.168.0.2:80/sv/adiciona-cliente.php";
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(2000);
            con.setRequestProperty("Content-type","application/json");
            con.setRequestProperty("Accept","*/*");

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


}
