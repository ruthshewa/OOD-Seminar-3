import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControllerTest {
    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @Test
    void testStartSale() {
        controller.startSale();
        assertNotNull(controller.sale);
    }

    @Test
    void testScanItem() {
        controller.startSale();
        ItemDTO item = controller.scanItem(1234, 2);
        assertNotNull(item);
        assertEquals(2, controller.sale.getItems().get(item));
    }

    @Test
    void testEndSale() {
        controller.startSale();
        controller.scanItem(1234, 2);
        double totalPrice = controller.endSale();
        assertTrue(totalPrice > 0);
    }

    @Test
    void testPay() {
        controller.startSale();
        controller.scanItem(1234, 2);
        double totalPrice = controller.endSale();
        controller.pay(totalPrice, 0, "Cash");
        assertNotNull(controller.receipt);
    }

    @Test
    void testRequestDiscount() {
        controller.startSale();
        controller.scanItem(1234, 2);
        double discount = controller.requestDiscount(1001);
        assertTrue(discount >= 0);
    }
}
