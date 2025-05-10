package DataManagement;
import Exceptions.NotFoundException;
import Models.Booking;

public class BookingManager extends BaseDataManager<Booking> {
    public BookingManager() {
        super("bookings_data.ser");
    }

    public void addBooking(Booking booking) {
        items.add(booking);
        saveData();
        printOperationResult("Add Booking", booking);
    }

    public Booking getBookings(String bookingId) throws NotFoundException {
        Booking booking = items.findById(bookingId);
        printOperationResult("Get Booking", booking);
        return booking;
    }

    public void updateBooking(Booking booking) throws NotFoundException {
        Booking existing = items.findById(booking.getId());
        saveData();
        printOperationResult("Update Booking", booking);
    }

    public void deleteBooking(String bookingId) throws NotFoundException {
        Booking booking = items.findById(bookingId);
        items.remove(booking);
        saveData();
        printOperationResult("Delete Booking", booking);
    }

    public Iterable<? extends Booking> getBooking() {
        return (Iterable<? extends Booking>) items;
    }
}