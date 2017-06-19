package kafka.examples.schema.registry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SchemaRegistryUtils {

  private static final String DEFAULT_BASE_SR_URL = "http://localhost:8081/";

  public static void addOrUpdateSchema() {

    try {

      final URL url = new URL("http://localhost:8081/subjects/my_topic_1-value/versions");
      final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/vnd.schemaregistry.v1+json");

      final String input =
          "{\"schema\":\"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"myrecord\\\",\\\"fields\\\":[{\\\"name\\\":\\\"f1\\\",\\\"type\\\":\\\"string\\\",\\\"default\\\":\\\"\\\"},"
              +
              "{\\\"name\\\":\\\"f2\\\",\\\"type\\\":\\\"string\\\",\\\"default\\\":\\\"\\\"}" +
              "]}\"}";
      final OutputStream os = conn.getOutputStream();
      os.write(input.getBytes());
      os.flush();

      if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
        throw new RuntimeException("Failed : HTTP error code : "
            + conn.getResponseCode());
      }

      final BufferedReader br = new BufferedReader(new InputStreamReader(
          (conn.getInputStream())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }

      conn.disconnect();

    } catch (final MalformedURLException e) {

      e.printStackTrace();

    } catch (final IOException e) {

      e.printStackTrace();

    }

  }

  public static void getRegisteredTypes() {

    URL url = null;
    try {
      url = new URL(DEFAULT_BASE_SR_URL + "subjects");
      final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : "
            + conn.getResponseCode());
      }

      final BufferedReader br = new BufferedReader(new InputStreamReader(
          (conn.getInputStream())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }

      conn.disconnect();

    } catch (final IOException e) {
      e.printStackTrace();
    }

  }

  private static void getLatestRegisteredVersionOfSubject(final String topicName) {

    URL url = null;
    try {
      url = new URL("http://localhost:8081/subjects/"
          + topicName
          + "-value/versions/latest");
      final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : "
            + conn.getResponseCode());
      }

      final BufferedReader br = new BufferedReader(new InputStreamReader(
          (conn.getInputStream())));

      String output;
      System.out.println("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }

      conn.disconnect();

    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

}
