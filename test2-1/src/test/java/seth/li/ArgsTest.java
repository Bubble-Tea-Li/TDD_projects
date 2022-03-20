package seth.li;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgsTest {
    // -l -p 8080 -d /usr/logs
    // Single Option
    @Test
    public void should_set_boolean_option_to_true_if_flag_present() throws Exception {
        BooleanOption option = Args.parse(BooleanOption.class, "-l");

        assertTrue(option.logging());
    }

    @Test
    public void should_set_boolean_option_to_true_if_flag_not_present() throws Exception {
        BooleanOption option = Args.parse(BooleanOption.class);

        assertFalse(option.logging());
    }

    static record BooleanOption(@Option("l")Boolean logging) {}

    @Test
    public void should_parse_int_as_option_value() throws Exception {
        IntOption option = Args.parse(IntOption.class, "-p", "8080");

        assertEquals(8080, option.port());
    }

    static record IntOption(@Option("p")int port) {}

    @Test void should_parse_string_as_option_value() throws Exception {
        StringOption option = Args.parse(StringOption.class, "-d", "/usr/logs");

        assertEquals("/usr/logs", option.directory());
    }

    static record StringOption(@Option("d")String directory) {}

    // TODO: Multiple Options: -l -p 8080 -d /usr/logs
    
    @Test
    public void should_parse_multi_options() throws Exception {
        MultiOptions options = Args.parse(MultiOptions.class, "-l", "-p", "8080", "-d", "/usr/logs");
        assertTrue(options.logging());
        assertEquals(8080, options.port());
        assertEquals("usr/logs", options.directory());
    }

    static record MultiOptions(@Option("l")boolean logging, @Option("p")int port, @Option("d")String directory) {}
    // sad path:
    // -boolean -l t // -l t f --> should fail
    // -int -p / -p 8080 8081
    // -string -d / -d /usr/logs /usr/vars
    // default value
    // - bool : false
    // - int: 0
    // - string: ""
}
