<?php
   $connect = mysqli_connect("localhost", "root", "", "quancoffe");
   mysqli_query($connect, "SET NAMES 'utf8'");

   $query = "SELECT * FROM menu";

   $data = mysqli_query($connect, $query);

   $arrayCourse = array();

   while ($row = mysqli_fetch_assoc($data)){
      $course = array(
         'id' => $row['id'],
         'tenmon' => $row['tenmon'],
         'price' => $row['price'],
      );
      array_push($arrayCourse, $course);
   }

   echo json_encode($arrayCourse);
?>
