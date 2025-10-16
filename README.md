# 💆‍♂️ Booking Massage Receipt App

## 📝 プロジェクト概要（Project Overview）
このアプリは、マッサージ店向けの予約管理・支払い領収書システムです。  
Spring Bootを使用し、ユーザーがオンラインでマッサージを予約し、支払い後に領収書をメールまたは通知として受け取ることができます。  
チーム開発として作成し、私は主にバックエンドの設計・実装（支払い処理・領収書送信機能など）を担当しました。  

**使用技術:**  
Java / Spring Boot / MySQL / JavaMailSender / Spring Validation / PayPal SDK（mock）  

---

## 🇻🇳 Giới thiệu (Vietnamese Description)
Ứng dụng đặt lịch massage và gửi biên lai thanh toán qua Email hoặc Notification.  
Dự án được xây dựng bằng Spring Boot, MySQL và JavaMailSender để gửi hóa đơn thanh toán HTML khi người dùng hoàn tất giao dịch.

---

## ⚙️ Chức năng chính

- Đặt lịch dịch vụ massage  
- Tạo đơn thanh toán  
- Gửi biên lai qua Email  
- Gửi notification (mock - log console)  
- Quản lý dịch vụ, người dùng, nhân viên  
- Validate dữ liệu bằng DTO  
- Xử lý lỗi chi tiết theo HTTP status và mã lỗi  

---

## 🧰 Công nghệ sử dụng

| Công nghệ | Mô tả |
|------------|-------|
| **Java 17** | Ngôn ngữ chính |
| **Spring Boot** | Framework Backend |
| **Spring Data JPA** | ORM với Hibernate |
| **Spring Validation** | Kiểm tra dữ liệu đầu vào (DTO) |
| **JavaMailSender** | Gửi mail với Gmail SMTP |
| **MySQL** | Cơ sở dữ liệu |
| **PayPal SDK** | Mock thanh toán đơn giản |

---

## 🧩 Cấu trúc chính

- **Entity:** `Booking`, `Payment`, `User`, `Service`  
- **DTO:** `BookingRequestDTO`, `PaymentReceiptRequestDTO`, `PaymentRequestDTO`, ...  
- **Enum:** `BookingStatus`, `PaymentStatus`  
- **Repository:** `BookingRepository`, `PaymentRepository`  
- **Service:** `PaymentReceiptService`, `PaymentReceiptServiceImp` (Interface)  
- **Controller:** `PaymentController` (API thanh toán, gửi biên lai)  
- **Exception:** `BusinessException`, `ResourceNotFoundException`, `CustomException`  
- **Advice:** `HandlerExceptionController` (xử lý ngoại lệ)  

---

## ⚙️ Cấu hình Mail (application.properties)

```properties
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password  # (sẽ được cung cấp khi test)
```


## API chính 
### 1. Gửi thông báo mock push notification

- **POST** `/receipt/send-push`
- **Request body:**
```json
{
  "id": 1
}


### 2. Gửi biên lai qua mail
- - **POST** `/receipt/send-email`
- **Request body:**
```json
{
  "id": 1
}
```

### 2. Gửi request, kiểm tra response:

- Nếu thành công nhận được status 200 và message tiếng Nhật xác nhận gửi thông báo hoặc gửi biên lai mail.
- Nếu lỗi validate hoặc không tìm thấy booking nhận lỗi 400 hoặc 404.
- Nếu lỗi mail server nhận lỗi 500.
