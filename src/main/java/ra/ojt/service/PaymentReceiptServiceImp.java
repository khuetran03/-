package ra.ojt.service;

public interface PaymentReceiptServiceImp {
    String generateReceiptMessage(Long bookingId);
    void sendReceiptMail(Long bookingId);
}
