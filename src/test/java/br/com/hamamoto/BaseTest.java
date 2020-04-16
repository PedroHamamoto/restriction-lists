package br.com.hamamoto;

import com.mongodb.client.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongoCmdOptionsBuilder;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class BaseTest {

    private final String RESOURCE_DIRECTORY = "src/test/resources/";

    private static MongodExecutable MONGO;

    @Inject
    private MongoClient mongoClient;

    @BeforeAll
    public static void startMongoDatabase() throws IOException {
        var config = buildMongodConfiguration();
        MONGO = MongodStarter.getDefaultInstance().prepare(config);
        try {
            MONGO.start();
            System.out.println("foo.sout");
        } catch (IOException e) {

        }
    }


    @AfterAll
    static void afterAll() {
        MONGO.stop();
    }

    public String resource(String path) throws IOException {
        File file = new File(RESOURCE_DIRECTORY + path);

        if (!file.exists()) {
            throw new IllegalArgumentException("Payload file not found: " + RESOURCE_DIRECTORY + path);
        }

        return new String(Files.readAllBytes(Paths.get(file.getPath())));
    }

    private static IMongodConfig buildMongodConfiguration() throws IOException {
        var cmdOptions = new MongoCmdOptionsBuilder()
                .useNoJournal(false)
                .build();

        return new MongodConfigBuilder()
                .version(Version.Main.V4_0)
                .net(new Net("127.0.0.1", 27020, Network.localhostIsIPv6()))
                .cmdOptions(cmdOptions)
                .build();
    }

}
