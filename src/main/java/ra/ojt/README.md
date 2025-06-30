# Booking Massage Receipt App

Ứng dụng đặt lịch massage và gửi biên lai thanh toán qua Email hoặc Notification.

---
## Chức năng chính

-  Đặt lịch dịch vụ massage
-  Tạo đơn thanh toán
-  Gửi biên lai qua Email
-  Gửi notification (mock - log console)
-  Quản lý dịch vụ, người dùng, nhân viên
-  Validate dữ liệu bằng DTO
-  Xử lý lỗi chi tiết theo HTTP status và mã lỗi

---

## ️ Công nghệ sử dụng

| Công nghệ           | Mô tả                          |
|---------------------|--------------------------------|
| Java 17             | Ngôn ngữ chính                 |
| Spring Boot         | Framework Backend              |
| Spring Data JPA     | ORM với Hibernate              |
| Spring Validation   | Kiểm tra dữ liệu đầu vào (DTO) |
| JavaMailSender      | Gửi mail với Gmail SMTP        |
| MySQL               | Cơ sở dữ liệu                  |
| PayPal SDK          | Mock thanh toán đơn giản       |

## Cấu trúc chính 
- **Entity:** Booking, Payment, User, Service
- **DTO:** BookingRequestDTO, PaymentReceiptRequestDTO, PaymentRequestDTO, ...
- **Enum:** BookingStatus, PaymentStatus
- **Repository:** BookingRepository, PaymentRepository
- **Service:** PaymentReceiptService (tạo và gửi biên lai), PaymentReceiptServiceImp(Interface)
- **Controller:** PaymentController (API thanh toán, gửi biên lai)
- **Exception:** BusinessException, ResourceNotFoundException, CustomException
- **Advice:** HandlerExceptionController (xử lý ngoại lệ)

## Đối với `application.properties` cấu hình mail
- spring.mail.username=your_email@gmail.com
- spring.mail.password=your_app_password 
 --> (sẽ được cung cấp khi test)

## API chính 
### 1. Gửi biên lai qua Email

- **POST** `/receipt/send-email`
- **Request body:**
```json
{
  "id": 1
}
```
- - **POST** `/receipt/send-push`
- **Request body:**
```json
{
  "id": 1
}
```

### 2. Gửi request, kiểm tra response:

- Nếu thành công nhận được status 200 và message tiếng Nhật xác nhận gửi mail.
- Nếu lỗi validate hoặc không tìm thấy booking nhận lỗi 400 hoặc 404.
- Nếu lỗi mail server nhận lỗi 500.