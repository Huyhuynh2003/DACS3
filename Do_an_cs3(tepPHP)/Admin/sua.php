<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Cập nhật báo</title>

   <!-- font awesome cdn link  -->
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">

   <!-- custom css file link  -->
   <link rel="stylesheet" href="../css/style.css">
      <link rel="icon" href="https://cdn-icons.flaticon.com/png/512/3411/premium/3411447.png?token=exp=1655746654~hmac=a6a84ab5985010f032dc53a0f937687d">

      <style>
   .form-container form{
      max-width: 90%;
   }
   .form-container form h2{
      text-align: left;
      margin-left: 10px;
   }
</style>
</head>
<body>
   
<header class="header">

   <section class="flex">

      <nav class="navbar">
         <a href="sanpham.php">Quản lý món</a>
      </nav>

   </section>

</header>
<?php
// Kết nối Database
$connect= mysqli_connect("localhost", "root", "","quancoffe");
$sothutu=$_GET["id"];
$sql = "select * from menu where id='$sothutu'";
$result = mysqli_query($connect, $sql);
//in
while($row = mysqli_fetch_assoc($result)) {
   $sothutu =$row["id"];
   $name=$row["tenmon"];
   $price=$row["price"];
   $doc=$row["doc"];
}
?>
<section class="form-container">

   <form action="xulisua.php" method="POST">
      <h3>Cập nhật món</h3><br>
      <h2>Số thứ tự</h2>
      <input type="text" required maxlength="100" name="sothutu" class="box" value="<?php echo $sothutu ?>">
      <h2>Tên món</h2>
      <input type="text" required maxlength="100" name="tieu_de" class="box" value="<?php echo $name ?>">
      <h2>Giá</h2>
      <input type="text" required maxlength="100" name="price" class="box" value="<?php echo $price ?>">
      <h2>Thông tin món</h2>
      <textarea name="motasp" id="motasp" cols="30" rows="10"><?php if(!empty($doc)){echo $doc;}else echo $doc=""; ?></textarea>
      <input type="submit" value="Cập nhật" class="btn" name="update_product">
   </form>
</section>
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>

<script src="../js/script.js"></script>
<script src="../js/script.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.2/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.20.1/full/ckeditor.js"></script>
      <script>
            CKEDITOR.replace('motasp');
      </script>
</body>
</html>