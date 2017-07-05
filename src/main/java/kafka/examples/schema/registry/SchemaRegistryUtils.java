package kafka.examples.schema.registry;

import static kafka.examples.basic.BasicProperties.TOPIC_NAME;
import static kafka.examples.schema.registry.RegistryProperties.DEFAULT_BASE_REGISTRY_SUBJECT_URL;
import static kafka.examples.schema.registry.RegistryProperties.SCHEMA_FILE;
import static kafka.examples.schema.registry.RegistryProperties.VALUE_SUFFIX;
import static kafka.examples.schema.registry.RegistryProperties.VERSIONS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class SchemaRegistryUtils {

  public static void addSchemaAndPrintItsInfo() {

    addOrUpdateSchema();
    System.out.println("Now, getting registered types");
    getRegisteredTypes();

    getLatestRegisteredVersionOfSubject(TOPIC_NAME);

  }

  public static void addOrUpdateSchema() {

    try {

      final URL url = new URL(DEFAULT_BASE_REGISTRY_SUBJECT_URL + TOPIC_NAME + VALUE_SUFFIX + VERSIONS);
      final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/vnd.schemaregistry.v1+json");

      final String input =
          "{\"schema\":\"" + readFile(SCHEMA_FILE).replace("\"", "\\\"").replace(" ", "").replace("\n", "")
              .replace("\r", "") + "\"}";
      System.out.println(input);
      final OutputStream os = conn.getOutputStream();
      os.write(input.getBytes());
      os.flush();

      if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }

      final BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

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
      url = new URL(DEFAULT_BASE_REGISTRY_SUBJECT_URL);
      final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }

      final BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

      String output;
      System.out.println("Registered Schema Version(s):  \n");
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
      url = new URL(DEFAULT_BASE_REGISTRY_SUBJECT_URL + topicName + VALUE_SUFFIX + VERSIONS + "/latest");
      final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");

      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }

      final BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

      String output;
      System.out.println("Registered Schema:  \n");
      while ((output = br.readLine()) != null) {
        System.out.println(output);
      }

      conn.disconnect();

    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  protected static String readFile(final String fileName) {
    String content = null;
    try {
      content = new Scanner(new File(SchemaRegistryUtils.class.getClassLoader().getResource(fileName).getFile()))
          .useDelimiter("\\Z").next();
    } catch (final FileNotFoundException e) {
      e.printStackTrace();
    }
    return content;
  }

}
