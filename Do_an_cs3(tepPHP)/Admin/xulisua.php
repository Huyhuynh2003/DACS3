<?php
//Kết nối sql
$connect= mysqli_connect("localhost", "root", "","quancoffe");

if (isset($_POST["update_product"])) {
   $sothutu =$_POST["sothutu"];
   $name=$_POST["tieu_de"];
   $price=$_POST["price"]
   $doc=$_POST["motasp"];
}
$sql = "UPDATE menu SET tenmon = '$name',price='$price', doc='$doc' WHERE id = '$sothutu'";
if (mysqli_query($connect,$sql)) {
   echo '<script language="javascript">alert("Sửa món thành công"); window.location="sanpham.php";</script>';
}
else {
   $result = "Lỗi" . mysqli_error($connect);
}
?>