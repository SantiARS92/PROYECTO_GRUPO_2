<?php

require_once("lib/GestorBD.php");
class Modelo
{
    


    public function getCiudadesModelo()
    {
      $gbd = new GestorBD();
        if ($gbd->conectar()) {
            return $gbd->getCiudades();
        }
        else{
            return array();
        }
    }

    public function getEnfermedadModelo()
    {
      $gbd = new GestorBD();
        if ($gbd->conectar()) {
            return $gbd->getEnfermedad();
        }
        else{
            return array();
        }
    }

    public function getEsAmigoModelo()
    {
      $gbd = new GestorBD();
        if ($gbd->conectar()) {
            return $gbd->getEsAmigo();
        }
        else{
            return array();
        }
    }

    public function getHospitalModelo()
    {
      $gbd = new GestorBD();
        if ($gbd->conectar()) {
            return $gbd->getHospital();
        }
        else{
            return array();
        }
    }

    public function getMensajeModelo()
    {
      $gbd = new GestorBD();
        if ($gbd->conectar()) {
            return $gbd->getMensaje();
        }
        else{
            return array();
        }
    }

    public function getUsuariosModelo()
    {
      $gbd = new GestorBD();
        if ($gbd->conectar()) {
            return $gbd->getUsuarios();
        }
        else{
            return array();
        }
    }

    public function getUsuarioEnfermedadModelo()
    {
      $gbd = new GestorBD();
        if ($gbd->conectar()) {
            return $gbd->getUsuarioEnfermedad();
        }
        else{
            return array();
        }
    }

    public function loginModelo($email, $pw)
    {
      $gbd = new GestorBD();
        if ($gbd->conectar()) {
            return $gbd->login($email, $pw);
        }
        else{
            return null;
        }
    }

    public function registrarModelo($nombre, $apellidos, $ciudad, $hospital, $enfermedad, $descripcion, $email, $telefono, $pw)
    {
      $gbd = new GestorBD();
        if ($gbd->conectar()) {
            return $gbd->registrar($nombre, $apellidos, $ciudad, $hospital, $enfermedad, $descripcion, $email, $telefono, $pw);
        }
        else{
            return ["message" => "Error de conexión a la base de datos"];
        }

}

    public function buscarUsuariosModelo($ciudad, $enfermedad, $id_usuario)
    {
       $gbd = new GestorBD();
        if ($gbd->conectar()) {
            return $gbd->buscarUsuarios($ciudad, $enfermedad, $id_usuario);
        }
        else{
            return null;
        }

    }

}


?>