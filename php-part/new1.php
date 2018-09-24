<?php
 
require dirname( __FILE__ ) . '/internship/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['name']) && isset($_POST['phone']) && isset($_POST['pincode']) && isset($_POST['address1']) && isset($_POST['address2']) && isset($_POST['landmark']) && isset($_POST['city']) && isset($_POST['state'])) 
	{
 
    // receiving the post params
    $name = $_POST['name'];
    $phone = $_POST['phone'];
    $pincode = $_POST['pincode'];
	$address1 = $_POST['address1'];
    $address2 = $_POST['address2'];
	$landmark= $_POST['landmark'];
    $city = $_POST['city'];
	$state = $_POST['state'];
	$status = 2;
	
        $user = $db->storeDelieveryDetails($name,$phone,$pincode,$address1,$address2,$landmark,$city,$state,2);
        if ($user) {
            // user stored successfully
            $response["success"]=1;
            $response["error"] = FALSE;
            $response["name"] = $user["name"];
            $response["phone"] = $user["phone"];
			$response["pincode"] = $user["pincode"];
			$response["address1"] = $user["address1"];
			$response["address2"] = $user["address2"];
			$response["landmark"] = $user["landmark"];
			$response["city"] = $user["city"];
			$response["state"] = $user["state"];
			$response["status"] = $user["status"];
			
            echo json_encode($response);
        } 
        else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred in registration!";
            echo json_encode($response);
        }
   // }
} 
else {
    $response["success"]=0;
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters (name, email or password) is missing!";
    echo json_encode($response);
}
?>