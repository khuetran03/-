package ra.ojt.service.Imp;

public interface PaymentReceiptServiceImp {
    String generateReceiptMessage(Long bookingId);
    void sendReceiptMail(Long bookingId);
}
