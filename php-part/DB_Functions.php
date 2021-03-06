<?php

class DB_Functions {
 
    private $conn;
 
    // constructor
    function __construct() {
        require_once __DIR__ .'/db_connect.php';
        // connecting to database
        $db = new DB_CONNECT();
        $this->conn = $db->connect();
    }
 
    // destructor
    function __destruct() {
         
    }
 
    /**
     * Storing new user
     * returns user details
     */
    public function storeDelieveryDetails($name,$phone,$pincode,$address1,$address2,$landmark,$city,$state,$status) {
 
        $stmt = $this->conn->prepare("INSERT INTO delievery(name,phone,pincode,address1,address2,landmark,city,state,status) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
        $stmt->bind_param("sssssssss",$name,$phone,$pincode,$address1,$address2,$landmark,$city,$state,$status);
        $result = $stmt->execute();
        $stmt->close();
 
        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM delievery WHERE phone = ?");
            $stmt->bind_param("s", $phone);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
 
            return $user;
        } else {
            return false;
        }
    }
	
	
	
 
   
 
  
 
   
  
 
}
 
?>