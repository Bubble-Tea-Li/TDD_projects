package seth.li;

import org.junit.jupiter.api.Test;

public class ArgsTest {
    @Test
    public void should() {
        Arguments args = Args.Parse("l:b,p:d,d:s", "-l", "-p", "8080", "-d", "/usr/logs");
        args.getBool("l");
        args.getInt("p");

        Args.parse(Options.class, "-l", "-p", "8080", "-d", "/usr/logs");
        options.logging();
        options.port();
    }

    static record Options(@Option("l")boolean logging, @Option("p")int port, @Option("d")String directory) {}
}
