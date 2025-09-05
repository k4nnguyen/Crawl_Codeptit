## Test_crawl_codeptit

Công cụ cào các bài trên `code.ptit.edu.vn` bằng tài khoản sinh viên và tự động xuất ra file Word (`.docx`) hiển thị trạng thái từng bài. Dự án có sẵn cả giao diện Java (GUI) để nhập tài khoản/mật khẩu và chạy nhanh mà không cần dòng lệnh.

### Yêu cầu

- Python 3.8+ (khuyến nghị Python 3.10+)
- Google Chrome đã cài trên máy
- Internet ổn định

### Thiết lập môi trường (Windows, PowerShell)

1. Mở Command Prompt tại thư mục dự án
2. Tạo và kích hoạt môi trường ảo:

```
python -m venv venv
"venv\Scripts\activate"
```

3. Cập nhật pip và cài thư viện cần thiết:

```
python -m pip install -U pip
pip install selenium python-docx
```

Ghi chú: Selenium 4.6+ có Selenium Manager, tự động quản lý/chọn ChromeDriver tương ứng với Chrome cài trên máy. Chỉ cần đảm bảo Chrome được cài và có thể mở bình thường.

### Cách chạy bằng Python (CLI)

Tại thư mục dự án, sau khi kích hoạt môi trường ảo và cài thư viện:

```
python crawl.py <username> <password>
```

Ví dụ:

```
python crawl.py  B21DCCN001  mySecretPass
```

Kết quả: chương trình đăng nhập `https://code.ptit.edu.vn/student/question/`, lọc theo các chủ đề trong mã, đọc trạng thái từng bài (AC/WA) và xuất file Word:

- `Danh_sach_Code_PTIT.docx` (tại thư mục dự án)

### Cách dùng GUI Java

Thư mục `Java/` có giao diện `LoginGUI.java`.

Chạy nhanh bằng dòng lệnh (đã cài JDK 8+):

```
cd Java
javac LoginGUI.java
java LoginGUI
```

Giao diện sẽ yêu cầu nhập Username/Password PTIT và sẽ gọi script Python để thực hiện cào dữ liệu và xuất file `.docx` như trên.

Nếu đang chạy từ IDE (IntelliJ/VS Code/Eclipse), mở `Java/LoginGUI.java`, chạy `LoginGUI` trực tiếp. Đảm bảo đã thiết lập Python và cài thư viện theo phần hướng dẫn ở trên.

### Cấu trúc output

- Bảng trong file `.docx` gồm: `STT`, `Chủ đề`, `Mã bài Code PTIT`, `Tên bài`, `Trạng thái`.
- `Trạng thái`: `AC` (xanh) là đã Accepted, `WA` (đỏ) là chưa đạt.

### Lỗi thường gặp

- Không có Chrome/Chrome quá cũ: Cài hoặc cập nhật Google Chrome lên bản mới.
- Bị chặn đăng nhập/2FA/captcha: Thử lại sau, kiểm tra tài khoản, hoặc đăng nhập thủ công trên trình duyệt trước.
- Môi trường ảo không kích hoạt được (ExecutionPolicy): Chạy PowerShell với quyền Admin và tạm cho phép script: `Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass` rồi kích hoạt lại.

### Góp ý / Mở rộng

- Điều chỉnh danh sách chủ đề cần quét trong `crawl.py` (mảng `TOPICS`).
- Có thể thêm lựa chọn xuất `CSV/Excel` bên cạnh Word nếu cần.
