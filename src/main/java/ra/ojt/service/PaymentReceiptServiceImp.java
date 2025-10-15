package ra.ojt.service;

public interface PaymentReceiptServiceImp {
    void sendPushNotification (Long bookingId);

    void sendReceiptMail(Long bookingId);
}
