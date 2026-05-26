package com.example.proyecto_grupo_2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;

@RunWith(RobolectricTestRunner.class)
public class ExampleUnitTest {

    @Test
    public void test1_ParametrosOK() {  //LOS PARAMETROS SE GUARDAN CORRECTAMENTE EN EL MAP

        Response.Listener<String> listener = response -> {};

        RegisterRequest request = new RegisterRequest(
                "Francisco", "Marín", "Madrid", "Gregorio Marañón",
                "Fibrosis quística", null, "fran22@mail.es", null, "1234", listener
        );

        Map<String, String> params = request.getParams();

        assertEquals("Francisco", params.get("nombre"));
    }

    @Test
    public void test2_noNull() {    //VERIFICA QUE NO SEA NULL

        Response.Listener<String> listener = response -> {};

        RegisterRequest request = new RegisterRequest(
                "Francisco", "Marín", "Madrid", "Gregorio Marañón",
                "Fibrosis quística", null, "fran22@mail.es", null, "1234", listener
        );

        assertNotNull(request.getParams());
    }

    @Test
    public void test3_datosOK() {   //COMPRUEBA QUE LOS DATOS ENVIADOS ESTEN OK

        Response.Listener<String> listener = response -> {};

        RegisterRequest request = new RegisterRequest(
                "Francisco", "Marín", "Madrid", "Gregorio Marañón",
                "Fibrosis quística", null, "fran22@mail.es", null, "1234", listener
        );

        Map<String, String> params = request.getParams();

        assertEquals("Francisco", params.get("nombre"));
        assertEquals("Marín", params.get("apellidos"));
        assertEquals("Madrid", params.get("ciudad"));
        assertEquals("Gregorio Marañón", params.get("hospital"));
        assertEquals("Fibrosis quística", params.get("enfermedad"));
        assertEquals("fran22@mail.es", params.get("email"));
        assertEquals("1234", params.get("pw"));
    }

    @Test
    public void test4_POST() {  //LA PETICION HTTP ES CORRECTA

        Response.Listener<String> listener = response -> {};

        RegisterRequest request = new RegisterRequest(
                "Francisco", "Marín", "Madrid", "Gregorio Marañón",
                "Fibrosis quística", null, "fran22@mail.es", null, "1234", listener
        );

        assertEquals(Request.Method.POST, request.getMethod());
    }

    @Test
    public void test5_urlOK() { //LA URL DEL SERVIDOR ES LA CORRECTA (XAMPP, LOCAL)

        Response.Listener<String> listener = response -> {};

        RegisterRequest request = new RegisterRequest(
                "Francisco", "Marín", "Madrid", "Gregorio Marañón",
                "Fibrosis quística", null, "fran22@mail.es", null, "1234", listener
        );

        String expectedUrl = "http://192.168.1.39/Register.php";

        assertEquals(expectedUrl, request.getUrl());
    }

}