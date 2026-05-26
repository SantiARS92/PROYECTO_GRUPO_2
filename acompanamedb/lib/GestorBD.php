<?php

class GestorBD
{


private $conn;

   

    public function conectar()
    {
        $this->conn = new mysqli("localhost", "root", "", "acompanamedb");
        $this->conn->set_charset("utf8mb4");
        if ($this->conn->connect_error!=null) {
            return false;
        }
        else{
            return true;
        }
        
    }


    public function getCiudades()
    {
        $sql = "SELECT * FROM ciudad";
        $data=array();
        $result = $this->conn->query($sql);
        $i=0;

        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $data[$i]= $row;
                $i++;
            }
        }
        return $data;
    }

    public function getEnfermedad()
    {
        $sql = "SELECT * FROM enfermedad";
        $data=array();
        $result = $this->conn->query($sql);
        $i=0;

        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $data[$i]= $row;
                $i++;
            }
        }
        return $data;
    }

    public function getEsAmigo()
    {
        $sql = "SELECT * FROM esamigo";
        $data=array();
        $result = $this->conn->query($sql);
        $i=0;

        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $data[$i]= $row;
                $i++;
            }
        }
        return $data;
    }

    public function getHospital()
    {
        $sql = "SELECT * FROM hospital";
        $data=array();
        $result = $this->conn->query($sql);
        $i=0;

        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $data[$i]= $row;
                $i++;
            }
        }
        return $data;
    }

    public function getMensaje()
    {
        $sql = "SELECT * FROM mensaje";
        $data=array();
        $result = $this->conn->query($sql);
        $i=0;

        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $data[$i]= $row;
                $i++;
            }
        }
        return $data;
    }

    public function getUsuarios()
    {
        $sql = "SELECT id_usuario, nombre, apellidos, ciudad, hospitale, enfermedad, descripcion, email, telefono FROM usuario";
        $data=array();
        $result = $this->conn->query($sql);
        $i=0;

        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $data[$i]= $row;
                $i++;
            }
        }
        return $data; 
    }

    public function getUsuarioEnfermedad()
    {
        $sql = "SELECT * FROM usuarioenfermedad";
        $data=array();
        $result = $this->conn->query($sql);
        $i=0;

        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $data[$i]= $row;
                $i++;
            }
        }
        return $data;
    }

    public function login($email, $pw)
    {
        $sql = "SELECT id_usuario, nombre, apellidos, ciudad, hospital, enfermedad, descripcion, email, telefono FROM usuario WHERE email = '$email' AND password = '$pw'";
        $data=array();
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("ss", $email, $pw);
        $stmt->execute();
        $result = $stmt->get_result();
        return $result->fetch_assoc() ?: null;
    }



    public function registrar($nombre, $apellidos, $ciudad, $hospital, $enfermedad, $descripcion, $email, $telefono)
    {
        $sql = "INSERT INTO usuario (nombre, apellidos, ciudad, hospital, enfermedad, descripcion, email, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        $stmt = $this->conn->prepare($sql);
        $stmt->bind_param("ssssssss", $nombre, $apellidos, $ciudad, $hospital, $enfermedad, $descripcion, $email, $telefono);
        
        if ($stmt->execute()) {
            return ["message" => "Usuario registrado exitosamente"];
        } if ($this->conn->errno === 1062) {
            return ["message" => "El email ya está registrado"];
        } else {
            return ["message" => "Error al registrar el usuario"];
        }
    }
}

?>