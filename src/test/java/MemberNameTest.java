import codes.shawlas.json.api.MemberName;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

final class MemberNameTest {

    private final @NotNull MemberName name1 = MemberName.of("type");

    private MemberNameTest() {
    }

    @Test
    public void testInvalidNames() {
        final @NotNull String @NotNull [] invalids = new String[] {
                "_type", "-id", " attributes", "/link", "*relationship",
        };

        for (@NotNull String s : invalids) {
            Assertions.assertThrows(IllegalArgumentException.class, () -> MemberName.of(s));
        }
    }

    @Test
    public void testValidNames() {
        final @NotNull String @NotNull [] valid = new String[] {
                "type", "id", "attributes", "link", "relationship", "5Collection", "version api", "self_link", "author-api"
        };

        for (@NotNull String s : valid) {
            Assertions.assertDoesNotThrow(() -> MemberName.of(s));
        }
    }

    @Test
    public void equals() {
        Assertions.assertEquals(name1, MemberName.of("type"));
        Assertions.assertNotEquals(name1, MemberName.of("another"));
    }

    @Test
    public void nameEqualsIgnoreCase() {
        Assertions.assertEquals(name1, MemberName.of("TYPE"));
        Assertions.assertEquals(name1, MemberName.of("typE"));
    }
}