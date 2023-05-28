package com.example.pago.nomina;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
class PagoNominaQuincenaController {

    @GetMapping("/calcularFechaPago")
    public Empleado calcularFechaPago(@RequestParam("nombreEmpleado") String nombreEmpleado, @RequestParam("fechaIngresada") String fechaIngresada) {
        LocalDate fecha = LocalDate.parse(fechaIngresada);
        LocalDate fechaPagoQuincena = calculoFechaPago(fecha);
        return new Empleado(nombreEmpleado, fechaPagoQuincena);
    }

    private LocalDate calculoFechaPago(LocalDate fecha) {
        LocalDate fechaPago;

        if (fecha.getDayOfMonth() <= 15) {
            fechaPago = fecha.withDayOfMonth(15);
        } else {
            fechaPago = fecha.withDayOfMonth(fecha.lengthOfMonth());
        }

        if (fechaPago.getDayOfWeek() == DayOfWeek.SATURDAY) {
            fechaPago = fechaPago.minus(1, ChronoUnit.DAYS);
        } else if (fechaPago.getDayOfWeek() == DayOfWeek.SUNDAY) {
            fechaPago = fechaPago.minus(2, ChronoUnit.DAYS);
        }

        return fechaPago;
    }
}

class Empleado {
    private String nombreEmpleado;
    private LocalDate fechaPagoQuincena;

    public Empleado(String nombreEmpleado, LocalDate fechaPagoQuincena) {
        this.nombreEmpleado = nombreEmpleado;
        this.fechaPagoQuincena = fechaPagoQuincena;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public LocalDate getFechaPagoQuincena() {
        return fechaPagoQuincena;
    }

    public void setFechaPagoQuincena(LocalDate fechaPagoQuincena) {
        this.fechaPagoQuincena = fechaPagoQuincena;
    }
}







