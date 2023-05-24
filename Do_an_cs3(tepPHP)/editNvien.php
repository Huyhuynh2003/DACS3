<?php
// Kết nối tới cơ sở dữ liệu
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "quancoffe";

$conn = new mysqli($servername, $username, $password, $dbname);

// Kiểm tra kết nối
if ($conn->connect_error) {
    die("Kết nối tới cơ sở dữ liệu thất bại: " . $conn->connect_error);
}

// Lấy dữ liệu từ tham số truyền vào (oldComment và newComment)
$oldComment = urldecode($_GET['oldComment']);
$newComment = urldecode($_GET['newComment']);

// Chuẩn bị câu truy vấn UPDATE
$sql = "UPDATE infnvien SET name = '$newComment' WHERE name = '$oldComment'";

if ($conn->query($sql) === TRUE) {
    echo "Bình luận đã
    được chỉnh sửa thành công";
} else {
echo "Lỗi: " . $sql . "<br>" . $conn->error;
}

// Đóng kết nối
$conn->close();
?>