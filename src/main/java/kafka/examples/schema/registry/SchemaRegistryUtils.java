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
import java.net.URL;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaRegistryUtils {

  static final Logger LOG = LoggerFactory.getLogger(SchemaRegistryUtils.class);

  public static void addSchemaAndGetRegistrationInfo() {

    addOrUpdateSchema();
    LOG.info("Now, getting registered types");
    getRegisteredTypes();
    getLatestRegisteredVersionOfSubject(TOPIC_NAME);
  }

  private static void addOrUpdateSchema() {

    try {

      final URL url = new URL(DEFAULT_BASE_REGISTRY_SUBJECT_URL + TOPIC_NAME + VALUE_SUFFIX + VERSIONS);
      final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setDoOutput(true);
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/vnd.schemaregistry.v1+json");

      final String input =
          "{\"schema\":\"" + readFile(SCHEMA_FILE).replace("\"", "\\\"").replace(" ", "").replace("\n", "")
              .replace("\r", "") + "\"}";
      LOG.info(input);
      final OutputStream os = conn.getOutputStream();
      os.write(input.getBytes());
      os.flush();
      if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
        throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
      }
      final BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
      String output;
      LOG.info("Output from Server .... \n");
      while ((output = br.readLine()) != null) {
        LOG.info(output);
      }
      conn.disconnect();
    } catch (final IOException e) {
      LOG.trace("", e);
    }
  }

  private static void getRegisteredTypes() {

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
      LOG.info("Registered Schema Version(s):  \n");
      while ((output = br.readLine()) != null) {
        LOG.info(output);
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
      LOG.info("Registered Schema:  \n");
      while ((output = br.readLine()) != null) {
        LOG.info(output);
      }
      conn.disconnect();
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  private static String readFile(final String fileName) {
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