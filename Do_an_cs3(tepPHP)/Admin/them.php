<?php
require '../connect.php';
session_start();
?>

<!DOCTYPE html>
<html lang="en">

   <head>
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>Thêm bài báo</title>
      
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

      
      <section class="form-container" >

      <?php if(isset($_POST['submit']))
      {
         $name=$_POST['tenmon'];
         $price=$_POST['price'];
         $doc=$_POST['motasp'];
         $sql="INSERT INTO menu (tenmon,price, doc) VALUES ('$name','$price','$doc')";
         $result = mysqli_query($connect, $sql);

         if($result)
            {
               echo '<script language="javascript">alert("Thêm món mới thành công!!!"); window.location="sanpham.php";</script>';
            
            }else{
               echo "Lỗi {$sql}".$connect->error;
            }


      }

         ?>

         
         <form action="" method="post" enctype="multipart/form-data">
            <h3>Thêm món</h3>
            <input type="text" required maxlength="50" name="tenmon" placeholder="Nhập tên của món" class="box">
            <h3>Giá</h3>
            <input type="text" required maxlength="50" name="price" placeholder="Nhập giá của món" class="box">
            <h2>Thông tin món</h2>
            <textarea name="motasp" id="motasp" cols="30" rows="50"><?php if(!empty($doc)){echo $doc;}else echo $doc=""; ?></textarea>
            <input type="submit" value="Thêm" class="btn" name="submit">
         </form>

      </section>

<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>

<script src="../js/script.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.2/jquery.min.js"></script>
<script src="//cdn.ckeditor.com/4.20.1/full/ckeditor.js"></script>
      <script>
            CKEDITOR.replace('motasp');
      </script>

   </body>

</html>