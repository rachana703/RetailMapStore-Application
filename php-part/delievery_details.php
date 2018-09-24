<?php


	
	define('DB_USER', "id3235180_rmsanp"); // db user
	define('DB_PASSWORD', "pb300396"); // db password (mention your db password here)
	define('DB_DATABASE', "id3235180_sqlanp"); // database name
	define('DB_SERVER', "localhost"); // db server


	$con = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE) or die(mysql_error());


   if (mysqli_connect_errno($con)) {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
   }
	
   $name = $_POST['name'];
   $phone = $_POST['phone'];
   $pincode = $_POST['pincode'];
   $address1 = $_POST['address1'];
   $address2 = $_POST['address2'];
   $landmark = $_POST['landmark'];
   $city = $_POST['city'];
   $state = $_POST['state'];
   
   
   $result = mysqli_query($con,"INSERT INTO delievery VALUES ('$name','$phone','$pincode','$address1','$address2','$landmark','$city','$state')");


   if($result){
      echo "done";
   }
	
   mysqli_close($con);
?>