import model.ComputerRepairRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ComputerRepairRequestTest {
    @Test
    @DisplayName("First test")
    public void firstTest() {
        ComputerRepairRequest crr = new ComputerRepairRequest();
        assertEquals("",crr.getOwnerAddress());
        assertEquals("",crr.getOwnerName());
    }

    @Test
    @DisplayName("Second test")
    public void secondTest() {
        ComputerRepairRequest crr = new ComputerRepairRequest(1,"a","b","c","d","e","f");
        assertEquals(1,crr.getID());
        assertEquals("a",crr.getOwnerName());
        assertEquals("b",crr.getOwnerAddress());
        assertEquals("c",crr.getPhoneNumber());
        assertEquals("d",crr.getModel());
        assertEquals("e",crr.getDate());
        assertEquals("f",crr.getProblemDescription());
    }
}
