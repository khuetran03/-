# 💆‍♂️ Booking Massage Receipt App

---

## 📝 プロジェクト概要（Project Overview）

このアプリは、**マッサージ店向けの予約管理・支払い領収書システム**です。  
Spring Bootを使用し、ユーザーがオンラインでマッサージを予約し、支払い完了後に領収書を**メールまたは通知**として受け取ることができます。  

チーム開発として作成し、私は主に**バックエンドの設計・実装（支払い処理、領収書送信機能など）**を担当しました。  
また、DTOによるデータ検証や例外処理、メール送信処理の実装も行いました。  

---

### 🧰 使用技術（Technologies）

| 技術 | 内容 |
|------|------|
| Java 17 | メイン言語 |
| Spring Boot | バックエンドフレームワーク |
| Spring Data JPA | ORM（Hibernate） |
| Spring Validation | 入力データ検証（DTO） |
| JavaMailSender | メール送信（Gmail SMTP） |
| MySQL | データベース |
| PayPal SDK（mock） | 簡易的な決済処理 |

---

### 💼 担当部分（My Role）
- 支払い処理ロジックの実装  
- 領収書メール送信機能（HTMLテンプレート）  
- DTOによるデータバリデーション  
- 例外ハンドリング（CustomException, HandlerAdvice）  

---

### 🧠 学んだこと（Learning Points）
- Springのレイヤー構造（Controller → Service → Repository）  
- バリデーションと例外処理の設計  
- JavaMailSenderを用いたHTMLメール送信  
- チーム開発における連携・コミュニケーションスキルの向上  

---

## 🇻🇳 Giới thiệu (Vietnamese Description)

Ứng dụng đặt lịch massage và gửi biên lai thanh toán qua Email hoặc Notification.  
Dự án được xây dựng bằng **Spring Boot**, **MySQL** và **JavaMailSender** để gửi hóa đơn thanh toán HTML khi người dùng hoàn tất giao dịch.  

---

### ⚙️ Chức năng chính

- Đặt lịch dịch vụ massage  
- Tạo đơn thanh toán  
- Gửi biên lai qua Email  
- Gửi notification (mock - log console)  
- Quản lý dịch vụ, người dùng, nhân viên  
- Validate dữ liệu bằng DTO  
- Xử lý lỗi chi tiết theo HTTP status và mã lỗi  

---

### 🧩 Cấu trúc chính

- **Entity**: Booking, Payment, User, Service  
- **DTO**: BookingRequestDTO, PaymentReceiptRequestDTO, PaymentRequestDTO  
- **Enum**: BookingStatus, PaymentStatus  
- **Repository**: BookingRepository, PaymentRepository  
- **Service**: PaymentReceiptService, PaymentReceiptServiceImp (Interface)  
- **Controller**: PaymentController (API thanh toán, gửi biên lai)  
- **Exception**: BusinessException, ResourceNotFoundException, CustomException  
- **Advice**: HandlerExceptionController (xử lý ngoại lệ)  

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
```

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
