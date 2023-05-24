<?php header('Content-Type: application/json');

$host = 'localhost';
$username = 'root';
$password = '';
$dbname = 'quancoffe';

$dsn = "mysql:host=$host;dbname=$dbname;charset=utf8mb4";
$options = [
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_EMULATE_PREPARES => false,
];

try {
    $pdo = new PDO($dsn, $username, $password, $options);
} catch (\PDOException $e) {
    throw new \PDOException($e->getMessage(), (int)$e->getCode());
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $data = json_decode(file_get_contents('php://input'), true);

    $user = $data['user'];
    $pass = $data['pass'];

    $stmt = $pdo->prepare('SELECT user, pass FROM accounts WHERE user = ? AND pass = ?');
    $stmt->execute([$user, $pass]);

    $students = $stmt->fetchObject();
       header('Content-Type: application/json');

    if ($students !== false) {
        $result = [
            'user' => $students->user,
            'pass' => $students->pass,
        ];
    } else {
        $result = [
            'user' => null,
            'pass' => null,
        ];
    }

    echo json_encode($result);
}