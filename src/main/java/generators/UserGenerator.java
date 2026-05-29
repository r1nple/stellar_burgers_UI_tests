package generators;

import com.github.javafaker.Faker;
import models.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Locale;

public class UserGenerator {

    private static final Faker faker = new Faker(Locale.ENGLISH);

    public static User userGenerate() {
        return new User(generateName(), generateEmail(), generateValidPassword());
    }

    public static String generateName() {
        return faker.name().firstName();
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateValidPassword() {
        return faker.internet().password(6, 15, true, true);
    }

}
