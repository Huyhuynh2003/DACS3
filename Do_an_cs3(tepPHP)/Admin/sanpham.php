<?php
session_start();
require '../connect.php';
?>

<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Quản lý bài viết</title>

   <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />

   <!-- font awesome cdn link  -->
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">

   <!-- custom css file link  -->
   <link rel="stylesheet" href="../css/style1.css">
      <link rel="icon" href="https://cdn-icons.flaticon.com/png/512/3411/premium/3411447.png?token=exp=1655746654~hmac=a6a84ab5985010f032dc53a0f937687d">


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
   $sql = "SELECT * FROM menu";
   $result = mysqli_query($connect, $sql);
   ?>
   <section>
   <a href="them.php" type="button "class="btn btn-success">Thêm mới &#43;</a><br><br>
   <table>
      <div>
         <tr>
              <th>STT</th>
              <th>Tên món</th>
              <th>Giá</th>
              <th>Thông tin món </th>
              <th>Hoạt động</th>
              <th>Hoạt động</th>
            </tr>
      </div>
             <?php 
   while($row=mysqli_fetch_array($result)){
   ?>
            <tr>
              <td><?=$row['id']?></td>
              <td><?=$row['tenmon']?></td>
              <td><?=$row['price']?></td>
              <td><?=$row['doc']?></td>        
              <td><a href="sua.php?id=<?php echo $row['id']; ?>" type="button "class="button1">Sửa</a></td>
          <td>
          <a href="xoa.php?id=<?php echo $row['id']; ?>" onclick="return confirm('Hành động sẽ xóa đi món. Xóa ?');" type="button" class="button1">Xóa</a>
          <?php }?>
        </td>
            </tr>
   </table>
            </section>

<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>

<script src="../js/script.js"></script>
</body>
</html>