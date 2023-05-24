<?php
// Thay đổi thông tin kết nối theo cấu hình của bạn
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "quancoffe";

// Kết nối đến cơ sở dữ liệu MySQL
$conn = new mysqli($servername, $username, $password, $dbname);

// Kiểm tra kết nối
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Lấy tham số idnewpaper từ URL
$idnewpaper = $_GET["id"];

// Truy vấn cơ sở dữ liệu để lấy dữ liệu từ cột "doc" dựa trên idnewpaper
$sql = "SELECT doc FROM menu WHERE id = '$idnewpaper'";
$result = $conn->query($sql);

// Kiểm tra và trả về dữ liệu
if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    $data = $row["doc"];
    echo json_encode(array("doc" => $data));
} else {
    echo json_encode(array("doc" => ""));
}

// Đóng kết nối cơ sở dữ liệu
$conn->close();
?>
