package com.group.foctg.holidayMaker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.foctg.holidayMaker.model.Booking;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    @Query("SELECT c.bookings FROM Customer c WHERE c.id = ?1")
    List<Booking> findBookingsByCustomerID(Long ID);

}