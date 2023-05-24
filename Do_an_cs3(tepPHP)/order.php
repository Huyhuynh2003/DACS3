<?php

// Thay đổi thông tin kết nối cơ sở dữ liệu dựa trên cài đặt của bạn
$host = 'localhost';
$db   = 'quancoffe';
$user = 'root';
$pass = '';
$charset = 'utf8mb4';

$dsn = "mysql:host=$host;dbname=$db;charset=$charset";
$options = [
    PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    PDO::ATTR_EMULATE_PREPARES   => false,
];
try {
    $pdo = new PDO($dsn, $user, $pass, $options);
} catch (\PDOException $e) {
    throw new \PDOException($e->getMessage(), (int)$e->getCode());
}

// Truy vấn để lấy danh sách các món ăn và giá tiền từ bảng "menu"
$query = "SELECT * FROM menu";
$stmt = $pdo->query($query);
$menuList = [];
while ($row = $stmt->fetch()) {
    $menuList[] = $row;
}

// Trả về kết quả dưới dạng JSON
echo json_encode($menuList);

?>
