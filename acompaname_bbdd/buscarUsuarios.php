<?php

header('Content-Type: application/json');

$conexion = new mysqli("localhost", "root", "", "acompaname_bbdd");

if ($conexion->connect_error) {
    die(json_encode([
        "success" => false,
        "mensaje" => "Error conexión"
    ]));
}

$id_usuario = $_POST['id_usuario'];

/* 1. Obtener ciudad y enfermedad del usuario actual */
$sqlUsuario = "SELECT ciudad, enfermedad 
               FROM Usuario 
               WHERE id_usuario = ?";

$stmtUsuario = $conexion->prepare($sqlUsuario);
$stmtUsuario->bind_param("i", $id_usuario);
$stmtUsuario->execute();

$resultadoUsuario = $stmtUsuario->get_result();

if ($resultadoUsuario->num_rows == 0) {

    echo json_encode([
        "success" => false,
        "mensaje" => "Usuario no encontrado"
    ]);

    exit();
}

$datosUsuario = $resultadoUsuario->fetch_assoc();

$ciudad = $datosUsuario['ciudad'];
$enfermedad = $datosUsuario['enfermedad'];

/* 2. Buscar usuarios compatibles */
$sql = "SELECT 
            id_usuario,
            nombre,
            apellidos,
            ciudad,
            enfermedad,
            telefono,
            email
        FROM Usuario
        WHERE ciudad = ?
        AND enfermedad = ?
        AND id_usuario != ?";

$stmt = $conexion->prepare($sql);

$stmt->bind_param("ssi",
                   $ciudad,
                   $enfermedad,
                   $id_usuario);

$stmt->execute();

$resultado = $stmt->get_result();

$usuarios = array();

while($fila = $resultado->fetch_assoc()) {

    $usuarios[] = [
        "id_usuario" => $fila["id_usuario"],
        "nombre" => $fila["nombre"],
        "apellidos" => $fila["apellidos"],
        "ciudad" => $fila["ciudad"],
        "enfermedad" => $fila["enfermedad"],
        "telefono" => $fila["telefono"],
        "email" => $fila["email"]
    ];
}

/* 3. Respuesta final */
echo json_encode([
    "success" => true,
    "usuarios" => $usuarios
]);

?>