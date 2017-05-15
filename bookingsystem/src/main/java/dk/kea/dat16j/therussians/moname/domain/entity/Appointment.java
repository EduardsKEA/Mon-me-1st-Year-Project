package dk.kea.dat16j.therussians.moname.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Chris on 10-May-17.
 */
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @Column(name = "appointment_id")
    @GeneratedValue()
    private long appointmentId;

    @Column(name = "appointment_date")
    //@JsonSerialize(using = LocalDateTimeSerializer.class)
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC") JSON serialization problem
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private transient LocalDateTime dateAndTime;

    //@Column(name = "app_customer_id")
    private transient Customer customer;

    @Column(name = "comment")
    private String comment;

    //@Column(name = "appointment_treatment")
    private transient Treatment treatment;

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getDate() {
        return dateAndTime;
    }

    public void setDate(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public float getTotalPrice() {
        return treatment == null ? -1 : treatment.getPrice();
    }

    public long getDuration() {
        return treatment == null ? -1 : treatment.getDuration();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }
}
