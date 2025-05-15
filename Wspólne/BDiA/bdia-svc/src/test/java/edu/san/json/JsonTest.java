// © 2025 Konrad Grzanek <kongra@gmail.com>
package edu.san.json;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class JsonTest {

  ObjectMapper objectMapper = new ObjectMapper();

  {
    objectMapper.activateDefaultTyping(
        objectMapper.getPolymorphicTypeValidator(),
        ObjectMapper.DefaultTyping.NON_FINAL,
        JsonTypeInfo.As.PROPERTY
    );
  }

  Logger log = System.getLogger(JsonTest.class.getName());

  record Person(UUID id, String email, Address address) {}

  record Address(String city, String street, int number) {}

  record Greeting(String whoToGreet) {}

  @Test
  void test() throws JsonProcessingException {
    var id = UUID.fromString("7b20f4fd-7824-4986-8ef6-93ab7d5d5ed6");
    var person1 = new Person(id, "john@gmail.com",
        new Address("Warsaw", "Marszałkowska", 75));

    log.log(Level.INFO, objectMapper.writeValueAsString(person1));

    var json = "{\"id\":\"7b20f4fd-7824-4986-8ef6-93ab7d5d5ed6\",\"email\":\"john@gmail.com\",\"address\":{\"city\":\"Warsaw\",\"street\":\"Marszałkowska\",\"number\":75}}";
    var person2 = objectMapper.readValue(json, Person.class);

    log.log(Level.INFO, objectMapper.writeValueAsString(person2));

    log.log(Level.INFO, objectMapper.writeValueAsString(new Greeting("John")));

    var json2 = "{\"whoToGreet\":\"John\"}";
    var greeting1 = objectMapper.readValue(json2, Greeting.class);
    log.log(Level.INFO, objectMapper.writeValueAsString(greeting1));

    var json3 = "{\"profileId\":5,\"email\":\"john@gmail.com\",\"@class\": \"edu.san.json.UpdateCommand$UpdateEmail\"}";
    var cmd1 = objectMapper.readValue(json3, UpdateCommand.class);

    log.log(Level.INFO, objectMapper.writeValueAsString(cmd1));
  }

}
