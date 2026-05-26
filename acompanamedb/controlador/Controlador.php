<?php
header('Content-Type: application/json; charset=utf-8');
ini_set('display_errors', 0);
error_reporting(0);

require_once("modelo/Modelo.php");
class Controlador
{
    private $accion;
    

    public function main()
    {

        $accion="";
        if (isset($_GET["accion"]) and ($_GET["accion"]!="")) {
            $accion=$_GET["accion"];
            if (method_exists($this,$accion))
                {
                    $this->$accion();
                }
                else
                {
                    echo "No se ha encontrado el servicio especificado";
                }
        }
        else
        {
            echo "No se ha encontrado el servicio especificado";

        }

    
        } 


        public function ciudad()
        {
       
            $modelo=new Modelo();
            $data=$modelo->getCiudadesModelo();
            $salida = array("data"=>$data);
            echo json_encode($salida);
            
        }

        public function enfermedad()
        {
        
            $modelo=new Modelo();
            $data=$modelo->getEnfermedadModelo();
            $salida = array("data"=>$data);
            echo json_encode($salida);
        }

        public function esamigo()
        {
            $modelo=new Modelo();
            $data=$modelo->getEsAmigoModelo();
            $salida = array("data"=>$data);
            echo json_encode($salida);
        }

        public function hospital()
        {
            $modelo=new Modelo();
            $data=$modelo->getHospitalModelo();
            $salida = array("data"=>$data);
            echo json_encode($salida);
        }

        public function mensaje()
        {
            $modelo=new Modelo();
            $data=$modelo->getMensajeModelo();
            $salida = array("data"=>$data);
            echo json_encode($salida);
        }

        public function usuarios()
        {

            $modelo=new Modelo();
            $data=$modelo->getUsuariosModelo();
            $salida = array("data"=>$data);
            echo json_encode($salida);
        }

        public function usuarioenfermedad()
        {
            $modelo=new Modelo();
            $data=$modelo->getUsuariosEnfermedadModelo();
            $salida = array("data"=>$data);
            echo json_encode($salida);
        }


        // Función para manejar el login de manera segura
        public function login()
        {
            if($_SERVER["REQUEST_METHOD"]=="POST"){
                $email = $_POST["email"];
                $password = $_POST["password"];
                $modelo=new Modelo();
                $data=$modelo->loginModelo($email, $password);
                $salida = array("data"=>$data);
                echo json_encode($salida);
                if ($email == "" || $password == "") {
                    echo "Por favor, complete todos los campos.";
                }
            }
            else{
                echo "Credenciales incorrectas";
            }
        }

        public function registrar() {

            if($_SERVER["REQUEST_METHOD"]=="POST"){
                $nombre = $_POST["nombre"];
                $apellidos = $_POST["apellidos"];
                $ciudad = $_POST["ciudad"];
                $hospital = $_POST["hospital"];
                $enfermedad = $_POST["enfermedad"];
                $descripcion = $_POST["descripcion"];
                $email = $_POST["email"];
                $telefono = $_POST["telefono"];
                $password = $_POST["password"];

                if ($nombre == "" || $apellidos == "" || $ciudad == "" || $hospital == "" || $enfermedad == "" || $descripcion == "" || $email == "" || $telefono == "" || $password == "") {
                    echo "Por favor, complete todos los campos.";
                    return;
                }

                $modelo=new Modelo();
                $data=$modelo->registrarModelo($nombre, $apellidos, $ciudad, $hospital, $enfermedad, $descripcion, $email, $telefono, $password);
                echo json_encode($data);
            }
            else{
                echo "Error en el registro. Por favor, intente nuevamente.";
            }
        }

           }
    ?>
    