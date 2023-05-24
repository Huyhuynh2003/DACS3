<?php
   $connect = mysqli_connect("localhost", "root", "", "quancoffe");
   mysqli_query($connect, "SET NAMES 'utf8'");

   $query = "SELECT name FROM infnvien";

   $data = mysqli_query($connect, $query);

   $arrayCourse = array();

   while ($row = mysqli_fetch_assoc($data)){
      $course = array(
         'comment' => $row['name'],
      );
      array_push($arrayCourse, $course);
   }

   echo json_encode($arrayCourse);
?>
